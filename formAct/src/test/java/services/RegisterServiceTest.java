package services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;

import model.dao.FormatoreDao;
import registrazione.service.RegisterServices;

public class RegisterServiceTest extends Mockito{
	
	private FormatoreDao dao;
	private MockHttpServletRequest req;
	private RegisterServices register;
	
	void setupNaming() {
		System.setProperty("java.naming.factory.initial","testUtility.MyContextFactory");

		
	}
	@Before
	public void setUp() {
		
		setupNaming();
		dao = new FormatoreDao();
		req = new MockHttpServletRequest();
		register = new RegisterServices();
		
		req.addParameter("idFormatore", "24");
		req.addParameter("idStudente", "4");
		req.addParameter("email", "tesing@prova.it");
		req.addParameter("password", "testing");
		req.addParameter("name", "Test nome");
		req.addParameter("surname", "test cognome");
		req.addParameter("gender","f");
		req.addParameter("country", "Italia");
		req.addParameter("cf", "sdasdasd");
		req.addParameter("numCC", "dsadadja555sa");
		req.addParameter("birthdate", "18-07-2022");
		
	}
	
	@Test
	public void trainerRequestTest() throws ParseException {
		
		register.executeTrainerFormRequest(req);
		
		assertTrue(register.executeTrainerFormRequest(req));
		
	}
	
	@Test
	public void studentRequestTest() throws ParseException {
		
		register.executeStudentFormRequest(req);
		
		assertTrue(register.executeStudentFormRequest(req));
	}
	
	
	@Test
	public void isCfFormatoreContentNullTest() {
		
		register.isCfContent("");
		
		assertEquals(register.isCfContent(""),false);
		
	}
	
	@Test
	public void isCfFormatoreContentTest() {
		register.isCfContent("DASDADWETQG");
		
		assertEquals(register.isCfContent("DASDADWETQG"),true);
	}
	
	@Test
	public void isEmailStudentContentNullTest() {
		register.isEmailContent("");
		
		assertEquals(register.isEmailContent(""),false);
	}
	
	@Test
	public void isEmailStudentContentTest() {
		register.isEmailContent("fabio.pica10@gmail.com");
		
		assertEquals(register.isEmailContent("fabio.pica10@gmail.com"),true);
	}
	
	@Test
	public void isEmailTrainerContentNullTest() {
		register.isEmailContentTrainer("");
		
		assertEquals(register.isEmailContentTrainer(""),false);
	}
	
	@Test
	public void isEmailTrainerContentTest() {
		register.isEmailContentTrainer("testing@testing.it");
		
		assertEquals(register.isEmailContentTrainer("testing@testing.it"),true);
	}
	
	

}
