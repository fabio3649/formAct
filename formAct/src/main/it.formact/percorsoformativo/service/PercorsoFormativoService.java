package percorsoformativo.service;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.control.Action;
import controller.control.Service;
import model.dao.DisponibilitaDao;
import model.dao.IscrizioneDao;
import model.dao.PercorsoFormativoDao;
import model.dao.StudenteDao;
import model.entity.DisponibilitaEntity;
import model.entity.IscrizioneEntity;
import model.entity.PercorsoFormativoEntity;
import model.entity.StudenteEntity;
import model.utils.Utils;

public class PercorsoFormativoService implements Service{
	
	IscrizioneDao iDao = new IscrizioneDao();
	DisponibilitaDao dDao = new DisponibilitaDao();
	PercorsoFormativoDao pfDao = new PercorsoFormativoDao();
	int idStudente;
	int idPF;
	String giorno;
	String metodoPagamento;
	String orario;
	Date currentDate;
	Action errorPage = new Action("/formAct/view/percorsoformativo/FormIscrizione.jsp", true, true);
	Action homePage = new Action("/formAct/view/percorsoformativo/index.jsp", true, true);
	Action resultRicercaPage = new Action("/formAct/view/percorsoformativo/RicercaPercorsoFormativo.jsp",true,true);
	Action viewPercorsiFormativiPage = new Action("/formAct/view/percorsoformativo/VisualizzaPercorsiFormativi.jsp", true, true);
	
	public PercorsoFormativoService() throws ParseException {
		currentDate = Utils.sysDate();	
	}

	@Override
	public Action process(String serviceName , HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		
		if(serviceName.equalsIgnoreCase("IscrizionePercorsoService")) {
		try {
			if(iscrizione(req)){
				//iscrizione effettuata con successo torna alla home
				return homePage;
			}else {
				//iscrizione fallita
				req.getSession().setAttribute("logError", "true");
				return errorPage;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return errorPage;
	}
		
		if(serviceName.equalsIgnoreCase("DisiscrizionePercorsoService")) {
		
			try {
				if(disiscrizionePF(req)) {
					return homePage;
			}
				else return errorPage;
			}
					catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(serviceName.equalsIgnoreCase("RicercaPFService")) {
			try {
				if(ricercaPercorsoFormativo(req)) {
						return viewPercorsiFormativiPage;
					}
				else return resultRicercaPage;
			} catch (SQLException e) {
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
	
	public boolean iscrizione(HttpServletRequest req) throws SQLException {
		
		//inizializzazioni
		IscrizioneEntity i = new IscrizioneEntity();
		int idDisponibilita = 0; 
		
		//prelievo dati dalla request 
		idStudente = Integer.parseInt((String)req.getSession().getAttribute("currentId"));
		idPF = Integer.parseInt((String)req.getSession().getAttribute("currentPF"));
		metodoPagamento = req.getParameter("metodoPagamento");
		giorno = req.getParameter("giorno");
		orario = req.getParameter(orario);
		
		//Riempimento Entity IscrizioneEntity
		i.setDataPagamento(currentDate);
		i.setGiorno(giorno);
		i.setMetodoPagamento(metodoPagamento); 
		i.setOrario(orario);
		i.setPercorsoFormativo(idPF);
		i.setStudente(idStudente);
		
		//salvataggio nel db della entity
		iDao.doSave(i);
		
		//aggiornamento disponibilità
		ArrayList<DisponibilitaEntity> allDisp = dDao.doRetrieveByGiornoAndPercorsoFormativo(giorno, idPF);
		for(DisponibilitaEntity dis : allDisp) {
			if(dis.getOrario().equals(orario)) {
				idDisponibilita = dis.getIdDisp();
			}
		}
		dDao.updateStatus(idDisponibilita, 0);
		
		return true;
	}
	
	public boolean disiscrizionePF(HttpServletRequest req) throws SQLException {
		
		//prelievo id dello studente e id del PF dalla sessione
		int idStudente = Integer.parseInt((String)req.getSession().getAttribute("currentId"));
		int idPF = (int) req.getSession().getAttribute("idPF");
		
		IscrizioneDao iDao = new IscrizioneDao();
		
		iDao.doDelete(idStudente, idPF);	
		return true;
	}
	
	public boolean ricercaPercorsoFormativo (HttpServletRequest req) throws SQLException {
		// parametri della ricerca di percorsi formativi
		String argomento = (String) req.getParameter("argomento");
		String costoMin = (String) req.getParameter("costoMin");
		String costoMax = (String) req.getParameter("costoMax");
		String disponibilita = (String) req.getParameter("disponibilita");
		
		ArrayList<PercorsoFormativoEntity> percorsiFormativi = pfDao.doRetrieveAllByParams(argomento, costoMin, costoMax, disponibilita);
		req.getSession().setAttribute("percorsiFormativi", percorsiFormativi);
		
		return true;
	}
	
	
}