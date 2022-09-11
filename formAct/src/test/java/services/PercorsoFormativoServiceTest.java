package services;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;

import javax.servlet.ServletException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import controller.control.Action;
import model.dao.DisponibilitaDao;
import model.entity.DisponibilitaEntity;
import model.utils.Utils;
import percorsoformativo.service.PercorsoFormativoService;

public class PercorsoFormativoServiceTest extends Mockito{
	
	private MockHttpServletRequest req;
	private MockHttpServletResponse res;
	private PercorsoFormativoService service;
	private DisponibilitaDao daoD;
	private Action act;
	
	void setupNaming() {
		System.setProperty("java.naming.factory.initial","testUtility.MyContextFactory");

		
	}
	
	@Before
	public void setUp() throws ParseException {
		
		setupNaming();
		daoD = new DisponibilitaDao();
		req = new MockHttpServletRequest();
		res = new MockHttpServletResponse();
		service = new PercorsoFormativoService();
		act = new Action("",true,true);
	}
	
	
	@Test
	public void testIscrizionePercorso() throws ParseException, IOException, ServletException, SQLException {
		
		
		DisponibilitaEntity disp = new DisponibilitaEntity();
		disp.setStato(1);
		disp.setGiornoSettimana("venerdì");
		disp.setIdPercorso(2);
		disp.setOrario("10:00");
		daoD.doSave(disp);
		
		
		req.getSession().setAttribute("currentId", 1);
		req.setParameter("idPercorso", "2");
		req.setParameter("iterator", "1");
		req.addParameter("metodoPagamento", "visa");
		req.addParameter("giorno0", "3");
		
		
		act = service.process("IscrizionePFService", req, res);
		
		
		
		assertEquals("/formAct/view/index/index.jsp",act.getPage());
		
	}
	
	@Test
	public void testDisiscrizionePercorso() throws IOException, ServletException {
		
		req.getSession().setAttribute("currentId", 3);
		req.addParameter("currentPF", "1");
				
		act = service.process("DisiscrizionePFService", req, res);
		
		
		
		assertEquals("/formAct/view/index/index.jsp",act.getPage());
	}
	
	@Test
	public void testRicercaPF() throws IOException, ServletException {
		
		req.addParameter("argomento", "control");
		act = service.process("RicercaPFService", req, res);
		
		
		assertEquals("/formAct/view/percorsoformativo/VisualizzaPercorsiFormativi.jsp",act.getPage());
	}
	
	

}
