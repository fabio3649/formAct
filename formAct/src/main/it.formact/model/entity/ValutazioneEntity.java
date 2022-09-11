package model.entity;

import java.io.Serializable;
import java.sql.Date;

import model.utils.Utils;



public class ValutazioneEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int studente;
	private int formatore;
	private int percorsoFormativo;
	private String descrizione;
	private Date data;
	private int stelle;   // equivale al numero di stelle cliccate nella view della valutazione;
	
	public ValutazioneEntity() {
		
		studente = 0;
		formatore = 0;
		percorsoFormativo= 0;
		descrizione ="";
		data = null;
		stelle = 0;
	}

	public int getPercorsoFormativo() {
		return percorsoFormativo;
	}

	public void setPercorsoFormativo(int percorsoFormativo) {
		this.percorsoFormativo = percorsoFormativo;
	}

	public int getStudente() {
		return studente;
	}

	public void setStudente(int studente) {
		this.studente = studente;
	}

	public int getFormatore() {
		return formatore;
	}

	public void setFormatore(int formatore) {
		this.formatore = formatore;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public int getStelle() {
		return stelle;
	}

	public void setStelle(int stelle) {
		this.stelle = stelle;
	}
	
	@Override
	public boolean equals(Object o) {
		if( o instanceof ValutazioneEntity) {
			ValutazioneEntity e = (ValutazioneEntity)o;		
			return studente == e.studente &&
				   formatore == e.formatore &&
				   percorsoFormativo == e.percorsoFormativo &&
				   (descrizione == e.descrizione || (descrizione != null && e.descrizione != null && descrizione.equals(e.descrizione))) &&		
				   Utils.areDatesEqual(data,e.data) &&
				   stelle == e.stelle;
		}else
			return false;
	}


	@Override
	public String toString() {
		return "Valutazione di un formatore  [studente=" + studente + ", formatore=" + formatore + ", percorsoFormativo="
				+ percorsoFormativo + ", descrizione=" + descrizione + ", data=" + data + ", stelle=" + stelle + "]";
	}

	
	
	
	
	
	

}
