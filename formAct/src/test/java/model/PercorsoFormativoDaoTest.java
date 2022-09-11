package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import model.dao.DisponibilitaDao;
import model.dao.FormatoreDao;
import model.dao.IscrizioneDao;
import model.dao.PercorsoFormativoDao;
import model.entity.DisponibilitaEntity;
import model.entity.FormatoreEntity;
import model.entity.IscrizioneEntity;
import model.entity.PercorsoFormativoEntity;
import model.utils.Utils;

public class PercorsoFormativoDaoTest {

	private PercorsoFormativoDao dao;
	private PercorsoFormativoEntity p;
	private DisponibilitaDao daoD;
	private FormatoreDao fDao;
	private IscrizioneDao iscDao;
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

	void setupNaming() {
		System.setProperty("java.naming.factory.initial","testUtility.MyContextFactory");
		System.setErr(new PrintStream(errContent));
		
	}

	
	@Before
	public void setUp() throws SQLException {
		
		setupNaming();
		dao = new PercorsoFormativoDao();
		daoD = new DisponibilitaDao();
		fDao = new FormatoreDao();
		iscDao = new IscrizioneDao();
		
		
		
	}
	
	
	@Test
	public void testRetrieveByCosto() throws SQLException {
		
		ArrayList<PercorsoFormativoEntity> list = new ArrayList<PercorsoFormativoEntity>();
		
		list = dao.doRetrieveByCosto(10.00);
		System.out.println(list.toString());
		assertEquals(2,list.size());
		PercorsoFormativoEntity p1 = (PercorsoFormativoEntity) list.get(0);
		assertEquals("percorsoTestNuovo",p1.getNome());
		
		
	}
	
	@Test
	public void testRetrieveByString() throws SQLException {
		
		ArrayList<PercorsoFormativoEntity> list = new ArrayList<PercorsoFormativoEntity>();
		
		list = dao.doRetrieveByString("Test");
		System.out.println("query by String  " + list.toString());
		assertEquals(2,list.size());
		PercorsoFormativoEntity p1 = (PercorsoFormativoEntity) list.get(0);
		assertEquals("percorsoTestNuovo",p1.getNome());
		
		
	}
	
	
	@Test
	public void testRetrieveByName() throws SQLException {
		
		ArrayList<PercorsoFormativoEntity> list = new ArrayList<PercorsoFormativoEntity>();
		
		list = dao.doRetrieveByName("percorsoTestNuovo");
		System.out.println("query by Name  " +list.toString());
		assertEquals(1,list.size());
		
		PercorsoFormativoEntity p1 = (PercorsoFormativoEntity) list.get(0);
		
		assertEquals("percorsoTestNuovo",p1.getNome());
		
	}
	
	@Test
	public void testRetrieveByIdFormatore() throws SQLException {
		
		ArrayList<PercorsoFormativoEntity> list = new ArrayList<PercorsoFormativoEntity>();
		
		list = dao.doRetrieveByIdFormatore(1);
		System.out.println("query by idFormatore  " + list.toString());
		
		PercorsoFormativoEntity p1 = (PercorsoFormativoEntity) list.get(0);
		
		assertEquals(1,p1.getId_formatore());
		
		
		
	}
	
	@Test
	public void testRetrieveByNameFormatore() throws SQLException {
		
		ArrayList<PercorsoFormativoEntity> list = new ArrayList<PercorsoFormativoEntity>();
		
		list = dao.doRetrieveAllByFormatore("Test nome","test cognome");
		System.out.println("query by Name formatore  " +list.toString());
		PercorsoFormativoEntity p1 = (PercorsoFormativoEntity) list.get(0);
		FormatoreEntity f = (FormatoreEntity) fDao.doRetrieveByNameSurname("Test nome", "test cognome");
		assertEquals(1,p1.getId_formatore());
		assertEquals(1,f.getId());
		
		
	}
	
	@Test
	public void testRetrieveByCategoria() throws SQLException {
		
		ArrayList<PercorsoFormativoEntity> list = new ArrayList<PercorsoFormativoEntity>();
		
		list = dao.doRetrieveAllByCategory("programmazione web");
		System.out.println("query by categoria  " + list.toString());
		assertEquals(2,list.size());
		PercorsoFormativoEntity p1 = (PercorsoFormativoEntity) list.get(0);
		assertEquals("percorsoTestNuovo",p1.getNome());
		
		
	}
	
	@Test
	public void testRetrieveAvailable() throws SQLException {
		
		ArrayList<DisponibilitaEntity> list = new ArrayList<DisponibilitaEntity>();
		
		list = dao.doRetrieveAvailable();
		System.out.println("query by available  " + list.toString());
		assertEquals(1,list.size());
		DisponibilitaEntity p1 = (DisponibilitaEntity) list.get(0);
		assertEquals("martedì",p1.getGiornoSettimana());
		assertEquals(1,list.get(0).getIdPercorso());
		
		
	}
	
	@Test
	public void testRetrieveByGiorno() throws SQLException {
		
		ArrayList<PercorsoFormativoEntity> list = new ArrayList<PercorsoFormativoEntity>();
		
		list = dao.doRetrieveByGiorno("martedì");
		System.out.println("query by giorno  " + list.toString());
		assertEquals(1,list.size());
		PercorsoFormativoEntity p1 = (PercorsoFormativoEntity) list.get(0);
		assertEquals(1,p1.getId());
		
		
	}
	
	/*@Test
	public void testRetrieveByParams() throws SQLException {
		
		ArrayList<PercorsoFormativoEntity> list = new ArrayList<PercorsoFormativoEntity>();
		
		list = dao.doRetrieveAllByParams("test", "5.00" , "10.00" , "martedì");
		
		assertEquals(1,list.size());
		PercorsoFormativoEntity p1 = (PercorsoFormativoEntity) list.get(0);
		assertEquals(1,p1.getId());
		System.out.println("Percorso list  :  " + list.toString());
		
	}*/
	
	
	
	@Test
	public void testRetrieveByIscrizioni() throws SQLException, ParseException {
		
		ArrayList<PercorsoFormativoEntity> list = new ArrayList<PercorsoFormativoEntity>();
		
		IscrizioneEntity bean = new IscrizioneEntity();
		
		bean.setStudente(4);
		bean.setPercorsoFormativo(1);
		bean.setGiorno("martedì");
		bean.setOrario("10:00");
		bean.setMetodoPagamento("visa");
		java.sql.Date d = Utils.toDate("05-08-2022");
		bean.setDataPagamento(d);
		iscDao.doSave(bean);
		
		list = dao.doRetrieveIscrizioniStudente(4);
		System.out.println("query by iscrizioni  " + list.toString());
		assertEquals(1,list.size());
		PercorsoFormativoEntity p1 = (PercorsoFormativoEntity) list.get(0);
		assertEquals(1,p1.getId());
		
		
	}
	
	@Test
	public void testDoRetrieveAll() throws SQLException {
		
		ArrayList<PercorsoFormativoEntity> list = new ArrayList<PercorsoFormativoEntity>();
		
		list = dao.doRetrieveAll();
		
		assertEquals(2,list.size());
	}
	
	
	
	
	
}
