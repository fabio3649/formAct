package getionepf.control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.control.AbstractController;
import controller.control.Service;
import getionepf.service.DeletePercorsoServices;
import model.dao.PercorsoFormativoDao;
import model.entity.PercorsoFormativoEntity;

/**
 * Servlet implementation class EliminaPercorsoServlet
 */
@WebServlet("/EliminaPercorsoServlet")
public class EliminaPercorsoServlet extends AbstractController {
	
    
    public EliminaPercorsoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	@Override
	protected Service newService() {
		// TODO Auto-generated method stub
		return new DeletePercorsoServices();
	}

}
