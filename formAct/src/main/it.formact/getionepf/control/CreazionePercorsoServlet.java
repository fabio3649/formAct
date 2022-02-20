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

import getionepf.service.CreatorService;
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
		
	
		
		CreatorService cr = new CreatorService();
		cr.creatorPercorso(request);
		
	}

}
