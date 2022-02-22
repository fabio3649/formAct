package registrazione.control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import registrazione.service.*;
import model.dao.StudenteDao;
import registrazione.service.RegisterServices;

@WebServlet("/CheckStudentRegister")
public class CheckStudenteRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CheckStudenteRegister() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		
		//iniziallizzazione dao
		StudenteDao dao = new StudenteDao();
		RegisterServices services = new RegisterServices();
		
		if(request.getParameter("password").equals(request.getParameter("password2"))) {
			
			String newEmail = request.getParameter("email");
			
				//Controllo se l'email è già presente nella piattaforma
				if(!services.isEmailContent(newEmail)) {
					//Controllo passato
					//Creazione dell'oggetto studente, attributi prelevati dalla request (submit del form) 
					services.executeStudentFormRequest(request);
				}
				else {
					request.getSession().setAttribute("emailError", "true");	
					response.sendRedirect("/formAct/view/registrazione/registrazioneStudente.jsp");
				}	
		}
		else {
			request.getSession().setAttribute("pswError", "true");	
			response.sendRedirect("/formAct/view/registrazione/registrazioneStudente.jsp");
		}
	}
}
