package model;

import static org.junit.Assert.assertTrue;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import model.dao.InteresseDao;
import model.entity.FormatoreEntity;
import model.entity.InteresseEntity;

public class InteresseDaoTest {
	
	private InteresseDao dao;
	private InteresseEntity interesse;
	
	void setupNaming() {
		System.setProperty("java.naming.factory.initial","testUtility.MyContextFactory");

		
	}
	@Before
	public void setUp() {
		
		setupNaming();
		dao = new InteresseDao();
		interesse = new InteresseEntity();
		interesse.setNome("python");
		
	}
	
	@Test
	public void testDoSave() throws SQLException {
		
		dao.doSave(interesse);
		

		InteresseEntity result = (InteresseEntity) dao.doRetrieveByKey(interesse.getIdInteresse());
		boolean value = interesse.equals(result);
		System.out.println("Valore dell'equals : " + value);
		System.out.println(" interesse  content : " + interesse.toString());
		System.out.println("interesse byID content : " + result.toString());
		System.out.println("ID GENERATO : " + interesse.getIdInteresse());
		
		assertTrue(value);
	}
	
	
	
	
	
	
	
	

}
