package getionepf.service;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.control.Action;
import controller.control.Service;
import model.dao.FormatoreDao;
import model.dao.PercorsoFormativoDao;
import model.entity.FormatoreEntity;

public class DeletePercorsoServices implements Service{

	PercorsoFormativoDao dao;
	FormatoreDao fDao;
	FormatoreEntity f;
	Action errorPage = new Action("/formAct/view/gestionepf/creatorPercorso.jsp", true, true);
	Action homePage = new Action("/formAct/view/messagePages/successPFdelete.jsp", true, true);
	
	public DeletePercorsoServices() {
		dao = new PercorsoFormativoDao();
		fDao = new FormatoreDao();
		f = new FormatoreEntity();
	}
	
	
	public boolean deletePercorso(HttpServletRequest req)  {
		
		int idFormatore = Integer.parseInt((String) req.getSession().getAttribute("currentId"));
		int id =  Integer.parseInt(req.getParameter("idPercorso"));
		
		
		try {
			f = fDao.doRetrieveByKey(idFormatore);  // check del formatore della sessione corrente
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println("Formatore non valido");
		}
		if (id > 0 && f != null) {    // check formatore e se id percorso è valido
			try {
				dao.doDelete(id);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Formatore non valido oppure id percorso non valido");
			}
			return true;
		}else
		return false;
	}
	
	
	@Override
	public Action process(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		
		if(deletePercorso(req)) {
			return homePage;
		}
		else
		return errorPage;
	}

	@Override
	public Action getErrorAction() {
		// TODO Auto-generated method stub
		return errorPage;
	}
	
	

}
