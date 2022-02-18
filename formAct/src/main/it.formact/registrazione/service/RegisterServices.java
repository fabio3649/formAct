package registrazione.service;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import javax.servlet.http.*;
import model.dao.*;
import model.entity.FormatoreEntity;
import model.entity.StudenteEntity;

public class RegisterServices {
	
	private FormatoreDao daoFormatore = new FormatoreDao();
	private StudenteDao daoStudente = new StudenteDao();
	
	public boolean executeTrainerFormRequest(HttpServletRequest request){
		
		FormatoreEntity newTrainer = new FormatoreEntity();
		
		try {
			Date date = Date.valueOf(request.getParameter("birthdate"));
			
			newTrainer.setId(daoFormatore.nextId());
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

	public boolean executeStudentFormRequest(HttpServletRequest request){
		
		StudenteEntity newStudent = new StudenteEntity();
		
		try {
			Date date = Date.valueOf(request.getParameter("birthdate"));
			newStudent.setId(daoStudente.nextId());
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
}

		
	

	

