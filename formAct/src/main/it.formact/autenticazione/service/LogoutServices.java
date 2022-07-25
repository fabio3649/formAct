package autenticazione.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.control.Action;
import controller.control.Service;
import model.dao.FormatoreDao;
import model.dao.StudenteDao;

public class LogoutServices implements Service{

	StudenteDao sDao;
	FormatoreDao fDao;
	Action errorPage = new Action("/formAct/view/messagePages/errorLogout.jsp", true, true);
	Action homePage = new Action("/formAct/view/index/index.jsp", true, true);
	
	
	public LogoutServices() {
		fDao = new FormatoreDao();
		sDao = new StudenteDao();
	}
	
	public boolean canLogout(HttpServletRequest req) {
		HttpSession session= req.getSession();
		
		if((session != null) && (session.getAttribute("Validation")!=null) && (session.getAttribute("currentId")!=null) )
			{
		
				session.setAttribute("Validation", "false");
				session.removeAttribute("role");
				session.removeAttribute("currentId");
			
				return true;
		
		} else
		return false;
	}
	
	@Override
	public Action process(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		
		if(canLogout(req)) {
			return homePage;
		}else
		return errorPage;
		
	}

	@Override
	public Action getErrorAction() {
		// TODO Auto-generated method stub
		// errorPage contiene la pagina di errore che ci dice che un utente non è loggato quindi non può effettuare il Logout
		return null;
	}
	
	

}
