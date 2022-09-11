package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.PrintStream;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import model.dao.CategoriaDao;
import model.entity.CategoriaEntity;
import model.entity.FormatoreEntity;

public class CategoriaDaoTest {
	
	private CategoriaDao dao;
	private CategoriaEntity c;
	
	
	
	
	void setupNaming() {
		System.setProperty("java.naming.factory.initial","testUtility.MyContextFactory");

		
	}
	
	@Before
	public void setUp() throws SQLException, ParseException {
		
		setupNaming();
		dao = new CategoriaDao();
		c = new CategoriaEntity();
		
		c.setNome("programmazione web");
		c.setDescrizione("HTML , CSS, JAVASCRIPT");
		c.setAmbito("discipline informatiche");
		
	}
	
	@Test
	public void testDoInsert() throws SQLException {
		
		dao.doSave(c);
		CategoriaEntity result = (CategoriaEntity) dao.doRetrieveByKey(c.getIdCategoria());
		boolean value = c.equals(result);
		
		assertTrue(value);
		
		
	}
	
	@Test
	public void testDoRetrieveAll() throws SQLException
	{
		ArrayList<CategoriaEntity> categorie = new ArrayList<CategoriaEntity>();
		
		categorie = dao.doRetrieveAll();
		
		assertNotNull(categorie);
		
	}
	
	@Test
	public void testDoDelete() throws SQLException {
		
		
		
		assertTrue(dao.doDelete(3));
		
	}
	
	@Test
	public void testRetrieveByNameError() throws SQLException {
		String name = "classico";
		CategoriaEntity f = (CategoriaEntity) dao.doRetrieveByName(name);
		assertNotEquals(f.getNome(),name);
	}
	
	@Test
	public void testRetrieveByNameSuccess() throws SQLException {
		String name = "programmazione web";
		CategoriaEntity f = (CategoriaEntity) dao.doRetrieveByName(name);
		assertEquals(f.getNome(),name);
	}
	
	
	
	
	}
