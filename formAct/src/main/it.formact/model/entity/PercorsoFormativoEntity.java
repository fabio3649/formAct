package model.entity;

import java.io.Serializable;



public class PercorsoFormativoEntity implements Serializable{
	
	
	
	private int id;
	private int id_formatore;
	private String nome;
	private int categoria;
	private String descrizione;
	private String indice_contenuti;
	private int num_lezioni;
	private double costo;
	private int validate;
	
	
	
	
	public PercorsoFormativoEntity() {
		id = 0;
		id_formatore = 0;
		nome = "";
		categoria = 0;
		descrizione = "";
		indice_contenuti = "";
		num_lezioni = 0;
		costo = 0;
		validate = 0;
	}
	
	
	public int getValidate() {
		return validate;
	}


	public void setValidate(int validate) {
		this.validate = validate;
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
