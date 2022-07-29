package serviziutente.service;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.control.Action;
import controller.control.Service;

public class AgendaService implements Service{

	
	private Settimana corsi;
	public ArrayList<Calendario> lunedi;
	public ArrayList<Calendario> martedi;
	public ArrayList<Calendario> mercoledi;
	public ArrayList<Calendario> giovedi;
	public ArrayList<Calendario> venerdi;
	
	
	
	public AgendaService(HttpServletRequest req) throws SQLException, ParseException {
		int studente = (int) req.getSession().getAttribute("idStudente");
		lunedi = corsi.getLunedi(studente);
		martedi = corsi.getMartedi(studente);
		mercoledi = corsi.getMercoledi(studente);
		giovedi = corsi.getGiovedi(studente);
		venerdi = corsi.getVenerdi(studente);
		
	
	}
	
	
	@Override
	public Action process(String serviceName, HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Action getErrorAction() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	public ArrayList<Calendario> getLunedi() {
		return lunedi;
	}




	public void setLunedi(ArrayList<Calendario> lunedi) {
		this.lunedi = lunedi;
	}




	public ArrayList<Calendario> getMartedi() {
		return martedi;
	}




	public void setMartedi(ArrayList<Calendario> martedi) {
		this.martedi = martedi;
	}




	public ArrayList<Calendario> getMercoledi() {
		return mercoledi;
	}




	public void setMercoledi(ArrayList<Calendario> mercoledi) {
		this.mercoledi = mercoledi;
	}




	public ArrayList<Calendario> getGiovedi() {
		return giovedi;
	}




	public void setGiovedi(ArrayList<Calendario> giovedi) {
		this.giovedi = giovedi;
	}




	public ArrayList<Calendario> getVenerdi() {
		return venerdi;
	}




	public void setVenerdi(ArrayList<Calendario> venerdi) {
		this.venerdi = venerdi;
	}


	




	

}
