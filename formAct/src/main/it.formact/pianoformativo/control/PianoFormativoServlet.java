package pianoformativo.control;

import java.util.Map;

import javax.servlet.annotation.WebServlet;

import controller.control.AbstractController;
import controller.control.Service;
import pianoformativo.service.PianoFormativoService;

@WebServlet("/PianoFormativoServlet/*")
public class PianoFormativoServlet extends AbstractController {
	
	public PianoFormativoServlet() {
		super();
	}
	
	@Override
	protected Map<String, Service> initServices() {
		java.util.Map<String, Service> m = new java.util.HashMap<String, Service>();
		
		m.put("PIANOSERVICE", new PianoFormativoService());
		m.put("OTTIENIPIANOSERVICE", new PianoFormativoService());
		
		return m;
	}
	
}









