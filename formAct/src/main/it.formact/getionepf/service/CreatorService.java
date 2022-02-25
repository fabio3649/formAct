package getionepf.service;

import java.sql.SQLException;
import java.time.LocalTime;

import javax.servlet.http.HttpServletRequest;

import model.dao.CategoriaDao;
import model.dao.Disponibilit‡Dao;
import model.dao.PercorsoFormativoDao;
import model.entity.Disponibilit‡Entity;
import model.entity.PercorsoFormativoEntity;

public class CreatorService {
	
	PercorsoFormativoDao dao = new PercorsoFormativoDao();
	Disponibilit‡Dao daoDisp = new Disponibilit‡Dao();
	CategoriaDao daoCat = new CategoriaDao();
	PercorsoFormativoEntity p = new PercorsoFormativoEntity();
	Disponibilit‡Entity disp = new Disponibilit‡Entity();
	
	
		public boolean creatorPercorso(HttpServletRequest request) throws SQLException {
		
			
		p =	getRequestDataPercorso(request);
		disp = getRequestDataDisponibilit‡(request, p);
			
			System.out.println(p.toString());		
			try {
				dao.doSave(p);
				
				
				System.out.println("Inserimento percorso effettuato con successo id: "+ p.getId() +  "\n");
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			try {
				daoDisp.doSave(disp);
				System.out.println("Inserimento disponibilit‡ effettuato con successo Id: \n");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
	
		
			return false;
		
		}
	
	

	
	// acquisisce i dati dal form e crea l'oggetto percorso formativo entity
	public PercorsoFormativoEntity getRequestDataPercorso(HttpServletRequest request) {
		
		// recupero i parametri dal form
		String nome = (String) request.getParameter("nome");
		String descrizione = (String) request.getParameter("descrizione");
		String categoria = request.getParameter("categoria");
		
	
		
		String indice = (String) request.getParameter("indice");
		int lezioni = Integer.parseInt(request.getParameter("lezioni"));
		double costo = Double.parseDouble(request.getParameter("costo"));
			
		
		try {
			p.setId(dao.nextId());
			System.out.println("id percorso appena creato : " + p.getId());
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		p.setNome(nome);
		p.setCategoria(Integer.parseInt(categoria));
		p.setDescrizione(descrizione);
		p.setId_formatore(1);
		p.setIndice_contenuti(indice);
		p.setNum_lezioni(lezioni);
		if(costo!=0) {
		p.setCosto(costo);
		}
		
		return p;
	}
	
	
	// recupero i dati di una disponibilit‡ dal form e creo l'oggetto disponibilit‡ entity
	public Disponibilit‡Entity getRequestDataDisponibilit‡(HttpServletRequest request, PercorsoFormativoEntity p) throws SQLException {
		disp = new Disponibilit‡Entity();
		// recupero i parametri dal form
		String giorno = request.getParameter("giorno");
		String orario = request.getParameter("orario");
		
		
		LocalTime time = LocalTime.parse(orario);
		disp.setIdDisp(daoDisp.nextId());
		disp.setGiornoSettimana(giorno);
		disp.setOrario(time);
		disp.setStato(1);
		disp.setIdPercorso(p.getId());
		
		
		
		return disp;
	
	}
	
	
	
	

}
