package it.formAct.model.dao;

import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;

import model.dao.FormatoreDao;

public class FormatoreDaoTest {
	
	
	private DataSource ds;
	private FormatoreDao dao;
	
	
	
	
	@Test
	public void daoTest() throws SQLException {
		
		
		dao = new FormatoreDao();
		int next = dao.nextId();
		assertTrue(next != 0);
		
	} 
	
/*	@Test
	public void dataSourceTest() throws NamingException {
		
		Properties props = new Properties();
		props.setProperty(Context.INITIAL_CONTEXT_FACTORY, "com.sun.enterprise.naming.SerialInitContextFactory");
		Context initialContext = new InitialContext(props);
		
	
		Context envCtx = (Context) initialContext.lookup("java:comp/env");

		ds = (DataSource) envCtx.lookup("jdbc/formactds");
		
		assertTrue( ds != null);
	}  */
	

}
