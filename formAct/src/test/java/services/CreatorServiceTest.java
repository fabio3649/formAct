package services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;

import autenticazione.service.AutenticazioneService;
import controller.control.Action;
import gestionepf.service.GestionePFService;

import model.dao.PercorsoFormativoDao;
import model.entity.DisponibilitaEntity;
import model.entity.PercorsoFormativoEntity;

public class CreatorServiceTest extends Mockito{
	
	private MockHttpServletRequest req;
	private MockHttpServletResponse res;
	private GestionePFService cr;
	private PercorsoFormativoEntity p;
	private DisponibilitaEntity d;
	private PercorsoFormativoDao dao ;
	private Action act;
	
	void setupNaming() {
		System.setProperty("java.naming.factory.initial","testUtility.MyContextFactory");

		
	}
	
	@Before
	public void setUp() {
		
		setupNaming();
		p = new PercorsoFormativoEntity();
		req = new MockHttpServletRequest();
		res = new MockHttpServletResponse();
		cr = new GestionePFService();
		d = new DisponibilitaEntity();
		dao = new PercorsoFormativoDao();
		act = new Action("",true,true);
	
	}
	
	
	@Test
	public void testCreazionePercorso() throws SQLException, IOException, ServletException {
		
		//bean percorso
		req.addParameter("nome", "percorsoTestNuovo");
		req.addParameter("descrizione", "aggiunta percorso di prova per test in junit");
		req.addParameter("categoria", "1");  // categoria attuali : 1 / 2 / 4
		req.getSession().setAttribute("currentId", "1");
		req.addParameter("indice", "Indice contenuti");
		req.addParameter("costo", "10.00");
		req.addParameter("lezioni", "50");
		//bean disponibilità
		req.addParameter("giorno", "lunedì");
		req.addParameter("orario", "09:00");
		
		act = cr.process("CreatorService",req, res);
		
		
		String homePage = act.getPage();
		
		assertEquals("/formAct/view/gestionepf/percorsoView.jsp",homePage);
		
		
	}
	
	
	
	
	

}
