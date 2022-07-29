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

import model.entity.InteresseEntity;

public class InteresseDao  implements DaoInterface{
	
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
                                    
	private static final String TABLE_NAME = "interesse";
	
	

	
	public int doSave(Object bean) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		InteresseEntity interesse = (InteresseEntity) bean;
		String insertSQL = "INSERT INTO " + InteresseDao.TABLE_NAME
				+ " (IDINTERESSE, NOME)"
				+ " VALUES (?, ?)";
		int id = 0;
		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);
			
			preparedStatement.setInt(1, id);
			preparedStatement.setString(2, interesse.getNome());
			preparedStatement.executeUpdate();
			ResultSet rs = preparedStatement.getGeneratedKeys();
			if ( rs.next()) {   // generazione nuova chiave primaria
				id = rs.getInt(1);
				
			}
			else { id = 1;//;
			}
			connection.setAutoCommit(false);
			connection.commit();
		} finally {
			closePreparedStatement(preparedStatement);
			closeConnection(connection);
		}
		
		interesse.setIdInteresse(id);
		return id;
	}
		
	public boolean doUpdate(int id, String nome) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int result = 0;
		
		


		String selectSQL = "UPDATE " + InteresseDao.TABLE_NAME + " SET NOME = ? " + " WHERE IDINTERESSE = ? ";

		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, nome);
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

		String deleteSQL = "DELETE FROM " + InteresseDao.TABLE_NAME + " WHERE IDINTERESSE = ?";
        
		try {
			connection = getConnection();
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

		
		
		InteresseEntity bean = new InteresseEntity();
        
		String selectSQL = "SELECT * FROM " + InteresseDao.TABLE_NAME + " WHERE IDINTERESSE = ?";

		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, id);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean.setIdInteresse(rs.getInt("IDINTERESSE"));
				bean.setNome(rs.getString("NOME"));
			
				
	       
			    
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
	

	
	public ArrayList<InteresseEntity> doRetrieveAll() throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ArrayList<InteresseEntity> interessi = new ArrayList<InteresseEntity>();

		String selectSQL = "SELECT * FROM " + InteresseDao.TABLE_NAME;

		if (interessi != null && !interessi.equals("")) {
			selectSQL += " ORDER BY NOME";  
		}

		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				
				InteresseEntity bean = new InteresseEntity();

				bean.setIdInteresse(rs.getInt("IDINTERESSE"));
				bean.setNome(rs.getString("NOME"));
				interessi.add(bean);
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
		return interessi;
	}
	
	public ArrayList<InteresseEntity> doRetrieveAllByName(String name) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ArrayList<InteresseEntity> interessi = new ArrayList<InteresseEntity>();

		String selectSQL = "SELECT * FROM " + InteresseDao.TABLE_NAME +" WHERE NOME = ?";

		if (interessi != null && !interessi.equals("")) {
			selectSQL += " ORDER BY NOME";  
		}

		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, name);
			
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				
				InteresseEntity bean = new InteresseEntity();

				bean.setIdInteresse(rs.getInt("IDINTERESSE"));
				bean.setNome(rs.getString("NOME"));
				interessi.add(bean);
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
		return interessi;
	}


	@Override
	public boolean update(int id) throws SQLException {
		// TODO Auto-generated method stub
		return false;
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