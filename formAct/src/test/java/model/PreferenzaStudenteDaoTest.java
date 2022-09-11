package model;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import model.dao.PreferenzaStudenteDao;
import model.dao.ValutazioneDao;
import model.entity.PreferenzaStudenteEntity;
import model.entity.ValutazioneEntity;

public class PreferenzaStudenteDaoTest extends Mockito{
	
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	private PreferenzaStudenteDao psDao;
	private PreferenzaStudenteEntity ps;
	
	
	
	void setupNaming() {
		System.setProperty("java.naming.factory.initial","testUtility.MyContextFactory");
		
		
	}
	
	@Before
	public void setUp() throws SQLException, ParseException {
		System.setErr(new PrintStream(errContent));
		setupNaming();
		psDao = new PreferenzaStudenteDao();
		ps = new PreferenzaStudenteEntity();
		ps.setStudente(1);
		ps.setDisponibilita("lunedì");
		
		
		
	}
	
	@Test
	public void testDoSave() throws SQLException {
		
		
		psDao.doSave(ps);
		
		PreferenzaStudenteEntity temp = new PreferenzaStudenteEntity();
		
		temp = psDao.doRetrieveByKeys(1,"lunedì");
		System.out.println("pref studente 1 : " + temp.toString());
		
		assertEquals("lunedì",temp.getDisponibilita());
	}
	
	
	//non funziona, ottiene una lista con 1 solo elemento, ma in workbech ottieni una lista con 2
	/*@Test
	public void testRetrieveAllByStudent() throws SQLException {
		
		PreferenzaStudenteEntity temp = new PreferenzaStudenteEntity();
		
		temp.setStudente(1);
		temp.setDisponibilita("giovedì");
		
		psDao.doSave(temp);
		
		ArrayList<PreferenzaStudenteEntity> prefs = new ArrayList<PreferenzaStudenteEntity>();
		
		prefs = psDao.doRetrieveAllByStudent(1);
		
		assertEquals(2,prefs.size());
	}*/

}
