package percorsoformativo.service;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.control.Action;
import controller.control.Service;
import model.dao.DisponibilitaDao;
import model.dao.IscrizioneDao;
import model.dao.StudenteDao;
import model.entity.DisponibilitaEntity;
import model.entity.IscrizioneEntity;
import model.entity.StudenteEntity;

public class IscrizioneServices implements Service{
	
	StudenteDao studenteDao;
	StudenteEntity studente;
	DisponibilitaDao dispDao;
	DisponibilitaEntity disp;
	IscrizioneDao iscrizioneDao;
	IscrizioneEntity iscrizione;
	Action errorPage = new Action("/formAct/view/percorsoformativo/FormIscrizione.jsp", true, true);
	Action homePage = new Action("/formAct/view/percorsoformativo/IndexIscrizionePercorsoFormativo.jsp", true, true);
	
	
	
	public IscrizioneServices() {
		studenteDao = new StudenteDao();
		studente = new StudenteEntity();
		dispDao = new DisponibilitaDao();
		disp = new DisponibilitaEntity();
		iscrizioneDao = new IscrizioneDao();
		iscrizione = new IscrizioneEntity();
	}

	@Override
	public Action process(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		
		if(iscrizione(req))
			return homePage;
		else return errorPage;
			
	}

	@Override
	public Action getErrorAction() {
		// TODO Auto-generated method stub
		return errorPage;
	}
	
	public boolean iscrizione(HttpServletRequest req) {
		
		String giorno = req.getParameter("giorno");
		int idDisponibilita = Integer.parseInt(req.getParameter("idDisponibilita"));
		String societa = req.getParameter("societa");
		
		
		try {
			studente = (StudenteEntity) studenteDao.doRetrieveByKey(2);
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
	
		
		try {
			disp = (DisponibilitaEntity) dispDao.doRetrieveByKey(idDisponibilita);
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try {
			dispDao.doDelete(idDisponibilita);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		iscrizione.setStudente(studente.getId());
		iscrizione.setPercorsoFormativo(disp.getIdPercorso());
		iscrizione.setGiorno(giorno);
		iscrizione.setOrario(disp.getOrario());
		iscrizione.setMetodoPagamento(societa);
		long miliseconds = System.currentTimeMillis();
        Date dataAttuale = new Date(miliseconds);
		iscrizione.setDataPagamento(dataAttuale);
		try {
			iscrizioneDao.doSave(iscrizione);
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return false;
	}
	
	
	

}
