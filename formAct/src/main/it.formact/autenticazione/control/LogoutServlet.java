package autenticazione.control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
	
    public LogoutServlet() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session= request.getSession();
		
		if(session != null) {
			if(session.getAttribute("Validation")!=null)
				session.setAttribute("Validation", "false");
				
			if(session.getAttribute("role")!=null)
				session.removeAttribute("role");
			
			if(session.getAttribute("currentId")!=null)
				session.removeAttribute("currentId");
					
			//response.sendRedirect("/formAct/view/autenticazione/autenticazione.jsp");<------ HOME
		}else {
			
			
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
		
		
	}

}
