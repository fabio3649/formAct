package model.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class InteresseTest {
		
	@Test
    public void testDisponibilitàEmptyConstructor(){
        InteresseEntity interesse= new InteresseEntity();
        assertNotNull(interesse);
    }	
	
	@Test
	public void testIdInteresse() {
		InteresseEntity interesse= new InteresseEntity();
		interesse.setIdInteresse(1);
		assertEquals(1,interesse.getIdInteresse());
	}
	
	@Test
	public void testNome() {
		InteresseEntity interesse= new InteresseEntity();
		interesse.setNome("Paolo");
		assertEquals("Paolo",interesse.getNome());
	}
	
}
