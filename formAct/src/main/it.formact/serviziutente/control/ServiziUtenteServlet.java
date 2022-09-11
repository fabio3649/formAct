package serviziutente.control;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import autenticazione.service.AutenticazioneService;

import autenticazione.service.ModificaProfiloService;
import controller.control.AbstractController;
import controller.control.Service;
import serviziutente.service.UserService;


@WebServlet("/ServiziUtenteServlet/*")
public class ServiziUtenteServlet extends AbstractController {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ServiziUtenteServlet() {
        super();
        
    }

    @Override
	protected Map<String, Service> initServices() {
		// TODO Auto-generated method stub
				java.util.Map<String, Service> m = new java.util.HashMap<String, Service>();
				m.put("GETAGENDASERVICE", new UserService());
				m.put("VALUTAZIONESERVICE", new UserService());
				m.put("VISUALIZZAPROFILOSERVICE", new UserService());
				return m;
	}	
}
