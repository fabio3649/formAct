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

import controller.control.AbstractController;
import controller.control.Service;
import getionepf.service.CreatorServices;
import model.dao.CategoriaDao;
import model.dao.DisponibilitaDao;
import model.dao.PercorsoFormativoDao;
import model.entity.CategoriaEntity;
import model.entity.DisponibilitaEntity;
import model.entity.PercorsoFormativoEntity;

/**
 * Servlet implementation class CreazionePercorsoServlet
 */
@WebServlet("/CreazionePercorsoServlet")
public class CreazionePercorsoServlet extends AbstractController {
	
   
    public CreazionePercorsoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	@Override
	protected Service newService() {
		// TODO Auto-generated method stub
		return new CreatorServices();
	}

}
