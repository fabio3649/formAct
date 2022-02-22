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

import model.dao.IscrizioneDao;
import model.dao.PercorsoFormativoDao;
import model.dao.StudenteDao;
import model.entity.PercorsoFormativoEntity;
import model.entity.StudenteEntity;

/**
 * Servlet implementation class VisualizzaIscrizioniServlet
 */
@WebServlet("/DisiscrizionePercorsoFormativoServlet")
public class DisiscrizionePercorsoFormativoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisiscrizionePercorsoFormativoServlet() {
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
	    		if (action.equalsIgnoreCase("visualizzaIscrizioni")) {
	    			int idStudente = Integer.parseInt(request.getParameter("idStudente")); 
	    			PercorsoFormativoDao percorsoFormativoDao = new PercorsoFormativoDao();
	    			ArrayList<PercorsoFormativoEntity> percorsiFormativi = percorsoFormativoDao.doRetrieveIscrizioniStudente(idStudente);
	    			session.setAttribute("percorsiFormativi",percorsiFormativi);
	    			response.sendRedirect("/formAct/view/percorsoformativo/IscrizioniStudente.jsp");
	    		}
	    		else if (action.equalsIgnoreCase("disiscrizionePercorsoFormativo")) {
	    			StudenteDao studenteDao = new StudenteDao();
	    			StudenteEntity studente = (StudenteEntity) studenteDao.doRetrieveByKey(1);
	    			int idPercorsoFormativo = Integer.parseInt(request.getParameter("idPercorsoFormativo"));
	    			int idStudente= studente.getId();
	    			System.out.println(idPercorsoFormativo);
	    			System.out.println(idStudente);
	    			IscrizioneDao iscrizioneDao = new IscrizioneDao();
	    			iscrizioneDao.doDelete(idStudente, idPercorsoFormativo);
	    			PercorsoFormativoDao percorsoFormativoDao = new PercorsoFormativoDao();
	    			ArrayList<PercorsoFormativoEntity> percorsiFormativi = percorsoFormativoDao.doRetrieveIscrizioniStudente(idStudente);
	    			session.setAttribute("percorsiFormativi",percorsiFormativi);
	    			response.sendRedirect("/formAct/view/percorsoformativo/IscrizioniStudente.jsp");
	    		}
			}
    	}
	    catch (SQLException e) {
	    	System.out.println("Error:" + e.getMessage());
	    }
	}

}
