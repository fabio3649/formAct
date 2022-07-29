package serviziutente.service;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.control.Action;
import controller.control.Service;
import model.dao.DisponibilitaDao;
import model.dao.IscrizioneDao;
import model.dao.PercorsoFormativoDao;
import model.entity.DisponibilitaEntity;
import model.entity.IscrizioneEntity;
import model.entity.PercorsoFormativoEntity;

public class Settimana {
	
	PercorsoFormativoDao daoP = new PercorsoFormativoDao();
	DisponibilitaDao daoDisp = new DisponibilitaDao();
	IscrizioneDao daoIsc = new IscrizioneDao();
	ArrayList<IscrizioneEntity> iscrizioni ; 
	ArrayList<DisponibilitaEntity> disps;
	
	
	
	public Settimana() throws SQLException {
		
		
		
	}
	 
	
		
		public ArrayList<Calendario> getLunedi(int studente) throws SQLException, ParseException {
			
			iscrizioni = (ArrayList<IscrizioneEntity>) daoIsc.doRetrieveAllByDay(studente, "lunedì");
			ArrayList<Calendario> c = new ArrayList<Calendario>();
			for( int i=0;i< iscrizioni.size(); i++) {
				 PercorsoFormativoEntity p = new PercorsoFormativoEntity();
				 p =  (PercorsoFormativoEntity) daoP.doRetrieveByKey(iscrizioni.get(i).getPercorsoFormativo());
				 c.add(new Calendario(p.getNome(),iscrizioni.get(i).getOrario() , iscrizioni.get(i).getStudente()));
			}
			
			
			 
			return c;
		}
	
		public ArrayList<Calendario> getMartedi(int studente) throws SQLException, ParseException {
				
				iscrizioni = (ArrayList<IscrizioneEntity>) daoIsc.doRetrieveAllByDay(studente, "martedì");
				ArrayList<Calendario> c = new ArrayList<Calendario>();
				for( int i=0;i< iscrizioni.size(); i++) {
					 PercorsoFormativoEntity p = new PercorsoFormativoEntity();
					 p =  (PercorsoFormativoEntity) daoP.doRetrieveByKey(iscrizioni.get(i).getPercorsoFormativo());
					 c.add(new Calendario(p.getNome(),iscrizioni.get(i).getOrario(), iscrizioni.get(i).getStudente()));
				}
				
				
				
				return c;
			}
		
		public ArrayList<Calendario> getMercoledi(int studente) throws SQLException, ParseException {
			
			iscrizioni = (ArrayList<IscrizioneEntity>) daoIsc.doRetrieveAllByDay(studente, "mercoledì");
			ArrayList<Calendario> c = new ArrayList<Calendario>();
			for( int i=0;i< iscrizioni.size(); i++) {
				 PercorsoFormativoEntity p = new PercorsoFormativoEntity();
				 p =  (PercorsoFormativoEntity) daoP.doRetrieveByKey(iscrizioni.get(i).getPercorsoFormativo());
				 c.add(new Calendario(p.getNome(),iscrizioni.get(i).getOrario(), iscrizioni.get(i).getStudente()));
			}
			
			
			
			return c;
		}
		
		public ArrayList<Calendario> getGiovedi(int studente) throws SQLException, ParseException {
			
			iscrizioni = (ArrayList<IscrizioneEntity>) daoIsc.doRetrieveAllByDay(studente, "giovedì");
			ArrayList<Calendario> c = new ArrayList<Calendario>();
			for( int i=0;i< iscrizioni.size(); i++) {
				 PercorsoFormativoEntity p = new PercorsoFormativoEntity();
				 p =  (PercorsoFormativoEntity) daoP.doRetrieveByKey(iscrizioni.get(i).getPercorsoFormativo());
				 c.add(new Calendario(p.getNome(),iscrizioni.get(i).getOrario(), iscrizioni.get(i).getStudente()));
			}
			
			
			
			return c;
		}
		
		public ArrayList<Calendario> getVenerdi(int studente) throws SQLException, ParseException {
			
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
