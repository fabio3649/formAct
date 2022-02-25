package autenticazione.control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.InteresseDao;
import model.dao.InteresseStudenteDao;
import model.dao.StudenteDao;
import model.entity.FormatoreEntity;
import model.entity.InteresseEntity;
import model.entity.InteresseStudenteEntity;
import model.entity.StudenteEntity;


@WebServlet("/ModificaInteressiServlet")
public class ModificaInteressiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public ModificaInteressiServlet() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		
		InteresseDao dao= new InteresseDao();
		InteresseStudenteDao dao1= new InteresseStudenteDao();
		int currentId= (int) request.getSession().getAttribute("currentId");
		
		try {
			ArrayList<InteresseEntity> allInteressi=dao.doRetrieveAll();
			
			for(InteresseEntity a: allInteressi){
				
				if(request.getParameter(a.getNome())==null) {
					
					if(dao1.isContent(a.getIdInteresse(), currentId)== true) {
						dao1.doDelete(currentId, a.getIdInteresse());
					}
					
				}else{
					
					if(dao1.isContent(a.getIdInteresse(), currentId)== false) {
						InteresseStudenteEntity b = new InteresseStudenteEntity();
						b.setInteresse(a.getIdInteresse());
						b.setStudente(currentId);
						dao1.doSave(b);
					}
				}
			}
	
		}catch(SQLException e) {
		
			e.printStackTrace();
		}
	}
}

