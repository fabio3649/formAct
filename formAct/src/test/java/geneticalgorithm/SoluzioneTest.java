package geneticalgorithm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import model.entity.PercorsoFormativoEntity;
import pianoformativo.geneticalgorithm.Soluzione;
import pianoformativo.geneticalgorithm.Stato;

public class SoluzioneTest {
	
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
	
	private ArrayList<Stato> stati;
	 
	@Before
	public void setUp() {
		
		percorso0 = new PercorsoFormativoEntity();
		percorso0.setCategoria(4);
		percorso0.setCosto(15.5D);
		percorso0.setDescrizione("Corso sulla matematica");
		percorso0.setId(5);
		percorso0.setId_formatore(6);
		percorso0.setIndice_contenuti("- Algebra lineare; <br>- Analisi matematica di base.");
		percorso0.setNome("Analisi e algebra di base");
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
		percorso2.setCategoria(4);
		percorso2.setCosto(15.5D);
		percorso2.setDescrizione("Corso sulla matematica");
		percorso2.setId(5);
		percorso2.setId_formatore(6);
		percorso2.setIndice_contenuti("- Algebra lineare; <br>- Analisi matematica di base.");
		percorso2.setNome("Analisi e algebra di base");
		percorso2.setNum_lezioni(25);
		percorso2.setValidate(1);
		
		percorso3 = new PercorsoFormativoEntity();
		percorso3.setCategoria(7);
		percorso3.setCosto(60.0D);
		percorso3.setDescrizione("Tutto su python");
		percorso3.setId(8);
		percorso3.setId_formatore(9);
		percorso3.setIndice_contenuti("- POO; <br>- Python; <br>- Python avanzato");
		percorso3.setNome("Python e POO");
		percorso3.setNum_lezioni(50);
		percorso3.setValidate(1);
		
		giorno0 = "Giovedì";
		giorno1 = "Martedì";
		giorno2 = "Giovedì";
		giorno3 = "Venerdì";
		
		orario0 = LocalTime.parse("10:24");
		orario1 = LocalTime.parse("12:22");
		orario2 = LocalTime.parse("12:22");
		orario3 = LocalTime.parse("23:50");
		
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
		
	}
	
	@Test
	public void testCostructor1() {
		Soluzione soluzione = new Soluzione();
		assertNotNull(soluzione);
	}
	
	@Test
	public void testCostructor2() {
		stati = new ArrayList<>(Arrays.asList(stato0, stato1, stato2, stato3));
		Soluzione soluzione = new Soluzione(stati);
		assertNotNull(soluzione);
		assertEquals(stati, soluzione.getStati());
	}
	
	@Test
	public void testGetSetStati() {
		stati = new ArrayList<>(Arrays.asList(stato0, stato1, stato2, stato3));
		Soluzione soluzione = new Soluzione();
		soluzione.setStati(stati);
		assertEquals(stati, soluzione.getStati());
	}
	
	@Test
	public void testGetSetStato() {
		stati = new ArrayList<>(Arrays.asList(stato0, stato1, stato2, stato3));
		Soluzione soluzione = new Soluzione(stati);
		assertEquals(stato2, soluzione.getStato(2));
		soluzione.setStato(2, stato0);
		assertEquals(stato0, soluzione.getStato(2));
	}
	
	@Test
	public void testGetSetSize() {
		stati = new ArrayList<>(Arrays.asList(stato0, stato1, stato2, stato3));
		Soluzione soluzione = new Soluzione(stati);
		assertEquals(4, soluzione.getSize());
	}
	
	@Test
	public void testDeletePercorsiDuplicatiByNome() {	
		stati = new ArrayList<>(Arrays.asList(stato0, stato1, stato2, stato3));
		ArrayList<Stato> statiCopia = new ArrayList<>(Arrays.asList(stato0, stato1, stato2, stato3));
		ArrayList<Stato> stati2 = new ArrayList<>(Arrays.asList(stato0, stato1, stato3));
		
		Soluzione soluzione = new Soluzione(stati);
		
		assertEquals(stati, soluzione.getStati());
		assertEquals(statiCopia, soluzione.getStati());
//		for (Stato s : soluzione.getStati()) {
//			System.out.println(s.getPercorsoFormativo().getNome());
//		}
//		System.out.println();
		soluzione.deletePercorsiDuplicatiByNome();
//		for (Stato s : soluzione.getStati()) {
//			System.out.println(s.getPercorsoFormativo().getNome());
//		}
//		System.out.println();
		assertNotEquals(statiCopia, soluzione.getStati());
		assertEquals(stati2, soluzione.getStati());
	}
	
	@Test
	public void testSelectionSortPunteggio() {
		Soluzione soluzioneCrescente 
				= new Soluzione(new ArrayList<Stato>(Arrays.asList(stato3, stato1, stato2, stato0)));
		Soluzione soluzioneDecrescente 
				= new Soluzione(new ArrayList<Stato>(Arrays.asList(stato2, stato0, stato1, stato3))); 
		
		stati = new ArrayList<>(Arrays.asList(stato0, stato1, stato2, stato3));
		Soluzione soluzione = new Soluzione(stati);
		
		soluzione.selectionSort(soluzione.getSize(), true, "punteggio");
		assertEquals(soluzione, soluzioneCrescente);
		
		soluzione.selectionSort(soluzione.getSize(), false, "Punteggio");
		assertEquals(soluzione, soluzioneDecrescente);
	}
	
	@Test
	public void testSelectionSortGiorno() {
		Soluzione soluzioneCrescente 
				= new Soluzione(new ArrayList<Stato>(Arrays.asList(stato1, stato2, stato0, stato3)));
		Soluzione soluzioneDecrescente 
				= new Soluzione(new ArrayList<Stato>(Arrays.asList(stato3, stato2, stato0, stato1))); 

		stati = new ArrayList<>(Arrays.asList(stato0, stato1, stato2, stato3));
		Soluzione soluzione = new Soluzione(stati);
		
		soluzione.selectionSort(soluzione.getSize(), false, "giorno");
		assertEquals(soluzione, soluzioneDecrescente);
		
		soluzione.selectionSort(soluzione.getSize(), true, "Giorno");
		assertEquals(soluzione, soluzioneCrescente);
	}
	
	@Test
	public void testSelectionSortOrario() {
		Soluzione soluzioneCrescente 
				= new Soluzione(new ArrayList<Stato>(Arrays.asList(stato0, stato1, stato2, stato3)));
		Soluzione soluzioneDecrescente 
				= new Soluzione(new ArrayList<Stato>(Arrays.asList(stato3, stato1, stato2, stato0))); 
		
		stati = new ArrayList<>(Arrays.asList(stato0, stato1, stato2, stato3));
		Soluzione soluzione = new Soluzione(stati);
		
		soluzione.selectionSort(soluzione.getSize(), false, "Orario");
		assertEquals(soluzione, soluzioneDecrescente);
		
		soluzione.selectionSort(soluzione.getSize(), true, "Orario");
		assertEquals(soluzione, soluzioneCrescente);
	}
	
	@Test
	public void testSelectionSortGiornoAndOrario() {
		// giorno0 = "Giovedì";    orario0 = LocalTime.parse("10:24");	
		// giorno1 = "Martedì";    orario1 = LocalTime.parse("12:22");	
		// giorno2 = "Giovedì";    orario2 = LocalTime.parse("12:22");	
		// giorno3 = "Venerdì";    orario3 = LocalTime.parse("23:50");	
		Soluzione soluzioneCrescente 
				= new Soluzione(new ArrayList<Stato>(Arrays.asList(stato1, stato0, stato2, stato3)));
		Soluzione soluzioneDecrescente 
				= new Soluzione(new ArrayList<Stato>(Arrays.asList(stato3, stato2, stato0, stato1))); 

		stati = new ArrayList<>(Arrays.asList(stato0, stato1, stato2, stato3));
		Soluzione soluzione = new Soluzione(stati);
//		for (Stato s : soluzione.getStati()) {
//			System.out.println(s.getPercorsoFormativo().getNome() + " || " + s.getGiorno() + " " + s.getOrario());
//		}
//		System.out.println();
		soluzione.selectionSort(soluzione.getSize(), false, "giornoandorario");
		assertEquals(soluzione, soluzioneDecrescente);
//		for (Stato s : soluzione.getStati()) {
//			System.out.println(s.getPercorsoFormativo().getNome() + " || " + s.getGiorno() + " " + s.getOrario());
//		}
//		System.out.println();
		soluzione.selectionSort(soluzione.getSize(), true, "GiornoAndOrario");
		assertEquals(soluzione, soluzioneCrescente);
//		for (Stato s : soluzione.getStati()) {
//			System.out.println(s.getPercorsoFormativo().getNome() + " || " + s.getGiorno() + " " + s.getOrario());
//		}
//		System.out.println();
		
	}
	
}









