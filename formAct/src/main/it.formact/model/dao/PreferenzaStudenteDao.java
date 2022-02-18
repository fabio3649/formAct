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
import model.entity.FormatoreEntity;
import model.entity.PercorsoFormativoEntity;
import model.entity.PreferenzaStudenteEntity;

public class PreferenzaStudenteDao implements DaoInterface{
	
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
	
	
		public ArrayList<PreferenzaStudenteEntity> doRetrieveAll() throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;

			ArrayList<PreferenzaStudenteEntity> prefs = new ArrayList<PreferenzaStudenteEntity>();

			String selectSQL = "SELECT * FROM " + PreferenzaStudenteDao.TABLE_NAME;
					
			if (prefs != null && !prefs.equals("")) {
				selectSQL += " ORDER BY STUDENTE";  
			}

			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);
			

				ResultSet rs = preparedStatement.executeQuery();

				while (rs.next()) {
					
					PreferenzaStudenteEntity bean = new PreferenzaStudenteEntity();
					bean.setStudente(rs.getInt("STUDENTE"));
					bean.setDisponibilita(rs.getInt("DISPONIBILE"));
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
		
		public Object doRetrieveByStudent(int studente) throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;

			
			
			ArrayList<PreferenzaStudenteEntity> prefs = new ArrayList<PreferenzaStudenteEntity>();
	        
			String selectSQL = "SELECT * FROM " + PreferenzaStudenteDao.TABLE_NAME + " WHERE STUDENTE = ?";

			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);
				preparedStatement.setInt(1, studente);

				ResultSet rs = preparedStatement.executeQuery();

				while (rs.next()) {
					
					PreferenzaStudenteEntity bean = new PreferenzaStudenteEntity();
					bean.setStudente(rs.getInt("STUDENTE"));
					bean.setDisponibilita(rs.getInt("DISPONIBILE"));
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


		@Override
		public boolean doDelete(int id) throws SQLException {
			// TODO Auto-generated method stub
			return false;
		}


		@Override
		public Object doRetrieveByKey(int id) throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}
		
		
		
       

 }
