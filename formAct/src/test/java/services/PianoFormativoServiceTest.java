package services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import controller.control.Action;
import model.dao.IscrizioneDao;
import model.dao.PianoFormativoDao;
import model.dao.PreferenzaStudenteDao;
import model.dao.StudenteDao;
import model.entity.IscrizioneEntity;
import model.entity.PreferenzaStudenteEntity;
import model.entity.StudenteEntity;
import pianoformativo.geneticalgorithm.Soluzione;
import pianoformativo.geneticalgorithm.Stato;
import pianoformativo.service.PianoFormativoService;

public class PianoFormativoServiceTest {
	
	private PreferenzaStudenteDao psDao;
	private IscrizioneDao iDao;
	private PianoFormativoDao pianofDao;
	private StudenteDao sDao;
	
	private ArrayList<PreferenzaStudenteEntity> giorniLiberi;
	private ArrayList<IscrizioneEntity> iscrizioni;
	private ArrayList<Stato> spazioStati;
	private ArrayList<String> interessi;
	
	private Map<Integer, String> categorie;
	
	private int idStudente;
	private int sizeIndividui;
	private int maxEvaluation;
	private int populationSize;
	
	private long maxComputingTime;
	
	private MockHttpServletRequest req;
	private MockHttpServletResponse res;
	
	private Action act;
	
	void setupNaming() {
		
		System.setProperty("java.naming.factory.initial","testUtility.MyContextFactory");
		
	}
	
	@Before
	public void setUp() {
		
		setupNaming();
		
		psDao = new PreferenzaStudenteDao();
		iDao = new IscrizioneDao();
		pianofDao = new PianoFormativoDao();
		sDao = new StudenteDao();
		
		req = new MockHttpServletRequest();
		res = new MockHttpServletResponse();
		
	}
	
	@Test
	public void testOttieniSoluzioneNotEmpty() throws SQLException, ParseException {
		
		int idStudente = 5;
		giorniLiberi = psDao.doRetrieveByStudent(idStudente);
		iscrizioni = iDao.doRetrieveByStudent(idStudente);
		spazioStati = pianofDao.doRetrieveSpazioStati(giorniLiberi, iscrizioni);
		interessi = pianofDao.doRetrieveInteressiStudente(idStudente);
		categorie = pianofDao.doRetrieveCategorie();
		
		PianoFormativoService service = new PianoFormativoService();
		Soluzione soluzione = service.ottieniSoluzione(spazioStati, giorniLiberi, interessi, categorie);
		System.out.println("----------------------" + soluzione.getSize());
		assertNotNull(soluzione);
		assertTrue(soluzione.getSize() == 10);
		
	}
	
	@Test
	public void testOttieniSoluzioneEmpty() throws SQLException, ParseException {
		
		int idStudente = 3;
		giorniLiberi = new ArrayList<>();
		iscrizioni = iDao.doRetrieveByStudent(idStudente);
		spazioStati = pianofDao.doRetrieveSpazioStati(giorniLiberi, iscrizioni);
		interessi = pianofDao.doRetrieveInteressiStudente(idStudente);
		categorie = pianofDao.doRetrieveCategorie();
		
		PianoFormativoService service = new PianoFormativoService();
		Soluzione soluzione = service.ottieniSoluzione(spazioStati, giorniLiberi, interessi, categorie);
		
		assertNotNull(soluzione);
		assertTrue(soluzione.getSize() == 0);
				
	}
	
	@Test
	public void testOttieniPianoFormativoCorrectNotEmpty() throws SQLException, ParseException {
		
		int idStudente = 3;
		giorniLiberi = psDao.doRetrieveByStudent(idStudente);
		iscrizioni = iDao.doRetrieveByStudent(idStudente);
		spazioStati = pianofDao.doRetrieveSpazioStati(giorniLiberi, iscrizioni);
		interessi = pianofDao.doRetrieveInteressiStudente(idStudente);
		categorie = pianofDao.doRetrieveCategorie();
		
		req.getSession().setAttribute("role", "Studente");
		req.getSession().setAttribute("currentId", idStudente);
		PianoFormativoService service = new PianoFormativoService();
		boolean risultato = service.ottieniPianoFormativo(req);
		assertTrue(risultato);
		
	}
	
	@Test
	public void testOttieniPianoFormativoCorrectEmpty() throws SQLException, ParseException {
		
		int idStudente = 14;
		
		giorniLiberi = psDao.doRetrieveByStudent(idStudente);
		iscrizioni = iDao.doRetrieveByStudent(idStudente);
		spazioStati = pianofDao.doRetrieveSpazioStati(giorniLiberi, iscrizioni);
		interessi = pianofDao.doRetrieveInteressiStudente(idStudente);
		categorie = pianofDao.doRetrieveCategorie();
		
		req.getSession().setAttribute("role", "Studente");
		req.getSession().setAttribute("currentId", idStudente);
		PianoFormativoService service = new PianoFormativoService();
		boolean risultato = service.ottieniPianoFormativo(req);
		assertTrue(risultato);
		
		assertTrue(giorniLiberi.isEmpty());
		assertTrue(iscrizioni.isEmpty());
		assertTrue(spazioStati.isEmpty());
		
	}
	
	@Test
	public void testOttieniPianoFormativoError() throws SQLException, ParseException {
		
		int idStudente = 3;
		
		giorniLiberi = psDao.doRetrieveByStudent(idStudente);
		iscrizioni = iDao.doRetrieveByStudent(idStudente);
		spazioStati = pianofDao.doRetrieveSpazioStati(giorniLiberi, iscrizioni);
		interessi = pianofDao.doRetrieveInteressiStudente(idStudente);
		categorie = pianofDao.doRetrieveCategorie();
		
		req.getSession().setAttribute("role", "Formatore");
		req.getSession().setAttribute("currentId", idStudente);
		PianoFormativoService service = new PianoFormativoService();
		boolean risultato = service.ottieniPianoFormativo(req);
		assertFalse(risultato);
		
	}
	
	@Test
	public void testProcessOttieniPianoCorrect() {
		
		int idStudente = 3;
		
		req.getSession().setAttribute("role", "Studente");
		req.getSession().setAttribute("currentId", idStudente);
		
		PianoFormativoService service = new PianoFormativoService();
		
		act = service.process("OttieniPianoService", req, res);
		assertEquals("/formAct/view/pianoformativo/OttieniPianoFormativo.jsp", act.getPage());
	}
	
	@Test
	public void testProcessOttieniPianoError() {
		
		int idStudente = 3;
		
		req.getSession().setAttribute("role", "Formatore");
		req.getSession().setAttribute("currentId", idStudente);
		
		PianoFormativoService service = new PianoFormativoService();
		
		act = service.process("OttieniPianoService", req, res);
		assertEquals("/formAct/view/messagePages/Errori.jsp", act.getPage());
		
	}
	
	@Test
	public void testProcessServiceNotFound() {
		
		int idStudente = 3;
		
		req.getSession().setAttribute("role", "Studente");
		req.getSession().setAttribute("currentId", idStudente);
		
		PianoFormativoService service = new PianoFormativoService();
		
		act = service.process("ServizioNonPresente", req, res);
		assertEquals("/formAct/view/messagePages/Errori.jsp", act.getPage());
		
	}
	
	@Test
	public void testGetErrorAction() {
		
		Action errorPageExpected = new Action("/formAct/view/pianoformativo/ErrorPage.jsp", true, true);
		
		PianoFormativoService service = new PianoFormativoService();
		
		assertTrue(service.getErrorAction().isError());
		
	}
	/*	
*/
}









