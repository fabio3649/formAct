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
	
	public static String diff(FormatoreEntity e1, FormatoreEntity e2) {
		StringBuffer b = new StringBuffer();
		if( e1 == e2 ||(e1 != null && e2 != null && e1.equals(e2) ))
			b.append("Nessuna differenza");
		else if(( e1 != null && e2 == null ) || (e1 == null && e2 != null)) {
			b.append(e1 == null ? "Il primo è null, il secondo no" : "Il primo non è null, il secondo è null");
		}else {
			b.append("Differenza ==> ");
			if( e1.id != e2.id )
				b.append(" id differenti [").append(e1.id).append(",").append(e2.id).append("]");
			if(!(e1.email == e2.email || (e1.email != null && e2.email != null && e1.email.equalsIgnoreCase(e2.email))))
				b.append("Email differenti [").append(e1.email).append(",").append(e2.email).append("]");
			if(!(e1.password == e2.password || (e1.password != null && e2.password != null && e1.password.equals(e2.password))))
				b.append("Password differenti [").append(e1.password).append(",").append(e2.password).append("]");
			if(!(e1.name == e2.name || (e1.name != null && e2.name != null && e1.name.equals(e2.name))))
				b.append("Name differenti [").append(e1.name).append(",").append(e2.name).append("]");	
			if(!(e1.surname == e2.surname || (e1.surname != null && e2.surname != null && e1.surname.equals(e2.surname))))
				b.append("Surname differenti [").append(e1.surname).append(",").append(e2.surname).append("]");		
			if(!(e1.gender == e2.gender || (e1.gender != null && e2.gender != null && e1.gender.equals(e2.gender))))
				b.append("Gender differenti [").append(e1.gender).append(",").append(e2.gender).append("]");				
			if(!(e1.country == e2.country || (e1.country != null && e2.country != null && e1.country.equals(e2.country))))
				b.append("Country differenti [").append(e1.country).append(",").append(e2.country).append("]");	
			if(!(e1.codiceFiscale == e2.codiceFiscale || (e1.codiceFiscale != null && e2.codiceFiscale != null && e1.codiceFiscale.equals(e2.codiceFiscale))))
				b.append("CodiceFiscale differenti [").append(e1.codiceFiscale).append(",").append(e2.codiceFiscale).append("]");	
			if(!(e1.contoCorrente == e2.contoCorrente || (e1.contoCorrente != null && e2.contoCorrente != null && e1.contoCorrente.equals(e2.contoCorrente))))
				b.append("ContoCorrente differenti [").append(e1.contoCorrente).append(",").append(e2.contoCorrente).append("]");	
		
			if(! Utils.areDatesEqual(e1.birthDate,e2.birthDate))
				b.append("BirthDate differenti [").append(Utils.toStringDate(e1.birthDate)).append(",").append(Utils.toStringDate(e2.birthDate)).append("]");
		}
		return b.toString();
	}
	
	

}
