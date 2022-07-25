package controller.control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import autenticazione.service.LoginServices;

/**
 * Rappresenta il controller di base
 *
 */
public abstract class AbstractController extends HttpServlet{

	Service service;
	/**
	 * Costruttore di default
	 */
	public AbstractController() {
		super();
		this.service = newService();
	}

	/**
	 * Restituisce il service che gestisce il processamento della richiesta
	 * @return Servizio di gestione della request
	 */
	protected abstract Service newService();
	
	@Override
	public final void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
		
	}

	@Override
	public final void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean ok = preProcess(request, response);
		Action action = null;
		if( ok ) {
			action = service.process(request, response);
			if(!action.isError())
				ok = postProcess(request, response);

			if(!ok)
				action = service.getErrorAction();
			
		}else
			action = service.getErrorAction();
		
		if( action.isRedirect())
			response.sendRedirect(action.getPage());
		else
			request.getRequestDispatcher(action.getPage()).forward(request, response);
		
	}	
	
	/**
	 * Esegue eventuale logica prima di processare la request
	 * @param request, richiesta da processare
	 * @param response, risposta per il client
	 * @return true se tutto ok, false altrimenti
	 * @throws ServletException
	 * @throws IOException
	 */
	protected boolean preProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		return true;
	}
	
	/**
	 * Esegue eventuale logica dopo aver processato la request
	 * @param request, richiesta da processare
	 * @param response, risposta per il client
	 * @return true se tutto ok, false altrimenti
	 * @throws ServletException
	 * @throws IOException
	 */
	protected boolean postProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		return true;
	}
}

