package model.entity;

import java.io.Serializable;
import java.sql.Time;

import model.utils.Utils;

public class PreferenzaStudenteEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	int studente;
	String disponibilita;
	


	public PreferenzaStudenteEntity() {
		
		studente =0;
		disponibilita="";
	}


	public int getStudente() {
		return studente;
	}


	public void setStudente(int studente) {
		this.studente = studente;
	}


	public String getDisponibilita() {
		return disponibilita;
	}


	public void setDisponibilita(String disponibilita) {
		this.disponibilita = disponibilita;
	}

	
	@Override
	public boolean equals(Object o) {
		if( o instanceof PreferenzaStudenteEntity) {
			PreferenzaStudenteEntity e = (PreferenzaStudenteEntity)o;		
			return studente == e.studente &&
				   disponibilita == e.disponibilita;
		}else
			return false;
	}

	@Override
	public String toString() {
		return "Preferenza [studente=" + studente + ", disponibile=" + disponibilita + "]";
	}


	


	
	

}
