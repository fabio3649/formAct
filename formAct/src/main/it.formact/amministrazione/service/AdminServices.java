package amministrazione.service;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.control.Action;
import controller.control.Service;
import model.dao.CategoriaDao;
import model.dao.FormatoreDao;
import model.dao.IscrizioneDao;
import model.dao.PercorsoFormativoDao;
import model.dao.StudenteDao;
import model.dao.ValutazioneDao;
import model.entity.*;

public class AdminServices implements Service{

	StudenteDao sDao;
	FormatoreDao fDao;
	ValutazioneDao vDao;
	PercorsoFormativoDao pDao;
	IscrizioneDao iDao;
	CategoriaDao cDao;
	Action errorPage = new Action("/formAct/view/messagePages/Errori.jsp", true, true);
	Action homePage = new Action("/formAct/view/index/index.jsp", true, true);
	
	//Action fittizie per test
	Action allStudentPage = new Action("/formAct/view/amministrazione/AllStudentsResult.jsp", true, true);
	Action allTrainerPage = new Action("/formAct/view/amministrazione/AllTrainersResult.jsp", true, true);
	Action allValPage = new Action("/formAct/view/amministrazione/AllFeedBacksResult.jsp", true, true);
	
	Action allIscrizioniPage = new Action("/formAct/view/amministrazione/AllIscrizioniResult.jsp", true, true);
	Action adminPage = new Action("/formAct/view/amministrazione/AdminIndex.jsp", true, true);
	Action newCategoryPage = new Action("/formAct/view/amministrazione/NuovaCategoria.jsp", true, true);
	
	public AdminServices() {
		super();
		sDao = new StudenteDao();
		fDao = new FormatoreDao();
		vDao = new ValutazioneDao();
		pDao = new PercorsoFormativoDao();
		iDao = new IscrizioneDao();
		cDao = new CategoriaDao();
	}
	
	@Override
	public Action process(String serviceName, HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{
		
		if(serviceName.equalsIgnoreCase("GetAllStudentsService")){
			
			if(getStudenti(req))
				return allStudentPage;
			else
				return errorPage;
			
		}
		
		if(serviceName.equalsIgnoreCase("GetAllTrainersService")){
			if(getFormatori(req))
				return allTrainerPage;
			else
				return errorPage;
		}
		
		if(serviceName.equalsIgnoreCase("GetAllIscrizioniFromStudentService")){
			if(getIscrizioni(req))
				return allIscrizioniPage;
			else
				return errorPage;
		}
		
		if(serviceName.equalsIgnoreCase("GetAllFBFromTrainerService")){
			try {
				if(getValutazioni(req))
					return allValPage;
				else
					return errorPage;
			} catch (NumberFormatException | ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(serviceName.equalsIgnoreCase("DeleteUserService")){
			Action pageReturn = new Action("/formAct/view/amministrazione/Admin.jsp", true, true);
			if(deleteUser(req))
				return pageReturn;
			else
				return errorPage;
		}
		
		if(serviceName.equalsIgnoreCase("CreaCategoriaService")){
			if(createCategory(req))
				return newCategoryPage;
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

	public boolean getStudenti(HttpServletRequest req) {
		
		List<StudenteEntity> list = new ArrayList<StudenteEntity>();
		try {
			list = sDao.doRetrieveAll();
			req.getSession().setAttribute("allStudents", list);
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean getFormatori(HttpServletRequest req) {
		
		List<FormatoreEntity> list = new ArrayList<FormatoreEntity>();
		
		try {
			list = fDao.doRetrieveAll();
			req.getSession().setAttribute("allTrainer", list);
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean getValutazioni (HttpServletRequest req) throws NumberFormatException, ParseException {
		
		List<ValutazioneEntity> list = new ArrayList<ValutazioneEntity>();
		try {
			list = vDao.doRetrieveByFormatore(Integer.parseInt(req.getParameter("idFormatore")));
			FormatoreEntity formatore = fDao.doRetrieveByKey(Integer.parseInt(req.getParameter("idFormatore")));
			req.getSession().setAttribute("formatore", formatore);
			req.getSession().setAttribute("allVal", list);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean getIscrizioni(HttpServletRequest req) {
		
		List<IscrizioneEntity> list = new ArrayList<IscrizioneEntity>();
		
		try {
			list = (List<IscrizioneEntity>)iDao.doRetrieveByStudent(Integer.parseInt(req.getParameter("idStudente")));
			req.getSession().setAttribute("allIscrizioni", list);
			StudenteEntity studente = sDao.doRetrieveByKey(Integer.parseInt(req.getParameter("idStudente")));
			req.getSession().setAttribute("studente", studente);
			
			return true;	
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean deleteUser(HttpServletRequest req) {
		
		System.out.println(req.getParameter("role"));
		System.out.println(req.getParameter("idUser"));
		
		String role = (String) req.getParameter("role");
		int id = Integer.parseInt((String)req.getParameter("idUser"));
		
		if ( role.equalsIgnoreCase("st")) {  // se è stato scelto uno studente elimino lo studente
			try {
				sDao.doDelete(id);
				return true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}
		
		if(role.equalsIgnoreCase("fr")){
			try {   // altrimenti viene eliminato un formatore
				fDao.doDelete(id);
				return true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}
		return false;
		
	}
	
	private boolean createCategory(HttpServletRequest req) {
		cDao = new CategoriaDao();
		CategoriaEntity categoria = new CategoriaEntity();
		categoria.setNome(req.getParameter("nome"));
		categoria.setDescrizione(req.getParameter("descrizione"));
		categoria.setAmbito(req.getParameter("ambitoDisciplinare"));
		
		try {
			System.out.println(categoria);
			int id = cDao.doSave(categoria);
			
			// Se categoryIsInsert = true allora la categoria è stata inserita;
			// se categoryIsInsert = false allora la categoria non è stata inserita
			boolean categoryIsInsert = (id != 0) ? (true) : (false);
			req.getSession().setAttribute("categoryIsInsert", categoryIsInsert);
			req.getSession().setAttribute("nomeCategoria", categoria.getNome());
			return categoryIsInsert;
		} 
		catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}









