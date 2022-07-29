package amministrazione.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.control.Action;
import controller.control.Service;
import model.dao.FormatoreDao;
import model.dao.PercorsoFormativoDao;
import model.dao.StudenteDao;
import model.dao.ValutazioneDao;
import model.entity.*;

public class AdminServices implements Service{

	StudenteDao sDao;
	FormatoreDao fDao;
	ValutazioneDao vDao;
	PercorsoFormativoDao pDao;
	Action errorPage = new Action("/formAct/view/autenticazione/Login.jsp", true, true);
	Action homePage = new Action("/formAct/view/index/index.jsp", true, true);
	
	
	public AdminServices() {
		super();
		sDao = new StudenteDao();
		fDao = new FormatoreDao();
		vDao = new ValutazioneDao();
		pDao = new PercorsoFormativoDao();
	}
	@Override
	public Action process(String serviceName, HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		
		
		return null;
	}

	@Override
	public Action getErrorAction() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<StudenteEntity> getStudenti() {
		
		List<StudenteEntity> list = new ArrayList<StudenteEntity>();
		try {
			list = sDao.doRetrieveAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		return list;
		
	}
	
	public List<FormatoreEntity> getFormatori() {
		
		List<FormatoreEntity> list = new ArrayList<FormatoreEntity>();
		
		try {
			list = fDao.doRetrieveAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
		
	}
	
	public List<PercorsoFormativoEntity>  getPercorsi(int studente) {
		
		List<PercorsoFormativoEntity> list = new ArrayList<PercorsoFormativoEntity>();
		
		try {
			list = pDao.doRetrieveIscrizioniStudente(studente);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
		
		
	}
	
	public List<ValutazioneEntity> getValutazioni (int formatore) {
		
		List<ValutazioneEntity> list = new ArrayList<ValutazioneEntity>();
		try {
			list = vDao.doRetrieveByFormatore(formatore);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
		
	}
	
	
	public boolean deleteUser(HttpServletRequest req, int id) {
		
		String role = (String) req.getAttribute("role");
		
		if ( role.equalsIgnoreCase("studente")) {  // se è stato scelto uno studente elimino lo studente
			try {
				sDao.doDelete(id);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return true;
		} else
			try {   // altrimenti viene eliminato un formatore
				fDao.doDelete(id);
				return true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		return false;
		
	}
}
