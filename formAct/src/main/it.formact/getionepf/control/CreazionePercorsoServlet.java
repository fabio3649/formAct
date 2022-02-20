package getionepf.control;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.CategoriaDao;
import model.dao.Disponibilit‡Dao;
import model.dao.PercorsoFormativoDao;
import model.entity.CategoriaEntity;
import model.entity.Disponibilit‡Entity;
import model.entity.PercorsoFormativoEntity;

/**
 * Servlet implementation class CreazionePercorsoServlet
 */
@WebServlet("/CreazionePercorsoServlet")
public class CreazionePercorsoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreazionePercorsoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Dao e entity
		PercorsoFormativoEntity p = new PercorsoFormativoEntity();
		PercorsoFormativoDao dao = new PercorsoFormativoDao();
		ArrayList<PercorsoFormativoEntity> percorsi = (ArrayList<PercorsoFormativoEntity>) new ArrayList<PercorsoFormativoEntity>();
		CategoriaDao daoCat = new CategoriaDao();
		CategoriaEntity c = new CategoriaEntity();
		Disponibilit‡Dao daoDisp = new Disponibilit‡Dao();
		Disponibilit‡Entity disp = new Disponibilit‡Entity();
		PercorsoFormativoEntity id = new PercorsoFormativoEntity();
		
		
		// recupero i parametri dal form
		String nome = (String) request.getParameter("nome");
		String descrizione = (String) request.getParameter("descrizione");
		String categoria = request.getParameter("categoria");
		String giorno = request.getParameter("giorno");
		String orario = request.getParameter("orario");
		//String [] input = orario.split(":");
		
		LocalTime time = LocalTime.parse(orario);
	
		
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
		p.setCosto(costo);
		
		System.out.println(p.toString());		
		try {
			dao.doSave(p);
			
			
			System.out.println("Inserimento percorso effettuato con successo id: "+ p.getId() +  "\n");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		disp.setGiornoSettimana(giorno);
		disp.setOrario(time);
		disp.setStato(1);
		disp.setIdPercorso(p.getId());
		try {
			daoDisp.doSave(disp);
			System.out.println("Inserimento disponibilit‡ effettuato con successo Id: " + disp.getIdDisp() +"\n");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		
		
	}

}
