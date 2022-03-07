package model.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.Date;

import org.junit.Test;

public class StudenteTest {
	
	//Constructor
	
	@Test
	public void testStudenteConstructor()  {
		StudenteEntity s = new StudenteEntity();
		
		assertNotNull(s);
	}
	
	//GETTERS AND SETTERS
	
	@Test
	public void testGetSetID() {
		StudenteEntity s = new StudenteEntity();
		
		s.setId(1);
		
		assertEquals(1, s.getId());
	}
	
	@Test
	public void testGetSetName() {
		StudenteEntity s = new StudenteEntity();
		
		s.setName("Fabio");
		
		assertEquals("Fabio", s.getName());
		
	}
	
	@Test
	public void testGetSetSurname() {
		StudenteEntity s = new StudenteEntity();
		
		s.setSurname("Picariello");
		
		assertEquals("Picariello", s.getSurname());
	}
	
	@Test
	public void testGetSetEmail() {
		StudenteEntity s = new StudenteEntity();
		
		s.setEmail("fab@pica.it");
		
		assertEquals("fab@pica.it", s.getEmail());
	}
	
	@Test
	public void testGetSetPassword() {
		StudenteEntity s = new StudenteEntity();
		
		s.setPassword("08951asd");
		
		assertEquals("08951asd", s.getPassword());
	}
	
	@Test
	public void testGetSetGender() {
		StudenteEntity s = new StudenteEntity();
		
		s.setGender("M");
		
		assertEquals("M" , s.getGender());
	}
	
	@Test
	public void testGetSetBirthdate() {
		StudenteEntity s = new StudenteEntity();
		Date d = new Date(1995-9-10);
		
		s.setBirthDate(d);
		
		assertEquals(d, s.getBirthDate());
	}
	
	@Test
	public void testGetSetCountry() {
		StudenteEntity s = new StudenteEntity();
		
		s.setCountry("Italia");
		
		assertEquals("Italia" , s.getCountry());
	}
	
	//toString()
	
	@Test
	public void testToString() {
		StudenteEntity s = new StudenteEntity();
		String expectedValue = "User [id=" + s.getId() + ", email=" + s.getEmail() + ", password=" + s.getPassword() + ", name=" + s.getName() + ", surname="
				+ s.getSurname() + ", gender=" + s.getGender() + ", birthDate=" + s.getBirthDate() + ", country=" + s.getCountry() + "]";
		
		String actualValue = s.toString();
		
		assertEquals( expectedValue , actualValue);
	
	}

}
