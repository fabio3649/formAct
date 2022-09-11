package model.entity;

import java.io.Serializable;
import java.sql.Date;
import java.text.ParseException;

import model.utils.Utils;

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
	
	public void setBirthDate(String s) throws ParseException {
		setBirthDate(Utils.toDate(s));
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
	public boolean equals(Object o) {
		if( o instanceof FormatoreEntity) {
			FormatoreEntity e = (FormatoreEntity)o;		
			return id == e.id &&
				   (email == e.email || (email != null && e.email != null && email.equalsIgnoreCase(e.email))) &&
				   (password == e.password || (password != null && e.password != null && password.equals(e.password))) &&
				   (name == e.name || (name != null && e.name != null && name.equals(e.name))) &&
				   (surname == e.surname || (surname != null && e.surname != null && surname.equals(e.surname))) &&
				   (gender == e.gender || (gender != null && e.gender != null && gender.equals(e.gender))) &&
				   (country == e.country || (country != null && e.country != null && country.equals(e.country))) &&
				   (codiceFiscale == e.codiceFiscale || (codiceFiscale != null && e.codiceFiscale != null && codiceFiscale.equals(e.codiceFiscale))) &&		
				   (contoCorrente == e.contoCorrente || (contoCorrente != null && e.contoCorrente != null && contoCorrente.equals(e.contoCorrente))) &&
				   Utils.areDatesEqual(birthDate,e.birthDate);
		}else
			return false;
	}

	@Override
	public String toString() {
		return "Formatore [id=" + id + ", email=" + email + ", password=" + password + ", name=" + name
				+ ", surname=" + surname + ", gender=" + gender + ", birthDate=" + birthDate + ", country=" + country
				+ ", codiceFiscale=" + codiceFiscale + ", contoCorrente=" + contoCorrente + "]";
	}
	
	
	
	

}
