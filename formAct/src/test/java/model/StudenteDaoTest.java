package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import model.dao.FormatoreDao;
import model.dao.StudenteDao;
import model.entity.FormatoreEntity;
import model.entity.StudenteEntity;
import model.utils.Utils;

public class StudenteDaoTest extends Mockito{
	
	private StudenteDao dao;
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;
	private StudenteEntity s;
	private int id;
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	
	void setupNaming() {
		System.setProperty("java.naming.factory.initial","testUtility.MyContextFactory");

		
	}
	
	
	@Before
	public void setUp() throws SQLException, ParseException {
		System.setErr(new PrintStream(errContent));
		setupNaming();
		
		dao = new StudenteDao();
		
		s = new StudenteEntity();
		
		s.setEmail("federico.discipio@gmail.com");
		s.setPassword("PinoPallino");
		s.setName("federico");
		s.setSurname("di scipio");
		s.setGender("M");
		java.sql.Date d = Utils.toDate("10-09-1995");
		s.setBirthDate(d);
		s.setCountry("Italy");
		
		
		
		
	}
	
	
	
	@Test
	public void testDoRetrieveAll() throws SQLException
	{
		ArrayList<StudenteEntity> studenti = new ArrayList<StudenteEntity>();
		
		studenti = dao.doRetrieveAll();
		
		assertNotNull(studenti);
		
	}
	
	@Test
	public void testSaveAndRetrieve() throws SQLException {
		
		dao.doSave(s);
		System.out.println(s.getId() + "id creato da retieveByKey");
		StudenteEntity s2 = dao.doRetrieveByKey(s.getId());
		
		boolean value = s.equals(s2);
		
		assertTrue(value);
		assertNotNull(s2);
		assertEquals("PinoPallino",s2.getPassword());
		System.out.println("Valore dell'equals : " + value);
		System.out.println(" studente  s content : " + s.toString());
		System.out.println("formatore byID content : " + s2.toString());
		System.out.println("ID GENERATO : " + s.getId());
		System.out.println(s2.getPassword() + "password by key");
	}
	
	
	@Test
	public void testUpdatePassword() throws SQLException {
		
		dao.updatePassword(4, "cambioPassword");
		
		StudenteEntity s = (StudenteEntity) dao.doRetrieveByKey(4);
		System.out.println(s.getEmail());
		assertEquals("cambioPassword",s.getPassword());
		
		
	}  
	
	@Test
	public void testUpdateStudent() throws SQLException {
		
		dao.updateStudent(4, "federico.discipio@gmail.com");
		
		StudenteEntity s = (StudenteEntity) dao.doRetrieveByMail("federico.discipio@gmail.com");
		System.out.println(s.getEmail());
		assertEquals("federico.discipio@gmail.com" , s.getEmail());
		assertEquals("Italy",s.getCountry());
		
	} 
	
	
	
	
	@Test
	public void testRetrieveByMailError() throws SQLException {
		String email = "ciao.ueue@test.it";
		StudenteEntity f = (StudenteEntity) dao.doRetrieveByMail(email);
		assertNotEquals(f.getEmail(),email);
	}
	
	@Test
	public void testRetrieveByMailSuccess() throws SQLException {
		String email = "federico.discipio@gmail.com";
		StudenteEntity f = (StudenteEntity) dao.doRetrieveByMail(email);
		assertEquals(f.getEmail(),email);
	}
	
	@Test
	public void testDoDeleteError() throws SQLException {
		
	
		assertEquals(dao.doDelete(0),false);
		
	}

}
