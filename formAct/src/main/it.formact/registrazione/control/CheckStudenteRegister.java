package registrazione.control;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

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
		doPost(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text.html");
		
		//iniziallizzazione dao
		StudenteDao dao = new StudenteDao();
		RegisterServices services = new RegisterServices();
				
		ArrayList<Boolean> risultati = new ArrayList<>();
		
		String newEmail = request.getParameter("email");
		//Controllo se l'email è già presente nella piattaforma
		if(!services.isEmailContent(newEmail)) {
			//Controllo passato
			risultati.add(false); // nessun errore (non presente nel DB)
		}
		else {
			//Controllo NON passato
			risultati.add(true); // errore (presente nel DB)
		}
		
		boolean salvataggio = false;
		// se l'email inserita non è presente del DB (nessun errore)
		// allora salvo lo studente nel DB
		if (risultati.get(0) == false) {
			//Creazione dell'oggetto studente, attributi prelevati dalla request (submit del form) 
			salvataggio = services.executeStudentFormRequest(request);
		}
		
		risultati.add(salvataggio);
		
		String risultatiJSON = new Gson().toJson(risultati);
		response.getWriter().write(risultatiJSON);
		
		response.setStatus(200);
		
		System.out.println(risultatiJSON);
	}
}
