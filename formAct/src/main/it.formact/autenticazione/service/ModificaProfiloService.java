package autenticazione.service;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.control.Action;
import controller.control.Service;
import model.dao.FormatoreDao;
import model.dao.InteresseDao;
import model.dao.InteresseStudenteDao;
import model.dao.StudenteDao;
import model.entity.FormatoreEntity;
import model.entity.InteresseEntity;
import model.entity.InteresseStudenteEntity;
import model.entity.StudenteEntity;
import model.utils.Utils;


/*
 Service del control che permette:
  	- Modificare l'anagrafica utente
  	- Modificare la password account
  	- Modificare gli interessi di un utente studente
 * */
 
public class ModificaProfiloService implements Service{

		// definizione dei dao e delle due possibili Action
		StudenteDao sDao ;
		FormatoreDao fDao;
		InteresseDao iDao;
		InteresseStudenteDao isDao;
		Action errorPage = new Action("/formAct/view/errorPages/errorEditProfile.jsp", true, true);
		Action homePage = new Action("/formAct/view/index/index.jsp",true,true);
	
		public ModificaProfiloService() {
			sDao = new StudenteDao();
			fDao = new FormatoreDao();
			iDao = new InteresseDao();
			isDao = new InteresseStudenteDao();
		} 
		@Override
		public Action process(String serviceName, HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
			// TODO Auto-generated method stub
			if(serviceName.equalsIgnoreCase("ModificaProfiloService")) {
				// chiamata metodo modificaProfilo
				try {
					modificaProfilo(req);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			return homePage;
			} 
			
			if(serviceName.equalsIgnoreCase("ModificaPasswordService")) {
				// chiamata metodo modificaPassword
				try {
					modificaPassword(req);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return homePage;
			}
			
			if(serviceName.equalsIgnoreCase("ModificaInteressiService")) {
				// chiamata metodo modificaInteressi
				try {
					modificaInteressi(req);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return homePage;
			}
			
			 
			return errorPage;
		}

		@Override
		public Action getErrorAction() {
			// TODO Auto-generated method stub
			return errorPage;
		}
		
	public boolean modificaProfilo(HttpServletRequest req) throws ParseException, SQLException{
		
		
		//controllo presenza dati nella sessione
		if(req.getSession()!=null && req.getSession().getAttribute("role")!=null && req.getSession().getAttribute("currentId")!=null) {
			
			
			
			//prelievo dati dalla sessione
			int id = (int)req.getSession().getAttribute("currentId");
			String role = (String)req.getSession().getAttribute("role");
			
			//caso in cui l'uente è un Formatore
			if(role.equals("Formatore")) {
				String email = req.getParameter("email");
				String country = req.getParameter("country");
				String cc = req.getParameter("cc");
				
				if(fDao.updateTrainer(id, email, country, cc))
					return true;
			}
			//caso in cui l'uente è uno Studente
			if(role.equals("Studente")) {
				String email = req.getParameter("email");
				String country = req.getParameter("country");
				
				if(sDao.updateStudent(id, email, country))
					return true;
			}
		}
		return false;
	}
	// non funziona
	public boolean modificaInteressi(HttpServletRequest req) throws ParseException{
		 
		//Inizializzazione variabili
		
		int currentId= (int) req.getSession().getAttribute("currentId");
		
		try {
			
			//creazione array contenente tutte le tipologie di interessi
			ArrayList<InteresseEntity> allInteressi = iDao.doRetrieveAll();
			
			//ciclo su ogni interesse presente nel db
			for(InteresseEntity interesse: allInteressi){
				
				//se nel form sottomesso l'interesse i-esimo non è selezionato
				if(req.getParameter(interesse.getNome())==null) {
					
					//se precedentemente alla compilazione del form l'utente aveva selezionato linteresse i-esimo
					if(isDao.isContent(interesse.getIdInteresse(), currentId) == true) {
						isDao.doDelete(currentId, interesse.getIdInteresse());
					}
				
				//se nel form sottomesso l'interesse i-esimo è selezionato
				}else{
					
					//se precedentemente alla compilazione del form l'utente non aveva selezionato linteresse i-esimo
					if(isDao.isContent(interesse.getIdInteresse(), currentId)== false) {
						InteresseStudenteEntity is = new InteresseStudenteEntity();
						is.setInteresse(interesse.getIdInteresse());
						is.setStudente(currentId);
						isDao.doSave(is);
					}
				}
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return true;
	}
	
	public boolean modificaPassword(HttpServletRequest req) throws ParseException {
		
		
		FormatoreEntity trainer = new FormatoreEntity();
		StudenteEntity student = new StudenteEntity();
		
		
		/* Modifica Password */
		try {
			
			//controllo presenza dati nella sessione
			if(req.getSession()!=null && req.getSession().getAttribute("role")!=null && req.getSession().getAttribute("currentId")!=null) {
				
				//prelievo dati dalla sessione
				int id = (int)req.getSession().getAttribute("currentId");
				String role = (String)req.getSession().getAttribute("role");
				
				//caso in cui l'utente corrente è un Formatore
				if(role.equals("Formatore")) {
					
					trainer = (FormatoreEntity) fDao.doRetrieveByKey(id);				
					
					String passwordAttuale = req.getParameter("passwordAttuale");
					String nuovaPassword = req.getParameter("nuovaPassword");
					String confermaPassword = req.getParameter("confermaPassword");
					
					// se la password attuale inserita è uguale a quella memorizzata nel DB
					if( trainer.getPassword().equals(passwordAttuale) ) {
						// nessun errore (password presente nel DB)
						if(nuovaPassword.equals(confermaPassword)) {
							fDao.updatePassword(id, nuovaPassword);
							return true;
						}
					}
				}
				
				
				//caso in cui l'utente corrente è uno Studente
				if(role.equals("Studente")) {
					
					student = (StudenteEntity) sDao.doRetrieveByKey(id);				
					
					String passwordAttuale = req.getParameter("passwordAttuale");
					String nuovaPassword = req.getParameter("nuovaPassword");
					String confermaPassword = req.getParameter("confermaPassword");
					
					// se la password attuale inserita è uguale a quella memorizzata nel DB
					if( student.getPassword().equals(passwordAttuale) ) {
						// nessun errore (password presente nel DB)
						if(nuovaPassword.equals(confermaPassword)) {
							sDao.updatePassword(id, nuovaPassword);
							return true;
						}
					}
				}
				
			}
		}catch(Exception e) {
			System.out.println(e);
		}
		
		return false;
	}

	
	
	
	
	

}
