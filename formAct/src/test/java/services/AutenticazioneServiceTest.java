package services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;


import autenticazione.service.AutenticazioneService;


public class AutenticazioneServiceTest extends Mockito{
	
	private AutenticazioneService l;
	private MockHttpServletRequest req;
	
	void setupNaming() {
		System.setProperty("java.naming.factory.initial","testUtility.MyContextFactory");

		
	}
	@Before
	public void setUp() {
		
		setupNaming();
		req = new MockHttpServletRequest();
		l = new AutenticazioneService();
		
	}
	
	@Test
	public void testCheckTrainerLoginCorrect() throws SQLException {
		
		req.addParameter("email","testFormatore4@prova.it");
		req.addParameter("password","testing");
		
		assertEquals(l.checkTrainerLogin(req),true);
		
		
		
	}
	
	@Test
	public void testCheckTrainerLoginError() throws SQLException {
		
		req.addParameter("email","ciao");
		req.addParameter("password","t");
		
		assertNotEquals(l.checkTrainerLogin(req),true);
		
		
		
	}
	
	
	@Test
	public void testCheckStudentLoginCorrect() throws SQLException {
		
		req.addParameter("email","testStudente3@prova.it");
		req.addParameter("password","testing");
		
		assertEquals(l.checkStudentLogin(req),true);
	}
	
	
	@Test
	public void testCheckStudentLoginError() throws SQLException {
		
		req.addParameter("email","ciao");
		req.addParameter("password","t");
		
		assertNotEquals(l.checkStudentLogin(req),true);
	}
	
	@Test
	public void testCanLogoutCorrect() {
		req.getSession().setAttribute("Validation", "true");
		req.getSession().setAttribute("role", "studente");
		req.getSession().setAttribute("currentId", "46");
		
		assertEquals(l.canLogout(req),true);
		
	}
	
	@Test
	public void testCanLogoutError() {
		req.getSession().setAttribute("Validation", null);
		req.getSession().setAttribute("role", null);
		req.getSession().setAttribute("currentId", "2");
		assertNotEquals(l.canLogout(req),true);
	}
	
	
	
	

	
}
