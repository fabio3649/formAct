package model.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class InteresseStudenteTest {

	@Test
    public void testDisponibilitàEmptyConstructor(){
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
