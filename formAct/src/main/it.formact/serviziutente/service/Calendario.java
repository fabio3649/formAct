package serviziutente.service;

import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;

import model.dao.Disponibilit‡Dao;
import model.entity.Disponibilit‡Entity;
import model.entity.PercorsoFormativoEntity;

public class Calendario {
	
	
	String nome;
	LocalTime time;
	int idStudente;
	
	public Calendario(String nome, LocalTime t, int studente) {
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


	public LocalTime getTime() {
		return time;
	}


	public void setTime(LocalTime time) {
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
