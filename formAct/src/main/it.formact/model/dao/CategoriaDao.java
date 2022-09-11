package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import model.entity.CategoriaEntity;


public class CategoriaDao {

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
                                    
	private static final String TABLE_NAME = "categoria";
	
	// creazione id  dinamico
	/*	public int nextId() throws SQLException {
			
			ArrayList<CategoriaEntity> categorie = (ArrayList<CategoriaEntity>) this.doRetrieveAll();
			if(categorie.size()==0)
				return 1;
			int next = (categorie.get(categorie.size()-1).getIdCategoria())+1;
			
			return next;

		}  */
		
		public int doSave(Object bean) throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			CategoriaEntity categoria = (CategoriaEntity) bean;
			String insertSQL = "INSERT INTO " + CategoriaDao.TABLE_NAME
					+ " (IDCATEGORIA, NOME, DESCRIZIONE, AMBITODISCIPLINARE)"
					+ " VALUES (?, ?, ?, ?)";
			int id = 0;
			try {
				connection = getConnection();
				preparedStatement = connection.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);
				//id = this.nextId(); 
				preparedStatement.setInt(1, id);
				preparedStatement.setString(2, categoria.getNome());
				preparedStatement.setString(3, categoria.getDescrizione());
				preparedStatement.setString(4, categoria.getAmbito());
				preparedStatement.executeUpdate();
				ResultSet rs = preparedStatement.getGeneratedKeys();
				if ( rs.next()) {   // generazione nuova chiave primaria
					id = rs.getInt(1);
					
				}
				else { id = 1;// prima chiave;
				}
				connection.setAutoCommit(false);
				connection.commit();
			}
			catch(SQLException e)
			{
				e.printStackTrace(); 
			}
				finally {
					closePreparedStatement(preparedStatement);
					closeConnection(connection);
				}
			
			categoria.setIdCategoria(id);
			return id;
		}
			
		

		
		public boolean doDelete(int id) throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			
		
	         
			int result = 0;

			String deleteSQL = "DELETE FROM " + CategoriaDao.TABLE_NAME + " WHERE IDCATEGORIA = ?";
	        
			try {
				connection = getConnection();
				preparedStatement = connection.prepareStatement(deleteSQL,Statement.RETURN_GENERATED_KEYS);
				preparedStatement.setInt(1, id);

				result = preparedStatement.executeUpdate();
				return true;

			}
			catch(SQLException e)
			{
				e.printStackTrace(); 
			}
			finally {
				closePreparedStatement(preparedStatement);
				closeConnection(connection);
			}
			return false;
		}
		

		
		public Object doRetrieveByKey(int id) throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ResultSet rs = null;
			
			
			CategoriaEntity bean = new CategoriaEntity();
	        
			String selectSQL = "SELECT * FROM " + CategoriaDao.TABLE_NAME + " WHERE IDCATEGORIA = ?";

			try {
				connection = getConnection();
				preparedStatement = connection.prepareStatement(selectSQL,Statement.RETURN_GENERATED_KEYS);
				preparedStatement.setInt(1, id);

				rs = preparedStatement.executeQuery();

				while (rs.next()) {
					bean.setIdCategoria(rs.getInt("IDCATEGORIA"));
					bean.setNome(rs.getString("NOME"));
					bean.setDescrizione(rs.getString("DESCRIZIONE"));
					bean.setAmbito(rs.getString("AMBITODISCIPLINARE"));
		            
				    
				}

			}
			catch(SQLException e)
			{
				e.printStackTrace(); 
			}
				finally {
			
				closeResultSet(rs);
				closePreparedStatement(preparedStatement);
				closeConnection(connection);
			}
			return bean;
		}
		

		
		public ArrayList<CategoriaEntity> doRetrieveAll() throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ResultSet rs = null;
			ArrayList<CategoriaEntity> categorie =  new ArrayList<CategoriaEntity>();

			String selectSQL = "SELECT * FROM " + CategoriaDao.TABLE_NAME;

			if (categorie != null && !categorie.equals("")) {
				selectSQL += " ORDER BY IDCATEGORIA";  
			}

			try {
				connection = getConnection();
				preparedStatement = connection.prepareStatement(selectSQL,Statement.RETURN_GENERATED_KEYS);

				 rs = preparedStatement.executeQuery();

				while (rs.next()) {
					
					CategoriaEntity bean = new CategoriaEntity();

					bean.setIdCategoria(rs.getInt("IDCATEGORIA"));
					bean.setNome(rs.getString("NOME"));
					bean.setDescrizione(rs.getString("DESCRIZIONE"));
					bean.setAmbito(rs.getString("AMBITODISCIPLINARE"));
					categorie.add(bean);
				}

			}
			catch(SQLException e)
			{
				e.printStackTrace(); 
			} finally {
				closeResultSet(rs);
				closePreparedStatement(preparedStatement);
				closeConnection(connection);
			}
			
			
			return  categorie;
		}
		
		
		public CategoriaEntity doRetrieveByName(String name) throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ResultSet rs = null;
			

			String selectSQL = "SELECT IDCATEGORIA, NOME, DESCRIZIONE, AMBITODISCIPLINARE FROM " + CategoriaDao.TABLE_NAME + " WHERE NOME = ? ";

			CategoriaEntity bean = new CategoriaEntity();

			try {
				connection = getConnection();
				preparedStatement = connection.prepareStatement(selectSQL,Statement.RETURN_GENERATED_KEYS);
				preparedStatement.setString(1, name);
				rs = preparedStatement.executeQuery();
					
				
				while (rs.next()) {
					
					

					bean.setIdCategoria(rs.getInt(1));
					bean.setNome(rs.getString(2));
					bean.setDescrizione(rs.getString(3));
					bean.setAmbito(rs.getString(4));
					
				}

			}
			catch(SQLException e)
			{
				e.printStackTrace(); 
			} finally {
				closeResultSet(rs);
				closePreparedStatement(preparedStatement);
				closeConnection(connection);
			}
			
			return  bean;
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
	
	





