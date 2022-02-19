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
 * Servlet implementation class PercorsoFormativoServlet
 */
@WebServlet("/RicercaPercorsoFormativoServlet")
public class RicercaPercorsoFormativoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RicercaPercorsoFormativoServlet() {
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
	    		if (action.equalsIgnoreCase("ricercaPercorsoFormativo")) {
	    			String argomento = request.getParameter("argomento");
	    			PercorsoFormativoDao percorsoFormativoDao = new PercorsoFormativoDao();
	    			ArrayList<PercorsoFormativoEntity> percorsiFormativi = percorsoFormativoDao.doRetrieveByString(argomento);
	    			session.setAttribute("percorsiFormativi",percorsiFormativi);
	    			response.sendRedirect("/formAct/view/percorsoformativo/RisultatiRicercaProva.jsp");
	    		}
	    		else if (action.equalsIgnoreCase("ricercaPercorsoFormativoFiltri")) {
	    			String argomento = request.getParameter("argomento");
	    			String costoMin = request.getParameter("costoMin");
	    			String costoMax = request.getParameter("costoMax");
	    			String disponibilita = request.getParameter("disponibilita");
	    			System.out.println("argomento = " + argomento);
	    			System.out.println("costoMin = " + costoMin);
	    			System.out.println("costoMax = " + costoMax);
	    			System.out.println("disponibilita = " + disponibilita);
	    			
	    			PercorsoFormativoDao percorsoFormativoDao = new PercorsoFormativoDao();
	    			ArrayList<PercorsoFormativoEntity> percorsiFormativi = percorsoFormativoDao.doRetrieveAllByParams(argomento, costoMin, costoMax, disponibilita);
	    			session.setAttribute("percorsiFormativi",percorsiFormativi);
	    			response.sendRedirect("/formAct/view/percorsoformativo/RisultatiRicercaProva.jsp");
	    		}
	    	}
	    }
	    catch (SQLException e) {
	    	System.out.println("Error:" + e.getMessage());
	    }
	}
}








