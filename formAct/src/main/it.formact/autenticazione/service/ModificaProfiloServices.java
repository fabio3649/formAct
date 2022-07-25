package autenticazione.service;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.control.Action;
import controller.control.Service;
import model.dao.FormatoreDao;
import model.dao.InteresseDao;
import model.dao.InteresseStudenteDao;
import model.dao.StudenteDao;
import model.entity.StudenteEntity;
import model.utils.Utils;

public class ModificaProfiloServices implements Service{

	
	StudenteDao sDao ;
	FormatoreDao fDao;
	InteresseDao iDao;
	InteresseStudenteDao isDao;
	Action errorPage = new Action("/formAct/view/errorPages/errorEditProfile.jsp", true, true);
	Action homePage = new Action("",true,true);
	
	public boolean editStudentProfile(HttpServletRequest req) throws ParseException {
		
		String email = req.getParameter("email");
		String name = req.getParameter("name");
		String surname = req.getParameter("surname");
		String gender = req.getParameter("gender");
		java.sql.Date dataNascita = Utils.toDate(req.getParameter("dataNascita"));
		String country = req.getParameter("country");
		//ArrayList<String> interesse = req.getParameter("interesse");
		
		
		
		return false;
		
	}
	
	public boolean editFormatoreProfile() {
		return false;
		
	}
	
	@Override
	public Action process(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Action getErrorAction() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
