package services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;

import autenticazione.service.LoginServices;
import getionepf.service.CreatorServices;
import model.dao.PercorsoFormativoDao;
import model.entity.DisponibilitaEntity;
import model.entity.PercorsoFormativoEntity;

public class CreatorServiceTest extends Mockito{
	
	private MockHttpServletRequest req;
	private CreatorServices cr;
	private PercorsoFormativoEntity p;
	private DisponibilitaEntity d;
	private PercorsoFormativoDao dao ;
	
	void setupNaming() {
		System.setProperty("java.naming.factory.initial","testUtility.MyContextFactory");

		
	}
	
	@Before
	public void setUp() {
		
		setupNaming();
		p = new PercorsoFormativoEntity();
		req = new MockHttpServletRequest();
		cr = new CreatorServices();
		d = new DisponibilitaEntity();
		dao = new PercorsoFormativoDao();
		//percorso
		req.addParameter("nome", "percorsoTest");
		req.addParameter("descrizione", "aggiunta percorso di prova per test in junit");
		req.addParameter("categoria", "1");
		req.addParameter("formatore", "45");
		req.addParameter("indice", "Indice contenuti");
		req.addParameter("costo", "10.00");
		req.addParameter("lezioni", "50");
		//disponibilità
		req.addParameter("giorno", "lunedì");
		req.addParameter("orario", "09:00");
		
	}
	
	
	@Test
	public void creazionePercorsoTest() throws SQLException {
		
		/*p = cr.getRequestDataPercorso(req);
		d = cr.getRequestDataDisponibilita(req,p); */
		
		 //cr.creatorPercorso(req);
		

		//assertTrue(cr.creatorPercorso(req));
	}
	
	

}
