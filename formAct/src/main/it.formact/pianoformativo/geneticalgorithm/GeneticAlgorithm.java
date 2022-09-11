package pianoformativo.geneticalgorithm;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.uma.jmetal.algorithm.impl.AbstractGeneticAlgorithm;
import org.uma.jmetal.algorithm.singleobjective.geneticalgorithm.GenerationalGeneticAlgorithm;
import org.uma.jmetal.operator.crossover.CrossoverOperator;
import org.uma.jmetal.operator.mutation.MutationOperator;
import org.uma.jmetal.operator.selection.SelectionOperator;
import org.uma.jmetal.operator.selection.impl.BinaryTournamentSelection;
import org.uma.jmetal.operator.selection.impl.RankingAndDirScoreSelection;
import org.uma.jmetal.problem.Problem;
import org.uma.jmetal.solution.Solution;
import org.uma.jmetal.solution.doublesolution.DoubleSolution;
import org.uma.jmetal.solution.integersolution.IntegerSolution;
import org.uma.jmetal.util.JMetalLogger;
import org.uma.jmetal.util.comparator.ObjectiveComparator;
import org.uma.jmetal.util.evaluator.SolutionListEvaluator;

public class GeneticAlgorithm<S extends Solution<?>> extends GenerationalGeneticAlgorithm<S> {
	private Comparator<S> comparator;
	private int maxEvaluations;
	private S bestIndividual = null;
	private long initComputingTime;
	private long currentComputingTime;
	private long maxComputingTime;
	private int numberGeneration;
	
	public GeneticAlgorithm(Problem<S> problem, 
			  int maxEvaluation, 
			  int populationSize, 
			  CrossoverOperator<S> crossoverOperator,
			  MutationOperator<S> mutationOperator, 
			  SelectionOperator<List<S>, S> selectionOperator,
			  SolutionListEvaluator<S> evaluator, 
			  long maxComputingTime) {
		
		super(problem, maxEvaluation, populationSize, crossoverOperator, mutationOperator, selectionOperator, evaluator);
		
		this.numberGeneration = 0;
		this.bestIndividual = null;
		this.initComputingTime = System.currentTimeMillis();
		this.currentComputingTime = initComputingTime;
		this.maxComputingTime = maxComputingTime;
	}
	
	@Override
	protected boolean isStoppingConditionReached() {
		// se è scaduto il tempo e/oppure abbiamo raggiunto l'ottimo allora ci fermiamo altrimenti non ci fermiamo
		if (this.isStoppingByTime() || this.isStoppingByEvaluation()) {
			return true;
		}
		return false;
	}
	
	protected boolean isStoppingByEvaluation () {
		return super.isStoppingConditionReached();
	}
	
	protected boolean isStoppingByTime () { 
		this.currentComputingTime = System.currentTimeMillis() - this.getInitComputingTime();
		
		
		return this.getCurrentComputingTime() > this.getMaxComputingTime();
	}
	
	@Override 
	protected List<S> replacement(List<S> population, List<S> offspringPopulation) {
		this.numberGeneration ++;
		
		List<S> offspringPopWithElite = super.replacement(population, offspringPopulation);
		
		// individuo migliore della generazione corrente.
		if (this.getBestIndividual() != null) {
//			System.out.println(this.getBestIndividual().objectives()[0] + " > " + offspringPopWithElite.get(0).objectives()[0]);
			if (this.getBestIndividual().objectives()[0] > offspringPopWithElite.get(0).objectives()[0]) {
				this.setBestIndividual(offspringPopWithElite.get(0));
			} 
		}
		else { 
			this.setBestIndividual(offspringPopWithElite.get(0));
		}
		
		this.saveInfoPopulation(population, offspringPopulation);
		
		return (population.size() >= 10) ? offspringPopWithElite : offspringPopulation;
		
	}
	
	private void saveInfoPopulation (List<S> population, List<S> offspringPopulation) {
		
		String infoPopulation = "Generazione numero " + this.getNumberGeneration() + ":\n";
		infoPopulation += "Prima:\n";
		String objectivesPopulation = "Generazione numero " + this.getNumberGeneration() + ":\n";
		for (int i = 0; i < population.size(); i++) {
			for (int j = 0; j < population.get(i).variables().size(); j++) {
				infoPopulation +=  ((Double) population.get(i).variables().get(j)).intValue() + " ";
			}
			infoPopulation += "\n";
			objectivesPopulation += population.get(i).objectives()[0] + " ";
		}
		infoPopulation += "Dopo:\n";
		for (int i = 0; i < population.size(); i++) {
			for (int j = 0; j < population.get(i).variables().size(); j++) {
				infoPopulation +=  ((Double) offspringPopulation.get(i).variables().get(j)).intValue() + " ";
			}
			infoPopulation += "\n";
			objectivesPopulation += population.get(i).objectives()[0] + " ";
		}
		objectivesPopulation += "\n\n";
		infoPopulation += "Individuo migliore:\n";
		for (int i = 0; i < this.getBestIndividual().variables().size(); i++) {
			infoPopulation += ((Double) this.getBestIndividual().variables().get(i)).intValue() + " ";
		}
		infoPopulation += "\n\n";
		
		String posizione = "../git/formAct/formAct/src/main/webapp/view/pianoformativo/generazioni.txt";
		
		String error = "Informazione generazione numero " + this.getNumberGeneration() + "non salvata";
		this.saveFile(posizione, infoPopulation, error);
		posizione = "../git/formAct/formAct/src/main/webapp/view/pianoformativo/punteggioGenerazioni.txt";
		
		this.saveFile(posizione, objectivesPopulation, error);
	}
	
	public void saveFile(String path, String information, String error) {
		File file = new File(path);
		FileWriter fr = null;
		BufferedWriter br = null;
		try {
		    if(!file.exists()) {
				file.createNewFile();
			}
		    
		    if (this.getNumberGeneration() < 2) {
			    file.delete();
				file.createNewFile();
            }
		    
		    file = new File(path);
		    
		    fr = new FileWriter(file, true);
		    br = new BufferedWriter(fr);
			br.write(information);

		}
		catch (IOException e) {
			System.out.println(error);
			e.printStackTrace();
			return;
		}
		finally {
			try {
				br.close();
				fr.close();
			} catch (IOException e) {
				System.out.println(error);
				e.printStackTrace();
				return;
			}
		}
	}
	
	
	
	
	/**** Metodi get ****/
	

	
	public long getInitComputingTime() {
		return this.initComputingTime;
	}
	
	public long getCurrentComputingTime() {
		return this.currentComputingTime;
	}
		
	public long getMaxComputingTime() {
		return this.maxComputingTime;
	}
	
	public S getBestIndividual() {
		return this.bestIndividual;
	}
	
	public int getNumberGeneration () {
		return this.numberGeneration;
	}
	
	
	
	/**** Metodi set ****/
	
	public void setMaxComputingTime(long maxComputingTime) {
		this.maxComputingTime = maxComputingTime;
	}
	
	public void setBestIndividual (S bestIndividual) {
		this.bestIndividual = bestIndividual;
	}
	
}









