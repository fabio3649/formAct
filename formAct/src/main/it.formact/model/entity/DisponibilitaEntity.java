package model.entity;

import java.io.Serializable;
import java.sql.Time;
import java.time.LocalTime;

import model.utils.Utils;

public class DisponibilitaEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	int idDisp;
	int idPercorso;
	String giornoSettimana;
	String orario;
	int stato;
	
	
	
	public DisponibilitaEntity() {
		
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


	public String getOrario() {
		return orario;
	}


	public void setOrario(String orario) {
		this.orario = orario;
	}


	public int getStato() {
		return stato;
	}


	public void setStato(int stato) {
		this.stato = stato;
	}
/* int idDisp;
	int idPercorso;
	String giornoSettimana;
	LocalTime orario;
	int stato;     */

	@Override
	public boolean equals(Object o) {
		if( o instanceof DisponibilitaEntity) {
			DisponibilitaEntity e = (DisponibilitaEntity)o;		
			return idDisp == e.idDisp &&
				   (idPercorso == e.idPercorso ) &&
				   (giornoSettimana == e.giornoSettimana || (giornoSettimana != null && e.giornoSettimana != null && giornoSettimana.equals(e.giornoSettimana))) &&
				   (orario == e.orario || (orario != null && e.orario != null && orario.equals(e.orario))) &&
				   ( stato == e.stato);
		}else
			return false;
	}
	@Override
	public String toString() {
		return "Disponibilità [idDisp=" + idDisp + ", idPercorso=" + idPercorso + ", giornoSettimana="
				+ giornoSettimana + ", orario=" + orario + ", stato=" + stato + "]";
	}
	
	
	
	
	
	

}
