package geneticalgorithm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.time.LocalTime;

import org.junit.Before;
import org.junit.Test;
import org.uma.jmetal.util.errorchecking.JMetalException;

import model.entity.PercorsoFormativoEntity;
import pianoformativo.geneticalgorithm.Stato;

public class StatoTest {
	
	private PercorsoFormativoEntity percorso0;
	private PercorsoFormativoEntity percorso1;
	private PercorsoFormativoEntity percorso2;
	
	private String giorno0;
	private String giorno1;
	private String giorno2;
	
	private LocalTime orario0;
	private LocalTime orario1;
	private LocalTime orario2;
	
	private int punteggio0;
	private int punteggio1;
	private int punteggio2;

	
	@Before
	public void setUp() {
		
		percorso0 = new PercorsoFormativoEntity();
		percorso0.setCategoria(1);
		percorso0.setCosto(10.0D);
		percorso0.setDescrizione("Corso sulla programmazione web");
		percorso0.setId(2);
		percorso0.setId_formatore(3);
		percorso0.setIndice_contenuti("- POO; <br>- Java base.");
		percorso0.setNome("POO e java base.");
		percorso0.setNum_lezioni(20);
		percorso0.setValidate(1);
		
		percorso1 = new PercorsoFormativoEntity();
		percorso1.setCategoria(4);
		percorso1.setCosto(15.5D);
		percorso1.setDescrizione("Corso sulla matematica");
		percorso1.setId(5);
		percorso1.setId_formatore(6);
		percorso1.setIndice_contenuti("- Algebra lineare; <br>- Analisi matematica di base.");
		percorso1.setNome("Analisi e algebra di base");
		percorso1.setNum_lezioni(25);
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
		
		giorno0 = "Giovedì";
		giorno1 = "Martedì";
		giorno2 = "Giovedì";
		
		orario0 = LocalTime.parse("10:24:12");
		orario1 = LocalTime.parse("12:22:16");
		orario2 = LocalTime.parse("12:22:16");
		
		punteggio0 = 204;
		punteggio1 = 105;
		punteggio2 = 204;
		
	}
	
	@Test
	public void testCostructor() {
		Stato stato = new Stato();
		assertNotNull(stato);
	}
	
	@Test
	public void testGetSetPercorsoFormativo() {
		Stato stato = new Stato();
		stato.setPercorsoFormativo(percorso1);
		assertEquals(percorso1, stato.getPercorsoFormativo());
	}
	
	@Test
	public void testGetSetGiorno() {
		Stato stato = new Stato();
		stato.setGiorno(giorno1);
		assertEquals(giorno1, stato.getGiorno());
	}
	
	@Test
	public void testGetSetOrario() {
		Stato stato = new Stato();
		stato.setOrario(orario1);
		assertEquals(orario1, stato.getOrario());
	}
	
	@Test
	public void testGetSetPunteggio() {
		Stato stato = new Stato();
		stato.setPunteggio(punteggio1);
		assertEquals(punteggio1, stato.getPunteggio());
	}
	
	@Test
	public void testToString() {
		String toString = "Stato = [percorsoFormativo = " + percorso1
		+ ", giorno = " + giorno1 + ", orario = " + orario1 
		+ ", punteggio = " + punteggio1 + "]";

		Stato stato = new Stato();
		stato.setGiorno(giorno1);
		stato.setOrario(orario1);
		stato.setPercorsoFormativo(percorso1);
		stato.setPunteggio(punteggio1);
		
		assertEquals(toString, stato.toString());
				
	}
	
	@Test
	public void testCompareGiorno() {
		
		Stato s0 = new Stato();
		s0.setGiorno(giorno0);
		s0.setOrario(orario0);
		s0.setPercorsoFormativo(percorso0);
		s0.setPunteggio(punteggio0);
		
		Stato s1 = new Stato();
		s1.setGiorno(giorno1);
		s1.setOrario(orario1);
		s1.setPercorsoFormativo(percorso1);
		s1.setPunteggio(punteggio1);
		
		Stato s2 = new Stato();
		s2.setGiorno(giorno2);
		s2.setOrario(orario2);
		s2.setPercorsoFormativo(percorso2);
		s2.setPunteggio(punteggio2);
		
		// giorno0 = Giovedì
		// giorno1 = Martedì
		// giorno2 = Giovedì
		assertEquals(2, s0.compare(s1, "giorno"));
		assertEquals(-2, s1.compare(s0, "GIORNO"));
		assertEquals(0, s2.compare(s0, "Giorno"));
		
	}
	
	@Test  (expected = IllegalArgumentException.class)
	public void testCompareGiorno1Exception() {
		
		Stato s0 = new Stato();
		s0.setGiorno("marzo");
		s0.setOrario(orario0);
		s0.setPercorsoFormativo(percorso0);
		s0.setPunteggio(punteggio0);
		
		Stato s1 = new Stato();
		s1.setGiorno(giorno1);
		s1.setOrario(orario1);
		s1.setPercorsoFormativo(percorso1);
		s1.setPunteggio(punteggio1);
		
		s0.compare(s1, "giorno");
		
	}
	
	@Test  (expected = IllegalArgumentException.class)
	public void testCompareGiorno2Exception() {
		
		Stato s0 = new Stato();
		s0.setGiorno("marzo");
		s0.setOrario(orario0);
		s0.setPercorsoFormativo(percorso0);
		s0.setPunteggio(punteggio0);
		
		Stato s1 = new Stato();
		s1.setGiorno(giorno1);
		s1.setOrario(orario1);
		s1.setPercorsoFormativo(percorso1);
		s1.setPunteggio(punteggio1);
		
		s1.compare(s0, "GIORNO");
		
	}
	
	@Test
	public void testCompareOrario() {
		
		Stato s0 = new Stato();
		s0.setGiorno(giorno0);
		s0.setOrario(orario0);
		s0.setPercorsoFormativo(percorso0);
		s0.setPunteggio(punteggio0);
		
		Stato s1 = new Stato();
		s1.setGiorno(giorno1);
		s1.setOrario(orario1);
		s1.setPercorsoFormativo(percorso1);
		s1.setPunteggio(punteggio1);
		
		Stato s2 = new Stato();
		s2.setGiorno(giorno2);
		s2.setOrario(orario2);
		s2.setPercorsoFormativo(percorso2);
		s2.setPunteggio(punteggio2);
		
		// orario0 = 10:24:12
		// orario1 = 12:22:16
		// orario2 = 12:22:16
		assertEquals(-1, s0.compare(s1, "orario"));
		assertEquals(1, s1.compare(s0, "ORARIO"));
		assertEquals(0, s2.compare(s1, "Orario"));
		
	}
	
	@Test
	public void testCompareGiornoAndOrario() {
		
		Stato s0 = new Stato();
		s0.setGiorno(giorno0);
		s0.setOrario(orario0);
		s0.setPercorsoFormativo(percorso0);
		s0.setPunteggio(punteggio0);
		
		Stato s1 = new Stato();
		s1.setGiorno(giorno1);
		s1.setOrario(orario1);
		s1.setPercorsoFormativo(percorso1);
		s1.setPunteggio(punteggio1);
		
		Stato s2 = new Stato();
		s2.setGiorno(giorno2);
		s2.setOrario(orario2);
		s2.setPercorsoFormativo(percorso2);
		s2.setPunteggio(punteggio2);
		
		// giorno0 = Giovedì    orario0 = 10:24:12
		// giorno1 = Martedì    orario1 = 12:22:16
		// giorno2 = Giovedì    orario2 = 12:22:16
		assertEquals(1, s0.compare(s1, "giornoandorario"));
		assertEquals(-1, s1.compare(s0, "GIORNOANDORARIO"));
		assertEquals(-1, s0.compare(s2, "GiornoAndOrario"));
		
		s2.setOrario(LocalTime.parse("20:50:50"));
		
		// giorno0 = Giovedì    orario0 = 10:24:12
		// giorno2 = Giovedì    orario2 = 20:50:50
		assertEquals(1, s2.compare(s0, "GiornoAndOrario"));
		
		s2.setOrario(LocalTime.parse("10:24:12"));
		
		// giorno0 = Giovedì    orario0 = 10:24:12
		// giorno2 = Giovedì    orario2 = 10:24:12
		assertEquals(0, s0.compare(s2, "GiornoAndOrario"));
		
	}
	
	@Test
	public void testComparePunteggio() {
		
		Stato s0 = new Stato();
		s0.setGiorno(giorno0);
		s0.setOrario(orario0);
		s0.setPercorsoFormativo(percorso0);
		s0.setPunteggio(punteggio0);
		
		Stato s1 = new Stato();
		s1.setGiorno(giorno1);
		s1.setOrario(orario1);
		s1.setPercorsoFormativo(percorso1);
		s1.setPunteggio(punteggio1);
		
		Stato s2 = new Stato();
		s2.setGiorno(giorno2);
		s2.setOrario(orario2);
		s2.setPercorsoFormativo(percorso2);
		s2.setPunteggio(punteggio2);
		
		//punteggio0 = 204;
		//punteggio1 = 105;
		//punteggio2 = 204;
		assertEquals(99, s0.compare(s1, "punteggio"));
		assertEquals(-99, s1.compare(s0, "Punteggio"));
		assertEquals(0, s2.compare(s0, "Punteggio"));
		
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testCompareException() {
		
		Stato s0 = new Stato();
		s0.setGiorno(giorno0);
		s0.setOrario(orario0);
		s0.setPercorsoFormativo(percorso0);
		s0.setPunteggio(punteggio0);
		
		Stato s1 = new Stato();
		s1.setGiorno(giorno1);
		s1.setOrario(orario1);
		s1.setPercorsoFormativo(percorso1);
		s1.setPunteggio(punteggio1);
		
		s1.compare(s0, "percorsoFormativo");
		
	}
	
}









