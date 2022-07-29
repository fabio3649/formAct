package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;

import autenticazione.service.AutenticazioneService;
import model.dao.FormatoreDao;
import model.entity.FormatoreEntity;
import model.utils.Utils;

public class FormatoreDaoTest extends Mockito{
	
	private FormatoreDao dao;
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;
	private FormatoreEntity f;
	private int id;
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	
	void setupNaming() {
		System.setProperty("java.naming.factory.initial","testUtility.MyContextFactory");

		
	}
	
	@Before
	public void setUp() throws SQLException, ParseException {
		System.setErr(new PrintStream(errContent));
		setupNaming();
		
		System.out.println("NAMING CREATO");
	  
		dao = new FormatoreDao();
		
		
		f = new FormatoreEntity();
		
		
		
		f.setEmail("test2@formatore.it");
		f.setPassword("testing2");
		f.setName("test2");
		f.setSurname("test");
		f.setGender("M");
		java.sql.Date d = Utils.toDate("10-07-2022");
		f.setBirthDate(d);
		f.setCountry("Italia");
		f.setCodiceFiscale("TSD28072022");
		f.setContoCorrente("ITSDTESNPL28072022");
		 
	}
	
	
	
	@Test
	public void testDoInsert() throws SQLException {
		
		
		dao.doSave(f);
		

		FormatoreEntity result = (FormatoreEntity) dao.doRetrieveByKey(f.getId());
		boolean value = f.equals(result);
		System.out.println("Valore dell'equals : " + value);
		System.out.println(" formatore f content : " + f.toString());
		System.out.println("formatore byID content : " + result.toString());
		System.out.println("ID GENERATO : " + f.getId());
		System.out.println(FormatoreEntity.diff(f, result));
		assertTrue(value);
		
		
	}
	
	@Test
	public void testDoRetrieveAll() throws SQLException
	{
		ArrayList<FormatoreEntity> formatori = new ArrayList<FormatoreEntity>();
		
		formatori = dao.doRetrieveAll();
		
		assertNotNull(formatori);
		
	}
	
	@Test
	public void testUpdatePassword() throws SQLException {
		
		dao.updatePassword(2, "cambioPassword");
		
		FormatoreEntity f = (FormatoreEntity) dao.doRetrieveByKey(2);
		assertEquals("cambioPassword",f.getPassword());
		
		
	}
	
	@Test
	public void testDoDelete() throws SQLException {
		
		
		
		assertTrue(dao.doDelete(2));
		
	}
	
	@Test
	public void testRetrieveByMailError() throws SQLException {
		String email = "ciao.ueue@test.it";
		FormatoreEntity f = (FormatoreEntity) dao.doRetrieveByMail(email);
		assertNotEquals(f.getEmail(),email);
	}
	
	@Test
	public void testRetrieveByMailSuccess() throws SQLException {
		String email = "test2@formatore.it";
		FormatoreEntity f = (FormatoreEntity) dao.doRetrieveByMail(email);
		assertEquals(f.getEmail(),email);
	}
	
	
	
	
	
	

}
