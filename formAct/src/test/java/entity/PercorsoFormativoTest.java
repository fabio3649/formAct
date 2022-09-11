package entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import model.entity.PercorsoFormativoEntity;

public class PercorsoFormativoTest {
	
	//Constructor
		@Test
		public void testConstructor() {
				PercorsoFormativoEntity p = new PercorsoFormativoEntity();
				assertNotNull(p);
		}
		
		// GETTERS AND SETTERS
		
		@Test
		public void testGetSetID() {
			PercorsoFormativoEntity p = new PercorsoFormativoEntity();
			p.setId(1);
			
			assertEquals(1, p.getId());
		}
		
		@Test
		public void testGetSetIdFormatore() {
			PercorsoFormativoEntity p = new PercorsoFormativoEntity();
			p.setId_formatore(1);
			
			assertEquals(1, p.getId_formatore());
		}
		
		@Test
		public void testGetSetNome() {
		PercorsoFormativoEntity p = new PercorsoFormativoEntity();
		p.setNome("corso java");
		
		assertEquals("corso java" , p.getNome());

		}
		
		@Test
		public void testGetSetCategoria() {
			PercorsoFormativoEntity p = new PercorsoFormativoEntity();
			p.setCategoria(2);
			
			assertEquals(2, p.getCategoria());
		}
		
		@Test
		public void testGetSetDescrizione() {
			PercorsoFormativoEntity p = new PercorsoFormativoEntity();
			p.setDescrizione("corso di java");
			
			assertEquals("corso di java", p.getDescrizione());
		}
		
		@Test
		public void testGetSetIndice() {
			PercorsoFormativoEntity p = new PercorsoFormativoEntity();
			p.setIndice_contenuti("1. linguaggio\n "
					+ "2.Algoritmi\n "
					+ "3.Array e liste\n "
					+ "4.Collection\n "
					+ "5.Iterator\n "
					+ "6.Programmi esempio");
			
			assertEquals("1. linguaggio\n "
					+ "2.Algoritmi\n "
					+ "3.Array e liste\n "
					+ "4.Collection\n "
					+ "5.Iterator\n "
					+ "6.Programmi esempio" , p.getIndice_contenuti());
			
		}
		
		@Test
		public void testGetSetNumLezioni() {
			PercorsoFormativoEntity p = new PercorsoFormativoEntity();
			p.setNum_lezioni(30);
			
			assertEquals(30, p.getNum_lezioni());
		}
		
		
		@Test
		public void testGetSetCosto() {
			PercorsoFormativoEntity p = new PercorsoFormativoEntity();
			p.setCosto(120.00);
			
			assertEquals(120.00 , p.getCosto(), 0.00);
		}
		
		//ToString()
		@Test
		public void testToString() {
			PercorsoFormativoEntity p = new PercorsoFormativoEntity();
			String expectedValue = "PercorsoFormativo [id=" + p.getId() + ", id_formatore=" + p.getId_formatore() + ", nome=" + p.getNome()
					+ ", descrizione=" + p.getDescrizione() + ", categoria=" + p.getCategoria() + ", indice_contenuti=" + p.getIndice_contenuti()
					+ ", num_lezioni=" + p.getNum_lezioni() + ", costo=" + p.getCosto() + "]";
			String actualValue = p.toString();
			
			assertEquals( expectedValue , actualValue);
			
		}

}
