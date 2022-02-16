package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import model.entity.Disponibilit‡Entity;
import model.entity.PercorsoFormativoEntity;
import model.entity.PreferenzaStudenteEntity;

public class PreferenzaStudenteDao {
	
		private static DataSource ds;
		    
			static {
				try {
					Context initCtx = new InitialContext();
					Context envCtx = (Context) initCtx.lookup("java:comp/env");
		
					ds = (DataSource) envCtx.lookup("jdbc/formactds");
		          
				} catch (NamingException e) {
					System.out.println("Error:" + e.getMessage());
				}
			}
	
	     private static final String TABLE_NAME = "preferenza_studente";
	
	     
	
		public void doSave(Object bean) throws SQLException {
				
				Connection connection = null;
				PreparedStatement preparedStatement = null;
				
				PreferenzaStudenteEntity pref = (PreferenzaStudenteEntity) bean;
				
				String insertSQL = "INSERT INTO " + PreferenzaStudenteDao.TABLE_NAME
						+ " (STUDENTE, DISPONIBILE)"
						+ " VALUES (?, ?)";
				
				try {
					connection = ds.getConnection();
					preparedStatement = connection.prepareStatement(insertSQL);
					preparedStatement.setInt(1, pref.getStudente());
					preparedStatement.setInt(2, pref.getDisponibilita());
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
		
		
		public boolean doDelete(int studente , int disponibile) throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			
		
	         
			int result = 0;

			String deleteSQL = "DELETE FROM " + PreferenzaStudenteDao.TABLE_NAME + " WHERE STUDENTE = ? AND DISPONIBILE = ?";
	        
			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(deleteSQL);
				preparedStatement.setInt(1, studente);
				preparedStatement.setInt(2, disponibile);

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
	
	
		public ArrayList<PreferenzaStudenteEntity> doRetrieveAllPrefsByStudent(String nome, String cognome) throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;

			ArrayList<PreferenzaStudenteEntity> prefs = new ArrayList<PreferenzaStudenteEntity>();

			String selectSQL = "SELECT studente.nome,disponibilit‡.giornosettimana,disponibilit‡.orario"
					+ " FROM preferenza_studente,disponibilit‡,studente"
					+ "WHERE studente.nome = ? AND studente.cognome = ? ";

			if (prefs != null && !prefs.equals("")) {
				selectSQL += " ORDER BY STUDENTE";  
			}

			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);
				preparedStatement.setString(1, nome);
				preparedStatement.setString(2, cognome);

				ResultSet rs = preparedStatement.executeQuery();

				while (rs.next()) {
					
					PreferenzaStudenteEntity bean = new PreferenzaStudenteEntity();
					bean.setNomeStudente(rs.getString("NOME"));
					bean.setGiorno(rs.getString("GIORNOSETTIMANA"));
					bean.setOrario(rs.getTime("ORARIO"));
					prefs.add(bean);
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
			return prefs;
		}
		
       

 }
