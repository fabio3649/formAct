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
	String nomeStudente;
	String giorno;
	Time orario;
	
	
	public String getNomeStudente() {
		return nomeStudente;
	}


	public void setNomeStudente(String nomeStudente) {
		this.nomeStudente = nomeStudente;
	}


	public String getGiorno() {
		return giorno;
	}


	public void setGiorno(String giorno) {
		this.giorno = giorno;
	}


	public Time getOrario() {
		return orario;
	}


	public void setOrario(Time orario) {
		this.orario = orario;
	}


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
		return "PreferenzaStudente [nomeStudente=" + nomeStudente + ", giorno=" + giorno + ", orario=" + orario
				+ "]";
	}


	
	

}
