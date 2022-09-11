package controller.control;

public class Action {

	String page;
	boolean redirect;
	boolean error;
	/**
	 * Costruttore
	 * @param page, view a cui indirizzare la richiesta
	 * @param redirect, indica se eseguire il redirect oppure il forward
	 */
	public Action(String page, boolean redirect, boolean error) {
		this.page = page;
		this.error = error;
		this.redirect = redirect;
	}
	
	/**
	 * Indica se eseguire o meno il redirect
	 * @return true se bisogna eseguire il redirect alla pagina, false altrimenti
	 */
	public boolean isRedirect() {
		return redirect;
	}
	
	/**
	 * Indica se si tratta o meno di una pagina di errore
	 * @return true se rappresenta una pagina di errore, false altrimenti
	 */
	public boolean isError() {
		return error;
	}
	
	/**
	 * Path relativa della View
	 * @return Path relativa della View
	 */
	public String getPage() {
		return page;
	}
}

