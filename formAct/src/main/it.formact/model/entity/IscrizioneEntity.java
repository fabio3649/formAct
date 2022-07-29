package model.entity;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;

public class IscrizioneEntity implements Serializable{
	
	
	static final long serialVersionUID = 1L;
	private int studente;
	private int percorsoFormativo;
	private String giorno;
	private String orario;
	private String metodoPagamento;
	private Date dataPagamento;
	
	public IscrizioneEntity() {
		
		studente= 0;
		percorsoFormativo = 0;
		giorno="";
		orario= "00:00";
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
    
	public String getOrario() {
		return orario;
	}

	public void setOrario(String orario) {
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
