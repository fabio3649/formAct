package model.entity;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;

public class IscrizioneEntity implements Serializable{
	
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	int studente;
	
	int percorsoFormativo;
	String giorno;
	LocalTime orario;
	String metodoPagamento;
	Date dataPagamento;
	
	public IscrizioneEntity() {
		
		studente= 0;
		percorsoFormativo = 0;
		giorno="";
		orario= null;
		metodoPagamento="";
		dataPagamento= null;
	}

	public int getStudente() {
		return studente;
	}

	public void setStudente(int studente) {
		this.studente = studente;
	}

	public int getPercorsoFormativo() {
		return percorsoFormativo;
	}

	public void setPercorsoFormativo(int percorsoFormativo) {
		this.percorsoFormativo = percorsoFormativo;
	}

	public String getGiorno() {
		return giorno;
	}

	public void setGiorno(String giorno) {
		this.giorno = giorno;
	}

	public LocalTime getOrario() {
		return orario;
	}

	public void setOrario(LocalTime orario) {
		this.orario = orario;
	}

	public String getMetodoPagamento() {
		return metodoPagamento;
	}

	public void setMetodoPagamento(String metodoPagamento) {
		this.metodoPagamento = metodoPagamento;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	@Override
	public String toString() {
		return "Iscrizione [studente=" + studente + ", percorsoFormativo=" + percorsoFormativo + ", giorno="
				+ giorno + ", orario=" + orario + ", metodoPagamento=" + metodoPagamento + ", dataPagamento="
				+ dataPagamento + "]";
	}
	
	
	
	
	
	

}
