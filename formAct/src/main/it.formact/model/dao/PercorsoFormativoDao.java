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

import model.entity.PercorsoFormativoEntity;

public class PercorsoFormativoDao {
	
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
                                    
	private static final String TABLE_NAME = "percorso_formativo";
	
	// creazione id percorso formativo dinamico
	public int nextId() throws SQLException {
		
		ArrayList<PercorsoFormativoEntity> percorsi = (ArrayList<PercorsoFormativoEntity>) this.doRetrieveAll();
		if(percorsi.size()==0)
			return 1;
		int next = (percorsi.get(percorsi.size()-1).getId())+1;
		
		return next;

	}
	
	
	public void doSave(Object bean) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		PercorsoFormativoEntity percorso = (PercorsoFormativoEntity) bean;
		String insertSQL = "INSERT INTO " + PercorsoFormativoDao.TABLE_NAME
				+ " (IDPERCORSO_FORMATIVO, FORMATORE, NOME, DESCRIZIONE, CATEGORIA, INDICECONTENUTI, NUMEROLEZIONI, COSTO)"
				+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setInt(1, this.nextId());
			preparedStatement.setInt(2, percorso.getId_formatore());
			preparedStatement.setString(3, percorso.getNome());
			preparedStatement.setString(4, percorso.getDescrizione());
			preparedStatement.setInt(5, percorso.getCategoria());
			preparedStatement.setString(6, percorso.getIndice_contenuti());
			preparedStatement.setInt(7, percorso.getNum_lezioni());
			preparedStatement.setDouble(8, percorso.getCosto());
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
		
	

	
	public boolean doDelete(int id) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
	
         
		int result = 0;

		String deleteSQL = "DELETE FROM " + PercorsoFormativoDao.TABLE_NAME + " WHERE IDPERCORSO_FORMATIVO = ?";
        
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
	

	
	public Object doRetrieveByKey(int id) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		
		
		PercorsoFormativoEntity bean = new PercorsoFormativoEntity();
        
		String selectSQL = "SELECT * FROM " + PercorsoFormativoDao.TABLE_NAME + " WHERE IDPERCORSO_FORMATIVO = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, id);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean.setId(rs.getInt("IDPERCORSO_FORMATIVO"));
				bean.setId_formatore(rs.getInt("FORMATORE"));
				bean.setNome(rs.getString("NOME"));
				bean.setDescrizione(rs.getString("DESCRIZIONE"));
				bean.setCategoria(rs.getInt("CATEGORIA"));
			    bean.setIndice_contenuti(rs.getString("INDICECONTENUTI"));
	            bean.setNum_lezioni(rs.getInt("NUMEROLEZIONI"));
	            bean.setCosto(rs.getDouble("COSTO"));
	       
			    
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
	

	
	public ArrayList<PercorsoFormativoEntity> doRetrieveAll() throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ArrayList<PercorsoFormativoEntity> percorsi = new ArrayList<PercorsoFormativoEntity>();

		String selectSQL = "SELECT * FROM " + PercorsoFormativoDao.TABLE_NAME;

		if (percorsi != null && !percorsi.equals("")) {
			selectSQL += " ORDER BY IDPERCORSO_FORMATIVO";  // l' errore era qui , clausola order by non aveva un attributo corretto per la tabella studente.
		}

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				
				PercorsoFormativoEntity bean = new PercorsoFormativoEntity();

				bean.setId(rs.getInt("IDPERCORSO_FORMATIVO"));
				bean.setId_formatore(rs.getInt("FORMATORE"));
				bean.setNome(rs.getString("NOME"));
				bean.setDescrizione(rs.getString("DESCRIZIONE"));
				bean.setCategoria(rs.getInt("CATEGORIA"));
			    bean.setIndice_contenuti(rs.getString("INDICECONTENUTI"));
	            bean.setNum_lezioni(rs.getInt("NUMEROLEZIONI"));
	            bean.setCosto(rs.getDouble("COSTO"));
				percorsi.add(bean);
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
		return percorsi;
	}
	}

