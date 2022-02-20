package model.dao;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import model.entity.FormatoreEntity;


public class FormatoreDao implements DaoInterface{
	
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
                                    
	private static final String TABLE_NAME = "formatore";
	
	// creazione id studente dinamico
		public int nextId() throws SQLException {
			
			ArrayList<FormatoreEntity> users = (ArrayList<FormatoreEntity>) this.doRetrieveAll();
			if(users.size()==0)
				return 1;
			int next = (users.get(users.size()-1).getId())+1;
			
			return next;

		}

		
		public void doSave(Object bean) throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			FormatoreEntity user = (FormatoreEntity) bean;
			String insertSQL = "INSERT INTO " + FormatoreDao.TABLE_NAME
					+ " (IDFORMATORE, EMAIL, PASSWORD, NOME, COGNOME, SESSO, DATANASCITA, PAESEORIGINE, CODICEFISCALE, CONTOCORRENTE)"
					+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(insertSQL);
				preparedStatement.setInt(1, this.nextId());
				preparedStatement.setString(2, user.getEmail());
				preparedStatement.setString(3, user.getPassword());
				preparedStatement.setString(4, user.getName());
				preparedStatement.setString(5, user.getSurname());
				preparedStatement.setString(6, user.getGender());
				preparedStatement.setDate(7, user.getBirthDate());
				preparedStatement.setString(8, user.getCountry());
				preparedStatement.setString(9, user.getCodiceFiscale());
				preparedStatement.setString(10, user.getContoCorrente());
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
			
		

		public boolean updatePassword(int id, String pwd) throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			int result = 0;
			
			
	

			String selectSQL = "UPDATE " + FormatoreDao.TABLE_NAME + " SET PASSWORD = ? " + " WHERE IDFORMATORE = ? ";

			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);
				preparedStatement.setString(1, pwd);
				preparedStatement.setInt(2, id);

				
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
			
    
		
		public boolean doDelete(int id) throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			
		
	         
			int result = 0;

			String deleteSQL = "DELETE FROM " + FormatoreDao.TABLE_NAME + " WHERE IDFORMATORE = ?";
	        
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

			
			
			FormatoreEntity bean = new FormatoreEntity();
	        
			String selectSQL = "SELECT * FROM " + FormatoreDao.TABLE_NAME + " WHERE IDFORMATORE = ?";

			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);
				preparedStatement.setInt(1, id);

				ResultSet rs = preparedStatement.executeQuery();

				while (rs.next()) {
					bean.setId(rs.getInt("IDFORMATORE"));
					bean.setEmail(rs.getString("EMAIL"));
					bean.setPassword(rs.getString("PASSWORD"));
					bean.setName(rs.getString("NOME"));
					bean.setSurname(rs.getString("COGNOME"));
				    bean.setGender(rs.getString("SESSO"));
		            bean.setBirthDate(rs.getDate("DATANASCITA"));
		            bean.setCountry(rs.getString("PAESEORIGINE"));
		            bean.setCodiceFiscale(rs.getString("CODICEFISCALE"));
		            bean.setContoCorrente(rs.getString("CONTOCORRENTE"));
		            
				    
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
		

		
		public ArrayList<FormatoreEntity> doRetrieveAll() throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;

			ArrayList<FormatoreEntity> users = new ArrayList<FormatoreEntity>();

			String selectSQL = "SELECT * FROM " + FormatoreDao.TABLE_NAME;

			if (users != null && !users.equals("")) {
				selectSQL += " ORDER BY IDFORMATORE";  // l' errore era qui , clausola order by non aveva un attributo corretto per la tabella studente.
			}

			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);

				ResultSet rs = preparedStatement.executeQuery();

				while (rs.next()) {
					
					FormatoreEntity bean = new FormatoreEntity();

					bean.setId(rs.getInt("IDFORMATORE"));
					bean.setEmail(rs.getString("EMAIL"));
					bean.setPassword(rs.getString("PASSWORD"));
					bean.setName(rs.getString("NOME"));
					bean.setSurname(rs.getString("COGNOME"));
				    bean.setGender(rs.getString("SESSO"));
		            bean.setBirthDate(rs.getDate("DATANASCITA"));
		            bean.setCountry(rs.getString("PAESEORIGINE"));
		            bean.setCodiceFiscale(rs.getString("CODICEFISCALE"));
		            bean.setContoCorrente(rs.getString("CONTOCORRENTE"));
					users.add(bean);
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
			return users;
		}
		
		
	}
	
	


