package model.entity;

import java.io.Serializable;

import model.utils.Utils;



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
	public boolean equals(Object o) {
		if( o instanceof PercorsoFormativoEntity) {
			PercorsoFormativoEntity e = (PercorsoFormativoEntity)o;		
			return id == e.id &&
				   (id_formatore == e.id_formatore) &&
				   (nome == e.nome || (nome != null && e.nome != null && nome.equals(e.nome))) &&
				   (categoria == e.categoria) &&
				   (descrizione == e.descrizione || (descrizione != null && e.descrizione != null && descrizione.equals(e.descrizione))) &&
				   (indice_contenuti == e.indice_contenuti || (indice_contenuti != null && e.indice_contenuti != null && indice_contenuti.equals(e.indice_contenuti))) &&
				   (num_lezioni == e.num_lezioni) &&
				   (costo == e.costo) &&
				   (validate == e.validate);
		}else
			return false;
	}
	
	public PercorsoFormativoEntity clone() {
		
		PercorsoFormativoEntity copia = new PercorsoFormativoEntity();
		
		copia.setId(this.getId());
		copia.setId_formatore(this.getId_formatore());
		copia.setNome(this.getNome());
		copia.setCategoria(this.getCategoria());
		copia.setDescrizione(this.getDescrizione());
		copia.setIndice_contenuti(this.getIndice_contenuti());
		copia.setNum_lezioni(this.getNum_lezioni());
		copia.setCosto(this.getCosto());
		copia.setValidate(this.getValidate());
		
		return copia;
		
	}
	
	
	@Override
	public String toString() {
		return "PercorsoFormativo [id=" + id + ", id_formatore=" + id_formatore + ", nome=" + nome
				+ ", descrizione=" + descrizione + ", categoria=" + categoria + ", indice_contenuti=" + indice_contenuti
				+ ", num_lezioni=" + num_lezioni + ", costo=" + costo + "]";
	}
	
	

}
