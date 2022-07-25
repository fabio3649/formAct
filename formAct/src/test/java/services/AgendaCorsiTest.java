package services;

import java.util.ArrayList;

import org.junit.Before;

import model.dao.DisponibilitaDao;
import model.dao.IscrizioneDao;
import model.dao.PercorsoFormativoDao;
import model.entity.DisponibilitaEntity;
import model.entity.IscrizioneEntity;

public class AgendaCorsiTest {
	
	PercorsoFormativoDao daoP ;
	DisponibilitaDao daoDisp ;
	IscrizioneDao daoIsc ;
	ArrayList<IscrizioneEntity> iscrizioni ; 
	ArrayList<DisponibilitaEntity> disps;
	
	
	void setupNaming() {
		System.setProperty("java.naming.factory.initial","testUtility.MyContextFactory");

		
	}
	
	
	
	@Before
	public void setUp() {
		
		setupNaming();
		daoP = new PercorsoFormativoDao();
		daoDisp = new DisponibilitaDao();
		daoIsc = new IscrizioneDao();
		
	}
	
	

}
