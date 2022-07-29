package services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.text.ParseException;

import javax.servlet.ServletException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import controller.control.Action;
import model.dao.FormatoreDao;
import registrazione.service.RegisterService;

public class RegisterServiceTest extends Mockito{
	
	private FormatoreDao dao;
	private MockHttpServletRequest req;
	private MockHttpServletResponse res;
	private RegisterService register;
	private Action act;
	
	void setupNaming() {
		System.setProperty("java.naming.factory.initial","testUtility.MyContextFactory");

		
	}
	@Before
	public void setUp() {
		
		setupNaming();
		dao = new FormatoreDao();
		req = new MockHttpServletRequest();
		res = new MockHttpServletResponse();
		register = new RegisterService();
		act = new Action("",true,true);
		
	}
	
	@Test
	public void testProcessErrorPage() throws IOException, ServletException {
		
		req.getSession().setAttribute("register", "");
		act = register.process("RegisterService",req, res);
		
		String errorPage = act.getPage();
		
		assertNotEquals("/formAct/view/index/index.jsp",errorPage);
		
	}
	
	@Test
	public void testProcessStudenteHomePage() throws IOException, ServletException {
		
		req.addParameter("email", "testStudente3@prova.it");
		req.addParameter("password", "testing");
		req.addParameter("name", "Test nome");
		req.addParameter("surname", "test cognome");
		req.addParameter("gender","f");
		req.addParameter("country", "Italia");
		req.addParameter("birthdate", "18-07-2022");
		req.getSession().setAttribute("register", "studente");
		
		act = register.process("RegisterService",req, res);
		
		
		String homePage = act.getPage();
		
		assertEquals("/formAct/view/index/index.jsp",homePage);
		
	}
	
	@Test
	public void testProcessFormatoreHomePage() throws IOException, ServletException {
		
		req.addParameter("email", "testFormatore4@prova.it");
		req.addParameter("password", "testing");
		req.addParameter("name", "Test nome");
		req.addParameter("surname", "test cognome");
		req.addParameter("gender","f");
		req.addParameter("country", "Italia");
		req.addParameter("cf", "sdasdasd");
		req.addParameter("numCC", "dsadadja555sa");
		req.addParameter("birthdate", "18-07-2022");
		req.getSession().setAttribute("register", "formatore");
		
		act = register.process("RegisterService",req, res);
		
		
		String homePage = act.getPage();
		
		assertEquals("/formAct/view/index/index.jsp",homePage);
		
	}
	
	
	@Test
	public void testIsNotContentCfFormatore() {
		
		
		
		assertEquals(register.isCfContent(""),false);
		
	}
	
	@Test
	public void testIsCfFormatoreContent() {
		
		
		assertEquals(register.isCfContent("sdasdasd"),true);
	}
	
	@Test
	public void testIsNotContentStudentEmail() {
		
		assertEquals(register.isEmailContent(""),false);
	}
	
	/*@Test
	public void testIsStudentEmailContent() {
		
		
		assertEquals(register.isEmailContent("testStudente3@prova.it"),true);
	} */
	
	@Test
	public void testIsNotContentTrainerEmail() {
		
		
		assertEquals(register.isEmailContentTrainer(""),false);
	}
	
	/*@Test
	public void testIsTrainerEmailContent() {
		
		
		assertEquals(register.isEmailContentTrainer("testFormatore4@prova.it"),true);
	} */
	
	

}
