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

import model.entity.DisponibilitaEntity;
import model.entity.FormatoreEntity;
import model.entity.InteresseStudenteEntity;
import model.entity.PercorsoFormativoEntity;
import model.entity.PreferenzaStudenteEntity;

public class PreferenzaStudenteDao {
	
	
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
	
	     private static final String TABLE_NAME = "preferenza_studente";
	
	     
	
		public void doSave(PreferenzaStudenteEntity bean) throws SQLException {
				
				Connection connection = null;
				PreparedStatement preparedStatement = null;
				
				PreferenzaStudenteEntity pref = (PreferenzaStudenteEntity) bean;
				
				String insertSQL = "INSERT INTO " + PreferenzaStudenteDao.TABLE_NAME
						+ " (STUDENTE, DISPONIBILE)"
						+ " VALUES (?, ?)";
				
				try {
					connection = getConnection();
					preparedStatement = connection.prepareStatement(insertSQL);
					preparedStatement.setInt(1, pref.getStudente());
					preparedStatement.setString(2, pref.getDisponibilita());
					preparedStatement.executeUpdate();
					connection.setAutoCommit(false);
					connection.commit();
				} finally {
					closePreparedStatement(preparedStatement);
					closeConnection(connection);
				}
				
				
			}
			
		
		
		public boolean doDelete(int studente , String disponibile) throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			
		
	         
			int result = 0;

			String deleteSQL = "DELETE FROM " + PreferenzaStudenteDao.TABLE_NAME + " WHERE STUDENTE = ? AND DISPONIBILE = ?";
	        
			try {
				connection = getConnection();
				preparedStatement = connection.prepareStatement(deleteSQL);
				preparedStatement.setInt(1, studente);
				preparedStatement.setString(2, disponibile);

				result = preparedStatement.executeUpdate();

			} finally {
				closePreparedStatement(preparedStatement);
				closeConnection(connection);
			}
			return (result != 0);
		}
	
	
		public ArrayList<PreferenzaStudenteEntity> doRetrieveAll() throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ResultSet rs = null;
			ArrayList<PreferenzaStudenteEntity> prefs = new ArrayList<PreferenzaStudenteEntity>();

			String selectSQL = "SELECT * FROM " + PreferenzaStudenteDao.TABLE_NAME;
					
			if (prefs != null && !prefs.equals("")) {
				selectSQL += " ORDER BY STUDENTE";  
			}

			try {
				connection = getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);
			

				rs = preparedStatement.executeQuery();

				while (rs.next()) {
					
					PreferenzaStudenteEntity bean = new PreferenzaStudenteEntity();
					bean.setStudente(rs.getInt("STUDENTE"));
					bean.setDisponibilita(rs.getString("DISPONIBILE"));
					prefs.add(bean);
				}

			}  finally {
				closeResultSet(rs);
				closePreparedStatement(preparedStatement);
				closeConnection(connection);
			}
			return prefs;
		}
		
		public ArrayList<PreferenzaStudenteEntity> doRetrieveAllByStudent(int studente) throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ResultSet rs = null;
			
			
			ArrayList<PreferenzaStudenteEntity> prefs = new ArrayList<PreferenzaStudenteEntity>();
	        
			String selectSQL = "SELECT * FROM " + PreferenzaStudenteDao.TABLE_NAME + " WHERE STUDENTE = ?";

			try {
				connection = getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);
				preparedStatement.setInt(1, studente);

				rs = preparedStatement.executeQuery();

				while (rs.next()) {
					
					PreferenzaStudenteEntity bean = new PreferenzaStudenteEntity();
					bean.setStudente(rs.getInt("STUDENTE"));
					bean.setDisponibilita(rs.getString("DISPONIBILE"));
					prefs.add(bean);
		            
				    
				}

			}  finally {
				closeResultSet(rs);
				closePreparedStatement(preparedStatement);
				closeConnection(connection);
			}
			return prefs;
		}
		
		public ArrayList<PreferenzaStudenteEntity> doRetrieveByStudent(int studente) throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ResultSet rs = null;
			
			
			String selectSQL = "SELECT * FROM " + PreferenzaStudenteDao.TABLE_NAME + " WHERE STUDENTE = ?";
			
			ArrayList<PreferenzaStudenteEntity> preferenze = new ArrayList<>();
			try {
				connection = getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);
				preparedStatement.setInt(1, studente);

				rs = preparedStatement.executeQuery();
				
				while (rs.next()) {
					PreferenzaStudenteEntity bean = new PreferenzaStudenteEntity();					
					
					bean.setStudente(rs.getInt("STUDENTE"));
					bean.setDisponibilita(rs.getString("DISPONIBILE"));
					
		            preferenze.add(bean);
				}

			}  finally {
				closeResultSet(rs);
				closePreparedStatement(preparedStatement);
				closeConnection(connection);
			}
			return preferenze;
		}
		
		public PreferenzaStudenteEntity doRetrieveByKeys(int studente, String giorno) throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ResultSet rs = null;
			
			
			PreferenzaStudenteEntity bean = new PreferenzaStudenteEntity();
			String selectSQL = "SELECT * FROM " + PreferenzaStudenteDao.TABLE_NAME + " WHERE STUDENTE = ?"
					+ " AND DISPONIBILE = ? ";

			try {
				connection = getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);
				preparedStatement.setInt(1, studente);
				preparedStatement.setString(2, giorno);

				rs = preparedStatement.executeQuery();

				while (rs.next()) {
					
					
					bean.setStudente(rs.getInt("STUDENTE"));
					bean.setDisponibilita(rs.getString("DISPONIBILE"));
					
		            
				}

			}  finally {
				closeResultSet(rs);
				closePreparedStatement(preparedStatement);
				closeConnection(connection);
			}
			return bean;
		}
		
		public boolean isContent(int studente, String giorno) throws SQLException {
			
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			
			PreferenzaStudenteEntity bean= new PreferenzaStudenteEntity();
			
			String selectSQL = "SELECT * FROM " + PreferenzaStudenteDao.TABLE_NAME + " WHERE STUDENTE=? AND DISPONIBILE= ?";
			
			try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, studente);
			preparedStatement.setString(2, giorno);
			
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				bean.setStudente(rs.getInt("STUDENTE"));
				bean.setDisponibilita(rs.getString("DISPONIBILE"));;
			
			}

			}catch(SQLException e) {
				
				
			}finally {
				try {
					if (preparedStatement != null)
						preparedStatement.close();
				} finally {
					if (connection != null)
						connection.close();
				}
			}
			
			if(bean.getStudente()==0)
				return false;
		
			return true;
		}
		
		protected final void closeConnection(Connection c) {
			if( c != null)
				try {
					c.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		
		protected final void closePreparedStatement(PreparedStatement p) {
			if( p != null)
				try {
					p.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
		protected final void closeResultSet(ResultSet rs) {
			if( rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}			
		}


		


	
		
		
       

 }
