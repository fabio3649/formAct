package model.entity;

import java.io.Serializable;

import model.utils.Utils;

public class CategoriaEntity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public int idCategoria;
	public String nome;
	public String descrizione;
	public String ambito;
	
	
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
	public boolean equals(Object o) {
		if( o instanceof CategoriaEntity) {
			CategoriaEntity e = (CategoriaEntity)o;		
			return idCategoria == e.idCategoria &&
				   (nome == e.nome || (nome != null && e.nome != null && nome.equalsIgnoreCase(e.nome))) &&
				   (descrizione == e.descrizione || (descrizione != null && e.descrizione != null && descrizione.equals(e.descrizione))) &&
				   (ambito == e.ambito || (ambito != null && e.ambito != null && ambito.equals(e.ambito))) ;
		}else
			return false;
	}

	@Override
	public String toString() {
		return "Categoria [idCategoria=" + idCategoria + ", nome=" + nome + ", descrizione=" + descrizione
				+ ", ambito=" + ambito +  "]";
	}

	
	
	

}
