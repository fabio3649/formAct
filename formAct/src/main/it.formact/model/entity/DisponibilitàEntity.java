package model.entity;

import java.io.Serializable;
import java.sql.Time;
import java.time.LocalTime;

public class Disponibilit‡Entity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	int idDisp;
	int idPercorso;
	String giornoSettimana;
	LocalTime orario;
	int stato;
	
	
	
	public Disponibilit‡Entity() {
		
		idDisp=0;
		idPercorso=0;
		giornoSettimana="";
		orario=null;
		stato=1;
	}


	public int getIdDisp() {
		return idDisp;
	}


	public void setIdDisp(int idDisp) {
		this.idDisp = idDisp;
	}


	public int getIdPercorso() {
		return idPercorso;
	}


	public void setIdPercorso(int idPercorso) {
		this.idPercorso = idPercorso;
	}


	public String getGiornoSettimana() {
		return giornoSettimana;
	}


	public void setGiornoSettimana(String giornoSettimana) {
		this.giornoSettimana = giornoSettimana;
	}


	public LocalTime getOrario() {
		return orario;
	}


	public void setOrario(LocalTime orario) {
		this.orario = orario;
	}


	public int getStato() {
		return stato;
	}


	public void setStato(int stato) {
		this.stato = stato;
	}


	@Override
	public String toString() {
		return "Disponibilit‡ [idDisp=" + idDisp + ", idPercorso=" + idPercorso + ", giornoSettimana="
				+ giornoSettimana + ", orario=" + orario + ", stato=" + stato + "]";
	}
	
	
	
	
	
	

}
