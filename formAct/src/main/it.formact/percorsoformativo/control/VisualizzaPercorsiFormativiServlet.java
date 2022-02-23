package percorsoformativo.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.PercorsoFormativoDao;
import model.entity.PercorsoFormativoEntity;

/**
 * Servlet implementation class VisualizzaPercorsiFormativiServlet
 */
@WebServlet("/VisualizzaPercorsiFormativiServlet")
public class VisualizzaPercorsiFormativiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VisualizzaPercorsiFormativiServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
	    response.setContentType("text.html");
	    PrintWriter out = response.getWriter();
	    
	    String action = request.getParameter("action");
	    
	    try {
	    	if (action != null) {
	    		if (action.equalsIgnoreCase("visualizzaPercorsiFormativi")) {
	    			PercorsoFormativoDao percorsoFormativoDao = new PercorsoFormativoDao();
	    			ArrayList<PercorsoFormativoEntity> percorsiFormativi = percorsoFormativoDao.doRetrieveAll();
	    			session.setAttribute("percorsiFormativi",percorsiFormativi);
	    			response.sendRedirect("/formAct/view/percorsoformativo/RisultatiVisualizzaPercorsiFormativi.jsp");
	    		}
	    	}
	    }
	    catch (SQLException e) {
	    	System.out.println("Error:" + e.getMessage());
	    }
	}

}
