package model;

import static org.junit.Assert.assertTrue;

import java.io.PrintStream;
import java.sql.SQLException;
import java.text.ParseException;

import org.junit.Before;
import org.junit.Test;

import model.dao.CertificazioneDao;
import model.entity.CategoriaEntity;
import model.entity.CertificazioneEntity;
import model.utils.Utils;

public class CertificazioneDaoTest {
	
	private CertificazioneDao dao;
	private CertificazioneEntity c;
	
	
	
	void setupNaming() {
		System.setProperty("java.naming.factory.initial","testUtility.MyContextFactory");

		
	}
	
	@Before
	public void setUp() throws SQLException, ParseException {
		
		setupNaming();
		c= new CertificazioneEntity();
		dao = new CertificazioneDao();
		
		c.setIdCertificazione(123456);
		c.setFormatore(24);
		c.setNome("INGLESE B2");
		c.setTipologia("CERTIFICATO LINGUA INGLESE");
		c.setIstituto("KENNEDY");
		c.setDescrizione("CERTIFICATO LINGUA INGLESE EUROPEO LIVELLO INTERMEDIO");
		java.sql.Date d1 = Utils.toDate("10-02-2020");
		c.setAnnoInizio(d1);
		java.sql.Date d2 = Utils.toDate("15-07-2020");
		c.setAnnoFine(d2);
		
		
	}
	
	
	@Test
	public void testDoSave() throws SQLException {
		
		dao.doSave(c);
		CertificazioneEntity c1 = (CertificazioneEntity) dao.doRetrieveByKey(c.getIdCertificazione());
		boolean value = c.equals(c1);
		System.out.println("Valore dell'equals : " + value);
		System.out.println(" cert C content : " + c.toString() + "ID certificazione: " + c.getIdCertificazione());
		System.out.println("cert C1 byID content : " + c1.toString());
		
		
		assertTrue(value);
		
	}

}
