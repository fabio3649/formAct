package model.entity;

import java.io.Serializable;
import java.sql.Time;

public class Disponibilit‡Entity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	int idDisp;
	int idPercorso;
	String giornoSettimana;
	Time orario;
	Boolean stato;
	
	
	public Disponibilit‡Entity() {
		
		idDisp=0;
		idPercorso=0;
		giornoSettimana="";
		orario=null;
		stato=true;
	}
	
	
	
	

}
