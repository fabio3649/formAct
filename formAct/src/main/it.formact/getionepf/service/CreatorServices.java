package getionepf.service;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalTime;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.control.Action;
import controller.control.Service;
import model.dao.CategoriaDao;
import model.dao.DisponibilitaDao;
import model.dao.PercorsoFormativoDao;
import model.entity.DisponibilitaEntity;
import model.entity.PercorsoFormativoEntity;

public class CreatorServices implements Service{
	
	PercorsoFormativoDao dao;
	DisponibilitaDao daoDisp;
	CategoriaDao daoCat;
	PercorsoFormativoEntity p; 
	DisponibilitaEntity  disp;
	Action errorPage = new Action("/formAct/view/messagePages/errorCreatePercorso.jsp", true, true);
	Action percorsoPage = new Action("/formAct/view/gestionepf/percorsoView.jsp?idPercorso="+p.getId(), true, true);
	
	
	public CreatorServices() {
		dao = new PercorsoFormativoDao();
		daoDisp = new DisponibilitaDao();
		daoCat = new CategoriaDao();
		p = new PercorsoFormativoEntity();
		disp = new DisponibilitaEntity();
	}


	@Override
	public Action process(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		
		p =	getRequestDataPercorso(req);
		disp = getRequestDataDisponibilita(req, p);
		
		if(p!=null && disp !=null) {
			
			//System.out.println("Inserimento percorso effettuato con successo id: "+ p.getId() +  "\n");
			return percorsoPage;
		}
			else {
				return errorPage;
			}
	
	}


	@Override
	public Action getErrorAction() {
		// TODO Auto-generated method stub
		return errorPage;
	}
	
	
	// acquisisce i dati dal form e crea l'oggetto percorso formativo entity
	public PercorsoFormativoEntity getRequestDataPercorso(HttpServletRequest request) {
		
		// recupero i parametri dal form
		String nome = (String) request.getParameter("nome");
		String descrizione = (String) request.getParameter("descrizione");
		String categoria = request.getParameter("categoria");
		//PercorsoFormativoEntity p = new PercorsoFormativoEntity();
	
		int formatore = Integer.parseInt((String) request.getSession().getAttribute("currentId"));
		String indice = (String) request.getParameter("indice");
		int lezioni = Integer.parseInt(request.getParameter("lezioni"));
		double costo = Double.parseDouble(request.getParameter("costo"));
			
		
		
		p.setNome(nome);
		p.setCategoria(Integer.parseInt(categoria));
		p.setDescrizione(descrizione);
		p.setId_formatore(formatore);
		p.setIndice_contenuti(indice);
		p.setNum_lezioni(lezioni);
		if(costo!=0) {
		p.setCosto(costo);
		}
		try {
			dao.doSave(p);
			System.out.println("id percorso appena creato : " + p.getId());
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		return p;
	}
	
	
	// recupero i dati di una disponibilità dal form e creo l'oggetto disponibilità entity
	public DisponibilitaEntity getRequestDataDisponibilita(HttpServletRequest request, PercorsoFormativoEntity p)  {
		disp = new DisponibilitaEntity();
		// recupero i parametri dal form
		String giorno = request.getParameter("giorno");
		String orario = request.getParameter("orario");
		
		
		LocalTime time = LocalTime.parse(orario);
		
		disp.setGiornoSettimana(giorno);
		disp.setOrario(time);
		disp.setStato(1);
		disp.setIdPercorso(p.getId());
		
		try {
			daoDisp.doSave(disp);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return disp;
	
	}
	

}
