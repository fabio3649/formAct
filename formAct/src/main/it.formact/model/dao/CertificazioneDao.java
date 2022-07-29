package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import model.entity.CertificazioneEntity;
import model.utils.Utils;

public class CertificazioneDao implements DaoInterface{
	
	
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
                                    
	private static final String TABLE_NAME = "certificazione";
	
	
	public int doSave(Object bean) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		CertificazioneEntity certificazione = (CertificazioneEntity) bean;
		
		String insertSQL = "INSERT INTO " + CertificazioneDao.TABLE_NAME
				+ " (IDCERTIFICAZIONE, FORMATORE, NOME, TIPOLOGIA, ISTITUTO, DESCRIZIONE, ANNOINIZIO, ANNOFINE)"
				+ " VALUES (?, ?, ?, ?, ?, ?, str_to_date(?,'%d-%m-%Y'), str_to_date(?,'%d-%m-%Y') )";
		
		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setInt(1, certificazione.getIdCertificazione());
			preparedStatement.setInt(2, certificazione.getFormatore());
			preparedStatement.setString(3, certificazione.getNome());
			preparedStatement.setString(4, certificazione.getTipologia());
			preparedStatement.setString(5, certificazione.getIstituto());
			preparedStatement.setString(6, certificazione.getDescrizione());
			if (certificazione.getAnnoInizio() == null)
				preparedStatement.setNull(7, java.sql.Types.VARCHAR);
			else
				preparedStatement.setString(7, Utils.toStringDate(certificazione.getAnnoInizio()));
			//preparedStatement.setDate(7,certificazione.getAnnoInizio());
			if (certificazione.getAnnoFine() == null)
				preparedStatement.setNull(8, java.sql.Types.VARCHAR);
			else
				preparedStatement.setString(8, Utils.toStringDate(certificazione.getAnnoFine()));
			
			preparedStatement.executeUpdate();
			connection.setAutoCommit(false);
			connection.commit();
		}
		catch(SQLException e)
		{
			e.printStackTrace(); 
		} finally {
			closePreparedStatement(preparedStatement);
			closeConnection(connection);
		}
		
		return	certificazione.getIdCertificazione();
	}
		
	
	
	@Override
	public List<CertificazioneEntity> doRetrieveAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	public boolean doDeleteByIdFormatore(int formatore) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
	
         
		int result = 0;

		String deleteSQL = "DELETE FROM " + CertificazioneDao.TABLE_NAME + " WHERE FORMATORE = ?";
        
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
	

	
	
	
	
	public ArrayList<CertificazioneEntity>  doRetrieveAllByIdFormatore(int formatore) throws SQLException{
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		
		
		ArrayList<CertificazioneEntity> certificazioni = new ArrayList<CertificazioneEntity>();
        
		String selectSQL = "SELECT * FROM " + CertificazioneDao.TABLE_NAME + " WHERE FORMATORE = ?";

		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, formatore);

			rs = preparedStatement.executeQuery();

			while (rs.next()) {
				
				CertificazioneEntity bean = new CertificazioneEntity();

				bean.setIdCertificazione(rs.getInt("IDCERTIFICAZIONE"));
				bean.setFormatore(rs.getInt("FORMATORE"));
				bean.setNome(rs.getString("NOME"));
				bean.setTipologia(rs.getString("TIPOLOGIA"));
			    bean.setIstituto(rs.getString("ISTITUTO"));
			    bean.setDescrizione(rs.getString("DESCRIZIONE"));
			    bean.setAnnoInizio(Utils.toDate(rs.getString("ANNOINIZIO")));
			    bean.setAnnoFine(Utils.toDate(rs.getString("ANNOFINE")));
				certificazioni.add(bean);
			    
			}

		} catch (ParseException e) {
			throw new SQLException(e);
			
		} finally {
			closeResultSet(rs);
			closePreparedStatement(preparedStatement);
			closeConnection(connection);
		}
		
		return certificazioni;
	}
	

	
	public ArrayList<CertificazioneEntity> doRetrieveAllByFormatore(String nome, String cognome) throws SQLException, ParseException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		ArrayList<CertificazioneEntity> certificazioni = new ArrayList<CertificazioneEntity>();

		String selectSQL = "SELECT * FROM certificazione,formatore WHERE FORMATORE.NOME = ? AND FORMATORE.COGNOME = ? ";

		if (certificazioni != null && !certificazioni.equals("")) {
			selectSQL += " ORDER BY ANNOFINE";  // ordinamento certificazioni per più recenti;
		}

		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, nome);
			preparedStatement.setString(2, cognome);

			 rs = preparedStatement.executeQuery();

			while (rs.next()) {
				
				CertificazioneEntity bean = new CertificazioneEntity();

				bean.setIdCertificazione(rs.getInt("IDCERTIFICAZIONE"));
				bean.setFormatore(rs.getInt("FORMATORE"));
				bean.setNome(rs.getString("NOME"));
				bean.setTipologia(rs.getString("TIPOLOGIA"));
			    bean.setIstituto(rs.getString("ISTITUTO"));
			    bean.setDescrizione(rs.getString("DESCRIZIONE"));
			    bean.setAnnoInizio(Utils.toDate(rs.getString("ANNOINIZIO")));
			    bean.setAnnoFine(Utils.toDate(rs.getString("ANNOFINE")));
				certificazioni.add(bean);
			}

		} finally {
			closeResultSet(rs);
			closePreparedStatement(preparedStatement);
			closeConnection(connection);
		}
		
		return certificazioni;
	}
	
	
	






	@Override
	public boolean doDelete(int id) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
	
         
		int result = 0;

		String deleteSQL = "DELETE FROM " + CertificazioneDao.TABLE_NAME + " WHERE IDCERTIFICAZIONE = ?";
        
		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setInt(1, id);

			result = preparedStatement.executeUpdate();
			return true;
			
		}  finally {
			closePreparedStatement(preparedStatement);
			closeConnection(connection);
		}
		
		
	}






	@Override
	public Object doRetrieveByKey(int id) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		
		CertificazioneEntity bean = new CertificazioneEntity();
		
		String selectSQL = "SELECT IDCERTIFICAZIONE, FORMATORE, NOME, TIPOLOGIA, ISTITUTO, DESCRIZIONE, date_format(ANNOINIZIO,'%d-%m-%Y'), date_format(ANNOFINE,'%d-%m-%Y')"
				+ " FROM " + CertificazioneDao.TABLE_NAME + " WHERE IDCERTIFICAZIONE = ? ";
		
		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, id);

			rs = preparedStatement.executeQuery();
		
			while (rs.next()) {
				bean.setIdCertificazione(rs.getInt(1));
				bean.setFormatore(rs.getInt(2));
				bean.setNome(rs.getString(3));
				bean.setTipologia(rs.getString(4));
			    bean.setIstituto(rs.getString(5));
			    bean.setDescrizione(rs.getString(6));
			    bean.setAnnoInizio(Utils.toDate(rs.getString(7)));
			    bean.setAnnoFine(Utils.toDate(rs.getString(8)));
				
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



	@Override
	public boolean update(int id) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}






	
	
}
