package control;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockRequestDispatcher;

import autenticazione.control.AutenticazioneServlet;
import controller.control.Action;
import gestionepf.control.GestionePFServlet;
import model.entity.DisponibilitaEntity;
import model.entity.PercorsoFormativoEntity;
/*
 * Classe di Test che testa i servizi : GestionePFService e VisualizzaPercorsoService
 * 
 * 
 */
public class GestionePFServletTest extends Mockito{
	
	
	private GestionePFServlet control;
	private MockHttpServletRequest req;
	private MockHttpServletResponse res;
	private MockRequestDispatcher dp;
	
	
	void setupNaming() {
		System.setProperty("java.naming.factory.initial","testUtility.MyContextFactory");

		
	}
	@Before
	public void setUp() {
		
		
		setupNaming();
		
		control = new GestionePFServlet();
		res = new MockHttpServletResponse();
		
		
	}
	
	@Test
	public void testDoPostRedirectCreatorPercorso() throws ServletException, IOException {
		
		String page = "/formAct/view/gestionepf/PercorsoCreato.jsp";
		
		req = new MockHttpServletRequest("POST","/formAct/gestionepf/GestionePFServlet/CreatorService") {
			  public RequestDispatcher getRequestDispatcher(String path) {
						return new MockRequestDispatcher(path); } };
						
		req.addParameter("nome", "prova control creator percorso");
		req.addParameter("categoria", "1");
        req.addParameter("descrizione","aggiunta percorso per test controller");
        req.getSession().setAttribute("currentId", 1);
		req.addParameter("indice", "1--2--3");			
		req.addParameter("lezioni", "12");
		req.addParameter("costo", "10.00");
		req.addParameter("giorno", "venerdì");
		req.addParameter("orario", "10:00");
						
		control.doPost(req, res);
		
		System.out.println(res.getRedirectedUrl() + " Percorso creato page");
		
		
		assertEquals(page, res.getRedirectedUrl());
		
	}
	
	
	
	@Test
	public void testDoPostRedirectDeletePercorso() throws ServletException, IOException {
		
		
		String homepage = "/formAct/view/index/index.jsp";
		// elimino il terzo percorso
		req = new MockHttpServletRequest("POST","/formAct/gestionepf/GestionePFServlet/DeletePercorsoService") {
			  public RequestDispatcher getRequestDispatcher(String path) {
						return new MockRequestDispatcher(path); } };
					
					
					//req.getSession().setAttribute("idPercorso", 3);
					req.addParameter("idPercorso", "3");
					control.doPost(req, res);
					
	    System.out.println(res.getRedirectedUrl() + " home page ");	
					
	    assertEquals(homepage, res.getRedirectedUrl());
		
	}

	
	@Test
	public void testForwardSchedaPercorso() throws ServletException, IOException {
		
		String page = "/view/gestionepf/percorsoView.jsp";
		
		req = new MockHttpServletRequest("POST","/formAct/gestionepf/GestionePFServlet/VisualizzaPercorsoService") {
			  public RequestDispatcher getRequestDispatcher(String path) {
						return new MockRequestDispatcher(path); } };
										
		req.setParameter("idPercorso", "1");
		req.getSession().setAttribute("role", "Formatore");
		
		
		control.doPost(req, res);
		
		System.out.println(res.getForwardedUrl() + " Percorso Page");
		
		
		assertEquals(page, res.getForwardedUrl());
		
	}
	
	@Test
	public void testForwardPercorsi() throws ServletException, IOException {
		
		String page = "/view/gestionepf/percorsiFormatore.jsp";
		
		req = new MockHttpServletRequest("POST","/formAct/gestionepf/GestionePFServlet/PercorsiFormatoreService") {
			  public RequestDispatcher getRequestDispatcher(String path) {
						return new MockRequestDispatcher(path); } };
										
		
		req.getSession().setAttribute("currentId", 1);
		
		
		control.doPost(req, res);
		
		System.out.println(res.getForwardedUrl() + " Percorsi formatore Page");
		
		
		assertEquals(page, res.getForwardedUrl());
	}
	
	
	
}

