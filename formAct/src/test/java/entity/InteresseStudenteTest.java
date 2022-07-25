package entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import model.entity.InteresseStudenteEntity;

public class InteresseStudenteTest {


	@Test
    public void testConstructor(){
        InteresseStudenteEntity interesse= new InteresseStudenteEntity();
        assertNotNull(interesse);
    }	
	
	@Test
	public void testStudente() {
		 InteresseStudenteEntity interesse= new InteresseStudenteEntity();
		 interesse.setStudente(1);
		 assertEquals(1,interesse.getStudente());
	}
	
	@Test
	public void testInteresse() {
		 InteresseStudenteEntity interesse= new InteresseStudenteEntity();
		 interesse.setInteresse(1);
		 assertEquals(1,interesse.getInteresse());
	}
}
