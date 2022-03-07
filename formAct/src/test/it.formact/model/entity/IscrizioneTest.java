package model.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.Date;
import java.time.LocalTime;

import org.junit.Test;

public class IscrizioneTest {
	
	
	//Constructor
	@Test
	public void testIscrizioneConstructor() {
		IscrizioneEntity nuovo = new IscrizioneEntity();
		assertNotNull(nuovo);
		
	}
	

	
	//GETTERS AND SETTERS
	
	@Test
	public void testGetSetStudente() {
		IscrizioneEntity nuovo = new IscrizioneEntity();
		nuovo.setStudente(1);
		
		assertEquals(1, nuovo.getStudente());
	}
	
	@Test
	public void testGetSetPercorso() {
		IscrizioneEntity nuovo = new IscrizioneEntity();
		nuovo.setPercorsoFormativo(1);
		
		assertEquals(1, nuovo.getPercorsoFormativo());
	}
	
	@Test
	public void testGetSetGiorno() {
		IscrizioneEntity nuovo = new IscrizioneEntity();
		nuovo.setGiorno("lunedì");
		
		assertEquals("lunedì", nuovo.getGiorno());
	}
	
	@Test
	public void testGetSetOrario() {
		IscrizioneEntity nuovo = new IscrizioneEntity();
		String orario = "12:00";
		LocalTime t = LocalTime.parse(orario);
		nuovo.setOrario(t);
		
		assertEquals(LocalTime.parse(orario),nuovo.getOrario());
	}
	
	@Test
	public void testGetSetMetodoPagamento() {
		IscrizioneEntity nuovo = new IscrizioneEntity();
		nuovo.setMetodoPagamento("visa");
		
		assertEquals("visa", nuovo.getMetodoPagamento());
	}
	
	@Test
	public void testGetSetDataPagamento() {
		IscrizioneEntity nuovo = new IscrizioneEntity();
		Date d = new Date(2022-01-12);
		nuovo.setDataPagamento(d);
		assertEquals(d, nuovo.getDataPagamento());
	}
	
	//ToString()
	@Test
	public void testToString() {
		IscrizioneEntity nuovo = new IscrizioneEntity();
		String expectedValue = "Iscrizione [studente=" + nuovo.getStudente() + ", percorsoFormativo=" + nuovo.getPercorsoFormativo() + ", giorno="
				+ nuovo.getGiorno() + ", orario=" + nuovo.getOrario() + ", metodoPagamento=" + nuovo.getMetodoPagamento() + ", dataPagamento="
				+ nuovo.getDataPagamento() + "]";
		
		String actualValue = nuovo.toString();
		
		assertEquals(expectedValue, actualValue);
	}
	
	
	
	
}
