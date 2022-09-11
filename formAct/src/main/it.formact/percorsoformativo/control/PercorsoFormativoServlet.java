package percorsoformativo.control;

import java.text.ParseException;
import java.util.Map;

import javax.servlet.annotation.WebServlet;

import controller.control.AbstractController;
import controller.control.Service;
import gestionepf.service.GestionePFService;


import gestionepf.service.VisualizzaPercorsoService;

import percorsoformativo.service.PercorsoFormativoService;



@WebServlet("/PercorsoFormativoServlet/*")
public class PercorsoFormativoServlet extends AbstractController{

	
	
	
	@Override
	protected Map<String, Service> initServices() {
		// TODO Auto-generated method stub
				java.util.Map<String, Service> m = new java.util.HashMap<String, Service>();
				try {
					m.put("ISCRIZIONEPFSERVICE", new PercorsoFormativoService());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				try {
					m.put("DISISCRIZIONEPFSERVICE", new PercorsoFormativoService());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					m.put("RICERCAPFSERVICE", new PercorsoFormativoService());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				return m;
	}

}
