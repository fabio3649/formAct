package pianoformativo.geneticalgorithm;

import java.util.ArrayList;
import java.util.List;

import org.uma.jmetal.operator.crossover.CrossoverOperator;
import org.uma.jmetal.solution.doublesolution.DoubleSolution;
import org.uma.jmetal.util.errorchecking.Check;
import org.uma.jmetal.util.errorchecking.JMetalException;
import org.uma.jmetal.util.pseudorandom.JMetalRandom;
import org.uma.jmetal.util.pseudorandom.RandomGenerator;

/** 
 * 
 * Classe che definisce l'operazione di crossover: Random Uniform Crossover
 * 
 */
public class UniformCrossover<S extends DoubleSolution> implements CrossoverOperator<S> {
	
	private double crossoverProbability;
	private final RandomGenerator<Double> crossoverRandomGenerator;
	private JMetalRandom jmetalRandomGenerator;
	
	
	/**
	 * Costruttore della classe UniformCrossover.
	 * 
	 * @param  crossoverProbability la probabilità del crossover.
	 */
	public UniformCrossover (double crossoverProbability) {
		this(crossoverProbability, () -> JMetalRandom.getInstance().nextDouble());
	}
	
	/**
	 * Costruttore della classe DoubleUniformCrossover.
	 * 
	 * @param  crossoverProbability la probabilità del crossover.
	 * @param  crossoverRandomGenerator generatore pseudo-casuale di numeri double.
	 */
	public UniformCrossover (double crossoverProbability, RandomGenerator<Double> crossoverRandomGenerator) {
		if (crossoverProbability < 0) {
			throw new JMetalException("La probabilita' del crossover è negativa: " + crossoverProbability);
		}
		
		this.crossoverProbability = crossoverProbability;
		this.crossoverRandomGenerator = crossoverRandomGenerator;
		this.jmetalRandomGenerator = JMetalRandom.getInstance();
	}
	
	public UniformCrossover (double crossoverProbability, RandomGenerator<Double> crossoverRandomGenerator, JMetalRandom jmetalRandomGenerator) {
		if (crossoverProbability < 0) {
			throw new JMetalException("La probabilita' del crossover è negativa: " + crossoverProbability);
		}
		
		this.crossoverProbability = crossoverProbability;
		this.crossoverRandomGenerator = crossoverRandomGenerator;
		this.jmetalRandomGenerator = jmetalRandomGenerator;
	}

	/**
	 * Metodo che ritorna la probabilità del crossover.
	 * 
	 * @return  la probabilità del crossover.
	 */
	@Override
	public double getCrossoverProbability() {
		return crossoverProbability;
	}
	
	/**
	 * Metodo che modifica la probabilità del crossover.
	 * 
	 * @param  crossoverProbability la nuova probabilità del crossover.
	 */
	public void setCrossoverProbability(double crossoverProbability) {
		this.crossoverProbability = crossoverProbability;
	}
	
	/**
	 * Metodo che ritorna il generatore pseudo-casuale di numeri double.
	 * 
	 * @return  il generatore pseudo-casuale di numeri double.
	 */
	public RandomGenerator<Double> getCrossoverRandomGenerator() {
		return crossoverRandomGenerator;
	}
	
	public JMetalRandom getJmetalRandomGenerator() {
		return jmetalRandomGenerator;
	}
	
	
	@Override
	public List<S> execute(List<S> solutions) {
		Check.notNull(solutions);
		Check.that(solutions.size() == 2, "Ci devono essere due genitori invece di " + solutions.size());
		
		return doCrossover(crossoverProbability, solutions.get(0), solutions.get(1));
	}
	
	/**
	 * Metodo che esegue il crossover tra parent1 e parent2 se otteniamo 
	 * un numero double pseudo-casuale minore del numero double crossoverProbability.
	 * 
	 * @param  crossoverProbability la probabilità del crossover.
	 * @param  parent1 il genitore 1.
	 * @param  parent2 il genitore 2.
	 * 
	 * @return  2 figli ottenuti dal crossover tra parent1 e parent2.
	 */
	public List<S> doCrossover(double crossoverProbability, S parent1, S parent2) {
		List<S> offspring = new ArrayList<>(2);
	    offspring.add((S) parent1.copy());
	    offspring.add((S) parent2.copy());
	    
	    
	    if (crossoverRandomGenerator.getRandomValue() < crossoverProbability) {
	    	for (int variableIndex = 0; variableIndex < parent1.variables().size(); variableIndex++) {
	    		
	    		double geneParent1 = parent1.variables().get(variableIndex);
	        	double geneParent2 = parent2.variables().get(variableIndex);
	        	
	        	// ottengo un numero casuale: 0 oppure 1
	    		int randomNumber = this.jmetalRandomGenerator.nextInt(0, 1);
	    		
	    		// se randomNumber = 0:
	    		// - gene i-esimo parent1 = gene i-esimo parent1 (nessuna modifica effettuata)
	    		// - gene i-esimo parent2 = gene i-esimo parent2 (nessuna modifica effettuata)
	    		if (randomNumber == 0) {
	    			offspring.get(0).variables().set(variableIndex, geneParent1);
	    			offspring.get(1).variables().set(variableIndex, geneParent2);	
	    		}
	    		// se invece randomNumber = 1:
	    		// - gene i-esimo parent1 = gene i-esimo parent2 (modifica effettuata)
	    		// - gene i-esimo parent2 = gene i-esimo parent1 (modifica effettuata)
	    		else {
	    			offspring.get(0).variables().set(variableIndex, geneParent2);
	    			offspring.get(1).variables().set(variableIndex, geneParent1);
	    		}
	        }
	    }
	    // ritorno i 2 figli ottenuti dal crossover
	    return offspring;
	}
	
	/**
	 * Metodo che ritorna il numero di genitori (parents) richiesti per il crossover.
	 * 
	 * @return  il numero di genitori (parents) richiesti per il crossover.
	 */
	@Override
	public int getNumberOfRequiredParents() {
		return 2;
	}
	
	/**
	 * Metodo che ritorna il numero di figli (children) ottenuti dal crossover.
	 * 
	 * @return  il numero di figli (children) ottenuti dal crossover.
	 */
	@Override
	public int getNumberOfGeneratedChildren() {
		return 2;
	}
	
}









