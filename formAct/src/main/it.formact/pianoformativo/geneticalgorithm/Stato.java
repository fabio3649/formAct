package pianoformativo.geneticalgorithm;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;

import model.entity.PercorsoFormativoEntity;

/**
 * 
 * Classe che rappresenta uno stato.
 *
 */
public class Stato {
	
	private PercorsoFormativoEntity percorsoFormativo;
	private String giorno;
	private LocalTime orario;
	private int punteggio;
	
	/**
	 * Costruttore della classe Stato.
	 */
	public Stato() {
		this.percorsoFormativo = new PercorsoFormativoEntity();
		this.giorno = "";
		this.orario = null;
		this.punteggio = 0;
	} 
	
	/**
	 * Metodo che restituisce il percorso formativo dello stato.
	 * 
	 * @return  il percorso formativo dello stato.
	 */
	public PercorsoFormativoEntity getPercorsoFormativo() {
		return percorsoFormativo;
	}
	
	/**
	 * Metodo che modifica il percorso formativo dello stato.
	 * 
	 * @param  percorsoFormativo nuovo percorso formativo dello stato.
	 */
	public void setPercorsoFormativo(PercorsoFormativoEntity percorsoFormativo) {
		this.percorsoFormativo = percorsoFormativo;
	}

	/**
	 * Metodo che ritorna il giorno dello stato.
	 * 
	 * @return  il giorno dello stato.
	 */
	public String getGiorno() {
		return giorno;
	}
	
	/**
	 * Metodo che modifica il giorno dello stato.
	 * 
	 * @param  giorno nuovo giorno dello stato.
	 */
	public void setGiorno(String giorno) {
		this.giorno = giorno;
	}
	
	/**
	 * Metodo che ritorna l'orario dello stato.
	 * 
	 * @return  l'orario dello stato.
	 */
	public LocalTime getOrario() {
		return orario;
	}
	
	/**
	 * Metodo che modifica l'orario dello stato.
	 * 
	 * @param  orario nuovo orario dello stato.
	 */
	public void setOrario(LocalTime orario) {
		this.orario = orario;
	}
	
	/**
	 * Metodo che ritorna il punteggio dello stato.
	 * 
	 * @return  il punteggio dello stato.
	 */
	public int getPunteggio() {
		return punteggio;
	}
	
	/**
	 * Metodo che modifica il punteggio dello stato.
	 * 
	 * @param  punteggio nuovo punteggio dello stato.
	 */
	public void setPunteggio(int punteggio) {
		this.punteggio = punteggio;
	}
	
	/**
	 * Metodo che ritorna una stringa che rappresenta un oggetto Stato.
	 * 
	 * @return una stringa che rappresenta un oggetto Stato.
	 */
	@Override 
	public String toString() { 
		return "Stato = [percorsoFormativo = " + this.getPercorsoFormativo().toString()
				+ ", giorno = " + this.getGiorno() + ", orario = " + this.getOrario() 
				+ ", punteggio = " + this.getPunteggio() + "]";
	}
	
	/**
	 * Metodo che confronta lo stato this con lo stato s2 in base alla stringa cosaConfrontare.
	 * 
	 * @param  s2 lo stato da confrontare. 
	 * @param  cosaConfrontare stringa che indica cosa confrontare. E' uguale ad una delle seguenti stringhe:
	 *         <ul>
	 *           <li>"Giorno": se vogliamo confrontare i loro giorni;</li>
	 *           <li>"Orario": se vogliamo confrontare i loro orari;</li>
	 *           <li>"Punteggio": se vogliamo confrontare i loro punteggi;</li>
	 *           <li>"GiornoAndOrario": se vogliamo confrontare insieme sia i loro giorni e sia i loro orari.</li>
	 *         </ul>
	 *          
	 * @return
	 *      <ul>
	 * 		  <li>valore maggiore di 1: se il/i parametro/i confrontato/i dello stato this è/sono maggiore/i al/agli stesso/stessi parametro/i dello stato s2;</li>
	 *        <li>valore minore di 1: se il/i parametro/i confrontato/i dello stato this è/sono minore/i al/agli stesso/stessi parametro/i dello stato s2;</li>
	 *        <li>valore uguale a 0: se il/i parametro/i confrontato/i dello stato this è/sono uguale/i al/agli stesso/stessi parametro/i dello stato s2.</li>
	 *      </ul>
	 */
	public int compare(Stato s2, String cosaConfrontare) {
		if (cosaConfrontare.equalsIgnoreCase("Giorno")) {
			return this.compareGiorno(s2);
		}
		else if (cosaConfrontare.equalsIgnoreCase("Orario")) {
			return this.compareOrario(s2);
		}
		else if (cosaConfrontare.equalsIgnoreCase("Punteggio")) {
			return this.comparePunteggio(s2);
		}
		else if (cosaConfrontare.equalsIgnoreCase("GiornoAndOrario")) {
			return this.compareGiornoAndOrario(s2);
		}
		else {
			throw new IllegalArgumentException
					("Errore nella comparazione, non posso comparare il seguente argomento: " + cosaConfrontare);
		}
	}
	
	/**
	 * Metodo che confronta il giorno e l'orario (insieme) dello stato this con il giorno e l'orario (insieme) dello stato s2.
	 * 
	 * @param  s2 lo stato da confrontare
	 * @return  
	 *      <ul>
	 *        <li>1: se il giorno e l'orario (insieme) dello stato this è maggiore al giorno e l'orario (insieme) dello stato s2;</li>
	 *        <li>-1: se il giorno e l'orario (insieme) dello stato this è minore al giorno e l'orario (insieme) dello stato s2;</li>
	 *        <li>0: se il giorno e l'orario (insieme) dello stato this è uguale al giorno e l'orario (insieme) dello stato s2.</li>
	 *      </ul>
	 */
	private int compareGiornoAndOrario(Stato s2) {
		
		int risultatoGiorni = this.compareGiorno(s2);
	    
		if (risultatoGiorni > 0) {
			return 1;
		}
		if (risultatoGiorni < 0) {
			return -1;
		}
		
		return this.compareOrario(s2);
		
	}
	
	/**
	 * Metodo che confronta il giorno dello stato this con il giorno dello stato s2.
	 * 
	 * @param  s2 lo stato da confrontare
	 * @return
	 *      <ul>
	 *        <li>valore maggiore di 1: se il giorno dello stato this è maggiore al giorno dello stato s2;</li>
	 *        <li>valore minore di 1: se il giorno dello stato this è minore al giorno dello stato s2;</li>
	 *        <li>valore uguale a 0: se il giorno dello stato this è uguale al giorno dello stato s2.</li>
	 *      </ul>
	 */
	private int compareGiorno(Stato s2) {
		// array contenente i giorni della settimana
		ArrayList<String> giorni = new ArrayList<>(Arrays.asList("lunedì", "martedì", "mercoledì", "giovedì", "venerdì"));
				
		int indice1 = giorni.indexOf(this.getGiorno().toLowerCase());
		int indice2 = giorni.indexOf(s2.getGiorno().toLowerCase());
		
		// se indice1 < 0 allora il giorno giorno1 non è valido e lancio un'eccezione
		if (indice1 < 0) {
			throw new IllegalArgumentException("giorno1 non è un giorno valido, valore riscontrato: " + this.getGiorno());
		}
		
		// se indice2 < 0 allora il giorno giorno2 non è valido e lancio un'eccezione
		if (indice2 < 0) {
			throw new IllegalArgumentException("giorno2 non è un giorno valido, valore riscontrato: " + s2.getGiorno());
		}
		
		return indice1 - indice2;
	}
	
	/**
	 * Metodo che confronta l'orario dello stato this con l'orario dello stato s2.
	 * 
	 * @param  s2 lo stato da confrontare
	 * @return
	 *      <ul>
	 * 		  <li>1: se l'orario dello stato this è maggiore dell'orario dello stato s2;</li>
	 *        <li>-1: se l'orario dello stato this è minore dell'orario dello stato s2;</li>
	 *        <li>0: se l'orario dello stato this è uguale all'orario dello stato s2.</li>
	 *      </ul>
	 */
	private int compareOrario(Stato s2) {
		return this.getOrario().compareTo(s2.getOrario());
	}
	
	/**
	 * Metodo che confronta il punteggio dello stato this con il punteggio dello stato s2.
	 * 
	 * @param  s2 lo stato da confrontare
	 * @return
	 *      <ul>
	 * 		  <li>1: se il punteggio dello stato this è maggiore del punteggio dello stato s2;</li>
	 *        <li>-1: se il punteggio dello stato this è minore del punteggio dello stato s2;</li>
	 *        <li>0: se il punteggio dello stato this è uguale al punteggio dello stato s2.</li>
	 *      </ul>
	 */
	private int comparePunteggio(Stato s2) {
		return this.getPunteggio() - s2.getPunteggio();
	}
	
}
