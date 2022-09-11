package control;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpMethod;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockRequestDispatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import autenticazione.control.AutenticazioneServlet;
import autenticazione.service.AutenticazioneService;

public class AutenticazioneServletTest extends Mockito{
	
	
	
	private AutenticazioneServlet control;
	private MockHttpServletRequest req;
	private MockHttpServletResponse res;
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	
	
	
	void setupNaming() {
		System.setProperty("java.naming.factory.initial","testUtility.MyContextFactory");

		
	}
	@Before
	public void setUp() {
		
		System.setErr(new PrintStream(errContent));
		setupNaming();
		
		control = new AutenticazioneServlet();
		
		res = new MockHttpServletResponse();
		
		
	}
	
	
	//caso login corretto
	@Test
	public void testDoPostRedirectHomePage() throws ServletException, IOException {
		String page = "/formAct/view/index/index.jsp";
		
		
		req = new MockHttpServletRequest("POST","/formAct/autenticazione/AutenticazioneServlet/LoginService") {
			  public RequestDispatcher getRequestDispatcher(String path) {
						return new MockRequestDispatcher(path); } };
		req.setParameter("email","federico.discipio@gmail.com");
		req.setParameter("password","cambioPassword");
		control.doPost(req, res);
		
		System.out.println(res.getRedirectedUrl() + " HomePage");
		
		
		assertEquals(page, res.getRedirectedUrl());
		
	}
	
	
	//caso login errato
	@Test
	public void testDoPostRedirectErrorPage() throws ServletException, IOException {
		String page = "/formAct/view/autenticazione/Login.jsp";
		
		
		req = new MockHttpServletRequest("POST","/formAct/autenticazione/AutenticazioneServlet/LoginService") {
			  public RequestDispatcher getRequestDispatcher(String path) {
						return new MockRequestDispatcher(path); } };
		
		req.setParameter("email","federico");
		req.setParameter("password","cambio");
		control.doPost(req, res);
		
		System.out.println(res.getRedirectedUrl() + " LoginPage");
		
		
		assertEquals(page,res.getRedirectedUrl()); 
		
	}
	
	
	
	
	
	
	
	
	
	
		

}
