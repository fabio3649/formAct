package autenticazione.control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import model.dao.FormatoreDao;
import model.dao.StudenteDao;
import model.entity.FormatoreEntity;
import model.entity.StudenteEntity;


@WebServlet("/ModificaPasswordServlet")
public class ModificaPasswordServlet extends HttpServlet {
	
	
    public ModificaPasswordServlet() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
        //  request.getSession().setAttribute("role", "Formatore");   
		request.getSession().setAttribute("role", "Studente");   
        
		request.getSession().setAttribute("currentId", 1);     
		
		response.setContentType("text.html");
		try {
			if(request.getSession()!=null && request.getSession().getAttribute("role")!=null) {
				FormatoreDao daoFormatore = new FormatoreDao();
				StudenteDao daoStudente = new StudenteDao();
				
				if(request.getSession().getAttribute("role").equals("Formatore")) {
					
					FormatoreEntity trainer = (FormatoreEntity) daoFormatore.doRetrieveByKey((int)request.getSession().getAttribute("currentId"));
					
					ArrayList<Boolean> risultati = new ArrayList<>();
					
					String passwordAttuale = request.getParameter("passwordAttuale");
					String nuovaPassword = request.getParameter("nuovaPassword");
					// se la password inserita è uguale a quella nel DB
					if( trainer.getPassword().equals(passwordAttuale) ) {
						risultati.add(false);  // nessun errore (password presente nel DB)
					}
					// se la password inserita è diversa da quella presente nel database
					else {
						risultati.add(true);  // errore (password NON presente nel DB)
					}
					
					Boolean modifica = false;
					if (risultati.get(0) == false) {
						
						if (passwordAttuale.equals(nuovaPassword)) {
							response.setStatus(400);
							return;
						}
						modifica = daoFormatore.updatePassword((int)request.getSession().getAttribute("currentId"), nuovaPassword);
						System.err.println("Password del Formatore n."+request.getSession().getAttribute("currentId")+" aggiornata con successo");
					}
					
					risultati.add(modifica);
					
					String risultatiJSON = new Gson().toJson(risultati);
					response.getWriter().write(risultatiJSON);
					
					response.setStatus(200);
					
					System.out.println(risultatiJSON);
				}
				
				
				if(request.getSession().getAttribute("role").equals("Studente")) {
					StudenteEntity student = (StudenteEntity) daoStudente.doRetrieveByKey((int)request.getSession().getAttribute("currentId"));
					
					ArrayList<Boolean> risultati = new ArrayList<>();
					
					String passwordAttuale = request.getParameter("passwordAttuale");
					String nuovaPassword = request.getParameter("nuovaPassword");
					
					// se la password inserita è uguale a quella nel DB
					if(student.getPassword().equals(passwordAttuale)) {
						risultati.add(false);  // nessun errore (password presente nel DB)
					}
					// se la password inserita è diversa da quella presente nel database
					else {
						risultati.add(true);  // errore (password NON presente nel DB)
					}
					
					Boolean modifica = false;
					// se la password inserita è diversa da quella presente nel database
					if (risultati.get(0) == false) {
						// se la nuova password inserita è uguale a quella presente nel database
						if (passwordAttuale.equals(nuovaPassword)) {
							response.setStatus(400);
							return;
						}
						// se la nuova password inserita è diversa da quella presente nel database
						// allora eseguo la modifica
						modifica = daoStudente.updatePassword((int)request.getSession().getAttribute("currentId"), nuovaPassword);
						System.err.println("Password dello Studente n."+request.getSession().getAttribute("currentId")+" aggiornata con successo");
					}
					
					risultati.add(modifica);
					
					String risultatiJSON = new Gson().toJson(risultati);
					response.getWriter().write(risultatiJSON);
					
					response.setStatus(200);
					
					System.out.println(risultatiJSON);
				}
			}
		}catch(Exception e) {
			System.out.println(e);
		}
	}
}