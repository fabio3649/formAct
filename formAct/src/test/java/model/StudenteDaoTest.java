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
		s.setCountry("Salerno");
		
		
		
		
	}
	
	
	@Test
	public void doSaveTest() throws SQLException {
        
		dao.doSave(s);
	
		StudenteEntity result = (StudenteEntity) dao.doRetrieveByKey(s.getId());
		boolean value = s.equals(result);
		System.out.println("Valore dell'equals : " + value);
		System.out.println(" studente s content : " + s.toString());
		System.out.println("  studente byID content : " + result.toString());
		System.out.println("ID GENERATO : " + s.getId());
		
		assertTrue(value);
	}
	
	@Test
	public void testDoRetrieveAll() throws SQLException
	{
		ArrayList<StudenteEntity> studenti = new ArrayList<StudenteEntity>();
		
		studenti = dao.doRetrieveAll();
		
		assertNotNull(studenti);
		
	}
	
	@Test
	public void testUpdatePassword() throws SQLException {
		
		dao.updatePassword(1, "cambioPassword");
		
		StudenteEntity s = (StudenteEntity) dao.doRetrieveByKey(1);
		System.out.println(s.getEmail());
		assertEquals("cambioPassword",s.getPassword());
		
		
	}
	// studente id = 2
	@Test
	public void testUpdateStudent() throws SQLException {
		
		dao.updateStudent(1, "federico.discipio@gmail.com", "Spagna");
		
		StudenteEntity s = (StudenteEntity) dao.doRetrieveByMail("federico.discipio@gmail.com");
		System.out.println(s.getEmail());
		assertEquals("federico.discipio@gmail.com" , s.getEmail());
		assertEquals("Spagna",s.getCountry());
		
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
