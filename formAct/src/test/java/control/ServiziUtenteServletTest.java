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

import model.dao.PercorsoFormativoDao;
import model.dao.ValutazioneDao;
import model.entity.PercorsoFormativoEntity;
import model.entity.ValutazioneEntity;
import serviziutente.control.ServiziUtenteServlet;

public class ServiziUtenteServletTest extends Mockito{

	
	private ServiziUtenteServlet control;
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
		control = new ServiziUtenteServlet();
		res = new MockHttpServletResponse();
	}
	
	
	@Test
	public void testRedirectAgenda() throws ServletException, IOException {
		
		String page = "/formAct/view/serviziutente/agendaCorsi.jsp";
		
		req = new MockHttpServletRequest("POST","/formAct/serviziutente/ServiziUtenteServlet/GetAgendaService") {
			  public RequestDispatcher getRequestDispatcher(String path) {
						return new MockRequestDispatcher(path); } };
					
	    req.getSession().setAttribute("currentId", 1);
		control.doPost(req, res);
		
		
		System.out.println(res.getRedirectedUrl()+ " Agenda Page");
						
						
		assertEquals(page, res.getRedirectedUrl());
		
	}
	
	@Test
	public void testRedirectProfiloStudente() throws ServletException, IOException {
		
		String page = "/formAct/view/autenticazione/Profilo.jsp";
		
		req = new MockHttpServletRequest("POST","/formAct/serviziutente/ServiziUtenteServlet/VisualizzaProfiloService") {
			  public RequestDispatcher getRequestDispatcher(String path) {
						return new MockRequestDispatcher(path); } };
						
	    req.getSession().setAttribute("currentId", 1);	
	    req.getSession().setAttribute("role", "Studente");
		control.doPost(req, res);
						
		System.out.println(res.getRedirectedUrl()+ " Profilo Page");
						
						
		assertEquals(page, res.getRedirectedUrl());
		
	}
	
	@Test
	public void testRedirectProfiloFormatore() throws ServletException, IOException {
		
		String page = "/formAct/view/autenticazione/Profilo.jsp";
		
		req = new MockHttpServletRequest("POST","/formAct/serviziutente/ServiziUtenteServlet/VisualizzaProfiloService") {
			  public RequestDispatcher getRequestDispatcher(String path) {
						return new MockRequestDispatcher(path); } };
						
	    req.getSession().setAttribute("currentId", 1);	
	    req.getSession().setAttribute("role", "Formatore");
		control.doPost(req, res);
						
		System.out.println(res.getRedirectedUrl()+ " Profilo Page");
						
						
		assertEquals(page, res.getRedirectedUrl());
		
	}
	
	@Test
	public void testCreaValutazione() throws ServletException, IOException {
		
		String page = "/formAct/view/index/index.jsp";
		
		req = new MockHttpServletRequest("POST","/formAct/serviziutente/ServiziUtenteServlet/ValutazioneService") {
			  public RequestDispatcher getRequestDispatcher(String path) {
						return new MockRequestDispatcher(path); } };
						
		req.getSession().setAttribute("role","Studente");
		req.getSession().setAttribute("currentId",4);
		req.setParameter("idPercorso", "1");
		req.setParameter("descrizione", "prova valutazione servizi utente servlet Test");
		req.setParameter("star0IsSelected","false");
		req.setParameter("star1IsSelected","false");
		req.setParameter("star2IsSelected","false");
		req.setParameter("star3IsSelected","true");
		req.setParameter("star4IsSelected","false");
		control.doPost(req, res);
		
		assertEquals(page, res.getRedirectedUrl());
		
		
		
	}
	
	
	
	
}
