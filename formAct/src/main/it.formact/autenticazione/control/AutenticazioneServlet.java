package autenticazione.control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import autenticazione.service.AutenticazioneService;
import autenticazione.service.ModificaProfiloService;
import controller.control.AbstractController;
import controller.control.Action;
import controller.control.Service;
import model.dao.FormatoreDao;
import model.dao.StudenteDao;
import model.entity.FormatoreEntity;
import model.entity.StudenteEntity;

/**
 * Controller di gestione della autenticazione e della modifica profilo
 *
 */
@WebServlet("/AutenticazioneServlet/*")
public class AutenticazioneServlet extends AbstractController{

	private static final long serialVersionUID = 1L;
	
	

	public AutenticazioneServlet() {
		super();
		

		
	 
	} 

	@Override
	protected Map<String, Service> initServices() {
		// TODO Auto-generated method stub
				java.util.Map<String, Service> m = new java.util.HashMap<String, Service>();
				m.put("LOGINSERVICE", new AutenticazioneService());
				m.put("LOGOUTSERVICE", new AutenticazioneService());
				m.put("MODIFICAINTERESSISERVICE", new ModificaProfiloService());
				m.put("MODIFICAPASSWORDSERVICE", new ModificaProfiloService());
				m.put("MODIFICAPROFILOSERVICE", new ModificaProfiloService());
				m.put("DISISCRIZIONEPIATTAFORMASERVICE", new AutenticazioneService());
				return m; 
	}	 
}