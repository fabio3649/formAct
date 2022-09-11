package registrazione.control;

import java.util.Map;

import javax.servlet.annotation.WebServlet;

import autenticazione.service.AutenticazioneService;

import autenticazione.service.ModificaProfiloService;
import controller.control.AbstractController;
import controller.control.Service;
import registrazione.service.RegisterService;

@WebServlet("/RegisterServlet/*")
public class RegisterServlet extends AbstractController{
	
	public RegisterServlet() {
		
		super();
	}

	@Override
	protected Map<String, Service> initServices() {
		// TODO Auto-generated method stub
				java.util.Map<String, Service> m = new java.util.HashMap<String, Service>();
				m.put("REGISTERSERVICE", new RegisterService());
				
				return m;
	}

	

}
