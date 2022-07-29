package model.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.time.LocalTime;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import model.entity.IscrizioneEntity;
import model.utils.Utils;


public class IscrizioneDao {
	
private Connection getConnection() throws SQLException{
		
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");

			 DataSource ds = (DataSource) envCtx.lookup("jdbc/formactds");
			 return ds.getConnection();
          
		} catch (NamingException e) {
			
			System.out.println("Error:" + e.getMessage());
			throw new SQLException(e);
		}
		
		
		
	}
                                    
	private static final String TABLE_NAME = "iscrizione";
	
	
		
	
		public void doSave(Object bean) throws SQLException {
			
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			IscrizioneEntity iscrizione = (IscrizioneEntity) bean;
			
			String insertSQL = "INSERT INTO " + IscrizioneDao.TABLE_NAME
					+ " (STUDENTE, PERCORSO_FORMATIVO, GIORNO, ORARIO, METODO_PAGAMENTO, DATA_PAGAMENTO)"
					+ " VALUES (?, ?, ?, ?, ?, ?)";
			
			try {
				connection = getConnection();
				preparedStatement = connection.prepareStatement(insertSQL);
				preparedStatement.setInt(1, iscrizione.getStudente());
				preparedStatement.setInt(2, iscrizione.getPercorsoFormativo());
				preparedStatement.setString(3, iscrizione.getGiorno());
				preparedStatement.setTime(4, Time.valueOf(iscrizione.getOrario()));
				preparedStatement.setString(5, iscrizione.getMetodoPagamento());
				if (iscrizione.getDataPagamento() == null)
					preparedStatement.setNull(6, java.sql.Types.VARCHAR);
				else
					preparedStatement.setString(6, Utils.toStringDate(iscrizione.getDataPagamento()));
				
				preparedStatement.executeUpdate();
				connection.setAutoCommit(false);
				connection.commit();
			} finally {
				try {
					if (preparedStatement != null)
						preparedStatement.close();
				} finally {
					if (connection != null)
						connection.close();
				}
			}
		}
			
		

		
		public boolean doDelete(int studente, int percorso) throws SQLException {   // eliminazione iscrizione per id studente, si dovrebbe eliminare 
																		//in automatico grazie ai vincoli delete on cascade su chiave esterna studente
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			 
		
	         
			int result = 0;

			String deleteSQL = "DELETE FROM " + IscrizioneDao.TABLE_NAME + " WHERE STUDENTE = ? AND PERCORSO_FORMATIVO = ?";
	        
			try {
				connection = getConnection();
				preparedStatement = connection.prepareStatement(deleteSQL);
				preparedStatement.setInt(1, studente);
				preparedStatement.setInt(2, percorso);

				result = preparedStatement.executeUpdate();

			} finally {
				try {
					if (preparedStatement != null)
						preparedStatement.close();
				} finally {
					if (connection != null)
						connection.close();
				}
			}
			return (result != 0);
		}
		

		/**
		 * 
		 * Modificato
		 * @throws ParseException 
		 * 
		 */
		public Object doRetrieveByStudent(int studente) throws SQLException, ParseException {    // restituisce una entry di iscrizione per id studente, #iscrizione di uno studente#
			Connection connection = null;
			PreparedStatement preparedStatement = null;
            
			
			
			ArrayList<IscrizioneEntity> iscrizioni = new ArrayList<IscrizioneEntity>();
	        
			String selectSQL = "SELECT * FROM " + IscrizioneDao.TABLE_NAME + " WHERE STUDENTE = ?";
			
			if (iscrizioni != null && !iscrizioni.equals("")) {
				selectSQL += " ORDER BY STUDENTE";  
			}

			try {
				connection = getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);
				preparedStatement.setInt(1, studente);

				ResultSet rs = preparedStatement.executeQuery();

				while (rs.next()) {
					
					IscrizioneEntity bean = new IscrizioneEntity();
					
					bean.setStudente(rs.getInt("STUDENTE"));
					bean.setPercorsoFormativo(rs.getInt("PERCORSO_FORMATIVO"));
					bean.setGiorno(rs.getString("GIORNO"));
					bean.setOrario(rs.getString("ORARIO"));
					bean.setMetodoPagamento(rs.getString("METODO_PAGAMENTO"));
					bean.setDataPagamento(Utils.toDate(rs.getString("DATA_PAGAMENTO")));
					iscrizioni.add(bean);
				    
				}

			} finally {
				try {
					if (preparedStatement != null)
						preparedStatement.close();
				} finally {
					if (connection != null)
						connection.close();
				}
			}
			return iscrizioni;
		}
		

		
		public ArrayList<IscrizioneEntity> doRetrieveAll() throws SQLException, ParseException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;

			ArrayList<IscrizioneEntity> iscrizioni = new ArrayList<IscrizioneEntity>();

			String selectSQL = "SELECT * FROM " + IscrizioneDao.TABLE_NAME;

			if (iscrizioni != null && !iscrizioni.equals("")) {
				selectSQL += " ORDER BY STUDENTE";  
			}

			try {
				connection = getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);

				ResultSet rs = preparedStatement.executeQuery();

				while (rs.next()) {
					
					IscrizioneEntity bean = new IscrizioneEntity();

					bean.setStudente(rs.getInt("STUDENTE"));
					bean.setPercorsoFormativo(rs.getInt("PERCORSO_FORMATIVO"));
					bean.setGiorno(rs.getString("GIORNO"));
					bean.setOrario(rs.getString("ORARIO"));
					bean.setMetodoPagamento(rs.getString("METODO_PAGAMENTO"));
					bean.setDataPagamento(Utils.toDate(rs.getString("DATA_PAGAMENTO")));
					iscrizioni.add(bean);
				}

			} finally {
				try {
					if (preparedStatement != null)
						preparedStatement.close();
				} finally {
					if (connection != null)
						connection.close();
				}
			}
			return iscrizioni;
		}
		
		public ArrayList<IscrizioneEntity> doRetrieveAllByDay(int studente, String giorno) throws SQLException, ParseException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;

			ArrayList<IscrizioneEntity> iscrizioni = new ArrayList<IscrizioneEntity>();

			String selectSQL = "SELECT DISTINCT * FROM " + IscrizioneDao.TABLE_NAME + " WHERE STUDENTE = ? AND GIORNO = ?"; 

			if (iscrizioni != null && !iscrizioni.equals("")) {
				selectSQL += " ORDER BY STUDENTE";  
			} 

			try {
				connection = getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);
				preparedStatement.setInt(1, studente);
				preparedStatement.setString(2, giorno);
				ResultSet rs = preparedStatement.executeQuery();
				



				while (rs.next()) {
					
					IscrizioneEntity bean = new IscrizioneEntity();

					bean.setStudente(rs.getInt("STUDENTE"));
					bean.setPercorsoFormativo(rs.getInt("PERCORSO_FORMATIVO"));
					bean.setGiorno(rs.getString("GIORNO"));
					bean.setOrario(rs.getString("ORARIO"));
					bean.setMetodoPagamento(rs.getString("METODO_PAGAMENTO"));
					bean.setDataPagamento(Utils.toDate(rs.getString("DATA_PAGAMENTO")));
					iscrizioni.add(bean);
				}

			} finally {
				try {
					if (preparedStatement != null)
						preparedStatement.close();
				} finally {
					if (connection != null)
						connection.close();
				}
			}
			return iscrizioni;
		}

		


		
		
		
	}
	
	


