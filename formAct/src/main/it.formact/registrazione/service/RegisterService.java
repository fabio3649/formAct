package registrazione.service;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import controller.control.Action;
import controller.control.Service;
import model.dao.*;
import model.entity.FormatoreEntity;
import model.entity.StudenteEntity;
import model.utils.Utils;

public class RegisterService implements Service{
	
	private FormatoreDao daoFormatore;
	private StudenteDao daoStudente;
	// sendRedirect
	Action errorPage = new Action("/formAct/view/messagePages/Errori.jsp", true, true);
	Action loginPage = new Action("/formAct/view/autenticazione/Login.jsp", true, true);
	
	public RegisterService() {
		daoFormatore = new FormatoreDao();
		daoStudente = new StudenteDao();
		
	} 
	
	@Override
	public Action process(String serviceName ,HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

		
		// se nella request è presente l'idStudente allora sto registrando un account studente
		
		if(req.getSession().getAttribute("register") != null && req.getSession().getAttribute("register").equals("studente")) {	
		    try {
			    if(executeStudentFormRequest(req)) {
			    	return loginPage;
			    }
			    else {
			    	req.getSession().setAttribute("erroreRegistrazione", "true");
			    	return errorPage;
			    }
			} 
		    catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		if(req.getSession().getAttribute("register") != null && req.getSession().getAttribute("register").equals("formatore")) {	
			try {
				if(executeTrainerFormRequest(req)) {
					return loginPage;
				}
				else {
					req.getSession().setAttribute("erroreRegistrazione", "true");
					return errorPage;
				}
			} 
			catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}  
			
				
		return errorPage;
		
	}
		
		


	@Override
	public Action getErrorAction() {
		// TODO Auto-generated method stub
		return errorPage;
	}
	
	public boolean executeTrainerFormRequest(HttpServletRequest request) throws ParseException{
		
		FormatoreEntity newTrainer = new FormatoreEntity();
		
		try {
			String r = Utils.format(request.getParameter("birthdate"));
			Date date = Utils.toDate(r);
			newTrainer.setEmail(request.getParameter("email"));
			newTrainer.setPassword(request.getParameter("password"));
			newTrainer.setName(request.getParameter("name"));
			newTrainer.setSurname(request.getParameter("surname"));
			newTrainer.setGender(request.getParameter("gender"));
			newTrainer.setBirthDate(date);
			newTrainer.setCountry(request.getParameter("country"));
			newTrainer.setCodiceFiscale(request.getParameter("cf"));
			newTrainer.setContoCorrente(request.getParameter("numCC"));
			daoFormatore.doSave(newTrainer);
			
			if(daoFormatore.doRetrieveByKey(newTrainer.getId())!=null) {
				System.err.println("Trainer id = "+newTrainer.getId()+" added successfull");
				System.err.println((daoFormatore.doRetrieveByKey(newTrainer.getId())));
				return true;
			}
		}catch(SQLException exception) {
				exception.printStackTrace();
		}
		
		return false;
	}

	public boolean executeStudentFormRequest(HttpServletRequest request) throws ParseException{
		
		StudenteEntity newStudent = new StudenteEntity();
	
		
		try {
			String r = Utils.format(request.getParameter("birthdate"));
			Date date = Utils.toDate(r);
			newStudent.setEmail(request.getParameter("email"));
			newStudent.setPassword(request.getParameter("password"));
			newStudent.setName(request.getParameter("name"));
			newStudent.setSurname(request.getParameter("surname"));
			newStudent.setGender(request.getParameter("gender"));
			newStudent.setBirthDate(date);
			newStudent.setCountry(request.getParameter("country"));
			daoStudente.doSave(newStudent);
			
			if(daoStudente.doRetrieveByKey(newStudent.getId())!=null) {
				System.err.println("Student id = "+newStudent.getId()+" added successfull");
				System.err.println((daoStudente.doRetrieveByKey(newStudent.getId())));
				return true;
			}
		}catch(SQLException exception) {
				exception.printStackTrace();
		}
		
		return false;
	}
	
	public boolean isCfContent(String cf){
		
		boolean isContent = false;
		
		try {
			ArrayList<FormatoreEntity> AllTrainers = (ArrayList)daoFormatore.doRetrieveAll();
	
			if(AllTrainers.size()!=0){
			
				for(FormatoreEntity trainer : AllTrainers) {
					if(trainer.getCodiceFiscale().equals(cf)) {
						//Controllo non passato, codice fiscale già esistente nel database
						isContent=true;
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//Valore di ritorno dopo i controlli
		if(isContent==true)
			return true;
		return false;
	}
	
	public boolean isEmailContent(String email){
		
		
		boolean isContent = false;
		
		try {
			ArrayList<StudenteEntity> AllStudents = (ArrayList)daoStudente.doRetrieveAll();
	
			if(AllStudents.size()!=0){
			
				for(StudenteEntity student : AllStudents) {
					if(student.getEmail().equals(email)) {
						//Controllo non passato, email già esistente nel database
						isContent=true;
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//Valore di ritorno dopo i controlli
		if(isContent==true)
			return true;
		return false;
	}
	
	public boolean isEmailContentTrainer(String email){
		
		
		boolean isContent = false;
		
		try {
			ArrayList<FormatoreEntity> AllTrainers = (ArrayList)daoFormatore.doRetrieveAll();
	
			if(AllTrainers.size()!=0){
			
				for(FormatoreEntity trainer : AllTrainers) {
					if(trainer.getEmail().equals(email)) {
						//Controllo non passato, email già esistente nel database
						isContent=true;
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//Valore di ritorno dopo i controlli
		if(isContent==true)
			return true;
		return false;
	}

	
}

		
	

	

