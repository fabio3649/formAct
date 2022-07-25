package services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.LocalTime;

import org.junit.Before;
import org.junit.Test;

import model.entity.CategoriaEntity;
import serviziutente.service.Calendario;

public class CalendarioTest {
	
	
	
	
	void setupNaming() {
		System.setProperty("java.naming.factory.initial","testUtility.MyContextFactory");

		
	}
	
	
	
	@Before
	public void setUp() {
		
		setupNaming();
		
		
		
	}
	
	
	@Test
    public void testConstructor(){
		
		String nomePercorso = "prova costo";
		int studente = 1;
		LocalTime t = LocalTime.now();
		Calendario c = new Calendario(nomePercorso,t,studente);
        assertNotNull(c);
    }
	
	@Test
	public void getNomeTest() {
		String nomePercorso = "prova costo";
		int studente = 1;
		LocalTime t = LocalTime.now();
		Calendario c = new Calendario(nomePercorso,t,studente);
		
		assertEquals("prova costo",c.getNome());
		
	}
	
	@Test
	public void getStudenteTest() {
		String nomePercorso = "prova costo";
		int studente = 1;
		LocalTime t = LocalTime.now();
		Calendario c = new Calendario(nomePercorso,t,studente);
		
		assertEquals(1,c.getIdStudente());
		
	}
	
	@Test
	public void getTimeTest() {
		String nomePercorso = "prova costo";
		int studente = 1;
		LocalTime t = LocalTime.now();
		Calendario c = new Calendario(nomePercorso,t,studente);
		
		assertEquals(t,c.getTime());
		
	}
	
	
	
	
	
	

}
