package percorsoformativo.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import model.dao.Disponibilit‡Dao;
import model.dao.FormatoreDao;
import model.dao.IscrizioneDao;
import model.dao.PercorsoFormativoDao;
import model.dao.StudenteDao;
import model.entity.Disponibilit‡Entity;
import model.entity.FormatoreEntity;
import model.entity.IscrizioneEntity;
import model.entity.PercorsoFormativoEntity;
import model.entity.StudenteEntity;

/**
 * Servlet implementation class IscrizionePercorsoFormativoServlet
 */
@WebServlet("/IscrizionePercorsoFormativoServlet")
public class IscrizionePercorsoFormativoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IscrizionePercorsoFormativoServlet() {
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
	    
	    PrintWriter out = response.getWriter();
	    
	    String action = request.getParameter("action");
	    
	    try {
	    	if (action != null) {
	    		if (action.equalsIgnoreCase("visualizzaIscrizioni")) {
	    			response.setContentType("text.html");
	    			int idStudente = Integer.parseInt(request.getParameter("idStudente")); 
	    			PercorsoFormativoDao percorsoFormativoDao = new PercorsoFormativoDao();
	    			ArrayList<PercorsoFormativoEntity> percorsiFormativi = percorsoFormativoDao.doRetrieveIscrizioniStudente(idStudente);
	    			session.setAttribute("percorsiFormativi",percorsiFormativi);
	    			response.sendRedirect("/formAct/view/percorsoformativo/IscrizioniStudente.jsp");
	    		}
	    		else if (action.equalsIgnoreCase("getFormIscrizione")) {
	    			response.setContentType("text.html");
	    			int idPercorsoFormativo = Integer.parseInt(request.getParameter("idPercorsoFormativo"));
	    			PercorsoFormativoDao percorsoFormativoDao = new PercorsoFormativoDao();
	    			PercorsoFormativoEntity percorsoFormativo = (PercorsoFormativoEntity) percorsoFormativoDao.doRetrieveByKey(idPercorsoFormativo);
	    			session.setAttribute("percorsoFormativo", percorsoFormativo);
	    			FormatoreDao formatoreDao = new FormatoreDao();
	    			FormatoreEntity formatore = (FormatoreEntity) formatoreDao.doRetrieveByKey(percorsoFormativo.getId_formatore());
	    			session.setAttribute("formatore", formatore);
	    			Disponibilit‡Dao disponibilitaDao = new Disponibilit‡Dao();
	    			ArrayList<Disponibilit‡Entity> aLDisponibilita = disponibilitaDao.doRetrieveAllByPercorso(idPercorsoFormativo);
	    			session.setAttribute("aLDisponibilita", aLDisponibilita);
	    			response.sendRedirect("/formAct/view/percorsoformativo/FormIscrizione.jsp");
	    		}
	    		else if (action.equalsIgnoreCase("ottieniOrariGiorno")) {
	    			response.setContentType("text.html");
	    			String giorno = request.getParameter("giorno");
	    			int idPercorsoFormativo = Integer.parseInt(request.getParameter("idPercorsoFormativo"));
	    			Disponibilit‡Dao disponibilitaDao = new Disponibilit‡Dao();
	    			ArrayList<Disponibilit‡Entity> aLDisponibilita = disponibilitaDao.doRetrieveByGiornoAndPercorsoFormativo(giorno, idPercorsoFormativo);
	    			ArrayList<String> orari = new ArrayList<String>();
	    			for (Disponibilit‡Entity disponibilita : aLDisponibilita) {
	    				String idDisponibilita = Integer.toString(disponibilita.getIdDisp());
	    				String orarioDisponibilita = disponibilita.getOrario().toString();
	    				orari.add(idDisponibilita);
	    				orari.add(orarioDisponibilita);
	    			}			
	    			String orariJSON = new Gson().toJson(orari);
	    			response.getWriter().write(orariJSON);
	    		}
	    		else if (action.equalsIgnoreCase("iscrizionePercorsoFormativo")) {
	    			String giorno = request.getParameter("giorno");
	    			int idDisponibilita = Integer.parseInt(request.getParameter("idDisponibilita"));
	    			String societa = request.getParameter("societa");
	    			
	    			StudenteDao studenteDao = new StudenteDao();
	    			StudenteEntity studente = (StudenteEntity) studenteDao.doRetrieveByKey(2);
	    			Disponibilit‡Dao disponibilitaDao = new Disponibilit‡Dao();
	    			Disponibilit‡Entity disponibilita = (Disponibilit‡Entity) disponibilitaDao.doRetrieveByKey(idDisponibilita);
	    			disponibilitaDao.doDelete(idDisponibilita);
	    			IscrizioneDao iscrizioneDao = new IscrizioneDao();
	    			IscrizioneEntity iscrizione = new IscrizioneEntity();
	    			iscrizione.setStudente(studente.getId());
	    			iscrizione.setPercorsoFormativo(disponibilita.getIdPercorso());
	    			iscrizione.setGiorno(giorno);
	    			iscrizione.setOrario(disponibilita.getOrario());
	    			iscrizione.setMetodoPagamento(societa);
	    			long miliseconds = System.currentTimeMillis();
	    	        Date dataAttuale = new Date(miliseconds);
	    			iscrizione.setDataPagamento(dataAttuale);
	    			iscrizioneDao.doSave(iscrizione);
	    			response.setStatus(200);
	    		}
	    	}
	    }
	    catch (SQLException e) {
	    	System.out.println("Error:" + e.getMessage());
	    }
	}

}
