package model.entity;

import java.io.Serializable;

public class InteresseStudenteEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	int studente;
	int interesse;
	
	
	public InteresseStudenteEntity() {
		studente=0;
		interesse=0;
	}


	public int getStudente() {
		return studente;
	}


	public void setStudente(int studente) {
		this.studente = studente;
	}


	public int getInteresse() {
		return interesse;
	}


	public void setInteresse(int interesse) {
		this.interesse = interesse;
	}


	@Override
	public String toString() {
		return "Interesse dello studente[studente=" + studente + ", interesse=" + interesse + "]";
	}
	
	

}
