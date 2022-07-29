package serviziutente.service;

import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;

import model.dao.DisponibilitaDao;
import model.entity.DisponibilitaEntity;
import model.entity.PercorsoFormativoEntity;

public class Calendario {
	
	
	String nome;
	String time;
	int idStudente;
	
	public Calendario(String nome, String t, int studente) {
		this.nome=nome;
		this.time = t;
		this.idStudente = studente;
		
	}
	


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getTime() {
		return time;
	}


	public void setTime(String time) {
		this.time = time;
	}



	public int getIdStudente() {
		return idStudente;
	}



	public void setIdStudente(int idStudente) {
		this.idStudente = idStudente;
	}



	@Override
	public String toString() {
		return "Calendario [nome=" + nome + ", time=" + time + "]";
	}
	
	
	
	
	

}
