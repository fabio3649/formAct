package autenticazione.control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import autenticazione.service.LoginServices;
import controller.control.AbstractController;
import controller.control.Action;
import controller.control.Service;
import model.dao.FormatoreDao;
import model.dao.StudenteDao;
import model.entity.FormatoreEntity;
import model.entity.StudenteEntity;

/**
 * Controller di gestione della login di un utente
 *
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends AbstractController{

	private String servizio;
	private String uri;
	
	public LoginServlet() {
		super();
		
		//String uri = req.getRequestURI();
	}

	
	@Override
	protected Service newService() {
	
		return new LoginServices();
		
		
	}	
	
	
	
	
	
	
	
	
	


	


	

}
