package model;

import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.SQLException;
import java.text.ParseException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import model.dao.InteresseStudenteDao;
import model.entity.InteresseStudenteEntity;

public class InteresseStudenteDaoTest extends Mockito{
	
	private InteresseStudenteDao iDao;
	private InteresseStudenteEntity ent;

	
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	
	void setupNaming() {
		System.setProperty("java.naming.factory.initial","testUtility.MyContextFactory");

		
	}
	
	@Before
	public void setUp() throws SQLException, ParseException {
		System.setErr(new PrintStream(errContent));
		setupNaming();
		
		iDao = new InteresseStudenteDao();
		ent = new InteresseStudenteEntity();
		
		ent.setInteresse(1);
		ent.setStudente(1);
		
		
	}
	
	@Test
	public void testDoSave() throws SQLException {
		
		iDao.doSave(ent);
		
		InteresseStudenteEntity i = iDao.doRetrieveByKeys(1,1);
		boolean value = i.equals(ent);
		
		assertTrue(value);
		
	}
}
