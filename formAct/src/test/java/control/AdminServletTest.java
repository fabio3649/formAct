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

import amministrazione.control.AdminServlet;
import percorsoformativo.control.PercorsoFormativoServlet;

public class AdminServletTest extends Mockito{
	
	private AdminServlet control;
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
		control = new AdminServlet();
		res = new MockHttpServletResponse();
	}
	
	@Test
	public void testRedirectAllStudents() throws ServletException, IOException {
		
		String page = "/formAct/view/amministrazione/AllStudentsResult.jsp";
		
		req = new MockHttpServletRequest("POST","/formAct/amministrazione/AdminServlet/GetAllStudentsService") {
			  public RequestDispatcher getRequestDispatcher(String path) {
						return new MockRequestDispatcher(path); } };
						
		control.doPost(req, res);
						
		System.out.println(res.getRedirectedUrl()+ " StudentPage");
						
						
		assertEquals(page, res.getRedirectedUrl());
	}
	
	
	@Test
	public void testRedirectAllTrainers() throws ServletException, IOException {
		
		String page = "/formAct/view/amministrazione/AllTrainersResult.jsp";
		
		req = new MockHttpServletRequest("POST","/formAct/amministrazione/AdminServlet/GetAllTrainersService") {
			  public RequestDispatcher getRequestDispatcher(String path) {
						return new MockRequestDispatcher(path); } };
						
		control.doPost(req, res);
						
		System.out.println(res.getRedirectedUrl()+ " TrainerPage");
						
						
		assertEquals(page, res.getRedirectedUrl());
	}
	
	@Test
	public void testRedirectAllIscrizioniStudent() throws ServletException, IOException {
		
		String page = "/formAct/view/amministrazione/AllIscrizioniResult.jsp";
		
		req = new MockHttpServletRequest("POST","/formAct/amministrazione/AdminServlet/GetAllIscrizioniFromStudentService") {
			  public RequestDispatcher getRequestDispatcher(String path) {
						return new MockRequestDispatcher(path); } };
		req.setParameter("idStudente", "1");	
		
		control.doPost(req, res);
						
		System.out.println(res.getRedirectedUrl()+ " ALL ISCRIZIONI Page");
						
						
		assertEquals(page, res.getRedirectedUrl());
	}

	@Test
	public void testRedirectAllValutazioni() throws ServletException, IOException {
		
		String page = "/formAct/view/amministrazione/AllFeedBacksResult.jsp";
		
		req = new MockHttpServletRequest("POST","/formAct/amministrazione/AdminServlet/GetAllFBFromTrainerService") {
			  public RequestDispatcher getRequestDispatcher(String path) {
						return new MockRequestDispatcher(path); } };
				
		req.setParameter("idFormatore", "1");
		control.doPost(req, res);
						
		System.out.println(res.getRedirectedUrl()+ " ALL valutazioni trainer Page");
						
						
		assertEquals(page, res.getRedirectedUrl());
	}
	
	@Test
	public void testDeleteUser() throws ServletException, IOException {
		
		String page ="/formAct/view/amministrazione/Admin.jsp";
		
		req = new MockHttpServletRequest("POST","/formAct/amministrazione/AdminServlet/DeleteUserService") {
			  public RequestDispatcher getRequestDispatcher(String path) {
						return new MockRequestDispatcher(path); } };
		
		req.setParameter("role", "fr");
		req.setParameter("idUser", "4");
		
		control.doPost(req, res);
		
		System.out.println(res.getRedirectedUrl()+ " delete user Page");
		
		assertEquals(page,res.getRedirectedUrl());
		
		
	}
	
	@Test
	public void testCreateCategory() throws ServletException, IOException {
		
		String page = "/formAct/view/amministrazione/NuovaCategoria.jsp";
		
		req = new MockHttpServletRequest("POST","/formAct/amministrazione/AdminServlet/CreaCategoriaService") {
			  public RequestDispatcher getRequestDispatcher(String path) {
						return new MockRequestDispatcher(path); } };
						
		req.setParameter("nome", "basi di dati");
		req.setParameter("descrizione", "sql, database, linguaggi relazionali");
		req.setParameter("ambitoDisciplinare", "informatica");
		
		control.doPost(req, res);
		
		System.out.println(res.getRedirectedUrl()+ " new category Page");
		
		assertEquals(page,res.getRedirectedUrl());
		
	}
}
