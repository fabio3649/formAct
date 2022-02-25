package autenticazione.control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
				
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		
		try {
			if(request.getSession()!=null && request.getSession().getAttribute("role")!=null) {
			
				FormatoreDao daoFormatore = new FormatoreDao();
				StudenteDao daoStudente = new StudenteDao();
				
				if(request.getSession().getAttribute("role").equals("Formatore")) {
					
					FormatoreEntity trainer = (FormatoreEntity) daoFormatore.doRetrieveByKey((int)request.getSession().getAttribute("currentId"));
					
					if(trainer.getPassword().equals(request.getParameter("password")) && (request.getParameter("nuovaPassword").equals(request.getParameter("confermaPassword")))) {
						daoFormatore.updatePassword((int)request.getSession().getAttribute("currentId"), request.getParameter("nuovaPassword"));
						System.err.println("Password del Formatore n."+request.getSession().getAttribute("currentId")+" aggiornata con successo");
					}
					else {
						request.getSession().setAttribute("updateError", "true");
						response.sendRedirect("/formAct/view/autenticazione/ModificaPassword.jsp");
					}
				}
				
				
				if(request.getSession().getAttribute("role").equals("Studente")) {
					
					StudenteEntity student = (StudenteEntity) daoStudente.doRetrieveByKey((int)request.getSession().getAttribute("currentId"));
					
					if(student.getPassword().equals(request.getParameter("password")) && (request.getParameter("nuovaPassword").equals(request.getParameter("confermaPassword")))) {
						daoStudente.updatePassword((int)request.getSession().getAttribute("currentId"), request.getParameter("nuovaPassword"));
						System.err.println("Password dello Studente n."+request.getSession().getAttribute("currentId")+" aggiornata con successo");
					}
					else {
						request.getSession().setAttribute("updateError", "true");
						response.sendRedirect("/formAct/view/autenticazione/ModificaPassword.jsp");
					}
					
				}
			}
		}catch(Exception e) {
			System.out.println(e);
		}
	}
}