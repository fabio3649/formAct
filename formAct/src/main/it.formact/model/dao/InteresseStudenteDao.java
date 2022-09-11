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

import model.entity.InteresseStudenteEntity;
import model.entity.PreferenzaStudenteEntity;

public class InteresseStudenteDao {
	
	
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

	private static final String TABLE_NAME = "interesse_studente";
 
 
	
	public void doSave(Object bean) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		InteresseStudenteEntity pref = (InteresseStudenteEntity) bean;
		
		String insertSQL = "INSERT INTO " + InteresseStudenteDao.TABLE_NAME
				+ " (STUDENTE, INTERESSE)"
				+ " VALUES (?, ?)";
		int id = 0;
		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setInt(1, pref.getStudente());
			preparedStatement.setInt(2, pref.getInteresse());
			id = 
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


	public boolean doDelete(int studente , int interesse) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
	
	     
		int result = 0;
	
		String deleteSQL = "DELETE FROM " + InteresseStudenteDao.TABLE_NAME + " WHERE STUDENTE = ? AND INTERESSE = ?";
	    
		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setInt(1, studente);
			preparedStatement.setInt(2, interesse);
	
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
public boolean isContent(int idInteresse,int idStudente) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		InteresseStudenteEntity bean= new InteresseStudenteEntity();
		
		String selectSQL = "SELECT * FROM " + InteresseStudenteDao.TABLE_NAME + " WHERE STUDENTE=? AND INTERESSE= ?";
		
		try {
		connection = getConnection();
		preparedStatement = connection.prepareStatement(selectSQL);
		preparedStatement.setInt(1, idStudente);
		preparedStatement.setInt(2, idInteresse);
		
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			bean.setInteresse(rs.getInt("INTERESSE"));
			bean.setStudente(rs.getInt("STUDENTE"));
		
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
		
		if(bean.getInteresse() == 0)
			return false;
	
		return true;
	}
	
	public ArrayList<InteresseStudenteEntity> doRetrieveAll() throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
	
		ArrayList<InteresseStudenteEntity> prefs = new ArrayList<InteresseStudenteEntity>();
		ResultSet rs = null;
		String selectSQL = "SELECT * FROM " + InteresseStudenteDao.TABLE_NAME;
				
		if (prefs != null && !prefs.equals("")) {
			selectSQL += " ORDER BY STUDENTE";  
		}
	
		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
		
	
			rs = preparedStatement.executeQuery();
	
			while (rs.next()) {
				
				InteresseStudenteEntity bean = new InteresseStudenteEntity();
				bean.setStudente(rs.getInt("STUDENTE"));
				bean.setInteresse(rs.getInt("INTERESSE"));
				prefs.add(bean);
			}
	
		} finally {
			closeResultSet(rs);
			closePreparedStatement(preparedStatement);
			closeConnection(connection);
		}
		return prefs;
	}

	
	/**
	 * Il seguente metodo restituisce gli interessi dello studente.
	 * 
	 * @param idStudente: identificativo studente
	 * @return gli interessi dello studente
	 * @throws SQLException
	 */
	public ArrayList<String> doRetrieveInteressiStudente(int idStudente) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		ArrayList<String> interessiStudente = new ArrayList<>();
		
		String selectSQL = "SELECT interesse.nome FROM interesse_studente, interesse ";
		selectSQL += " WHERE ";
		selectSQL += " interesse_studente.interesse = interesse.idinteresse";
		selectSQL += " AND interesse_studente.studente = ?";
		
		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, idStudente);
			
			 rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				String nomeInteresse = rs.getString("nome");
				
				interessiStudente.add(nomeInteresse);
			}
		}
		finally {
			closeResultSet(rs);
			closePreparedStatement(preparedStatement);
			closeConnection(connection);
		}
		
		
		// ritorno un array di interessi
		return interessiStudente;
	}
	
	public InteresseStudenteEntity doRetrieveByKeys(int studente , int interesse) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		 InteresseStudenteEntity bean  = new InteresseStudenteEntity();
		
		String selectSQL = "SELECT * FROM interesse_studente ";
		selectSQL += " WHERE STUDENTE = ? AND INTERESSE = ? ";
		
		
		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, studente);
			preparedStatement.setInt(2, interesse);
			
			rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				
				bean.setStudente(rs.getInt("STUDENTE"));
				bean.setInteresse(rs.getInt("INTERESSE"));
			}
		}
	 finally {
		closeResultSet(rs);
		closePreparedStatement(preparedStatement);
		closeConnection(connection);
	}
	return bean;
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

 

