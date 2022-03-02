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
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

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
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    response.setContentType("text.html");
	    	    
		//iniziallizzazione dao
		FormatoreDao dao = new FormatoreDao();
		RegisterServices services = new RegisterServices();
		
		String newCF = request.getParameter("cf");
		
		ArrayList<Boolean> risultati = new ArrayList<>();
		
		//Controllo se il codice fiscale è già presente nella piattaforma
		if(!services.isCfContent(newCF)) {
			//Controllo passato
			risultati.add(false); // nessun errore (non presente nel DB)
		}
		else {
			//Controllo NON passato
			risultati.add(true); // errore (presente nel DB)
		}
		
		String newEmail = request.getParameter("email");
		//Controllo se l'email è già presente nella piattaforma
		if(!services.isEmailContentTrainer(newEmail)) {
			//Controllo passato
			risultati.add(false); // nessun errore (non presente nel DB)
		}
		else {
			//Controllo NON passato
			risultati.add(true); // errore (presente nel DB)
		}
		
		boolean salvataggio = false;
		// se l'email e il codice fiscale inserito 
		// non sono presenti del DB (nessun errore)
		// allora salvo il formatore nel DB
		if (risultati.get(0) == false && risultati.get(1) == false) {
			//Creazione dell'oggetto formatore, attributi prelevati dalla request (submit del form) 
			salvataggio = services.executeTrainerFormRequest(request);
		}
		
		risultati.add(salvataggio);
		
		String risultatiJSON = new Gson().toJson(risultati);
		response.getWriter().write(risultatiJSON);
		
		response.setStatus(200);
		
		System.out.println(risultatiJSON);
	}
}
