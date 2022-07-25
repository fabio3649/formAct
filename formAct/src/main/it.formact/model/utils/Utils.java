package model.utils;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Classe di utilita' 
 *
 */
public class Utils {
	// Formattatore di Date
	private static SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy");

	/**
	 * Converte la data in String oppure restituisce null se il parametro passato è null
	 * @param d data da formattare
	 * @return l data formattata come "dd-MM-yyyy", oppure null se d è null
	 */
	public static String toStringDate(Date d) {
		if (d == null) return null;
		
		return f.format(d);
	}
	
	/**
	 * Converte la stringa s in Date
	 * @param s data da convertire nel formato "dd-MM-yyyy"
	 * @return la rappresentazione Date di s, oppure null se s è null
	 * @throws ParseException, nel caso di problemi di parsing
	 */
	public static Date toDate(String s) throws ParseException {
		if( s == null) return null;
		return new Date(f.parse(s).getTime());
	}
	
	/**
	 * Verifica se le due date sono uguali
	 * @param d, data da confrontare
	 * @param d1, data da confrontare
	 * @return true se d e d1 hanno la stessa rappresentazione nel formato "dd-MM-yyyy", false altrimenti.
	 */
	public static boolean areDatesEqual(Date d, Date d1) {
		return (d == d1) ||(d != null && d1 != null && toStringDate(d).equals(toStringDate(d1)));
	}
	
	/**
	 * Restituisce la data odierna
	 * @return la rapprsentazione Date della data corrente
	 * @throws ParseException, se ci sono problemi di parsing.
	 */
	public static Date sysDate() throws ParseException {
		
		return new Date(f.parse(f.format(new Date(System.currentTimeMillis()))).getTime());
	}
	
}
