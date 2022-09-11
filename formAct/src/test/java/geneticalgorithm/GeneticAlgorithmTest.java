package geneticalgorithm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.uma.jmetal.example.AlgorithmRunner;
import org.uma.jmetal.operator.crossover.CrossoverOperator;
import org.uma.jmetal.operator.mutation.MutationOperator;
import org.uma.jmetal.operator.mutation.impl.SimpleRandomMutation;
import org.uma.jmetal.operator.selection.impl.BinaryTournamentSelection;
import org.uma.jmetal.problem.Problem;
import org.uma.jmetal.solution.doublesolution.DoubleSolution;
import org.uma.jmetal.util.evaluator.SolutionListEvaluator;
import org.uma.jmetal.util.evaluator.impl.SequentialSolutionListEvaluator;

import model.dao.IscrizioneDao;
import model.dao.PianoFormativoDao;
import model.dao.PreferenzaStudenteDao;
import model.entity.IscrizioneEntity;
import model.entity.PreferenzaStudenteEntity;
import pianoformativo.geneticalgorithm.GeneticAlgorithm;
import pianoformativo.geneticalgorithm.PianoFormativoProblem;
import pianoformativo.geneticalgorithm.Soluzione;
import pianoformativo.geneticalgorithm.Stato;
import pianoformativo.geneticalgorithm.UniformCrossover;
import pianoformativo.service.PianoFormativoService;

public class GeneticAlgorithmTest {
	
	private PreferenzaStudenteDao psDao;
	private IscrizioneDao iDao;
	private PianoFormativoDao pianofDao;
	
	private ArrayList<PreferenzaStudenteEntity> giorniLiberi;
	private ArrayList<IscrizioneEntity> iscrizioni;
	private ArrayList<Stato> spazioStati;
	private ArrayList<String> interessi;
	
	private Map<Integer, String> categorie;
	
	private int idStudente;
	private int sizeIndividui;
	private int maxEvaluation;
	private int populationSize;
	
	private long maxComputingTime;
	
	private BinaryTournamentSelection<DoubleSolution> selection;
	private CrossoverOperator<DoubleSolution> crossover;
	private MutationOperator<DoubleSolution> mutation;
	private SolutionListEvaluator<DoubleSolution> evaluator;
	
	private Problem<DoubleSolution> problem;
	
	private GeneticAlgorithm<DoubleSolution> ga;
	
	void setupNaming() {
		
		System.setProperty("java.naming.factory.initial","testUtility.MyContextFactory");
		
	}
	
	@Before 
	public void setUp() throws SQLException, ParseException {
		
		setupNaming();
		
		psDao = new PreferenzaStudenteDao();
		iDao = new IscrizioneDao();
		pianofDao = new PianoFormativoDao();
		
		idStudente = 1;
		
		giorniLiberi = psDao.doRetrieveByStudent(idStudente);
		iscrizioni = iDao.doRetrieveByStudent(idStudente);
		spazioStati = pianofDao.doRetrieveSpazioStati(giorniLiberi, iscrizioni);
		interessi = pianofDao.doRetrieveInteressiStudente(idStudente);
		
		categorie = pianofDao.doRetrieveCategorie();
		
		sizeIndividui = 10;
		maxEvaluation = (10 + ((10 * interessi.size()) * 3)) * 10 * 100;
		populationSize = ( (spazioStati.size()/3) % 2 == 0 ) ? spazioStati.size()/3 : (spazioStati.size()/3)+1;
		
		maxComputingTime = 5000;
		
		selection = new BinaryTournamentSelection<>();
		crossover = new UniformCrossover<>(0.9);
		mutation = new SimpleRandomMutation(0.15);
		evaluator = new SequentialSolutionListEvaluator<>();
		
		problem = new PianoFormativoProblem<>(spazioStati, sizeIndividui, giorniLiberi, interessi, categorie);
		
	}
	
	@Test
	public void testCostructor() {
		
		ga = new GeneticAlgorithm<>
				(problem, maxEvaluation, populationSize, crossover, mutation, selection, evaluator, maxComputingTime);
		
		assertEquals(0, ga.getNumberGeneration());
		assertEquals(null, ga.getBestIndividual());
		assertEquals(System.currentTimeMillis(), ga.getInitComputingTime(), 1000);
		assertEquals(maxComputingTime, ga.getMaxComputingTime());
		
	}
	
	@Test 
	public void testReplacement1() {
		
		if (spazioStati.size() < 10) {
			int i = 0;
			while(spazioStati.size() <= 10) {
				spazioStati.add(spazioStati.get(i));
				i++;
			}
		}
		
		PianoFormativoService service = new PianoFormativoService();
		Soluzione soluzione = null;
		assertNull(soluzione);
		soluzione = service.ottieniSoluzione(spazioStati, giorniLiberi, interessi, categorie);
		assertNotNull(soluzione);
	}
	
	@Test 
	public void testReplacement2() {
		
		ArrayList<Stato> spazioStati2 = new ArrayList<>();
		if (spazioStati.size() > 8) {
			for (int i = 0; i < 8; i++) {
				spazioStati2.add(spazioStati.get(i));
			}
			spazioStati = spazioStati2;
		}
		
		PianoFormativoService service = new PianoFormativoService();
		Soluzione soluzione = null;
		assertNull(soluzione);
		soluzione = service.ottieniSoluzione(spazioStati, giorniLiberi, interessi, categorie);
		assertNotNull(soluzione);
		
	}
	
	@Test 
	public void testGetInitComputingTime() {
		ga = new GeneticAlgorithm<>
				(problem, maxEvaluation, populationSize, crossover, mutation, selection, evaluator, maxComputingTime);
		
		assertEquals(System.currentTimeMillis(), ga.getInitComputingTime(), 100);
	}
	
	@Test 
	public void testGetCurrentComputingTime() {
		ga = new GeneticAlgorithm<>
				(problem, maxEvaluation, populationSize, crossover, mutation, selection, evaluator, maxComputingTime);
		
		assertEquals(ga.getInitComputingTime(), ga.getCurrentComputingTime());
	}
	
	@Test 
	public void testGetSetMaxComputingTime() {
		ga = new GeneticAlgorithm<>
				(problem, maxEvaluation, populationSize, crossover, mutation, selection, evaluator, maxComputingTime);
		
		ga.setMaxComputingTime(43210);
		assertEquals(43210, ga.getMaxComputingTime());
	}
	
	@Test 
	public void testGetSetBestIndividual() {
		ga = new GeneticAlgorithm<>
				(problem, maxEvaluation, populationSize, crossover, mutation, selection, evaluator, maxComputingTime);
		
		AlgorithmRunner runner = new AlgorithmRunner.Executor(ga).execute();
		
		DoubleSolution oldSolution = ga.getBestIndividual();
		DoubleSolution newSolution = (DoubleSolution) ga.getBestIndividual().copy();
		assertEquals(oldSolution.variables(), ga.getBestIndividual().variables());
		assertEquals(newSolution.variables(), ga.getBestIndividual().variables());
		newSolution.variables().set(0, ga.getPopulation().size() + 20.0D);
		assertNotEquals(newSolution.variables(), ga.getBestIndividual().variables());
		ga.setBestIndividual(newSolution);
		assertEquals(newSolution.variables(), ga.getBestIndividual().variables());
	}
	
	@Test 
	public void testGetNumberGeneration() {
		ga = new GeneticAlgorithm<>
				(problem, maxEvaluation, populationSize, crossover, mutation, selection, evaluator, maxComputingTime);
		
		assertEquals(0, ga.getNumberGeneration());
	}
	
}









