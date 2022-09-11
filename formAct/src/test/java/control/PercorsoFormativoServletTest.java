package control;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockRequestDispatcher;


import percorsoformativo.control.PercorsoFormativoServlet;

public class PercorsoFormativoServletTest extends Mockito{
	
	private PercorsoFormativoServlet control;
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
		control = new PercorsoFormativoServlet();
		res = new MockHttpServletResponse();
		
	}
	
		
		@Test
		public void testDoPostRicerca() throws ServletException, IOException {
			String page = "/formAct/view/percorsoformativo/VisualizzaPercorsiFormativi.jsp";
			
			
			req = new MockHttpServletRequest("POST","/formAct/percorsoformativo/PercorsoFormativoServlet/RicercaPFService") {
				  public RequestDispatcher getRequestDispatcher(String path) {
							return new MockRequestDispatcher(path); } };
							
			
			req.addParameter("argomento", "java");
			
			
			
			control.doPost(req, res);
			
			System.out.println(res.getRedirectedUrl() + " Ricerca Page");
			
			
			assertEquals(page, res.getRedirectedUrl());
			
		}
		
	
	
	
	

}
