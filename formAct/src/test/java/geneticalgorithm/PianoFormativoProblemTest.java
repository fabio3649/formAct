package geneticalgorithm;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.Before;
import org.junit.Test;
import org.uma.jmetal.solution.doublesolution.DoubleSolution;
import org.uma.jmetal.solution.doublesolution.impl.DefaultDoubleSolution;
import org.uma.jmetal.util.bounds.Bounds;
import org.uma.jmetal.util.pseudorandom.RandomGenerator;

import model.entity.PercorsoFormativoEntity;
import model.entity.PreferenzaStudenteEntity;
import pianoformativo.geneticalgorithm.PianoFormativoProblem;
import pianoformativo.geneticalgorithm.Stato;

public class PianoFormativoProblemTest {
	
	private PercorsoFormativoEntity percorso0;
	private PercorsoFormativoEntity percorso1;
	private PercorsoFormativoEntity percorso2;
	private PercorsoFormativoEntity percorso3;
	
	private String giorno0;
	private String giorno1;
	private String giorno2;
	private String giorno3;
	
	private LocalTime orario0;
	private LocalTime orario1;
	private LocalTime orario2;
	private LocalTime orario3;
	
	private int punteggio0;
	private int punteggio1;
	private int punteggio2;
	private int punteggio3;
	
	private Stato stato0;
	private Stato stato1;
	private Stato stato2;
	private Stato stato3;
	
	private ArrayList<Stato> spazioStati;
	
	private int sizeIndividui;
	
	private PreferenzaStudenteEntity preferenza0;
	private PreferenzaStudenteEntity preferenza1;
	private PreferenzaStudenteEntity preferenza2;
	
	private ArrayList<PreferenzaStudenteEntity> giorniLiberi;
	
	private ArrayList<String> interessi;
	
	private Map<Integer, String> categorie;
	
	private DoubleSolution solution0;
	
	@Before 
	public void setUp() {
		
		percorso0 = new PercorsoFormativoEntity();
		percorso0.setCategoria(0); 
		percorso0.setCosto(15.5D);
		percorso0.setDescrizione("Corso sulla matematica");
		percorso0.setId(1);
		percorso0.setId_formatore(6);
		percorso0.setIndice_contenuti("- Algebra lineare; <br>- Analisi matematica di base.");
		percorso0.setNome("Analisi matematica e algebra di base");
		percorso0.setNum_lezioni(25);
		percorso0.setValidate(1);
		
		percorso1 = new PercorsoFormativoEntity();
		percorso1.setCategoria(1);
		percorso1.setCosto(10.0D);
		percorso1.setDescrizione("Corso sulla programmazione web");
		percorso1.setId(2);
		percorso1.setId_formatore(3);
		percorso1.setIndice_contenuti("- POO; <br>- Java base.");
		percorso1.setNome("POO e java base.");
		percorso1.setNum_lezioni(20);
		percorso1.setValidate(1);
		
		percorso2 = new PercorsoFormativoEntity();
		percorso2.setCategoria(0);
		percorso2.setCosto(15.5D);
		percorso2.setDescrizione("Corso sulla matematica");
		percorso2.setId(3);
		percorso2.setId_formatore(6);
		percorso2.setIndice_contenuti("- Algebra lineare; <br>- Analisi matematica di base.");
		percorso2.setNome("Analisi e algebra di base");
		percorso2.setNum_lezioni(25);
		percorso2.setValidate(1);
		
		percorso3 = new PercorsoFormativoEntity();
		percorso3.setCategoria(2);
		percorso3.setCosto(60.0D);
		percorso3.setDescrizione("Tutto su python");
		percorso3.setId(4);
		percorso3.setId_formatore(9);
		percorso3.setIndice_contenuti("- POO; <br>- Python; <br>- Python avanzato");
		percorso3.setNome("Python e POO");
		percorso3.setNum_lezioni(50);
		percorso3.setValidate(1);
		
		giorno0 = "Giovedì";
		giorno1 = "Martedì";
		giorno2 = "Giovedì";
		giorno3 = "Venerdì";
		
		orario0 = LocalTime.parse("10:24:12");
		orario1 = LocalTime.parse("12:22:16");
		orario2 = LocalTime.parse("12:22:16");
		orario3 = LocalTime.parse("23:50:00");
		
		punteggio0 = 204;
		punteggio1 = 105;
		punteggio2 = 204;
		punteggio3 = 50;
		
		stato0 = new Stato();
		stato0.setGiorno(giorno0);
		stato0.setOrario(orario0);
		stato0.setPercorsoFormativo(percorso0);
		stato0.setPunteggio(punteggio0);
		
		stato1 = new Stato();
		stato1.setGiorno(giorno1);
		stato1.setOrario(orario1);
		stato1.setPercorsoFormativo(percorso1);
		stato1.setPunteggio(punteggio1);
		
		stato2 = new Stato();
		stato2.setGiorno(giorno2);
		stato2.setOrario(orario2);
		stato2.setPercorsoFormativo(percorso2);
		stato2.setPunteggio(punteggio2);
		
		stato3 = new Stato();
		stato3.setGiorno(giorno3);
		stato3.setOrario(orario3);
		stato3.setPercorsoFormativo(percorso3);
		stato3.setPunteggio(punteggio3);
		
		spazioStati = new ArrayList<>(Arrays.asList(stato0, stato1, stato2, stato3));
		
		sizeIndividui = 4;
		
		preferenza0 = new PreferenzaStudenteEntity();
		preferenza0.setStudente(1);
		preferenza0.setDisponibilita("lunedì");
		
		preferenza1 = new PreferenzaStudenteEntity();
		preferenza1.setStudente(1);
		preferenza1.setDisponibilita("giovedì");
		
		preferenza2 = new PreferenzaStudenteEntity();
		preferenza2.setStudente(1);
		preferenza2.setDisponibilita("mercoledì");
		
		giorniLiberi = new ArrayList<>(Arrays.asList(preferenza0, preferenza1, preferenza2));
		
		interessi = new ArrayList<>(Arrays.asList("Python (Linguaggio)", "Matematica (Matematica)", "Java (Linguaggio)", "Reti (Informatica)"));
		
		categorie = new HashMap<>();
		categorie.put(0, "Matematica");
		categorie.put(1, "Informatica");
		categorie.put(2, "Linguaggio di programmazione");
		
		Double upper = spazioStati.size() - 1.0D;
		
		List<Double> lowerBounds = new ArrayList<> (Arrays.asList(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D));
		List<Double> upperBounds = new ArrayList<> (
				Arrays.asList(upper, upper, upper, upper, upper, upper, upper, upper, upper, upper)
		); 
		
		int numberOfObjectives = 1;
		List<Bounds<Double>> boundsList = new ArrayList<Bounds<Double>>();
		boundsList = IntStream.range(0, lowerBounds.size())
				.mapToObj(i -> Bounds.create(lowerBounds.get(i), upperBounds.get(i)))
				.collect(Collectors.toList());
		
		solution0 = new DefaultDoubleSolution(numberOfObjectives, boundsList);
		
	}
	
	@Test
	public void testCostructor() {
		
		PianoFormativoProblem<DoubleSolution> problem = 
				new PianoFormativoProblem<>(spazioStati, sizeIndividui, giorniLiberi, interessi, categorie);
		
		assertEquals(spazioStati, problem.getSpazioStati());
		assertEquals(sizeIndividui, problem.getSizeIndividui());
		assertEquals(giorniLiberi, problem.getGiorniLiberi());
		assertEquals(interessi, problem.getInteressi());
		assertEquals(categorie, problem.getCategorie());
		assertEquals("Piano formativo personalizzato problem", problem.getName());
		assertEquals(spazioStati.size(), problem.getNumberOfVariables());
		
		List<Double> lowerBounds = new ArrayList<>();
		List<Double> upperBounds = new ArrayList<>();
		for (int i = 0; i < problem.getSizeIndividui(); i++) {
			lowerBounds.add(0.0D);
			upperBounds.add(spazioStati.size() + 0.0D);
			assertEquals(lowerBounds.get(i), problem.getBoundsForVariables().get(i).getLowerBound());
			assertEquals(upperBounds.get(i), problem.getBoundsForVariables().get(i).getUpperBound());
		}
		assertEquals(1, problem.getNumberOfObjectives());
				
	}
	
	@Test 
	public void testGetSetSpazioStati() {
		
		PianoFormativoProblem<DoubleSolution> problem = 
				new PianoFormativoProblem<>(spazioStati, sizeIndividui, giorniLiberi, interessi, categorie);
		
		problem.setSpazioStati(new ArrayList<Stato>(Arrays.asList(stato2, stato1, stato0)));
		assertEquals(new ArrayList<Stato>(Arrays.asList(stato2, stato1, stato0)), problem.getSpazioStati());
		
	}
	
	@Test 
	public void testGetSetSizeIndividui() {
		
		PianoFormativoProblem<DoubleSolution> problem = 
				new PianoFormativoProblem<>(spazioStati, sizeIndividui, giorniLiberi, interessi, categorie);
		
		problem.setSizeIndividui(5);
		assertEquals(5, problem.getSizeIndividui());
		
	}
	
	@Test 
	public void testGetSetGiorniLiberi() {
		
		PianoFormativoProblem<DoubleSolution> problem = 
				new PianoFormativoProblem<>(spazioStati, sizeIndividui, giorniLiberi, interessi, categorie);
		
		ArrayList<PreferenzaStudenteEntity> nuoviGiorniLiberi = 
				new ArrayList<PreferenzaStudenteEntity>
						(Arrays.asList(preferenza2, preferenza1, preferenza0));
		
		problem.setGiorniLiberi(nuoviGiorniLiberi);
		assertEquals(nuoviGiorniLiberi, problem.getGiorniLiberi());
		
	}
	
	@Test 
	public void testGetSetInteressi() {
		
		PianoFormativoProblem<DoubleSolution> problem = 
				new PianoFormativoProblem<>(spazioStati, sizeIndividui, giorniLiberi, interessi, categorie);
		
		ArrayList<String> nuoviInteressi = new ArrayList<>(Arrays.asList("Reti", "Java", "Matematica", "Python"));
		
		problem.setInteressi(nuoviInteressi);
		assertEquals(nuoviInteressi, problem.getInteressi());
		
	}
	
	@Test 
	public void testGetSetCategorie() {
		
		PianoFormativoProblem<DoubleSolution> problem = 
				new PianoFormativoProblem<>(spazioStati, sizeIndividui, giorniLiberi, interessi, categorie);
		
		Map<Integer, String> nuoveCategorie = new HashMap<>();
		nuoveCategorie.put(2, "Linguaggio di programmazione");
		nuoveCategorie.put(1, "Informatica");
		nuoveCategorie.put(0, "Matematica");
		
		problem.setCategorie(nuoveCategorie);
		assertEquals(nuoveCategorie, problem.getCategorie());
		
	}
	
	@Test
	public void testEvaluate() {
		
		PianoFormativoProblem<DoubleSolution> problem = 
				new PianoFormativoProblem<>(spazioStati, sizeIndividui, giorniLiberi, interessi, categorie);
		
		ArrayList<Double> codifica = (ArrayList<Double>) solution0.variables();
		ArrayList<String> nomiAccettati = new ArrayList<>();
		Map<String, HashSet<LocalTime>> giorniConOrari = new HashMap<>();
		
		solution0.variables().clear();
		solution0.variables().add(0.0D);
		solution0.variables().add(1.0D);
		
		// 0 + 10 + (valuto il nome percorso formativo con gli interessi) 
		// + (valuto l'indice contenuti con gli interessi) + (valuto la categoria con gli interessi) 
		// 0 + 10 + (5 + 10 + 5 + 5) + (5 + 10 + 5 + 5) + (5 + 10 + 5 + 5) = 10 + 25 + 25 + 25 = 85
		int punteggioGene0 = 0 + 10 + (5 + 10 + 5 + 5) + (5 + 10 + 5 + 5) + (5 + 10 + 5 + 5); // 85
		
		// 0 + 10 + (valuto il nome percorso formativo con gli interessi) 
		// + (valuto l'indice contenuti con gli interessi) + (valuto la categoria con gli interessi) 
		// 0 + 10 + (5 + 5 + 10 + 5) + (5 + 5 + 10 + 5) + (5 + 5 + 5 + 10) = 10 + 25 + 25 + 25 = 85
		int punteggioGene1 = 0 + 10 + (5 + 5 + 10 + 5) + (5 + 5 + 10 + 5) + (5 + 5 + 5 + 10); // 85
		
		// 85 + 85 = 170
		int punteggio = punteggioGene0 + punteggioGene1; // - 170
		
		DoubleSolution solutionEvaluate = problem.evaluate(solution0);
		
		assertEquals(-punteggio, solutionEvaluate.objectives()[0], 0.0D);
		
	}
	
}









