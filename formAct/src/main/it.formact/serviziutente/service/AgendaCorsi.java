package serviziutente.service;

import java.sql.SQLException;
import java.util.ArrayList;

import model.dao.DisponibilitàDao;
import model.dao.IscrizioneDao;
import model.dao.PercorsoFormativoDao;
import model.entity.DisponibilitàEntity;
import model.entity.IscrizioneEntity;
import model.entity.PercorsoFormativoEntity;

public class AgendaCorsi {
	
	PercorsoFormativoDao daoP = new PercorsoFormativoDao();
	DisponibilitàDao daoDisp = new DisponibilitàDao();
	IscrizioneDao daoIsc = new IscrizioneDao();
	ArrayList<IscrizioneEntity> iscrizioni ; 
	ArrayList<DisponibilitàEntity> disps;
	
	
	
	public AgendaCorsi() throws SQLException {
		
		
		
	}
	
	
		
		public ArrayList<Calendario> getLunedi(int studente) throws SQLException {
			
			iscrizioni = (ArrayList<IscrizioneEntity>) daoIsc.doRetrieveAllByDay(studente, "lunedì");
			ArrayList<Calendario> c = new ArrayList<Calendario>();
			for( int i=0;i< iscrizioni.size(); i++) {
				 PercorsoFormativoEntity p = new PercorsoFormativoEntity();
				 p =  (PercorsoFormativoEntity) daoP.doRetrieveByKey(iscrizioni.get(i).getPercorsoFormativo());
				 c.add(new Calendario(p.getNome(),iscrizioni.get(i).getOrario() , iscrizioni.get(i).getStudente()));
			}
			
			
			
			return c;
		}
	
		public ArrayList<Calendario> getMartedi(int studente) throws SQLException {
				
				iscrizioni = (ArrayList<IscrizioneEntity>) daoIsc.doRetrieveAllByDay(studente, "martedì");
				ArrayList<Calendario> c = new ArrayList<Calendario>();
				for( int i=0;i< iscrizioni.size(); i++) {
					 PercorsoFormativoEntity p = new PercorsoFormativoEntity();
					 p =  (PercorsoFormativoEntity) daoP.doRetrieveByKey(iscrizioni.get(i).getPercorsoFormativo());
					 c.add(new Calendario(p.getNome(),iscrizioni.get(i).getOrario(), iscrizioni.get(i).getStudente()));
				}
				
				
				
				return c;
			}
		
		public ArrayList<Calendario> getMercoledi(int studente) throws SQLException {
			
			iscrizioni = (ArrayList<IscrizioneEntity>) daoIsc.doRetrieveAllByDay(studente, "mercoledì");
			ArrayList<Calendario> c = new ArrayList<Calendario>();
			for( int i=0;i< iscrizioni.size(); i++) {
				 PercorsoFormativoEntity p = new PercorsoFormativoEntity();
				 p =  (PercorsoFormativoEntity) daoP.doRetrieveByKey(iscrizioni.get(i).getPercorsoFormativo());
				 c.add(new Calendario(p.getNome(),iscrizioni.get(i).getOrario(), iscrizioni.get(i).getStudente()));
			}
			
			
			
			return c;
		}
		
		public ArrayList<Calendario> getGiovedi(int studente) throws SQLException {
			
			iscrizioni = (ArrayList<IscrizioneEntity>) daoIsc.doRetrieveAllByDay(studente, "giovedì");
			ArrayList<Calendario> c = new ArrayList<Calendario>();
			for( int i=0;i< iscrizioni.size(); i++) {
				 PercorsoFormativoEntity p = new PercorsoFormativoEntity();
				 p =  (PercorsoFormativoEntity) daoP.doRetrieveByKey(iscrizioni.get(i).getPercorsoFormativo());
				 c.add(new Calendario(p.getNome(),iscrizioni.get(i).getOrario(), iscrizioni.get(i).getStudente()));
			}
			
			
			
			return c;
		}
		
		public ArrayList<Calendario> getVenerdi(int studente) throws SQLException {
			
			iscrizioni = (ArrayList<IscrizioneEntity>) daoIsc.doRetrieveAllByDay(studente, "venerdì");
			ArrayList<Calendario> c = new ArrayList<Calendario>();
			for( int i=0;i< iscrizioni.size(); i++) {
				 PercorsoFormativoEntity p = new PercorsoFormativoEntity();
				 p =  (PercorsoFormativoEntity) daoP.doRetrieveByKey(iscrizioni.get(i).getPercorsoFormativo());
				 c.add(new Calendario(p.getNome(),iscrizioni.get(i).getOrario(), iscrizioni.get(i).getStudente()));
			}
			
			
			
			return c;
		}
			
	
	

	
					
					
					
					
				}
