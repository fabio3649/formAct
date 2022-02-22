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
					}
					else {
						request.getSession().setAttribute("updateError", "true");
						response.sendRedirect("/formAct/view/autenticazione/ModificaPassword.jsp");
					}
				}
				
				
				if(request.getSession().getAttribute("role").equals("Studente")) {
					
					
				}
			}
		}catch(Exception e) {
			System.out.println(e);
		}
	}
}