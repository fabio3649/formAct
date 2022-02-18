package getionepf.control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.CategoriaDao;
import model.dao.PercorsoFormativoDao;
import model.entity.CategoriaEntity;
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
		CategoriaDao daoCat = new CategoriaDao();
		CategoriaEntity c = new CategoriaEntity();
		
		
		// recupero i parametri dal form
		String nome = (String) request.getParameter("nome");
		String descrizione = (String) request.getParameter("descrizione");
		String categoria = request.getParameter("categoria");
		
		
		
		String indice = (String) request.getParameter("indice");
		int lezioni = Integer.parseInt(request.getParameter("lezioni"));
		double costo = Double.parseDouble(request.getParameter("costo"));
		
		
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
			System.out.println("Inserimento percorso effettuato con successo\n");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
	}

}
