package model.entity;

import java.io.Serializable;



public class PercorsoFormativoEntity implements Serializable{
	
	
	
	private int id;
	private int id_formatore;
	private String nome;
	private String descrizione;
	private int categoria;
	private String indice_contenuti;
	private int num_lezioni;
	private double costo;
	
	
	
	
	public PercorsoFormativoEntity() {
		id = 0;
		id_formatore = 0;
		nome = "";
		descrizione = "";
		categoria = 0;
		indice_contenuti = "";
		num_lezioni = 0;
		costo = 0;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId_formatore() {
		return id_formatore;
	}
	public void setId_formatore(int id_formatore) {
		this.id_formatore = id_formatore;
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
	public int getCategoria() {
		return categoria;
	}
	public void setCategoria(int categoria) {
		this.categoria = categoria;
	}
	public String getIndice_contenuti() {
		return indice_contenuti;
	}
	public void setIndice_contenuti(String indice_contenuti) {
		this.indice_contenuti = indice_contenuti;
	}
	public int getNum_lezioni() {
		return num_lezioni;
	}
	public void setNum_lezioni(int num_lezioni) {
		this.num_lezioni = num_lezioni;
	}
	public double getCosto() {
		return costo;
	}
	public void setCosto(double costo) {
		this.costo = costo;
	}
	@Override
	public String toString() {
		return "PercorsoFormativo [id=" + id + ", id_formatore=" + id_formatore + ", nome=" + nome
				+ ", descrizione=" + descrizione + ", categoria=" + categoria + ", indice_contenuti=" + indice_contenuti
				+ ", num_lezioni=" + num_lezioni + ", costo=" + costo + "]";
	}
	
	

}
