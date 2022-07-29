package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalTime;

import org.junit.Before;
import org.junit.Test;

import model.dao.DisponibilitaDao;
import model.entity.DisponibilitaEntity;

public class DisponibilitaDaoTest {

	private DisponibilitaDao dao;
	private DisponibilitaEntity disp;
	
	void setupNaming() {
		System.setProperty("java.naming.factory.initial","testUtility.MyContextFactory");
		
	}
	
	@Before
	public void setUp() throws SQLException, ParseException {
		
		setupNaming();
		dao = new DisponibilitaDao();
		disp = new DisponibilitaEntity();
		
		disp.setGiornoSettimana("martedì");
		disp.setOrario("10:00");
		disp.setStato(1);
		disp.setIdPercorso(1); 
		
		
		
	}
	
	@Test
	public void doSaveTest() throws SQLException {
		
		dao.doSave(disp);
		
		DisponibilitaEntity result = (DisponibilitaEntity) dao.doRetrieveByKey(disp.getIdDisp());
		boolean value = result.equals(disp);
		
		assertEquals(value,true);
		
	}
	
	
	
	
}
