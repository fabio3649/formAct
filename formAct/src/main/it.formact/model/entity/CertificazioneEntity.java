package model.entity;

import java.io.Serializable;
import java.sql.Date;


public class CertificazioneEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	int idCertificazione;
	int formatore;
	String nome;
	String tipologia;
	String istituto;
	String descrizione;
	Date annoInizio;
	Date annoFine;
	
	public CertificazioneEntity() {
		
		idCertificazione= 0;
		formatore= 0;
		nome="";
		tipologia="";
		istituto="";
		descrizione="";
		annoInizio=null;
		annoFine=null;
		
	}

	public int getIdCertificazione() {
		return idCertificazione;
	}

	public void setIdCertificazione(int idCertificazione) {
		this.idCertificazione = idCertificazione;
	}

	public int getFormatore() {
		return formatore;
	}

	public void setFormatore(int formatore) {
		this.formatore = formatore;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTipologia() {
		return tipologia;
	}

	public void setTipologia(String tipologia) {
		this.tipologia = tipologia;
	}

	public String getIstituto() {
		return istituto;
	}

	public void setIstituto(String istituto) {
		this.istituto = istituto;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Date getAnnoInizio() {
		return annoInizio;
	}

	public void setAnnoInizio(Date annoInizio) {
		this.annoInizio = annoInizio;
	}

	public Date getAnnoFine() {
		return annoFine;
	}

	public void setAnnoFine(Date annoFine) {
		this.annoFine = annoFine;
	}

	@Override
	public String toString() {
		return "Certificazione di un formatore [idCertificazione=" + idCertificazione + ", formatore=" + formatore + ", nome="
				+ nome + ", tipologia=" + tipologia + ", istituto=" + istituto + ", descrizione=" + descrizione
				+ ", annoInizio=" + annoInizio + ", annoFine=" + annoFine + "]";
	}
	
	
	
	
	
	

}
