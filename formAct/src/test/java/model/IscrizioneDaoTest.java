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

import model.dao.IscrizioneDao;
import model.entity.IscrizioneEntity;
import model.utils.Utils;

public class IscrizioneDaoTest extends Mockito{

	
	private IscrizioneDao isc;
	
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	
	void setupNaming() {
		System.setProperty("java.naming.factory.initial","testUtility.MyContextFactory");

		
	}
	
	
	@Before
	public void setUp() throws SQLException, ParseException {
		System.setErr(new PrintStream(errContent));
		setupNaming();
		isc = new IscrizioneDao();
		
		

	
	}
	
	@Test
	public void testRetrieveAll() throws SQLException, ParseException {
		
		
		ArrayList<IscrizioneEntity> list = new ArrayList<IscrizioneEntity>();
		
		list = isc.doRetrieveAll();
		
		assertEquals(2,list.size());
	}
	
	@Test
	public void testRetrieveAllByDay() throws SQLException, ParseException {
		
		ArrayList<IscrizioneEntity> list = new ArrayList<IscrizioneEntity>();
		
		list = isc.doRetrieveAllByDay(1, "venerdì");
		
		assertEquals(1,list.size());
		
	}
	
	
	
	
}
