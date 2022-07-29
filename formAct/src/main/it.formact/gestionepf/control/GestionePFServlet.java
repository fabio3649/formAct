package gestionepf.control;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
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
import gestionepf.service.GestionePFService;

import gestionepf.service.PercorsiFormatoreService;
import gestionepf.service.VisualizzaPercorsoService;
import model.dao.CategoriaDao;
import model.dao.DisponibilitaDao;
import model.dao.PercorsoFormativoDao;
import model.entity.CategoriaEntity;
import model.entity.DisponibilitaEntity;
import model.entity.PercorsoFormativoEntity;

/**
 * Servlet implementation class CreazionePercorsoServlet
 */
@WebServlet("/GestionPFServlet/*")
public class GestionePFServlet extends AbstractController {
	
   
    public GestionePFServlet() {
        super();
        // TODO Auto-generated constructor stub
    }



 

    @Override
	protected Map<String, Service> initServices() {
		// TODO Auto-generated method stub
				java.util.Map<String, Service> m = new java.util.HashMap<String, Service>();
				m.put("CREATORSERVICE", new GestionePFService());
				m.put("DELETEPERCORSOSERVICE", new GestionePFService());
				m.put("PERCORSIFORMATORESERVICE", new PercorsiFormatoreService());
				m.put("VISUALIZZAPERCORSOSERVICE", new VisualizzaPercorsoService());
				
				return m;
	}

}



