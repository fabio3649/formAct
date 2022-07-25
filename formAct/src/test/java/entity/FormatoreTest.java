package entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.Date;

import org.junit.Test;

import model.entity.FormatoreEntity;

public class FormatoreTest {

	@Test
    public void testConstructor(){
        FormatoreEntity formatore= new FormatoreEntity();
        assertNotNull(formatore);
    }	
	
	@Test
	public void testId() {
		FormatoreEntity formatore= new FormatoreEntity();
		formatore.setId(1);
		assertEquals(1,formatore.getId());
	}
	
	@Test
	public void testEmail() {
		FormatoreEntity formatore= new FormatoreEntity();
		formatore.setEmail("aaaa@libero.it");;
		assertEquals("aaaa@libero.it",formatore.getEmail());
	}
	
	@Test
	public void testPassword() {
		FormatoreEntity formatore= new FormatoreEntity();
		formatore.setPassword("oooo");
		assertEquals("oooo",formatore.getPassword());
	}
	
	@Test
	public void testName() {
		FormatoreEntity formatore= new FormatoreEntity();
		formatore.setName("Paolo");
		assertEquals("Paolo",formatore.getName());
	}
	
	@Test
	public void testSurname() {
		FormatoreEntity formatore= new FormatoreEntity();
		formatore.setSurname("Anzalone");
		assertEquals("Anzalone",formatore.getSurname());
	}
	
	@Test
	public void testGender() {
		FormatoreEntity formatore= new FormatoreEntity();
		formatore.setGender("M");
		assertEquals("M",formatore.getGender());
	}
	
	@Test
	public void testBirthday() {
		Date d= new Date(2010-12-12);
		FormatoreEntity formatore= new FormatoreEntity();
		formatore.setBirthDate(d);
		assertEquals(d,formatore.getBirthDate());
	}
	
	@Test
	public void testCountry() {
		FormatoreEntity formatore= new FormatoreEntity();
		formatore.setCountry("Italia");
		assertEquals("Italia",formatore.getCountry());
	}
	
	@Test
	public void testCF() {
		FormatoreEntity formatore= new FormatoreEntity();
		formatore.setCodiceFiscale("AAAAA");
		assertEquals("AAAAA",formatore.getCodiceFiscale());
	}
	
	@Test
	public void testContoCorrente() {
		FormatoreEntity formatore= new FormatoreEntity();
		formatore.setContoCorrente("12234");
		assertEquals("12234",formatore.getContoCorrente());
	}
}
