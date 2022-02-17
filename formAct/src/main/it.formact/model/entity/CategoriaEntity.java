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
	int percorso;
	
	public CategoriaEntity() {
		
		idCategoria= 0;
		nome="";
		descrizione="";
		ambito="";
		percorso=0;
		
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

	public int getPercorso() {
		return percorso;
	}

	public void setPercorso(int percorso) {
		this.percorso = percorso;
	}

	@Override
	public String toString() {
		return "Categoria [idCategoria=" + idCategoria + ", nome=" + nome + ", descrizione=" + descrizione
				+ ", ambito=" + ambito + ", percorso=" + percorso + "]";
	}

	
	
	

}
