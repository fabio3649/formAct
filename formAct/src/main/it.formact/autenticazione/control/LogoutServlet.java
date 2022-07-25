package autenticazione.control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import autenticazione.service.LoginServices;
import autenticazione.service.LogoutServices;
import controller.control.AbstractController;
import controller.control.Service;


@WebServlet("/LogoutServlet")
public class LogoutServlet extends AbstractController {
	
	public LogoutServlet() {
		super();
	}
			
	@Override
	protected Service newService() {
		// TODO Auto-generated method stub
		return new LogoutServices();
	}	
	
}
