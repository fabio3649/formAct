package entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.LocalTime;

import org.junit.Test;

import model.entity.CategoriaEntity;
import model.entity.DisponibilitaEntity;

public class DisponibilitaTest {

	@Test
    public void testConstructor(){
        DisponibilitaEntity disp= new DisponibilitaEntity();
        assertNotNull(disp);
    }	
	
	@Test
	public void testIdDisponibilita(){
		DisponibilitaEntity disp= new DisponibilitaEntity();
		disp.setIdDisp(1);
		assertEquals(1,disp.getIdDisp());
	}
	
	@Test
	public void testIdPercorso() {
		DisponibilitaEntity disp= new DisponibilitaEntity();
		disp.setIdPercorso(1);
		assertEquals(1,disp.getIdPercorso());
	}
	
	@Test
	public void testGiornoSettimana() {
		DisponibilitaEntity disp= new DisponibilitaEntity();
		disp.setGiornoSettimana("martedì");
		assertEquals("martedì",disp.getGiornoSettimana());
	}
	
	@Test
	public void testStato() {
		DisponibilitaEntity disp= new DisponibilitaEntity();
		disp.setStato(2);
		assertEquals(2,disp.getStato());
	}
	
	@Test
	public void testOrario() {
		String t = "14:00";
		DisponibilitaEntity disp= new DisponibilitaEntity();
		disp.setOrario(t);
		assertEquals(t,disp.getOrario());
		
	}
	
	
	
}
