package model.entity;

import java.io.Serializable;
import java.sql.Time;

public class PreferenzaStudenteEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	int studente;
	int disponibilita;
	


	public PreferenzaStudenteEntity() {
		
		studente =0;
		disponibilita=0;
	}


	public int getStudente() {
		return studente;
	}


	public void setStudente(int studente) {
		this.studente = studente;
	}


	public int getDisponibilita() {
		return disponibilita;
	}


	public void setDisponibilita(int disponibilita) {
		this.disponibilita = disponibilita;
	}


	@Override
	public String toString() {
		return "Preferenza [studente=" + studente + ", disponibilita=" + disponibilita + "]";
	}


	


	
	

}
