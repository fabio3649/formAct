package model.entity;

import java.io.Serializable;
import java.sql.Date;

public class FormatoreEntity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	int id;
	String email;
	String password;
	String name;
	String surname;
	String gender;
	Date birthDate;
	String country;
	String codiceFiscale;
	String contoCorrente;
	
	
	public FormatoreEntity() {
		
		id = 0;
		email="";
		password="";
		name="";
		surname="";
		gender="";
		birthDate= null;
		country="";
		codiceFiscale="";
		contoCorrente="";
		
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCodiceFiscale() {
		return codiceFiscale;
	}
	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}
	public String getContoCorrente() {
		return contoCorrente;
	}
	public void setContoCorrente(String contoCorrente) {
		this.contoCorrente = contoCorrente;
	}
	@Override
	public String toString() {
		return "Formatore [id=" + id + ", email=" + email + ", password=" + password + ", name=" + name
				+ ", surname=" + surname + ", gender=" + gender + ", birthDate=" + birthDate + ", country=" + country
				+ ", codiceFiscale=" + codiceFiscale + ", contoCorrente=" + contoCorrente + "]";
	}
	
	

}
