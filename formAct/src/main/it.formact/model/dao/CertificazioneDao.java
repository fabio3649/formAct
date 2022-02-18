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

import model.entity.CertificazioneEntity;

public class CertificazioneDao implements DaoInterface{
	
	
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
                                    
	private static final String TABLE_NAME = "certificazione";
	
	
	public void doSave(Object bean) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		CertificazioneEntity certificazione = (CertificazioneEntity) bean;
		
		String insertSQL = "INSERT INTO " + CertificazioneDao.TABLE_NAME
				+ " (IDCERTIFICAZIONE, FORMATORE, NOME, TIPOLOGIA, ISTITUTO, DESCRIZIONE, ANNOINIZIO, ANNOFINE)"
				+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setInt(1, certificazione.getIdCertificazione());
			preparedStatement.setInt(2, certificazione.getFormatore());
			preparedStatement.setString(3, certificazione.getNome());
			preparedStatement.setString(4, certificazione.getTipologia());
			preparedStatement.setString(5, certificazione.getIstituto());
			preparedStatement.setString(6, certificazione.getDescrizione());
			preparedStatement.setDate(7,certificazione.getAnnoInizio());
			preparedStatement.setDate(8,certificazione.getAnnoFine());
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
		
	

	
	
	
	public boolean doDeleteByIdFormatore(int formatore) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
	
         
		int result = 0;

		String deleteSQL = "DELETE FROM " + CertificazioneDao.TABLE_NAME + " WHERE FORMATORE = ?";
        
		try {
			connection = ds.getConnection();
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
	

	
	
	
	
	public ArrayList<CertificazioneEntity>  doRetrieveAllByIdFormatore(int formatore) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		
		
		ArrayList<CertificazioneEntity> certificazioni = new ArrayList<CertificazioneEntity>();
        
		String selectSQL = "SELECT * FROM " + CertificazioneDao.TABLE_NAME + " WHERE FORMATORE = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, formatore);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				
				CertificazioneEntity bean = new CertificazioneEntity();

				bean.setIdCertificazione(rs.getInt("IDCERTIFICAZIONE"));
				bean.setFormatore(rs.getInt("FORMATORE"));
				bean.setNome(rs.getString("NOME"));
				bean.setTipologia(rs.getString("TIPOLOGIA"));
			    bean.setIstituto(rs.getString("ISTITUTO"));
			    bean.setDescrizione(rs.getString("DESCRIZIONE"));
			    bean.setAnnoInizio(rs.getDate("ANNOINIZIO"));
			    bean.setAnnoFine(rs.getDate("ANNOFINE"));
				certificazioni.add(bean);
			    
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
		return certificazioni;
	}
	

	
	public ArrayList<CertificazioneEntity> doRetrieveAllByFormatore(String nome, String cognome) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ArrayList<CertificazioneEntity> certificazioni = new ArrayList<CertificazioneEntity>();

		String selectSQL = "SELECT * FROM certificazione,formatore WHERE FORMATORE.NOME = ? AND FORMATORE.COGNOME = ? ";

		if (certificazioni != null && !certificazioni.equals("")) {
			selectSQL += " ORDER BY ANNOFINE";  // ordinamento certificazioni per più recenti;
		}

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, nome);
			preparedStatement.setString(2, cognome);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				
				CertificazioneEntity bean = new CertificazioneEntity();

				bean.setIdCertificazione(rs.getInt("IDCERTIFICAZIONE"));
				bean.setFormatore(rs.getInt("FORMATORE"));
				bean.setNome(rs.getString("NOME"));
				bean.setTipologia(rs.getString("TIPOLOGIA"));
			    bean.setIstituto(rs.getString("ISTITUTO"));
			    bean.setDescrizione(rs.getString("DESCRIZIONE"));
			    bean.setAnnoInizio(rs.getDate("ANNOINIZIO"));
			    bean.setAnnoFine(rs.getDate("ANNOFINE"));
				certificazioni.add(bean);
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
		return certificazioni;
	}
	
	
	public Object doRetrieveAll() throws SQLException {  // vuoto;
	
	return null;
	}






	@Override
	public boolean doDelete(int id) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
	
         
		int result = 0;

		String deleteSQL = "DELETE FROM " + CertificazioneDao.TABLE_NAME + " WHERE IDCERTIFICAZIONE = ?";
        
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setInt(1, id);

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






	@Override
	public Object doRetrieveByKey(int id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
}
