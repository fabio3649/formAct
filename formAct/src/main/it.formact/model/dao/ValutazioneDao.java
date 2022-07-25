package model.dao;

import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import model.entity.ValutazioneEntity;

public class ValutazioneDao {
	
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
                                    
	private static final String TABLE_NAME = "valutazione";
	
	
	public ValutazioneEntity doSave(Object bean) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ValutazioneEntity valutazione = (ValutazioneEntity) bean;
		
		String insertSQL = "INSERT INTO " + ValutazioneDao.TABLE_NAME
				+ " (IDSTUDENTE, IDFORMATORE, IDPERCORSOFORMATIVO, DESCRIZIONE, DATA, STELLE)"
				+ " VALUES (?, ?, ?, ?, ?, ?)";
		
		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setInt(1, valutazione.getStudente());
			preparedStatement.setInt(2, valutazione.getFormatore());
			preparedStatement.setInt(3, valutazione.getPercorsoFormativo());
			preparedStatement.setString(4, valutazione.getDescrizione());
			preparedStatement.setDate(5, valutazione.getData());
			preparedStatement.setInt(6, valutazione.getStelle());
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
		return valutazione;
	}
		
	

	
	public boolean doDeleteByStudente(int studente) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
	
         
		int result = 0;

		String deleteSQL = "DELETE FROM " + ValutazioneDao.TABLE_NAME + " WHERE IDSTUDENTE = ?";
        
		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setInt(1, studente);

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
	
	public boolean doDeleteByFormatore(int formatore) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
	
         
		int result = 0;

		String deleteSQL = "DELETE FROM " + ValutazioneDao.TABLE_NAME + " WHERE IDFORMATORE = ?";
        
		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setInt(1, formatore);

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
	

	
	public Object doRetrieveByStudente(int studente) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		
		
		ValutazioneEntity bean = new ValutazioneEntity();
        
		String selectSQL = "SELECT * FROM " + ValutazioneDao.TABLE_NAME + " WHERE IDSTUDENTE = ?";

		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, studente);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				
				bean.setStudente(rs.getInt("IDSTUDENTE"));
				bean.setFormatore(rs.getInt("IDFORMATORE"));
				bean.setPercorsoFormativo(rs.getInt("IDPERCORSOFORMATIVO"));
				bean.setDescrizione(rs.getString("DESCRIZIONE"));
			    bean.setData(rs.getDate("DATA"));
			    bean.setStelle(rs.getInt("STELLE"));
	       
			    
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
		return bean;
	}
	
	
	public ArrayList<ValutazioneEntity> doRetrieveByFormatore(int formatore) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		ArrayList<ValutazioneEntity> allValutazioni=new ArrayList<ValutazioneEntity>();
		
		
        
		String selectSQL = "SELECT * FROM " + ValutazioneDao.TABLE_NAME + " WHERE IDFORMATORE = ?";

		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, formatore);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				ValutazioneEntity bean = new ValutazioneEntity();
				bean.setStudente(rs.getInt("IDSTUDENTE"));
				bean.setFormatore(rs.getInt("IDFORMATORE"));
				bean.setPercorsoFormativo(rs.getInt("IDPERCORSOFORMATIVO"));
				bean.setDescrizione(rs.getString("DESCRIZIONE"));
			    bean.setData(rs.getDate("DATA"));
			    bean.setStelle(rs.getInt("STELLE"));
	       
			    allValutazioni.add(bean);
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
		return allValutazioni;
	}
	

	
	public ArrayList<ValutazioneEntity> doRetrieveAll() throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ArrayList<ValutazioneEntity> valutazioni = new ArrayList<ValutazioneEntity>();

		String selectSQL = "SELECT * FROM " + ValutazioneDao.TABLE_NAME;

		if (valutazioni != null && !valutazioni.equals("")) {
			selectSQL += " ORDER BY IDFORMATORE";  // ordinamento valutazioni per formatore;
		}

		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				
				ValutazioneEntity bean = new ValutazioneEntity();

				bean.setStudente(rs.getInt("IDSTUDENTE"));
				bean.setFormatore(rs.getInt("IDFORMATORE"));
				bean.setPercorsoFormativo(rs.getInt("IDPERCORSOFORMATIVO"));
				bean.setDescrizione(rs.getString("DESCRIZIONE"));
			    bean.setData(rs.getDate("DATA"));
			    bean.setStelle(rs.getInt("STELLE"));
				valutazioni.add(bean);
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
		return valutazioni;
	}




	
	}
