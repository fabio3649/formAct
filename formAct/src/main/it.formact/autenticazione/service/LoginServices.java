package autenticazione.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.dao.FormatoreDao;
import model.dao.StudenteDao;
import model.entity.FormatoreEntity;
import model.entity.StudenteEntity;

public class LoginServices {
	
	public boolean checkTrainerLogin(HttpServletRequest request) throws SQLException {
		
		 FormatoreDao dao = new FormatoreDao();
		List<FormatoreEntity> allFormatori= dao.doRetrieveAll();
		String newEmail= request.getParameter("email");
		String newPassword = request.getParameter("password");
		
		
		if(allFormatori.size() != 0){
			for(FormatoreEntity a : allFormatori) {
				 if((a.getEmail().equals(newEmail)) && (a.getPassword().equals(newPassword))) {
					request.getSession().setAttribute("currentId", a.getId());
					request.getSession().setAttribute("validation", "true");
					request.getSession().setAttribute("role", "Formatore");
					return true;
				 }
			}
		}
		return false;
	}
	
	public FormatoreDao createDaoF() {
		
		return new FormatoreDao();
	}
	
public boolean checkStudentLogin(HttpServletRequest request) throws SQLException {
		
		StudenteDao dao1 = new StudenteDao();
		List<StudenteEntity> allStudenti = dao1.doRetrieveAll();
		String newEmail= request.getParameter("email");
		String newPassword = request.getParameter("password");
		boolean isSubscribed = false;
		
		if(allStudenti.size() != 0){
			for(StudenteEntity a : allStudenti) {
				 if((a.getEmail().equals(newEmail)) && (a.getPassword().equals(newPassword))) {
					request.getSession().setAttribute("currentId", a.getId());
					request.getSession().setAttribute("validation", "true");
					request.getSession().setAttribute("role", "Studente");
					return true;
				}
			}
		}
		
		return false;
	}
}
