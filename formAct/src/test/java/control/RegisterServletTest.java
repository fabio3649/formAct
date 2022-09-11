package control;

import static org.junit.Assert.assertEquals;

import java.io.IOException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockRequestDispatcher;


import registrazione.control.RegisterServlet;

public class RegisterServletTest {

	
	
	private RegisterServlet control;
	private MockHttpServletRequest req;
	private MockHttpServletResponse res;
	private MockRequestDispatcher dp;
	
	void setupNaming() {
		System.setProperty("java.naming.factory.initial","testUtility.MyContextFactory");

		
	}
	
	@Before
	public void setUp() {
		
		setupNaming();
		control = new RegisterServlet();
		
		res = new MockHttpServletResponse();
		
		
	}
	
	
	@Test
	public void testDoPostRedirectRegisterFormatore() throws ServletException, IOException {
		String page = "/formAct/view/autenticazione/Login.jsp";
		
		
		req = new MockHttpServletRequest("POST","/formAct/registrazione/RegisterServlet/RegisterService") {
			  public RequestDispatcher getRequestDispatcher(String path) {
						return new MockRequestDispatcher(path); } };
		// Registrazione formatore, parametri nella request.
		req.getSession().setAttribute("register", "formatore");		
		req.setParameter("email","testingRegisterControl@gmail.com");
		req.setParameter("password","testControl");
		req.setParameter("name", "Formatore Control register");
		req.setParameter("surname", "control register");
		req.setParameter("gender", "M");
		req.setParameter("birthdate", "19-01-1998");
		req.setParameter("country","Italia");
		req.setParameter("cf","TSTCTRLRGSTSD2");
		req.setParameter("numCC", "provanumCCregisterControl");
		
		control.doPost(req, res);
		
		System.out.println(res.getRedirectedUrl() + "LoginPage");
		
		
		assertEquals(res.getRedirectedUrl(),page);
		
	}
	
	@Test
	public void testDoPostRedirectRegisterStudente() throws ServletException, IOException {
		String page = "/formAct/view/autenticazione/Login.jsp";
		
		
		req = new MockHttpServletRequest("POST","/formAct/registrazione/RegisterServlet/RegisterService") {
			  public RequestDispatcher getRequestDispatcher(String path) {
						return new MockRequestDispatcher(path); } };
		// Registrazione formatore, parametri nella request.
		req.getSession().setAttribute("register", "studente");		
		req.setParameter("email","testingRegisterControl@gmail.com");
		req.setParameter("password","testControl");
		req.setParameter("name", "Studente Control register");
		req.setParameter("surname", "control register");
		req.setParameter("gender", "M");
		req.setParameter("birthdate", "19-01-1998");
		req.setParameter("country","Italia");
		req.setParameter("cf","TSTCTRLRGSTSD2");
		req.setParameter("numCC", "provanumCCregisterControl");
		
		control.doPost(req, res);
		
		System.out.println(res.getRedirectedUrl() + "LoginPage");
		
		
		assertEquals(res.getRedirectedUrl(),page);
		
	}
	
	@Test
	public void testDoPostRedirectErrorPage() throws ServletException, IOException {
		String page = "/formAct/view/messagePages/Errori.jsp";
		
		
		req = new MockHttpServletRequest("POST","/formAct/registrazione/RegisterServlet/RegisterService") {
			  public RequestDispatcher getRequestDispatcher(String path) {
						return new MockRequestDispatcher(path); } };
		
		control.doPost(req, res);
		
		System.out.println(res.getRedirectedUrl() + "errorPage");
		
		
		assertEquals(res.getRedirectedUrl(),page);
		
	} 
}
