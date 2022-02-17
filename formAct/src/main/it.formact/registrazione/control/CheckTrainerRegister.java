package registrazione.control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.entity.FormatoreEntity;
import model.dao.*;
import java.sql.Date;

@WebServlet("/CheckTrainerRegister")
public class CheckTrainerRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public CheckTrainerRegister() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	
		//iniziallizzazione dao
		PercorsoFormativoDao dao = new PercorsoFormativoDao();
		
		try {
			System.out.println(dao.doRetrieveAll());
		} catch (SQLException e) {
			
		}
		//
		/*
		//Check del codice fiscale per controllare se l'utente già è registrato nella piattaforma
		if(request.getParameter("cf")!=null) {
			
			String newCF = request.getParameter("cf");
			
			try{
				List<FormatoreEntity> AllTrainers = dao.doRetrieveAll();
				
				if(AllTrainers.size()!=0){
					for(FormatoreEntity trainer : AllTrainers) {
						if(trainer.getCodiceFiscale().equals(newCF)) {
							
							//Controllo non passato, codice fiscale già esistente nel database
							//Qualcosa
							
						}
					}
				}
			
			}catch(SQLException exception) {
				exception.printStackTrace();
			}
		}
		//Controllo interrotto, parametri non passati correttamente, errore nella jsp
		else
			response.sendRedirect("/formAct/view/registrazione/registratoreFormatore.jsp");
		
		//Controllo passato
		//Creazione dell'oggetto formatore, attributi prelevati dalla request (submit del form) 
		FormatoreEntity newTrainer = new FormatoreEntity();
		
		try {
			Date date = Date.valueOf(request.getParameter("birthdate"));
			
			newTrainer.setId(dao.nextId());
			newTrainer.setEmail(request.getParameter("email"));
			newTrainer.setPassword(request.getParameter("password"));
			newTrainer.setName(request.getParameter("name"));
			newTrainer.setSurname(request.getParameter("surname"));
			newTrainer.setGender(request.getParameter("gender"));
			newTrainer.setBirthDate(date);
			newTrainer.setCountry(request.getParameter("country"));
			newTrainer.setCodiceFiscale(request.getParameter("cf"));
			newTrainer.setContoCorrente(request.getParameter("numCC"));
			
			dao.doSave(newTrainer);
			System.out.println(dao.doRetrieveByKey(newTrainer.getId()));
			
		}catch(SQLException exception) {
				exception.printStackTrace();
		}
		
		*/
	}
}
