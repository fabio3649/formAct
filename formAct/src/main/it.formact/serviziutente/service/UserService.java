package serviziutente.service;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.control.Action;
import controller.control.Service;
import model.dao.FormatoreDao;
import model.dao.IscrizioneDao;
import model.dao.PercorsoFormativoDao;
import model.dao.StudenteDao;
import model.dao.ValutazioneDao;
import model.entity.FormatoreEntity;
import model.entity.PercorsoFormativoEntity;
import model.entity.StudenteEntity;
import model.entity.ValutazioneEntity;
import model.utils.Utils;

public class UserService implements Service{

	
	Action AgendaPage = new Action("/formAct/view/serviziutente/agendaCorsi.jsp", true, true);
	Action errorPage = new Action("/formAct/view/percorsoformativo/RicercaPercorsoFormativo.jsp",true,true);
	Action profiloPage = new Action("/formAct/view/autenticazione/Profilo.jsp", true, true);
	Action homePage = new Action("/formAct/view/index/index.jsp",true,true);
	
	public UserService(){
	}
	
	@Override
	public Action process(String serviceName, HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException{
		// TODO Auto-generated method stub
		
		if(serviceName.equalsIgnoreCase("GetAgendaService")) {
			try {
				try {
					if(getAgenda(req)){
						return AgendaPage;
					}else {
						
						return errorPage;
					}
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(serviceName.equalsIgnoreCase("VisualizzaProfiloService")) {
			try{
				if(getProfilo(req)){
					return profiloPage;
				}else {
					
					return errorPage;
				}
			}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
		}
		
		if(serviceName.equalsIgnoreCase("ValutazioneService")) {
			try {
				if(createValutazione(req)) {
					return homePage;
				}
				else return errorPage;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		return errorPage;
	}
	
	@Override
	public Action getErrorAction() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public boolean createValutazione(HttpServletRequest req) throws SQLException, ParseException {
		
		int id = (Integer)req.getSession().getAttribute("currentId");
		String role = (String) req.getSession().getAttribute("role");
		System.out.println("id p : " + req.getParameter("idPercorso"));
		int pf = (Integer.parseInt(req.getParameter("idPercorso")));
		
		String desc =  req.getParameter("descrizione"); 
		int stars = 0;
		
		int i = 4;
		
		while(true) {
			
			String s = "star" + i + "IsSelected";
			if(i == - 1) {
				break;
			}
			if(req.getParameter(s).equals("true"))  {
				stars = i + 1;
				break;
			}
			else
				i -- ;
			
		}
		
		Date date = new java.sql.Date(System.currentTimeMillis());
		
		if(role.equals("Studente")) {
			PercorsoFormativoDao pDao = new PercorsoFormativoDao();
			PercorsoFormativoEntity percorso = (PercorsoFormativoEntity) pDao.doRetrieveByKey(pf);
			ValutazioneDao vDao = new ValutazioneDao();
			ValutazioneEntity val = new ValutazioneEntity();
			//riempio il bean
			val.setPercorsoFormativo(pf);
			val.setFormatore(percorso.getId_formatore());
			val.setStudente(id);
			val.setDescrizione(desc);
			val.setData(date);
			val.setStelle(stars);
			//salvataggio valutazione
			vDao.doSave(val);
			return true;
		}
		
		return false;
		
	}
	
	public boolean getAgenda(HttpServletRequest req) throws SQLException, ParseException {
		
		int id = (Integer)req.getSession().getAttribute("currentId");
		System.out.println("id studente: " + id);
		IscrizioneDao iDao = new IscrizioneDao();
		
		req.getSession().setAttribute("allSubscribe", iDao.doRetrieveByStudent(id));
		System.out.println("iscrizioni: " + iDao.doRetrieveByStudent(id).toString());
		if(req.getSession().getAttribute("allSubscribe")!=null)
			return true;
		else
			return false;
	}
	
	public boolean getProfilo(HttpServletRequest req) throws SQLException, ParseException {
		
		int id = (Integer)req.getSession().getAttribute("currentId");
		String role = (String) req.getSession().getAttribute("role");		
		
		if(role.equals("Studente")) {
			StudenteDao sDao = new StudenteDao();
			StudenteEntity std = new StudenteEntity();
			std = sDao.doRetrieveByKey(id);
			if(std!=null) {
				req.getSession().setAttribute("Profilo", std);
				return true;
			}
		}
		
		if(role.equals("Formatore")) {
			FormatoreDao fDao = new FormatoreDao();
			FormatoreEntity frm = new FormatoreEntity();
			frm = fDao.doRetrieveByKey(id);
			if(frm!=null) {
				req.getSession().setAttribute("Profilo", frm);
				return true;
			}
		}
		
		return false;
	}
}
