package entityTest;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import model.entity.PercorsoFormativoEntity;

class PercorsoFormativoTest {

	@Test
	void createPercorsoFormativoTest() {
		PercorsoFormativoEntity pfe = new PercorsoFormativoEntity();
		assertTrue(pfe.getId() == 0);
		assertTrue(pfe.getCategoria() == 0);
		//assert vari
	}
	
	@Test // testing di integrazione
	void setPercorsoFormativoTest() {
		PercorsoFormativoEntity pfe = new PercorsoFormativoEntity();
		int id = 5;
		pfe.setId(id);
		assertTrue(pfe.getId() == id);
		//assert vari
	}
	
	
	
	
	
	
	
	
	

}
