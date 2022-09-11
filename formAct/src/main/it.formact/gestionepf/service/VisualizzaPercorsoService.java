package gestionepf.service;

import java.io.IOException;
import java.sql.SQLException;
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
import model.entity.PercorsoFormativoEntity;

public class VisualizzaPercorsoService implements Service{

	
	private PercorsoFormativoDao pfDao;
	private DisponibilitaDao dDao;
	private CategoriaDao cDao;
	private FormatoreDao fDao;
	// forward 
	Action errorPage = new Action("/view/gestionepf/percorsoView.jsp", false, true); 
	Action percorsoPage = new Action("/view/gestionepf/percorsoView.jsp", false, true);
	Action percorsiPage = new Action("/view/gestionepf/percorsiFormatore.jsp",false ,true);
	
	
	public VisualizzaPercorsoService() { 
		
		pfDao = new PercorsoFormativoDao();
		dDao = new DisponibilitaDao();
		cDao = new CategoriaDao();
		fDao = new FormatoreDao();
	} 
	@Override
	public Action getErrorAction() {
		// TODO Auto-generated method stub
		return errorPage;
	}

	@Override
	public Action process(String serviceName, HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException {
		
	// visualizza tutti i percorsi creati di un dato formatore
		if(serviceName.equalsIgnoreCase("PercorsiFormatoreService")) {
			try {
				if(getPercorsiByFormatore(req)) {
					return percorsiPage;
				}else return errorPage;
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		// visualizza scheda del singolo percorso formativo
		if(serviceName.equalsIgnoreCase("VisualizzaPercorsoService")) {
		
			try {
				if (getSchedaPercorso(req)) {
					return percorsoPage; 
				}
				else return errorPage;
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
		 return errorPage;
	}
	
	public boolean getPercorsiByFormatore(HttpServletRequest req) throws NumberFormatException, SQLException {
		
		ArrayList<PercorsoFormativoEntity> list ;
		
		list = (ArrayList<PercorsoFormativoEntity>) pfDao.doRetrieveByIdFormatore((int) req.getSession().getAttribute("currentId"));
		                                                                         
		if( list != null) {
		req.setAttribute("percorsiFormativi", list);	
			
			return true;
		}
		else return false;
		} 
	
	public boolean getSchedaPercorso(HttpServletRequest req) throws NumberFormatException, SQLException { 
		
		Integer idPercorso = Integer.parseInt(req.getParameter("idPercorso"));
		PercorsoFormativoEntity p = (PercorsoFormativoEntity) pfDao.doRetrieveByKey(idPercorso);
		ArrayList<DisponibilitaEntity> disponibilita = new ArrayList<>();
		req.getSession().setAttribute("percorso", p);
		req.getSession().setAttribute("formatore", fDao.doRetrieveByKey(p.getId_formatore()));
		req.getSession().setAttribute("categoria", cDao.doRetrieveByKey(p.getCategoria()));
		
		if(req.getSession().getAttribute("role")!=null && req.getSession().getAttribute("role").equals("Formatore")) {
			disponibilita = dDao.doRetrieveAllByPercorso(p.getId());
			req.getSession().setAttribute("disponibilita", disponibilita);
		}
			
		if(req.getSession().getAttribute("percorso") != null) {
			return true;
		}
		else 
			return false;
		
	}
	
	
	
	

}
