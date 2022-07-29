package model;

import static org.junit.Assert.assertTrue;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import model.dao.PercorsoFormativoDao;
import model.entity.PercorsoFormativoEntity;

public class PercorsoFormativoDaoTest {

	private PercorsoFormativoDao dao;
	private PercorsoFormativoEntity p;
	

	void setupNaming() {
		System.setProperty("java.naming.factory.initial","testUtility.MyContextFactory");

		
	}

	
	@Before
	public void setUp() {
		
		setupNaming();
		dao = new PercorsoFormativoDao();
		p = new PercorsoFormativoEntity();
		p.setId_formatore(1);
		p.setNome("ProvaTest2");
		p.setCategoria(1);
		p.setDescrizione("Tupla per testare il dao PercosoFormativo");
		p.setIndice_contenuti("1 - test ;);"
				+ "2 - test;"
				+ "3 - test;");
		p.setNum_lezioni(0);
		p.setCosto(10);
		
		
		
		
	}
	
	@Test
	public void testDoSave() throws SQLException {
		
		dao.doSave(p);
		
		PercorsoFormativoEntity result = (PercorsoFormativoEntity) dao.doRetrieveByKey(p.getId());
		boolean value = p.equals(result);
		
		System.out.println("Valore dell'equals : " + value);
		System.out.println("P content : " + p.toString());
		System.out.println("p byID content : " + result.toString());
		System.out.println("ID GENERATO : " + p.getId());
		
		assertTrue(value);
		
	}
	
	
	
	
	
}
