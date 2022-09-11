package services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.SQLException;
import java.text.ParseException;

import javax.servlet.ServletException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;

import autenticazione.service.AutenticazioneService;
import controller.control.Action;
import model.dao.FormatoreDao;
import model.dao.StudenteDao;
import model.entity.FormatoreEntity;
import model.entity.StudenteEntity;
import model.utils.Utils;


public class AutenticazioneServiceTest extends Mockito{
	
	private AutenticazioneService l;
	private MockHttpServletRequest req;
	private MockHttpServletResponse res;
	private StudenteEntity s;
	private FormatoreEntity f;
	private FormatoreDao fDao;
	private StudenteDao sDao;
	private Action act;
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	
	void setupNaming() {
		System.setProperty("java.naming.factory.initial","testUtility.MyContextFactory");

		
	}
	@Before
	public void setUp() {
		System.setErr(new PrintStream(errContent));
		setupNaming();
		act = new Action("",true,true);
		req = new MockHttpServletRequest();
		res = new MockHttpServletResponse();
		l = new AutenticazioneService();
		f = new FormatoreEntity();
		s = new StudenteEntity();
		fDao = new FormatoreDao();
		sDao = new StudenteDao();
		
	}
	
	@Test
	public void testCheckTrainerLoginCorrect() throws SQLException {
		
		req.addParameter("email","testFormatore4@prova.it");
		req.addParameter("password","testing");
		
		assertEquals(l.checkTrainerLogin(req),true);
		
		
		
	}
	
	@Test
	public void testCheckTrainerLoginError() throws SQLException {
		
		req.addParameter("email","ciao");
		req.addParameter("password","t");
		
		assertNotEquals(l.checkTrainerLogin(req),true);
		
		
		
	}
	
	
	@Test
	public void testCheckStudentLoginCorrect() throws SQLException {
		
		req.addParameter("email","testStudente3@prova.it");
		req.addParameter("password","testing");
		
		assertEquals(l.checkStudentLogin(req),true);
	}
	
	
	@Test
	public void testCheckStudentLoginError() throws SQLException {
		
		req.addParameter("email","ciao");
		req.addParameter("password","t");
		
		assertNotEquals(l.checkStudentLogin(req),true);
	}
	
	@Test
	public void testCanLogoutCorrect() {
		
		req.getSession().setAttribute("validation", "true");
		
		req.getSession().setAttribute("role", "studente");
		req.getSession().setAttribute("currentId", "1");
		
		assertEquals(l.canLogout(req),true);
		
	}
	
	@Test
	public void testCanLogoutError() {
		req.getSession().setAttribute("validation", "false");
		
		assertNotEquals(l.canLogout(req),true);
		
	}
	
	
	@Test
	public void testCheckAdminLoginCorrect() throws SQLException {
		
		req.addParameter("email", "admin@admin.it");
		req.addParameter("password", "adminadmin");
		
		assertEquals(l.checkAdminLogin(req),true);
	}
	
	@Test
	public void testCheckAdminLoginError() throws SQLException {
		
		req.addParameter("email", "admin");
		req.addParameter("password", "ad");
		
		assertEquals(l.checkAdminLogin(req),false);
	}
	
	@Test
	public void testDisiscrizioneFormatoreCorrect() throws ParseException, SQLException {
		
		f.setEmail("test@disiscrizione.it");
		f.setPassword("disiscrizione");
		f.setName("test");
		f.setSurname("disiscrizione");
		f.setGender("M");
		java.sql.Date d = Utils.toDate("10-07-1995");
		f.setBirthDate(d);
		f.setCountry("Italia");
		f.setCodiceFiscale("TSD28072022");
		f.setContoCorrente("ITSDTESNPL28072022");
		
		int id = fDao.doSave(f);
		
		req.getSession().setAttribute("role", "Formatore");
		
		req.getSession().setAttribute("currentId", id);
		
		assertEquals(l.disiscrizionePiattaforma(req),true);
	}
	
	@Test
	public void testDisiscrizioneStudenteCorrect() throws ParseException, SQLException, IOException, ServletException {
		
		s.setEmail("test@disiscrizione.it");
		s.setPassword("disiscrizione");
		s.setName("test studente");
		s.setSurname("disiscrizione");
		s.setGender("M");
		java.sql.Date d = Utils.toDate("10-07-1995");
		s.setBirthDate(d);
		s.setCountry("Italia");
		
		int id = sDao.doSave(s);
		
		req.getSession().setAttribute("role", "Studente");
		
		req.getSession().setAttribute("currentId", id);
		
		req.getSession().setAttribute("validation", "true");
				
		act = l.process("DisiscrizionePiattaformaService",req, res);
		
		assertEquals("/formAct/view/index/index.jsp",act.getPage());
		
		
	}
	
	@Test
	public void testProcessError() throws IOException, ServletException {
		
		req.addParameter("email", "admin@it");
		req.addParameter("password", "ad");
		
		
		act = l.process("Login",req, res);
		
		assertEquals("/formAct/view/autenticazione/Login.jsp", act.getPage());
	}
	
	@Test
	public void testProcessLogin() throws IOException, ServletException {
		
		req.addParameter("email", "admin@admin.it");
		req.addParameter("password", "adminadmin");
		
		
		act = l.process("LoginService",req, res);
		
		assertEquals("/formAct/view/index/index.jsp", act.getPage());
	}
	
	@Test
	public void testProcessLoginError() throws IOException, ServletException {
		
		req.addParameter("email", "admin@it");
		req.addParameter("password", "ad");
		
		
		act = l.process("LoginService",req, res);
		
		assertEquals("/formAct/view/autenticazione/Login.jsp", act.getPage());
	}
	
	@Test
	public void testProcessoLogout() throws IOException, ServletException {
		
		req.addParameter("email", "admin@admin.it");
		req.addParameter("password", "admin");
		req.getSession().setAttribute("validation", "true");
		
		act = l.process("LogoutService", req, res);
		
		assertEquals("/formAct/view/index/index.jsp",act.getPage());
	}
	
	@Test
	public void testProcessoLogoutError() throws IOException, ServletException {
		
		
		req.getSession().setAttribute("validation", "false");
		
		act = l.process("LogoutService", req, res);
		
		assertEquals("/formAct/view/autenticazione/Login.jsp",act.getPage());
	}
	
	
	

	
}
