package model.dao;


import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;



import model.entity.FormatoreEntity;
import model.utils.Utils;

public class FormatoreDao implements DaoInterface<FormatoreEntity>{
	

   
		
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
                                    
	private static final String TABLE_NAME = "formatore";
	
	
		public int doSave(FormatoreEntity bean) throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			FormatoreEntity user = (FormatoreEntity) bean;
			String insertSQL = "INSERT INTO " + FormatoreDao.TABLE_NAME
					+ " (IDFORMATORE, EMAIL, PASSWORD, NOME, COGNOME, SESSO, DATANASCITA, PAESEORIGINE, CODICEFISCALE, CONTOCORRENTE)"
					+ " VALUES (?, ?, ?, ?, ?, ?, str_to_date(?,'%d-%m-%Y'), ?, ?, ?) " ;
			int id = 0 ;
			try {
				connection = getConnection();
				preparedStatement = connection.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);
				
				
				preparedStatement.setInt(1, id);
				preparedStatement.setString(2, user.getEmail());
				preparedStatement.setString(3, user.getPassword());
				preparedStatement.setString(4, user.getName());
				preparedStatement.setString(5, user.getSurname());
				preparedStatement.setString(6, user.getGender());
				if( user.getBirthDate() == null)
					preparedStatement.setNull(7, java.sql.Types.VARCHAR);
				else
					preparedStatement.setString(7, Utils.toStringDate(user.getBirthDate()));
				preparedStatement.setString(8, user.getCountry());
				preparedStatement.setString(9, user.getCodiceFiscale());
				preparedStatement.setString(10, user.getContoCorrente());
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
			
			user.setId(id);
			return id;
			
		}
			
		

		public boolean updatePassword(int id, String pwd) throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			int result = 0;
			
			
	

			String selectSQL = "UPDATE " + FormatoreDao.TABLE_NAME + " SET PASSWORD = ? " + " WHERE IDFORMATORE = ? ";

			try {
				connection = getConnection();
				preparedStatement = connection.prepareStatement(selectSQL,Statement.RETURN_GENERATED_KEYS);
				preparedStatement.setString(1, pwd);
				preparedStatement.setInt(2, id);

				
				result = preparedStatement.executeUpdate();
 
			} finally {
				closePreparedStatement(preparedStatement);
				closeConnection(connection);
			}
			return (result != 0);
		}
		
		public boolean updateTrainer(int id, String email, String country, String cc) throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			int result = 0;
			
			String selectSQL = "UPDATE " + FormatoreDao.TABLE_NAME + " SET EMAIL = ? , PAESEORIGINE = ? , CONTOCORRENTE = ? " + " WHERE IDFORMATORE = ? ";

			try {
				connection = getConnection();
				preparedStatement = connection.prepareStatement(selectSQL,Statement.RETURN_GENERATED_KEYS);
				preparedStatement.setString(1, email);
				preparedStatement.setString(2, country);
				preparedStatement.setString(3, cc);
				preparedStatement.setInt(4, id);
				
				result = preparedStatement.executeUpdate();

			} finally {
				closePreparedStatement(preparedStatement);
				closeConnection(connection);
			}
			return (result != 0);
		}
			
    
		
		public boolean doDelete(int id) throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			
		
	         
			int result = 0;

			String deleteSQL = "DELETE FROM " + FormatoreDao.TABLE_NAME + " WHERE IDFORMATORE = ?";
	        
			try {
				connection = getConnection();
				preparedStatement = connection.prepareStatement(deleteSQL,Statement.RETURN_GENERATED_KEYS);
				preparedStatement.setInt(1, id);

				result = preparedStatement.executeUpdate();
				return true;

			} finally {
				closePreparedStatement(preparedStatement);
				closeConnection(connection);
			}
			
		}
		

		
		public FormatoreEntity doRetrieveByKey(int id) throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ResultSet rs = null;
			
			 
			FormatoreEntity bean = new FormatoreEntity();
	        
			String selectSQL = "SELECT IDFORMATORE, EMAIL, PASSWORD, NOME, COGNOME, SESSO, date_format(DATANASCITA,'%d-%m-%Y'), PAESEORIGINE, CODICEFISCALE, CONTOCORRENTE FROM " + FormatoreDao.TABLE_NAME + " WHERE IDFORMATORE = ?";

			try {
				connection = getConnection();
				preparedStatement = connection.prepareStatement(selectSQL, Statement.RETURN_GENERATED_KEYS);
				preparedStatement.setInt(1, id);

				rs = preparedStatement.executeQuery();

				while (rs.next()) {
					bean.setId(rs.getInt(1));
					bean.setEmail(rs.getString(2));
					bean.setPassword(rs.getString(3));
					bean.setName(rs.getString(4));
					bean.setSurname(rs.getString(5));
				    bean.setGender(rs.getString(6));
		            bean.setBirthDate(Utils.toDate(rs.getString(7)));
		            bean.setCountry(rs.getString(8));
		            bean.setCodiceFiscale(rs.getString(9));
		            bean.setContoCorrente(rs.getString(10));
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
		
		public Object doRetrieveByMail(String email) throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ResultSet rs = null;
			
			
			FormatoreEntity bean = new FormatoreEntity();
	        
			String selectSQL = "SELECT IDFORMATORE, EMAIL, PASSWORD, NOME, COGNOME, SESSO, date_format(DATANASCITA,'%d-%m-%Y'), PAESEORIGINE, CODICEFISCALE, CONTOCORRENTE FROM " + FormatoreDao.TABLE_NAME + " WHERE EMAIL = ?";

			try {
				connection = getConnection();
				preparedStatement = connection.prepareStatement(selectSQL,Statement.RETURN_GENERATED_KEYS);
				preparedStatement.setString(1, email);

				rs = preparedStatement.executeQuery();

				if (rs.next()) {
					bean.setId(rs.getInt(1));
					bean.setEmail(rs.getString(2));
					bean.setPassword(rs.getString(3));
					bean.setName(rs.getString(4));
					bean.setSurname(rs.getString(5));
				    bean.setGender(rs.getString(6));
		            bean.setBirthDate(Utils.toDate(rs.getString(7)));
		            bean.setCountry(rs.getString(8));
		            bean.setCodiceFiscale(rs.getString(9));
		            bean.setContoCorrente(rs.getString(10));
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
		

		
		public ArrayList<FormatoreEntity> doRetrieveAll() throws SQLException {
			
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ResultSet rs = null;
			ArrayList<FormatoreEntity> users = new ArrayList<FormatoreEntity>();

			String selectSQL = "SELECT IDFORMATORE, EMAIL, PASSWORD, NOME, COGNOME, SESSO, date_format(DATANASCITA,'%d-%m-%Y'), PAESEORIGINE, CODICEFISCALE, CONTOCORRENTE FROM " + FormatoreDao.TABLE_NAME ;

			if (users != null && !users.equals("")) {
				selectSQL += " ORDER BY IDFORMATORE";  // l' errore era qui , clausola order by non aveva un attributo corretto per la tabella studente.
			}

			try {
				connection = getConnection(); 
				preparedStatement = connection.prepareStatement(selectSQL,Statement.RETURN_GENERATED_KEYS);

				rs = preparedStatement.executeQuery();

				while (rs.next()) {
					
					FormatoreEntity bean = new FormatoreEntity();
					bean.setId(rs.getInt(1)); // da verificare se corretto
					bean.setEmail(rs.getString(2));
					bean.setPassword(rs.getString(3));
					bean.setName(rs.getString(4));
					bean.setSurname(rs.getString(5));
				    bean.setGender(rs.getString(6));
		            bean.setBirthDate(Utils.toDate(rs.getString(7)));
		            bean.setCountry(rs.getString(8));
		            bean.setCodiceFiscale(rs.getString(9));
		            bean.setContoCorrente(rs.getString(10));
					users.add(bean);
				}
			}catch(ParseException e) {
				throw new SQLException(e);
			} finally {
				closeResultSet(rs);
				closePreparedStatement(preparedStatement);
				closeConnection(connection);
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



		@Override
		public boolean update(int id) throws SQLException {
			// TODO Auto-generated method stub
			return false;
		}
	}