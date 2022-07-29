package model.entity;

import java.io.Serializable;
import java.sql.Date;

import model.utils.Utils;


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
	public boolean equals(Object o) {
		if( o instanceof CertificazioneEntity) {
			CertificazioneEntity e = (CertificazioneEntity)o;		
			return idCertificazione == e.idCertificazione &&
				   (formatore == e.formatore ) &&
				   (nome == e.nome || (nome != null && e.nome != null && nome.equalsIgnoreCase(e.nome))) &&
				   (tipologia == e.tipologia || (tipologia != null && e.tipologia != null && tipologia.equalsIgnoreCase(e.tipologia))) &&
				   (istituto == e.istituto || (istituto != null && e.istituto != null && istituto.equalsIgnoreCase(e.istituto))) &&
				   (descrizione == e.descrizione || (descrizione != null && e.descrizione != null && descrizione.equals(e.descrizione))) &&
				   Utils.areDatesEqual(annoInizio,e.annoInizio) && Utils.areDatesEqual(annoFine,e.annoFine);
		}else
			return false;
	}

	@Override
	public String toString() {
		return "Certificazione di un formatore [idCertificazione=" + idCertificazione + ", formatore=" + formatore + ", nome="
				+ nome + ", tipologia=" + tipologia + ", istituto=" + istituto + ", descrizione=" + descrizione
				+ ", annoInizio=" + annoInizio + ", annoFine=" + annoFine + "]";
	}
	
	
	
	
	
	

}
