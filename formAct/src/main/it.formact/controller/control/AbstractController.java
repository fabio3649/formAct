package controller.control;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Rappresenta il controller di base
 *
 */
public abstract class AbstractController extends HttpServlet{

	private Map<String, Service> services;
	/**
	 * Costruttore di default
	 */
	public AbstractController() {
		super();
		services = initServices();
	}

	protected abstract Map<String, Service> initServices();
	
	@Override
	public final void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	@Override
	public final void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getRequestURI();
		int pos = path.lastIndexOf('/');
		path = path.substring(pos+1); 
		System.out.println("PATH DELLA RICHIESTA CLIENT  : " + path);
		if (path.contains("Service")) {
		Service service = services.get(path.toUpperCase());
		
		boolean ok = preProcess(path, request, response);
		Action action = null;
		if( ok ) {
			action = service.process(path, request, response);
			
			if(!action.isError())
				ok = postProcess(path, request, response);

			if(!ok)
				action = service.getErrorAction();
			
		}else
			action = service.getErrorAction();
		
		if( action.isRedirect())
			response.sendRedirect(action.getPage());
		else
			request.getRequestDispatcher(action.getPage()).forward(request, response);
		}
		else 
			System.out.println("errore nella request!");
	}	
	
	/**
	 * Esegue eventuale logica prima di processare la request
	 * @param request, richiesta da processare
	 * @param response, risposta per il client
	 * @return true se tutto ok, false altrimenti
	 * @throws ServletException
	 * @throws IOException
	 */
	protected boolean preProcess(String serviceName, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
	protected boolean postProcess(String serviceName, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		return true;
	}
}

