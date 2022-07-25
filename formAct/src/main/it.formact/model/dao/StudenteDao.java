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

import model.entity.StudenteEntity;

public class StudenteDao implements DaoInterface {
	
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
                                    
	private static final String TABLE_NAME = "studente";
	
	// creazione id studente dinamico
	/*public int nextId() throws SQLException {
		
		ArrayList<StudenteEntity> users = (ArrayList<StudenteEntity>) this.doRetrieveAll();
		if(users.size()==0)
			return 1;
		int next = (users.get(users.size()-1).getId())+1;
		
		return next;

	} */

	
	public int doSave(Object bean) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		StudenteEntity user = (StudenteEntity) bean;
		String insertSQL = "INSERT INTO " + StudenteDao.TABLE_NAME
				+ " (IDSTUDENTE, EMAIL, PASSWORD, NOME, COGNOME, SESSO, DATANASCITA, PAESEORIGINE)"
				+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		int id = 0;
		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(insertSQL , Statement.RETURN_GENERATED_KEYS);
			
			preparedStatement.setInt(1, id);
			preparedStatement.setString(2, user.getEmail());
			preparedStatement.setString(3, user.getPassword());
			preparedStatement.setString(4, user.getName());
			preparedStatement.setString(5, user.getSurname());
			preparedStatement.setString(6, user.getGender());
			preparedStatement.setDate(7, user.getBirthDate());
			preparedStatement.setString(8, user.getCountry());
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
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		
		user.setId(id);
		return id;
	}
		
	

	
	public boolean doDelete(int id) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
	
         
		int result = 0;

		String deleteSQL = "DELETE FROM " + StudenteDao.TABLE_NAME + " WHERE IDSTUDENTE = ?";
        
		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL, Statement.RETURN_GENERATED_KEYS);
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

		
		
		StudenteEntity bean = new StudenteEntity();
        
		String selectSQL = "SELECT * FROM " + StudenteDao.TABLE_NAME + " WHERE IDSTUDENTE = ?";

		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(selectSQL, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setInt(1, id);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean.setId(rs.getInt(1));
				bean.setEmail(rs.getString("EMAIL"));
				bean.setPassword(rs.getString("PASSWORD"));
				bean.setName(rs.getString("NOME"));
				bean.setSurname(rs.getString("COGNOME"));
			    bean.setGender(rs.getString("SESSO"));
	            bean.setBirthDate(rs.getDate("DATANASCITA"));
	            bean.setCountry(rs.getString("PAESEORIGINE"));
	       
			    
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
	
	
	public boolean updatePassword(int id, String pwd) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int result = 0;
		
		
        

		String selectSQL = "UPDATE " + StudenteDao.TABLE_NAME + " SET PASSWORD = ? " + " WHERE IDSTUDENTE = ? ";
        
		
		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(selectSQL, Statement.RETURN_GENERATED_KEYS);
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
	
	public boolean update(int id) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int result = 0;
		
		


		String selectSQL = "UPDATE " + StudenteDao.TABLE_NAME + " SET EMAIL = ? , PASSWORD = ? , NOME = ? , COGNOME = ? , SESSO = ? ,"
				+ " str_to_date(DATANASCITA,'%d-%m-%Y') , PAESEORIGINE = ?  " + " WHERE IDSTUDENTE = ? ";

		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(selectSQL,Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setInt(1, id);
			

			
			result = preparedStatement.executeUpdate();

		} finally {
			closePreparedStatement(preparedStatement);
			closeConnection(connection);
		}
		return (result != 0);
	}
	    
	

	
	public ArrayList<StudenteEntity> doRetrieveAll() throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ArrayList<StudenteEntity> users = new ArrayList<StudenteEntity>();

		String selectSQL = "SELECT * FROM " + StudenteDao.TABLE_NAME;

		if (users != null && !users.equals("")) {
			selectSQL += " ORDER BY IDSTUDENTE";  // l' errore era qui , clausola order by non aveva un attributo corretto per la tabella studente.
		}

		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(selectSQL, Statement.RETURN_GENERATED_KEYS);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				
				StudenteEntity bean = new StudenteEntity();

				bean.setId(rs.getInt(1));  // da verificare se corretto
				bean.setEmail(rs.getString("EMAIL"));
				bean.setPassword(rs.getString("PASSWORD"));
				bean.setName(rs.getString("NOME"));
				bean.setSurname(rs.getString("COGNOME"));
			    bean.setGender(rs.getString("SESSO"));
	            bean.setBirthDate(rs.getDate("DATANASCITA"));
	            bean.setCountry(rs.getString("PAESEORIGINE"));
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