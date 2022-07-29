package controller.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Rappresenta un servizio di gestione per un determinato controller
 *
 */
public interface Service {
	
	/**
	 * Processa la request
	 * @param req, request
	 * @param res, response
	 * @return la View a cui il controller deve indirizzare la richiesta
	 * @throws IOException
	 * @throws ServletException
	 */
	
	public Action process(String serviceName, HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException;
	
	/**
	 * Restituisce la pagina di errore di default
	 * @return
	 */
	public Action getErrorAction();
}