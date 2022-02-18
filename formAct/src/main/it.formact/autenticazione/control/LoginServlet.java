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
import model.dao.FormatoreDao;
import model.dao.StudenteDao;
import model.entity.FormatoreEntity;
import model.entity.StudenteEntity;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet{

	public LoginServlet() {
		super();
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
		
		FormatoreDao dao = new FormatoreDao();
		StudenteDao dao1 = new StudenteDao();
		String newEmail= request.getParameter("email");
		String newPassword = request.getParameter("password");
			
			try{
				LoginServices services= new LoginServices();
				if(services.checkTrainerLogin(request) || services.checkStudentLogin(request)){
					//login effettuato con successo torna alla home
					
				}else {
					//login fallito
					
					request.getSession().setAttribute("logError", "true");
					response.sendRedirect("/formAct/view/autenticazione/autenticazione.jsp");
				}
			}catch(SQLException e) {
				
			}
	}	
}
