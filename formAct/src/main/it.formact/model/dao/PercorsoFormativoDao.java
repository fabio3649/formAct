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

import model.entity.IscrizioneEntity;
import model.entity.PercorsoFormativoEntity;

public class PercorsoFormativoDao implements DaoInterface{
	
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
				+ " (IDPERCORSO_FORMATIVO, FORMATORE, NOME, AMBITO, DESCRIZIONE, INDICECONTENUTI, NUMEROLEZIONI, COSTO)"
				+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setInt(1, this.nextId());
			preparedStatement.setInt(2, percorso.getId_formatore());
			preparedStatement.setString(3, percorso.getNome());
			preparedStatement.setInt(4, percorso.getCategoria());
			preparedStatement.setString(5, percorso.getDescrizione());
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
				bean.setCategoria(rs.getInt("AMBITO"));
				bean.setDescrizione(rs.getString("DESCRIZIONE"));
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
	
	public ArrayList<PercorsoFormativoEntity> doRetrieveByCosto(Double costo) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		
		
		ArrayList<PercorsoFormativoEntity> corsi = new ArrayList<PercorsoFormativoEntity>();
        
		String selectSQL = "SELECT * FROM " + PercorsoFormativoDao.TABLE_NAME + " WHERE NOME = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setDouble(1, costo);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				
				PercorsoFormativoEntity bean = new PercorsoFormativoEntity();
				bean.setId(rs.getInt("IDPERCORSO_FORMATIVO"));
				bean.setId_formatore(rs.getInt("FORMATORE"));
				bean.setNome(rs.getString("NOME"));
				bean.setCategoria(rs.getInt("AMBITO"));
				bean.setDescrizione(rs.getString("DESCRIZIONE"));
			    bean.setIndice_contenuti(rs.getString("INDICECONTENUTI"));
	            bean.setNum_lezioni(rs.getInt("NUMEROLEZIONI"));
	            bean.setCosto(rs.getDouble("COSTO"));
	            corsi.add(bean);
			    
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
		return corsi;
	}
	
	
	public ArrayList<PercorsoFormativoEntity> doRetrieveByString(String str) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		
		
		ArrayList<PercorsoFormativoEntity> corsi = new ArrayList<PercorsoFormativoEntity>();
		
        String nome = "%" + str +"%";
		String selectSQL = "SELECT * FROM " + PercorsoFormativoDao.TABLE_NAME + " WHERE NOME LIKE ? ";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, nome);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				
				PercorsoFormativoEntity bean = new PercorsoFormativoEntity();
				bean.setId(rs.getInt("IDPERCORSO_FORMATIVO"));
				bean.setId_formatore(rs.getInt("FORMATORE"));
				bean.setNome(rs.getString("NOME"));
				bean.setCategoria(rs.getInt("AMBITO"));
				bean.setDescrizione(rs.getString("DESCRIZIONE"));
			    bean.setIndice_contenuti(rs.getString("INDICECONTENUTI"));
	            bean.setNum_lezioni(rs.getInt("NUMEROLEZIONI"));
	            bean.setCosto(rs.getDouble("COSTO"));
	            corsi.add(bean);
			    
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
		return corsi;
	}

	
	public ArrayList<PercorsoFormativoEntity> doRetrieveByName(String name) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		
		
		ArrayList<PercorsoFormativoEntity> corsi = new ArrayList<PercorsoFormativoEntity>();
        
		String selectSQL = "SELECT * FROM " + PercorsoFormativoDao.TABLE_NAME + " WHERE NOME = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, name);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				
				PercorsoFormativoEntity bean = new PercorsoFormativoEntity();
				bean.setId(rs.getInt("IDPERCORSO_FORMATIVO"));
				bean.setId_formatore(rs.getInt("FORMATORE"));
				bean.setNome(rs.getString("NOME"));
				bean.setCategoria(rs.getInt("AMBITO"));
				bean.setDescrizione(rs.getString("DESCRIZIONE"));
			    bean.setIndice_contenuti(rs.getString("INDICECONTENUTI"));
	            bean.setNum_lezioni(rs.getInt("NUMEROLEZIONI"));
	            bean.setCosto(rs.getDouble("COSTO"));
	            corsi.add(bean);
			    
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
		return corsi;
	}
	
	
	public ArrayList<PercorsoFormativoEntity> doRetrieveAllByFormatore(String nome, String cognome) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		
		
		ArrayList<PercorsoFormativoEntity> corsi = new ArrayList<PercorsoFormativoEntity>();
        
		String selectSQL = "SELECT * "
				+ "FROM PERCORSO_FORMATIVO,FORMATORE "
				+ "WHERE FORMATORE.NOME = ? AND FORMATORE.COGNOME = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, nome);
			preparedStatement.setString(2, cognome);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				PercorsoFormativoEntity bean = new PercorsoFormativoEntity();
				
				bean.setId(rs.getInt("IDPERCORSO_FORMATIVO"));
				bean.setId_formatore(rs.getInt("FORMATORE"));
				bean.setNome(rs.getString("NOME"));
				bean.setCategoria(rs.getInt("AMBITO"));
				bean.setDescrizione(rs.getString("DESCRIZIONE"));
			    bean.setIndice_contenuti(rs.getString("INDICECONTENUTI"));
	            bean.setNum_lezioni(rs.getInt("NUMEROLEZIONI"));
	            bean.setCosto(rs.getDouble("COSTO"));
	            corsi.add(bean);
			    
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
		return corsi;
	}
	
	public ArrayList<PercorsoFormativoEntity> doRetrieveAllByCategory(String categoria) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		
		
		ArrayList<PercorsoFormativoEntity> corsi = new ArrayList<PercorsoFormativoEntity>();
        
		String selectSQL = "SELECT * "
				+ "FROM PERCORSO_FORMATIVO,CATEGORIA "
				+ "WHERE CATEGORIA.NOME = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, categoria);
		

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				PercorsoFormativoEntity bean = new PercorsoFormativoEntity();
				
				bean.setId(rs.getInt("IDPERCORSO_FORMATIVO"));
				bean.setId_formatore(rs.getInt("FORMATORE"));
				bean.setNome(rs.getString("NOME"));
				bean.setCategoria(rs.getInt("AMBITO"));
				bean.setDescrizione(rs.getString("DESCRIZIONE"));
			    bean.setIndice_contenuti(rs.getString("INDICECONTENUTI"));
	            bean.setNum_lezioni(rs.getInt("NUMEROLEZIONI"));
	            bean.setCosto(rs.getDouble("COSTO"));
	            corsi.add(bean);
			    
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
		return corsi;
	}
	
	public ArrayList<PercorsoFormativoEntity> doRetrieveAvailable() throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		
		
		ArrayList<PercorsoFormativoEntity> corsi = new ArrayList<PercorsoFormativoEntity>();
        
		int disp = 1;
		
		String selectSQL = "SELECT * "
				+ "FROM percorso_formativo,disponibilità "
				+ "WHERE disponibilità.stato = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, disp);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				PercorsoFormativoEntity bean = new PercorsoFormativoEntity();
				
				bean.setId(rs.getInt("IDPERCORSO_FORMATIVO"));
				bean.setId_formatore(rs.getInt("FORMATORE"));
				bean.setNome(rs.getString("NOME"));
				bean.setCategoria(rs.getInt("AMBITO"));
				bean.setDescrizione(rs.getString("DESCRIZIONE"));
			    bean.setIndice_contenuti(rs.getString("INDICECONTENUTI"));
	            bean.setNum_lezioni(rs.getInt("NUMEROLEZIONI"));
	            bean.setCosto(rs.getDouble("COSTO"));
	            corsi.add(bean);
			    
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
		return corsi;
	}
	
	
	public ArrayList<PercorsoFormativoEntity> doRetrieveAll() throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ArrayList<PercorsoFormativoEntity> percorsi = new ArrayList<PercorsoFormativoEntity>();

		String selectSQL = "SELECT * FROM " + PercorsoFormativoDao.TABLE_NAME;

		if (percorsi != null && !percorsi.equals("")) {
			selectSQL += " ORDER BY IDPERCORSO_FORMATIVO";  
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
				bean.setCategoria(rs.getInt("AMBITO"));
				bean.setDescrizione(rs.getString("DESCRIZIONE"));
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
	
	public ArrayList<PercorsoFormativoEntity> doRetrieveAllByParams(String str , String min, String max, String giorno) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		
		
		ArrayList<PercorsoFormativoEntity> corsi = new ArrayList<PercorsoFormativoEntity>();
		String stringa = "%" + str +"%";
		
		String selectSQL = "";
		selectSQL += "SELECT DISTINCT *";
		selectSQL += " FROM percorso_formativo,disponibilità";
		selectSQL += " WHERE";
		selectSQL += " percorso_formativo.idpercorso_formativo = disponibilità.percorsoFormativo";
		
		if (str != null && !str.equals("")) {
			selectSQL += " AND percorso_formativo.nome LIKE ?";
		}
		if (min != null && !min.equals("")) {
			selectSQL += " AND percorso_formativo.costo >= ?";
		}
		if (max != null && !max.equals("")) {
			selectSQL += " AND percorso_formativo.costo <= ?";
		}
		if (giorno != null && !giorno.equals("")) {
			selectSQL += " AND disponibilità.giornoSettimana = ?";
		}
		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			
			int i = 1;
			if (str != null && !str.equals("")) {
				preparedStatement.setString(i, stringa);
				i++;
			}
			if (min != null && !min.equals("")) {
				preparedStatement.setDouble(i , Double.parseDouble(min));
				i++;
			}
			if (max != null && !max.equals("")) {
				preparedStatement.setDouble(i, Double.parseDouble(max));
				i++;
			}
			if (giorno != null && !giorno.equals("")) {
				preparedStatement.setString(i, giorno);
				i++;
			}
			
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				PercorsoFormativoEntity bean = new PercorsoFormativoEntity();
				
				bean.setId(rs.getInt("IDPERCORSO_FORMATIVO"));
				bean.setId_formatore(rs.getInt("FORMATORE"));
				bean.setNome(rs.getString("NOME"));
				bean.setCategoria(rs.getInt("AMBITO"));
				bean.setDescrizione(rs.getString("DESCRIZIONE"));
			    bean.setIndice_contenuti(rs.getString("INDICECONTENUTI"));
	            bean.setNum_lezioni(rs.getInt("NUMEROLEZIONI"));
	            bean.setCosto(rs.getDouble("COSTO"));
	            corsi.add(bean);
			    
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
		return corsi;
	}
	
	public ArrayList<PercorsoFormativoEntity> doRetrieveIscrizioniStudente(int idStudente) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ArrayList<PercorsoFormativoEntity> iscrizioni = new ArrayList<PercorsoFormativoEntity>();

		String selectSQL = "SELECT DISTINCT * FROM percorso_formativo,iscrizione"
								+" WHERE percorso_formativo.idpercorso_formativo = iscrizione.percorsoFormativo"
									+ " AND iscrizione.studente = ?";

		if (iscrizioni != null && !iscrizioni.equals("")) {
			selectSQL += " ORDER BY STUDENTE";  
		}

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			
			// Problema: questi 2 comandi erano invertiti:
			preparedStatement.setInt(1, idStudente);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				
				PercorsoFormativoEntity bean = new PercorsoFormativoEntity();

				bean.setId(rs.getInt("IDPERCORSO_FORMATIVO"));
				bean.setId_formatore(rs.getInt("FORMATORE"));
				bean.setNome(rs.getString("NOME"));
				bean.setCategoria(rs.getInt("AMBITO"));
				bean.setDescrizione(rs.getString("DESCRIZIONE"));
			    bean.setIndice_contenuti(rs.getString("INDICECONTENUTI"));
	            bean.setNum_lezioni(rs.getInt("NUMEROLEZIONI"));
	            bean.setCosto(rs.getDouble("COSTO"));
	            iscrizioni.add(bean);
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
		return iscrizioni;
	}
	
}

