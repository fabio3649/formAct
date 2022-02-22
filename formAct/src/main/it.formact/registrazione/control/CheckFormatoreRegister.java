package registrazione.control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.entity.FormatoreEntity;
import model.dao.*;
import registrazione.service.*;


@WebServlet("/CheckTrainerRegister")
public class CheckFormatoreRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public CheckFormatoreRegister() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	
		//iniziallizzazione dao
		FormatoreDao dao = new FormatoreDao();
		RegisterServices services = new RegisterServices();
		
		//Controllo se le due password inseerite coincidono 
		if(request.getParameter("password").equals(request.getParameter("password2"))) {
			
			String newCF = request.getParameter("cf");
			
				//Controllo se il codice fiscale è già presente nella piattaforma
				if(!services.isCfContent(newCF)) {
					//Controllo passato
					//Creazione dell'oggetto formatore, attributi prelevati dalla request (submit del form) 
					services.executeTrainerFormRequest(request);
				}
				else{
					request.getSession().setAttribute("cfError", "true");	
					response.sendRedirect("/formAct/view/registrazione/registrazioneFormatore.jsp");
				}	
		}
		//Se le password non coincidono 
		else {
			request.getSession().setAttribute("pswError", "true");	
			response.sendRedirect("/formAct/view/registrazione/registrazioneFormatore.jsp");
		}
	}
}
