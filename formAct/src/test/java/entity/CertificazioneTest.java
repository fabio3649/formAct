package entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;

import model.entity.CertificazioneEntity;
import model.entity.DisponibilitaEntity;

import java.sql.Date;
public class CertificazioneTest {
	
	@Test
    public void testConstructor(){
        CertificazioneEntity certificazione= new CertificazioneEntity();
        assertNotNull(certificazione);
    }
	
	@Test
	public void testId() {
		CertificazioneEntity certificazione = new CertificazioneEntity();
		certificazione.setIdCertificazione(1);
		assertEquals(1,certificazione.getIdCertificazione());
	}
	
	@Test
	public void testFormatore() {
		CertificazioneEntity certificazione = new CertificazioneEntity();
		certificazione.setFormatore(1);
		assertEquals(1,certificazione.getFormatore());
	}
	
	@Test
	public void testNome() {
		CertificazioneEntity certificazione = new CertificazioneEntity();
		certificazione.setNome("Peppe");
		assertEquals("Peppe",certificazione.getNome());
	}
	
	@Test
	public void testTipologia() {
		CertificazioneEntity certificazione = new CertificazioneEntity();
		certificazione.setTipologia("PROGRAMMAZIONE");
		assertEquals("PROGRAMMAZIONE",certificazione.getTipologia());
		
	}
	
	@Test
	public void testIstituto() {
		CertificazioneEntity certificazione = new CertificazioneEntity();
		certificazione.setIstituto("UNINA");
		assertEquals("UNINA",certificazione.getIstituto());
	}
	
	@Test
	public void testDescrizione() {
		CertificazioneEntity certificazione = new CertificazioneEntity();
		certificazione.setDescrizione("CERTIFICATO");
		assertEquals("CERTIFICATO",certificazione.getDescrizione());
	}
	
	@Test
	public void testAnnoInizio() {
		Date d= new Date(2005,12,12);
		CertificazioneEntity certificazione = new CertificazioneEntity();
		certificazione.setAnnoInizio(d);
		assertEquals(d,certificazione.getAnnoInizio());
	}
	
	@Test
	public void testAnnoFine() {
		Date d= new Date(2010,11,11);
		CertificazioneEntity certificazione= new CertificazioneEntity();
		certificazione.setAnnoFine(d);
		assertEquals(d,certificazione.getAnnoFine());
	}
	
	
	
}

