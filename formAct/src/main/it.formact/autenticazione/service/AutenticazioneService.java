package autenticazione.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.dao.FormatoreDao;
import model.dao.InteresseStudenteDao;
import model.dao.PreferenzaStudenteDao;
import model.dao.StudenteDao;
import model.entity.FormatoreEntity;
import model.entity.PreferenzaStudenteEntity;
import model.entity.StudenteEntity;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.control.Action;
import controller.control.Service;
import model.dao.FormatoreDao;
import model.dao.StudenteDao;
import model.entity.FormatoreEntity;
import model.entity.StudenteEntity;

/**
 * Service del Controller per la gestione della Login e Logout
 *
 */
public class AutenticazioneService implements Service{
	StudenteDao sDao;
	FormatoreDao fDao;
	Action loginPage = new Action("/formAct/view/autenticazione/Login.jsp", true, true);
	Action homePage = new Action("/formAct/view/index/index.jsp", true , true ); // redirect funziona a dovere, forward NO
	 // il forward ( 2 parametro , false ), reindirizza al LoginService e chiama il controller su diversi file nel documento jsp.
	/** 
	 * Costruttore di default
	 */ 
	public AutenticazioneService() {
		fDao = new FormatoreDao();
		sDao = new StudenteDao();
		
	}
	
	@Override
	public Action process(String serviceName, HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		// TODO Auto-generated method stub
		if(serviceName.equalsIgnoreCase("LoginService")) {
			try {
				if(checkAdminLogin(req) || checkTrainerLogin(req) || checkStudentLogin(req) ){
					//login effettuato con successo torna alla home
					req.getSession().setAttribute("logError", "false");
					return homePage;
				}else {
					//login fallito
					req.getSession().setAttribute("logError", "true");
					return loginPage;
				}
			}catch(SQLException e) {
				throw new ServletException(e);
			}
			
		}
		
		
		if(serviceName.equalsIgnoreCase("LogoutService")) {
			//chiamata metodo CanLogOut
			if(canLogout(req))
			return homePage;
			else  return loginPage;
		}
		
		if(serviceName.equalsIgnoreCase("DisiscrizionePiattaformaService")) {
            if(disiscrizionePiattaforma(req) && canLogout(req)) {
            	return homePage;
            }
            else {
            	return loginPage;
            }
        }
    
		
		return loginPage;
	}	
	
	
	@Override
	public Action getErrorAction() {
		// TODO Auto-generated method stub
		return loginPage;
	}
	
	public final boolean checkTrainerLogin(HttpServletRequest request) throws SQLException {

		List<FormatoreEntity> allFormatori= fDao.doRetrieveAll();
		String newEmail= request.getParameter("email");
		String newPassword = request.getParameter("password");
		
		
		if(allFormatori.size() != 0){
			for(FormatoreEntity a : allFormatori) {
				 if((a.getEmail().equals(newEmail)) && (a.getPassword().equals(newPassword))) {
					request.getSession().setAttribute("currentId", a.getId());
					request.getSession().setAttribute("validation", "true");
					request.getSession().setAttribute("role", "Formatore");
					request.getSession().setAttribute("nomeUtente", a.getName());
					request.getSession().setAttribute("utente", a);
					return true;
				 }
			}
		}
		return false;
	}

	
	public final boolean checkStudentLogin(HttpServletRequest request) throws SQLException {

		List<StudenteEntity> allStudenti = sDao.doRetrieveAll();
		String newEmail= request.getParameter("email");
		String newPassword = request.getParameter("password");
		boolean isSubscribed = false;
		
		if(allStudenti.size() != 0){
			for(StudenteEntity a : allStudenti) {
				 if((a.getEmail().equals(newEmail)) && (a.getPassword().equals(newPassword))) {
					request.getSession().setAttribute("currentId", a.getId());
					request.getSession().setAttribute("validation", "true");
					request.getSession().setAttribute("role", "Studente");
					request.getSession().setAttribute("nomeUtente", a.getName());
					request.getSession().setAttribute("utente", a);
					InteresseStudenteDao isDao = new InteresseStudenteDao();
					ArrayList<String> interessiStudente = isDao.doRetrieveInteressiStudente(a.getId());
					request.getSession().setAttribute("interessiStudente", interessiStudente);
					PreferenzaStudenteDao psDao = new PreferenzaStudenteDao();
					ArrayList<PreferenzaStudenteEntity> giorniLiberiStudente = (ArrayList<PreferenzaStudenteEntity>) psDao.doRetrieveAllByStudent(a.getId()); 
					request.getSession().setAttribute("giorniLiberiStudente", giorniLiberiStudente);
					
					return true;
				}
			}
		}	
		return false;
	}
	
	public boolean canLogout(HttpServletRequest req) {
		HttpSession session= req.getSession();
		
		if( session.getAttribute("validation").equals("true"))
		{
		
				session.setAttribute("validation", "false");
				session.removeAttribute("role");
				session.removeAttribute("currentId");
				session.removeAttribute("logError");
				
				session.invalidate();
				return true;
		
		}else
			
			return false;
	}
	
	public final boolean checkAdminLogin(HttpServletRequest request) throws SQLException {
		
		String email = (String) request.getParameter("email");
		String password = (String) request.getParameter("password");
		
		if (email.equals("admin@admin.it") && password.equals("adminadmin")) {
			request.getSession().setAttribute("validation", "true");
			request.getSession().setAttribute("role", "Amministratore");
			return true;
		}
		return false;
		
	}
	
	public boolean disiscrizionePiattaforma(HttpServletRequest req) {
		String ruolo = (String) req.getSession().getAttribute("role");
		if (ruolo != null) {
			Integer currentId = (Integer) req.getSession().getAttribute("currentId");
			if (ruolo.equalsIgnoreCase("Studente")) {
			    try {
					sDao.doDelete(currentId);
					return true;
				} catch (SQLException e) {
					e.printStackTrace();
					return false;
				}
			    
			}
			else if (ruolo.equalsIgnoreCase("Formatore")) {
			    try {
					fDao.doDelete(currentId);
					return true;
				} catch (SQLException e) {
					e.printStackTrace();
					return false;
				}
			}
		}
		return false;
	}
}
