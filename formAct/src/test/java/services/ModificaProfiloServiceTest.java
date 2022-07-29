package services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.text.ParseException;

import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;


import autenticazione.service.ModificaProfiloService;
import model.dao.FormatoreDao;
import model.dao.InteresseDao;
import model.dao.InteresseStudenteDao;
import model.dao.StudenteDao;
import model.entity.StudenteEntity;

public class ModificaProfiloServiceTest {


	private MockHttpServletRequest req;
	private ModificaProfiloService m;
	

	
	void setupNaming() {
		System.setProperty("java.naming.factory.initial","testUtility.MyContextFactory");


	}
	@Before
	public void setUp() {
		
		setupNaming();
		req = new MockHttpServletRequest();
		m = new ModificaProfiloService();
		
	
	}
	
	
	
	@Test
	public void testModificaProfiloStudente() throws ParseException, SQLException {
		//modifico la prima entry di studente, creata con RegisterServiceTest
		req.getSession().setAttribute("role", "Studente");
		req.getSession().setAttribute("currentId", 1);
		
		req.addParameter("email", "testStudente3@cambioEmail.it");
		req.addParameter("country", "Spagna");
		
		boolean value = m.modificaProfilo(req);
		
		assertEquals(value,true);
		
	}
	
	
	@Test
	public void testModificaProfiloFormatore() throws ParseException, SQLException {
		//modifico la prima entry di formatore, creata con RegisterServiceTest
		req.getSession().setAttribute("role", "Formatore");
		req.getSession().setAttribute("currentId", 1);
		
		req.addParameter("email", "testFormatore4@cambioEmail.it");
		req.addParameter("country", "Spagna");
		req.addParameter("cc", "ITSDASDA5411DASD5415");
		
		boolean value = m.modificaProfilo(req);
		
		assertEquals(value,true);
	}
	
	@Test
	public void testModificaProfiloSessionNotValid() throws ParseException, SQLException {
		
		req.getSession().setAttribute("role", null);
		req.getSession().setAttribute("currentId", 0);
		
		req.addParameter("email", "provaTestSeconda@gmail.com");
		req.addParameter("country", "Spagna");
		req.addParameter("cc", "ITSDASDA5411DASD5415");
		
		boolean value = m.modificaProfilo(req);
		
		assertEquals(value,false);
	}
	
	@Test
	public void testModificaPasswordStudente() throws SQLException, ParseException {
		
		req.getSession().setAttribute("role", "Studente");
		req.getSession().setAttribute("currentId", 1);
		
		
		req.addParameter("passwordAttuale", "testing");
		req.addParameter("nuovaPassword", "testing2LaVendetta");
		req.addParameter("confermaPassword", "testing2LaVendetta");
		
		
		boolean value = m.modificaPassword(req);
		
		assertEquals(value,true);
		
		
		
	}
	
	@Test
	public void testModificaPasswordTrainer() throws ParseException {
		
		req.getSession().setAttribute("role", "Formatore");
		req.getSession().setAttribute("currentId", 1);
		
		
		req.addParameter("passwordAttuale", "testing");
		req.addParameter("nuovaPassword", "testing2LaVendetta");
		req.addParameter("confermaPassword", "testing2LaVendetta");
		
		
		boolean value = m.modificaPassword(req);
		
		assertEquals(value,true);
		
	}
	
	@Test
	public void testModificaPasswordSessionNotValid() throws ParseException {
		
		//req.getSession().setAttribute("role", null);
		req.getSession().setAttribute("currentId", 0);
		
		
		req.addParameter("passwordAttuale", "PPLaVendetta");
		req.addParameter("nuovaPassword", "PPLaVendetta2");
		req.addParameter("confermaPassword", "PPLaVendetta2");
		
		
		boolean value = m.modificaPassword(req);
		
		assertEquals(value,false);
		
	}
	
	// non funziona
		@Test
		public void testModificaInteressiStudente() throws ParseException {
			
			req.getSession().setAttribute("currentId", 1);
			
			req.addParameter("algebra", "algebra");
			req.addParameter("Java", "Java");
			
			boolean value = m.modificaInteressi(req);
			
			assertEquals(value,true);
		}
		
		/*@Test
		public void testModificaInteressiSessionNotValid() throws ParseException {
			
			req.getSession().setAttribute("currentId", 0);
			
			req.addParameter("algebra", "algebra");
			req.addParameter("python", "python");
			
			boolean value = m.modificaInteressi(req);
			
			assertEquals(value,false);
		}*/
	
	
	
	
	
}
