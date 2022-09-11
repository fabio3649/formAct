package gestionepf.service;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;

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
	
	//utilizzo sendRedirect
	Action errorPage = new Action("/formAct/view/messagePages/errorCreatePercorso.jsp", true, true); 
	Action percorsoPage = new Action("/formAct/view/gestionepf/percorsoView.jsp", true , true); 
	Action homePage = new Action("/formAct/view/index/index.jsp",true ,true);
	Action pfCreatoPage = new Action("/formAct/view/gestionepf/PercorsoCreato.jsp",true ,true);
	
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
		
			if(creatorPercorso(req)) {
		
			//System.out.println("Inserimento percorso effettuato con successo id: "+ p.getId() +  "\n");
			req.getSession().setAttribute("idPercorso", p.getId());
			return pfCreatoPage;
		}
			else 
				return errorPage;
			
		}
		 
		if(serviceName.equalsIgnoreCase("DeletePercorsoService")) {
			if(deletePercorso(req)) {
				return homePage;
			
				} else return errorPage;
		}
		
		
		return errorPage;
		
	}
	

	@Override
	public Action getErrorAction() {
		// TODO Auto-generated method stub
		return errorPage;
	}
	
	
	// acquisisce i dati dal form e crea l'oggetto percorso formativo entity
	public boolean creatorPercorso(HttpServletRequest request) {
		
		//PercorsoFormativoEntity p riempimento parametri
		p.setNome((String) request.getParameter("nome"));
		p.setCategoria(Integer.parseInt(request.getParameter("categoria")));
		p.setDescrizione((String) request.getParameter("descrizione"));
		p.setId_formatore((Integer) request.getSession().getAttribute("currentId"));
		p.setIndice_contenuti((String) request.getParameter("indice"));
		p.setNum_lezioni(Integer.parseInt(request.getParameter("lezioni")));
		p.setCosto(Double.parseDouble(request.getParameter("costo")));
		try {
			int idPF = pfDao.doSave(p);
			int i = 0;
			boolean control = true; 
			String giorno;
			String orario;
			while(control) {
				
				giorno = "giorno" + i;
				orario = "orario" + i;
				System.out.println(giorno);
				System.out.println(orario);
				
				if(request.getParameter(giorno) != null && request.getParameter(orario) != null) {
					disp.setGiornoSettimana((String) request.getParameter(giorno));
					disp.setOrario((String) request.getParameter(orario));
					disp.setStato(1);
					disp.setIdPercorso(idPF);
					
					daoDisp.doSave(disp);
				}
				else {
					control = false;
				}
						
				i++;
				
			}
					
			System.out.println("id percorso appena creato : " + p.getId());
			
			System.out.println("disp creata per percorso : " + p.getId());
			//controlla se il PF è stato inserito
			if(pfDao.doRetrieveByKey((Integer) request.getSession().getAttribute("currentId"))!=null
					&& daoDisp.doRetrieveByKey(disp.getIdDisp()) != null)
				return true;
			else
				return false;
		}
		catch (SQLException e2) {
			e2.printStackTrace();
			return false;
		}
				
				
	}
	
	
	
	
	public boolean deletePercorso(HttpServletRequest req)   {
		
		
		//int id =  (Integer)( req.getSession().getAttribute("idPercorso"));
		int id = Integer.parseInt(req.getParameter("idPercorso"));
		
	
		
			try {
				pfDao.doDelete(id);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				return true;
			
		
		
		
	}

}
