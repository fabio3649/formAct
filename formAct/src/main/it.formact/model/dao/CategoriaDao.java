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

import model.entity.CategoriaEntity;




public class CategoriaDao implements DaoInterface{
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
                                    
	private static final String TABLE_NAME = "categoria";
	
	// creazione id  dinamico
		public int nextId() throws SQLException {
			
			ArrayList<CategoriaEntity> categorie = (ArrayList<CategoriaEntity>) this.doRetrieveAll();
			if(categorie.size()==0)
				return 1;
			int next = (categorie.get(categorie.size()-1).getIdCategoria())+1;
			
			return next;

		}
		
		public void doSave(Object bean) throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			CategoriaEntity categoria = (CategoriaEntity) bean;
			String insertSQL = "INSERT INTO " + CategoriaDao.TABLE_NAME
					+ " (IDCATEGORIA, NOME, DESCRIZIONE, AMBITODISCIPLINARE)"
					+ " VALUES (?, ?, ?, ?)";
			
			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(insertSQL);
				preparedStatement.setInt(1, this.nextId());
				preparedStatement.setString(2, categoria.getNome());
				preparedStatement.setString(3, categoria.getDescrizione());
				preparedStatement.setString(4, categoria.getAmbito());
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

			String deleteSQL = "DELETE FROM " + CategoriaDao.TABLE_NAME + " WHERE IDCATEGORIA = ?";
	        
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

			
			
			CategoriaEntity bean = new CategoriaEntity();
	        
			String selectSQL = "SELECT * FROM " + CategoriaDao.TABLE_NAME + " WHERE IDCATEGORIA = ?";

			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);
				preparedStatement.setInt(1, id);

				ResultSet rs = preparedStatement.executeQuery();

				while (rs.next()) {
					bean.setIdCategoria(rs.getInt("IDCATEGORIA"));
					bean.setNome(rs.getString("NOME"));
					bean.setDescrizione(rs.getString("DESCRIZIONE"));
					bean.setAmbito(rs.getString("AMBITODISCIPLINARE"));
		            
				    
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
		

		
		public ArrayList<CategoriaEntity> doRetrieveAll() throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;

			ArrayList<CategoriaEntity> categorie =  new ArrayList<CategoriaEntity>();

			String selectSQL = "SELECT * FROM " + CategoriaDao.TABLE_NAME;

			if (categorie != null && !categorie.equals("")) {
				selectSQL += " ORDER BY IDCATEGORIA";  
			}

			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);

				ResultSet rs = preparedStatement.executeQuery();

				while (rs.next()) {
					
					CategoriaEntity bean = new CategoriaEntity();

					bean.setIdCategoria(rs.getInt("IDCATEGORIA"));
					bean.setNome(rs.getString("NOME"));
					bean.setDescrizione(rs.getString("DESCRIZIONE"));
					bean.setAmbito(rs.getString("AMBITODISCIPLINARE"));
					categorie.add(bean);
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
			
			return  categorie;
		}
		
		
		public CategoriaEntity doRetrieveByName(String name) throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;

			

			String selectSQL = "SELECT * FROM " + CategoriaDao.TABLE_NAME + "WHERE NOME = ?";

			CategoriaEntity bean = new CategoriaEntity();

			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);
				ResultSet rs = preparedStatement.executeQuery();
				preparedStatement.setString(1, name);	
				
				while (rs.next()) {
					
					

					bean.setIdCategoria(rs.getInt("IDCATEGORIA"));
					bean.setNome(rs.getString("NOME"));
					bean.setDescrizione(rs.getString("DESCRIZIONE"));
					bean.setAmbito(rs.getString("AMBITODISCIPLINARE"));
					
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
			
			return  bean;
		}
		
		
		
		}
	
	





