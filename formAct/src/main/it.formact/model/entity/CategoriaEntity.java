package model.entity;

import java.io.Serializable;

public class CategoriaEntity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	int idCategoria;
	String nome;
	String descrizione;
	String ambito;
	
	
	public CategoriaEntity() {
		
		idCategoria= 0;
		nome="";
		descrizione="";
		ambito="";
		
	}

	public int getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getAmbito() {
		return ambito;
	}

	public void setAmbito(String ambito) {
		this.ambito = ambito;
	}

	

	@Override
	public String toString() {
		return "Categoria [idCategoria=" + idCategoria + ", nome=" + nome + ", descrizione=" + descrizione
				+ ", ambito=" + ambito +  "]";
	}

	
	
	

}
