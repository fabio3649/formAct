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


import pianoformativo.control.PianoFormativoServlet;

public class PianoFormativoServletTest extends Mockito {
	
	private PianoFormativoServlet control;
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
		control = new PianoFormativoServlet();
		res = new MockHttpServletResponse();
		
	}
	
	@Test
	public void testRedirectPianoFormativo() throws ServletException, IOException {
		
		String page = "/formAct/view/pianoformativo/OttieniPianoFormativo.jsp";
		
		req = new MockHttpServletRequest("POST","/formAct/pianoformativo/PianoFormativoServlet/OttieniPianoService") {
			  public RequestDispatcher getRequestDispatcher(String path) {
						return new MockRequestDispatcher(path); } };
		
		req.getSession().setAttribute("currentId", 2);
		req.getSession().setAttribute("role", "Studente");
		
		control.doPost(req, res);
		
		
		System.out.println(res.getRedirectedUrl()+ " Success Page");
		
		
		assertEquals(page, res.getRedirectedUrl());
	}
}
