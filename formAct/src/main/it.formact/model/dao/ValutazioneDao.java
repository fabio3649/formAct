package model.dao;

import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import model.entity.ValutazioneEntity;
import model.utils.Utils;

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
	
	
	public ValutazioneEntity doSave(ValutazioneEntity bean) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ValutazioneEntity valutazione = (ValutazioneEntity) bean;
		
		String insertSQL = "INSERT INTO " + ValutazioneDao.TABLE_NAME
				+ " (IDSTUDENTE, IDFORMATORE, IDPERCORSOFORMATIVO, DESCRIZIONE, DATA, STELLE)"
				+ " VALUES (?, ?, ?, ?, str_to_date(?,'%d-%m-%Y') , ?)";
		
		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setInt(1, valutazione.getStudente());
			preparedStatement.setInt(2, valutazione.getFormatore());
			preparedStatement.setInt(3, valutazione.getPercorsoFormativo());
			preparedStatement.setString(4, valutazione.getDescrizione());
			if( valutazione.getData() == null)
				preparedStatement.setNull(5, java.sql.Types.VARCHAR);
			else
				preparedStatement.setString(5, Utils.toStringDate(valutazione.getData()));
			preparedStatement.setInt(6, valutazione.getStelle());
			preparedStatement.executeUpdate();
			connection.setAutoCommit(false);
			connection.commit();
			
		} finally {
			closePreparedStatement(preparedStatement);
			closeConnection(connection);
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
			return true;
		} finally {
			closePreparedStatement(preparedStatement);
			closeConnection(connection);
		}
		
		
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
			return true;
		} finally {
			closePreparedStatement(preparedStatement);
			closeConnection(connection);
		}
		
	}
	

	
	public  ArrayList<ValutazioneEntity> doRetrieveByStudente(int studente) throws SQLException, ParseException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		
		ArrayList<ValutazioneEntity> all = new ArrayList<ValutazioneEntity>();
		
        
		String selectSQL = "SELECT IDSTUDENTE, IDFORMATORE, "
				+ " IDPERCORSOFORMATIVO, DESCRIZIONE, date_format(DATA,'%d-%m-%Y'), STELLE"
				+ " FROM " + ValutazioneDao.TABLE_NAME + " WHERE IDSTUDENTE = ? ";
		
		if( all != null && !all.equals("")) {
			selectSQL += " ORDER BY IDSTUDENTE";
		}
		
		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, studente);

			rs = preparedStatement.executeQuery();

			while (rs.next()) {
				ValutazioneEntity bean = new ValutazioneEntity();
				bean.setStudente(rs.getInt(1));
				bean.setFormatore(rs.getInt(2));
				bean.setPercorsoFormativo(rs.getInt(3));
				bean.setDescrizione(rs.getString(4));
			    bean.setData(Utils.toDate(rs.getString(5)));
			    bean.setStelle(rs.getInt(6));
			    all.add(bean);
			    
			}
			
		} finally {
			closeResultSet(rs);
			closePreparedStatement(preparedStatement);
			closeConnection(connection);
		}
		
		return all;
	}
	
	
	
	public ArrayList<ValutazioneEntity> doRetrieveByStudenteAndFormatore(int studente, int formatore) throws SQLException, ParseException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		
		
		ArrayList<ValutazioneEntity> all = new ArrayList<ValutazioneEntity>();
        
		String selectSQL = "SELECT IDSTUDENTE, IDFORMATORE, IDPERCORSOFORMATIVO"
				+ ", DESCRIZIONE, date_format(DATA,'%d-%m-%Y') , STELLE FROM " + ValutazioneDao.TABLE_NAME + " WHERE IDSTUDENTE = ? AND IDFORMATORE = ? ";

		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, studente);
			preparedStatement.setInt(2, formatore);

		    rs = preparedStatement.executeQuery();

			while (rs.next()) {
				ValutazioneEntity bean = new ValutazioneEntity();
				bean.setStudente(rs.getInt(1));
				bean.setFormatore(rs.getInt(2));
				bean.setPercorsoFormativo(rs.getInt(3));
				bean.setDescrizione(rs.getString(4));
			    bean.setData(Utils.toDate(rs.getString(5)));
			    bean.setStelle(rs.getInt(6));
	       
			    all.add(bean);
			}

		}catch(ParseException e) {
			throw new SQLException(e);
		} finally {
			closeResultSet(rs);
			closePreparedStatement(preparedStatement);
			closeConnection(connection);
		}
		return all;
	}
	
	public ArrayList<ValutazioneEntity> doRetrieveByPercorso(int p) throws SQLException, ParseException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		
		ArrayList<ValutazioneEntity> all = new ArrayList<ValutazioneEntity>();
		
        
		String selectSQL = "SELECT IDSTUDENTE, IDFORMATORE, IDPERCORSOFORMATIVO ,"
				+ " DESCRIZIONE, date_format(DATA,'%d-%m-%Y') , STELLE FROM " + ValutazioneDao.TABLE_NAME + " WHERE IDPERCORSOFORMATIVO = ? ";

		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, p);
			

		    rs = preparedStatement.executeQuery();

			while (rs.next()) {
				ValutazioneEntity bean = new ValutazioneEntity();
				bean.setStudente(rs.getInt(1));
				bean.setFormatore(rs.getInt(2));
				bean.setPercorsoFormativo(rs.getInt(3));
				bean.setDescrizione(rs.getString(4));
			    bean.setData(Utils.toDate(rs.getString(5)));
			    bean.setStelle(rs.getInt(6));
			    all.add(bean);
			    
			}

		}catch(ParseException e) {
			throw new SQLException(e);
		} finally {
			closeResultSet(rs);
			closePreparedStatement(preparedStatement);
			closeConnection(connection);
		}
		return all;
	}
	
	public ValutazioneEntity doRetrieveByKeys(int s, int f, int p) throws SQLException, ParseException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		

		ValutazioneEntity bean = new ValutazioneEntity();
		
		String selectSQL = "SELECT IDSTUDENTE, IDFORMATORE, IDPERCORSOFORMATIVO,"
				+ " DESCRIZIONE, date_format(DATA,'%d-%m-%Y') , STELLE FROM " + ValutazioneDao.TABLE_NAME + " WHERE IDSTUDENTE = ?  AND  "
						+ " IDFORMATORE = ? AND IDPERCORSOFORMATIVO = ? ";
		
		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, s);
			preparedStatement.setInt(2, f);
			preparedStatement.setInt(3, p);
			

		    rs = preparedStatement.executeQuery();
 
			while (rs.next()) {
				
				bean.setStudente(rs.getInt(1));
				bean.setFormatore(rs.getInt(2));
				bean.setPercorsoFormativo(rs.getInt(3));
				bean.setDescrizione(rs.getString(4));
			    bean.setData(Utils.toDate(rs.getString(5)));
			    bean.setStelle(rs.getInt(6));
			    
			    
			}

		}catch(ParseException e) {
			throw new SQLException(e);
		} finally {
			closeResultSet(rs);
			closePreparedStatement(preparedStatement);
			closeConnection(connection);
		}
		return bean;
	}
	
	
		public ArrayList<ValutazioneEntity> doRetrieveByFormatore(int formatore) throws SQLException, ParseException {
			
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			
			ArrayList<ValutazioneEntity> all =new ArrayList<ValutazioneEntity>();
			ResultSet rs = null;
			
	        
			String selectSQL = "SELECT IDSTUDENTE, IDFORMATORE, IDPERCORSOFORMATIVO"
					+ " , DESCRIZIONE, date_format(DATA,'%d-%m-%Y') , STELLE FROM " + ValutazioneDao.TABLE_NAME + " WHERE IDFORMATORE = ? ";
	
			try {
				connection = getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);
				preparedStatement.setInt(1, formatore);
	
				rs = preparedStatement.executeQuery();
	
				while (rs.next()) {
					ValutazioneEntity bean = new ValutazioneEntity();
					bean.setStudente(rs.getInt(1));
					bean.setFormatore(rs.getInt(2));
					bean.setPercorsoFormativo(rs.getInt(3));
					bean.setDescrizione(rs.getString(4));
				    bean.setData(Utils.toDate(rs.getString(5)));
				    bean.setStelle(rs.getInt(6));
		       
				    all.add(bean);
				}
	
			} finally {
				closeResultSet(rs);
				closePreparedStatement(preparedStatement);
				closeConnection(connection);
			}
			return all;
		}
	

	
	public ArrayList<ValutazioneEntity> doRetrieveAll() throws SQLException, ParseException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		ArrayList<ValutazioneEntity> all = new ArrayList<ValutazioneEntity>();

		String selectSQL = "SELECT IDSTUDENTE, IDFORMATORE, IDPERCORSOFORMATIVO"
				+ " DESCRIZIONE, date_format(DATA,'%d-%m-%Y') , STELLE FROM " + ValutazioneDao.TABLE_NAME ;

		if (all != null && !all.equals("")) {
			selectSQL += " ORDER BY IDFORMATORE";  // ordinamento valutazioni per formatore;
		}

		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			rs = preparedStatement.executeQuery();

			while (rs.next()) {
				ValutazioneEntity bean = new ValutazioneEntity();
				bean.setStudente(rs.getInt(1));
				bean.setFormatore(rs.getInt(2));
				bean.setPercorsoFormativo(rs.getInt(3));
				bean.setDescrizione(rs.getString(4));
			    bean.setData(Utils.toDate(rs.getString(5)));
			    bean.setStelle(rs.getInt(6));
	       
			    all.add(bean);
			}


		} finally {
			closeResultSet(rs);
			closePreparedStatement(preparedStatement);
			closeConnection(connection);
		}
		return all;
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
