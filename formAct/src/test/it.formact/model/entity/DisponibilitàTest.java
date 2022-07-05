package model.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.LocalTime;

import org.junit.Test;

public class Disponibilit‡Test {

	@Test
    public void testDisponibilit‡EmptyConstructor(){
        Disponibilit‡Entity disp= new Disponibilit‡Entity();
        assertNotNull(disp);
    }	
	
	@Test
	public void testIdDisponibilit‡(){
		Disponibilit‡Entity disp= new Disponibilit‡Entity();
		disp.setIdDisp(1);
		assertEquals(1,disp.getIdDisp());
	}
	
	@Test
	public void testIdPercorso() {
		Disponibilit‡Entity disp= new Disponibilit‡Entity();
		disp.setIdPercorso(1);
		assertEquals(1,disp.getIdPercorso());
	}
	
	@Test
	public void testGiornoSettimana() {
		Disponibilit‡Entity disp= new Disponibilit‡Entity();
		disp.setGiornoSettimana("martedÏ");
		assertEquals("martedÏ",disp.getGiornoSettimana());
	}
	
	@Test
	public void testStato() {
		Disponibilit‡Entity disp= new Disponibilit‡Entity();
		disp.setStato(2);
		assertEquals(2,disp.getStato());
	}
	
	@Test
	public void testOrario() {
		LocalTime t= LocalTime.of(14, 00, 00);
		Disponibilit‡Entity disp= new Disponibilit‡Entity();
		disp.setOrario(t);
		assertEquals(t,disp.getOrario());
		
	}
	
}
