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

public class InteresseStudenteDao implements DaoInterface{
	
	
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

	private static final String TABLE_NAME = "interesse_studente";
 
 
	
	public void doSave(Object bean) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		InteresseStudenteEntity pref = (InteresseStudenteEntity) bean;
		
		String insertSQL = "INSERT INTO " + InteresseStudenteDao.TABLE_NAME
				+ " (STUDENTE, INTERESSE)"
				+ " VALUES (?, ?)";
		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setInt(1, pref.getStudente());
			preparedStatement.setInt(2, pref.getInteresse());
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
			connection = ds.getConnection();
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
	
	
	public ArrayList<InteresseStudenteEntity> doRetrieveAll() throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
	
		ArrayList<InteresseStudenteEntity> prefs = new ArrayList<InteresseStudenteEntity>();
	
		String selectSQL = "SELECT * FROM " + InteresseStudenteDao.TABLE_NAME;
				
		if (prefs != null && !prefs.equals("")) {
			selectSQL += " ORDER BY STUDENTE";  
		}
	
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
		
	
			ResultSet rs = preparedStatement.executeQuery();
	
			while (rs.next()) {
				
				InteresseStudenteEntity bean = new InteresseStudenteEntity();
				bean.setStudente(rs.getInt("STUDENTE"));
				bean.setInteresse(rs.getInt("INTERESSE"));
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
	
	
	public boolean isContent(int idInteresse,int idStudente) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		InteresseStudenteEntity bean= new InteresseStudenteEntity();
		
		String selectSQL = "SELECT * FROM " + InteresseStudenteDao.TABLE_NAME + " WHERE STUDENTE=? AND INTERESSE= ?";
		
		try {
		connection = ds.getConnection();
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
}

 

