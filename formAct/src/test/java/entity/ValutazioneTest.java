package entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.Date;

import org.junit.Test;

import model.entity.ValutazioneEntity;

public class ValutazioneTest {
	
	//Constructor
	
		@Test
		public void testConstructor() {
			ValutazioneEntity v = new ValutazioneEntity();
			
			assertNotNull(v);
		}
		
		//GETTERS AND SETTERS
		
		@Test
		public void testGetSetStudente() {
			ValutazioneEntity v = new ValutazioneEntity();
			
			v.setStudente(1);
			
			assertEquals(1, v.getStudente());
		}
		
		@Test
		public void testGetSetFormatore() {
			ValutazioneEntity v = new ValutazioneEntity();
			
			v.setFormatore(1);
			
			assertEquals(1, v.getFormatore());
		}
		
		@Test
		public void testGetSetPercorso() {
			ValutazioneEntity v = new ValutazioneEntity();
			
			v.setPercorsoFormativo(1);
			
			assertEquals(1, v.getPercorsoFormativo());
		}
		
		@Test
		public void testGetSetDescrizione() {
			ValutazioneEntity v = new ValutazioneEntity();
			
			v.setDescrizione("descrizione");
			
			assertEquals("descrizione" ,v.getDescrizione());
		}
		
		@Test
		public void testeGetSetData() {
			ValutazioneEntity v = new ValutazioneEntity();
			Date d = new Date(2022-01-12);
			v.setData(d);
			
			assertEquals(d, v.getData());
		}
		
		@Test
		public void testGetSetStelle() {
			ValutazioneEntity v = new ValutazioneEntity();
			
			v.setStelle(4);
			
			assertEquals(4, v.getStelle());
		}
		
		
		//toString()
		@Test
		public void testToString() {
			ValutazioneEntity v = new ValutazioneEntity();
			
			String expectedValue = "Valutazione di un formatore  [studente=" + v.getStudente() + ", formatore=" + v.getFormatore() + ", percorsoFormativo="
					+ v.getPercorsoFormativo() + ", descrizione=" + v.getDescrizione() + ", data=" + v.getData() + ", stelle=" + v.getStelle() + "]";
		
			String actualValue = v.toString();
			
			assertEquals( expectedValue, actualValue);
		}

}
