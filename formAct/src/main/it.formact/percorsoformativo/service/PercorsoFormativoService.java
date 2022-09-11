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
	Action errorPage = new Action("/formAct/view/percorsoformativo/Iscrizione.jsp", true, true);
	//sendRedirect
	Action homePage = new Action("/formAct/view/index/index.jsp", true, true);
	Action resultRicercaPage = new Action("/formAct/view/percorsoformativo/RicercaPercorsoFormativo.jsp",true,true);
	Action viewPercorsiFormativiPage = new Action("/formAct/view/percorsoformativo/VisualizzaPercorsiFormativi.jsp", true, true);
	
	public PercorsoFormativoService() throws ParseException {
		currentDate = Utils.sysDate();	
		
	}

	@Override
	public Action process(String serviceName , HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		
		if(serviceName.equalsIgnoreCase("IscrizionePFService")) {
		try {
			if(iscrizione(req)){
				//iscrizione effettuata con successo torna alla home
				return homePage;
			}else {
				//iscrizione fallita
				//req.getSession().setAttribute("logError", "true");
				return errorPage;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
		
		if(serviceName.equalsIgnoreCase("DisiscrizionePFService")) {
		
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
		DisponibilitaDao dao = new DisponibilitaDao();
		//prelievo dati dalla request 
		idStudente = (Integer ) req.getSession().getAttribute("currentId");
		idPF = Integer.parseInt(req.getParameter("idPercorso"));
		metodoPagamento = req.getParameter("metodoPagamento");
		int k = Integer.parseInt(req.getParameter("iterator"));
		
		for(int j=0;j<k;j++) {
			String isSelected = req.getParameter("giorno"+j+"IsSelected");
			if (isSelected.equalsIgnoreCase("true")) {
				DisponibilitaEntity d = (DisponibilitaEntity) dao.doRetrieveByKey(Integer.parseInt(req.getParameter("giorno"+j)));
				i.setDataPagamento(currentDate); 
				i.setGiorno(d.getGiornoSettimana());
				i.setMetodoPagamento(metodoPagamento); 
				i.setOrario(d.getOrario());
				i.setPercorsoFormativo(idPF);
				i.setStudente(idStudente);
				iDao.doSave(i);				
				dDao.updateStatus(d.getIdDisp(), 0);
			}
		}
		/*
		for(int j=0;j<k;j++) {
			DisponibilitaEntity d = (DisponibilitaEntity) dao.doRetrieveByKey(Integer.parseInt(req.getParameter("giorno"+j)));
			i.setDataPagamento(currentDate); 
			i.setGiorno(d.getGiornoSettimana());
			i.setMetodoPagamento(metodoPagamento); 
			i.setOrario(d.getOrario());
			i.setPercorsoFormativo(idPF);
			i.setStudente(idStudente);
			iDao.doSave(i);
			dDao.updateStatus(d.getIdDisp(), 0);
		}
		//controllo stato di ogni disponibilità
		ArrayList<DisponibilitaEntity> disp = dao.doRetrieveAllByPercorso(idPF);
		PercorsoFormativoEntity p = (PercorsoFormativoEntity) pfDao.doRetrieveByKey(idPF);
		int x = 0;
		int n = 0;
		while(x< disp.size()) 
		{
			if(disp.get(n).getStato()==0)
				n++;
			x++;	
		}
		if(n == disp.size())
			pfDao.validation(0,idPF);
			
		*/
		return true;
		
		
	}
	
	public boolean disiscrizionePF(HttpServletRequest req) throws SQLException {
		
		//prelievo id dello studente e id del PF
		int idStudente = (int) (req.getSession().getAttribute("currentId")); 
		int idPF = Integer.parseInt(req.getParameter("currentPF"));
		
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
		
		removeDuplicates(percorsiFormativi);
		
		req.getSession().setAttribute("percorsiFormativi", percorsiFormativi);
		
		return true;
	}
	
	private ArrayList<PercorsoFormativoEntity> removeDuplicates(ArrayList<PercorsoFormativoEntity> list) {
		
		
		int i = 0;
		int j = 0;
		for (; i < list.size() - 1; i++) {
		    j = i+1;
		    if (list.get(i).getId() == list.get(j).getId()) {
		          list.remove(i);
		          i = 0;
		      }
		}
	
	return list;
	}
}