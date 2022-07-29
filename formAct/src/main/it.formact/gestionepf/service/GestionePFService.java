package gestionepf.service;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalTime;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.control.Action;
import controller.control.Service;
import model.dao.CategoriaDao;
import model.dao.DisponibilitaDao;
import model.dao.FormatoreDao;
import model.dao.PercorsoFormativoDao;
import model.entity.DisponibilitaEntity;
import model.entity.FormatoreEntity;
import model.entity.PercorsoFormativoEntity;

public class GestionePFService implements Service{
	
	PercorsoFormativoDao pfDao;
	FormatoreDao fDao;
	DisponibilitaDao daoDisp;
	CategoriaDao daoCat;
	PercorsoFormativoEntity p;
	DisponibilitaEntity  disp;
	
	Action errorPage = new Action("/formAct/view/messagePages/errorCreatePercorso.jsp", true, true);
	Action percorsoPage = new Action("/formAct/view/gestionepf/percorsoView.jsp", true, true);
	Action homePage = new Action("/formAct/view/index/index.jsp",true,true);
	
	public GestionePFService() {
		pfDao = new PercorsoFormativoDao();
		fDao = new FormatoreDao();
		daoDisp = new DisponibilitaDao();
		daoCat = new CategoriaDao();
		p = new PercorsoFormativoEntity();
		disp = new DisponibilitaEntity();
	}


	@Override
	public Action process(String serviceName ,HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		if(serviceName.equalsIgnoreCase("CreatorService")) {
			if(createPfFromReq(req)) {
				return percorsoPage;
			}
			else{
				return errorPage;
			}
		}
		
		if(serviceName.equalsIgnoreCase("DeletePercorsoService")) {
			if(deletePercorso(req))
				return homePage;
			else 
				return errorPage;
		}
		return errorPage;
	}
	
	@Override
	public Action getErrorAction() {
		// TODO Auto-generated method stub
		return errorPage;
	}
	
	//acquisisce i dati dal form, crea l'oggetto PercorsoFormativoEntity e lo salva nel db
	public boolean createPfFromReq(HttpServletRequest req) {
		
		//PercorsoFormativoEntity p riempimento parametri
		p.setNome((String) req.getParameter("nome"));
		p.setCategoria(Integer.parseInt(req.getParameter("categoria")));
		p.setDescrizione((String) req.getParameter("descrizione"));
		p.setId_formatore(Integer.parseInt((String) req.getSession().getAttribute("currentId")));
		p.setIndice_contenuti((String) req.getParameter("indice"));
		p.setNum_lezioni(Integer.parseInt(req.getParameter("lezioni")));
		p.setCosto(Double.parseDouble(req.getParameter("costo")));
		
		try {
			pfDao.doSave(p);
			System.out.println("id percorso appena creato : " + p.getId());
			
			//controlla se il PF è stato inserito
			if(pfDao.doRetrieveByKey(Integer.parseInt((String) req.getSession().getAttribute("currentId")))!=null)
				return true;
			else
				return false;
		}catch (SQLException e2) {
			e2.printStackTrace();
			return false;
		}
	}
	
	
	// recupero i dati di una disponibilità dal form e creo l'oggetto disponibilità entity
	//public DisponibilitaEntity getDisponibilitiesFromReq(HttpServletRequest request, PercorsoFormativoEntity p)  {
			
	//}
	
	public boolean deletePercorso(HttpServletRequest req)  {
		
		int id =  Integer.parseInt(req.getParameter("idPercorso"));
		
			try {
				pfDao.doDelete(id);
				return true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}	
	}
}
