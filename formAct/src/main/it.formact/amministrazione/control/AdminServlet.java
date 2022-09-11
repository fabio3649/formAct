package amministrazione.control;

import java.util.Map;

import javax.servlet.annotation.WebServlet;

import amministrazione.service.AdminServices;
import autenticazione.service.AutenticazioneService;
import autenticazione.service.ModificaProfiloService;
import controller.control.AbstractController;
import controller.control.Service;


@WebServlet("/AdminServlet/*")
public class AdminServlet extends AbstractController{

	public AdminServlet() {
		super();
	}
	
	@Override
	protected Map<String, Service> initServices() {
		// TODO Auto-generated method stub
		java.util.Map<String, Service> m = new java.util.HashMap<String, Service>();
		m.put("GETALLSTUDENTSSERVICE", new AdminServices());
		m.put("GETALLTRAINERSSERVICE", new AdminServices());
		m.put("GETALLISCRIZIONIFROMSTUDENTSERVICE", new AdminServices());
		m.put("GETALLFBFROMTRAINERSERVICE", new AdminServices());
		m.put("DELETEUSERSERVICE", new AdminServices());
		m.put("CREACATEGORIASERVICE", new AdminServices());
		return m;
	}
}
