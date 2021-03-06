package model.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class PreferenzaStudenteTest {
	
	
	//Constructor
	@Test
	public void testPreferenaStudenteConstructor() {
		PreferenzaStudenteEntity p = new PreferenzaStudenteEntity();
		
		assertNotNull(p);
		
	}
	
	//GETTERS AND SETTERS
	
	@Test
	public void testGetSetStudente() {
		PreferenzaStudenteEntity p = new PreferenzaStudenteEntity();
		
		p.setStudente(1);

		assertEquals(1, p.getStudente());
	}
	
	@Test
	public void testGetSetDisponibilitā() {
		PreferenzaStudenteEntity p = new PreferenzaStudenteEntity();
		
		p.setDisponibilita(1);
		
		assertEquals(1, p.getDisponibilita());
		
	}
	
	
	// To String()
	@Test
	public void testToString() {
		PreferenzaStudenteEntity p = new PreferenzaStudenteEntity();
		String expectedValue = "Preferenza [studente=" + p.getStudente() + ", disponibilita=" + p.getDisponibilita() + "]";
		String actualValue = p.toString();
		
		assertEquals(expectedValue, actualValue);
		
	}

}
