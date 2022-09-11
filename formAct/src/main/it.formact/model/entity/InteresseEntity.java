package model.entity;

import java.io.Serializable;

import model.utils.Utils;

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
	public boolean equals(Object o) {
		if( o instanceof InteresseEntity) {
			InteresseEntity e = (InteresseEntity)o;		
			return idInteresse == e.idInteresse &&
				   (nome == e.nome || (nome != null && e.nome != null && nome.equalsIgnoreCase(e.nome)));
		}else
			return false;
	}
	

	@Override
	public String toString() {
		return "Interesse Studente [ nome=" + nome + " ]";
	}
	
	

}
