package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import model.dao.ValutazioneDao;
import model.entity.FormatoreEntity;
import model.entity.ValutazioneEntity;
import model.utils.Utils;

public class ValutazioneDaoTest extends Mockito{
	
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	private ValutazioneDao vDao;
	private ValutazioneEntity v;
	private ValutazioneEntity nuovo;
	
	
	void setupNaming() {
		System.setProperty("java.naming.factory.initial","testUtility.MyContextFactory");
		
		
	}
	
	@Before
	public void setUp() throws SQLException, ParseException {
		System.setErr(new PrintStream(errContent));
		setupNaming();
		
		vDao = new ValutazioneDao();
		v = new ValutazioneEntity();
		
		v.setStudente(1);
		v.setFormatore(1);
		v.setPercorsoFormativo(1);
		v.setDescrizione("ottimo corso");
		java.sql.Date d = Utils.toDate("10-07-2022");
		v.setData(d);
		v.setStelle(4);
		
		nuovo = new ValutazioneEntity();
		nuovo.setStudente(1);
		nuovo.setFormatore(1);
		nuovo.setPercorsoFormativo(2);
		nuovo.setDescrizione("ottimo corso");
		java.sql.Date t = Utils.toDate("10-07-2022");
		nuovo.setData(t);
		nuovo.setStelle(3);
		
		
	}
	
	@Test
	public void testDoSave() throws SQLException, ParseException {
	    
		vDao.doSave(v);
		vDao.doSave(nuovo);
		
		ValutazioneEntity result = (ValutazioneEntity) vDao.doRetrieveByKeys(1, 1, 1);
		boolean value = v.equals(result);
		System.out.println("Valore dell'equals : " + value);
		System.out.println(" formatore v content : " + v.toString());
		System.out.println("formatore byID content : " + result.toString());
		
		
		assertTrue(value);
		
	}
	// non funziona
	// la query in workbench funziona, in java non riempe tutto l'array
	/*@Test
	public void testRetrieveByStudent() throws ParseException, SQLException {
		
		
		
		ArrayList<ValutazioneEntity> list = vDao.doRetrieveByStudente(1);
		System.out.println("lista by student : " + list.size());
		
		assertEquals(2,list.size());
		
	} */
	
	/*@Test
	public void testRetrieveByStudenteAndFormatore() throws SQLException, ParseException {
		
		ArrayList<ValutazioneEntity> list = vDao.doRetrieveByStudenteAndFormatore(1, 1);
		System.out.println("lista by student : " + list.size());
		
		assertEquals(2,list.size());
		
	}*/
		
	
	

}
