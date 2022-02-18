package model.entity;

import java.io.Serializable;

public class InteresseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	int idInteresse;
	String nome;
	
	
	public InteresseEntity() {
		idInteresse=0;
		nome="";
		
	}

	public int getIdInteresse() {
		return idInteresse;
	}

	public void setIdInteresse(int idInteresse) {
		this.idInteresse = idInteresse;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	

	@Override
	public String toString() {
		return "Interesse Studente [ nome=" + nome + " ]";
	}
	
	

}
