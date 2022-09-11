package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import model.entity.DisponibilitaEntity;


public class DisponibilitaDao implements DaoInterface{
	
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
                                    
	private static final String TABLE_NAME = "disponibilita";
	
	

	
	public int doSave(Object bean) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		DisponibilitaEntity disp = (DisponibilitaEntity) bean;
		
		String insertSQL = "INSERT INTO " + DisponibilitaDao.TABLE_NAME
				+ " (ID, GIORNOSETTIMANA, ORARIO, STATO, PERCORSOFORMATIVO)"
				+ " VALUES (?, ?, ?, ?, ?)";
		int id = 0;
		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);
			//id = this.nextId();
			preparedStatement.setInt(1, id);
			preparedStatement.setString(2, disp.getGiornoSettimana());
			preparedStatement.setString(3,  disp.getOrario());
			preparedStatement.setInt(4, disp.getStato());
			preparedStatement.setInt(5, disp.getIdPercorso());
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
		
		
		disp.setIdDisp(id);
		return id;
	}
		
	

	
	public boolean doDelete(int id) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
	
         
		int result = 0;

		String deleteSQL = "DELETE FROM " + DisponibilitaDao.TABLE_NAME + " WHERE ID = ?";
        
		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setInt(1, id);

			result = preparedStatement.executeUpdate();
			return true;

		} finally {
			closePreparedStatement(preparedStatement);
			closeConnection(connection);
		
		}
		
	}
	

	
	public Object doRetrieveByKey(int id) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		
		
		DisponibilitaEntity bean = new DisponibilitaEntity();
        
		String selectSQL = "SELECT ID, GIORNOSETTIMANA, ORARIO, STATO, PERCORSOFORMATIVO FROM " + DisponibilitaDao.TABLE_NAME + " WHERE ID = ?";

		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(selectSQL,Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setInt(1, id);

			rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean.setIdDisp(rs.getInt(1));
				bean.setGiornoSettimana(rs.getString(2));
				
				bean.setOrario(rs.getString(3));
				bean.setStato(rs.getInt(4));
				bean.setIdPercorso(rs.getInt(5));
	       
			    
			}

		} finally {
			closeResultSet(rs);
			closePreparedStatement(preparedStatement);
			closeConnection(connection);
		}
		
		return bean;
	}
	
	public boolean updateStatus(int id, int status) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int result = 0;
			
		String selectSQL = "UPDATE " + DisponibilitaDao.TABLE_NAME + " SET STATO = ? " + " WHERE ID = ? ";
		    
		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(selectSQL, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setInt(1, status);
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
	

	
	public ArrayList<DisponibilitaEntity> doRetrieveAll() throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		ArrayList<DisponibilitaEntity> disps = new ArrayList<DisponibilitaEntity>();

		String selectSQL = "SELECT * FROM " + DisponibilitaDao.TABLE_NAME;

		if (disps != null && !disps.equals("")) {
			selectSQL += " ORDER BY GIORNOSETTIMANA";  
		}

		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(selectSQL, Statement.RETURN_GENERATED_KEYS);

			rs = preparedStatement.executeQuery();

			while (rs.next()) {
				
				DisponibilitaEntity bean = new DisponibilitaEntity();

				bean.setIdDisp(rs.getInt("ID"));
				bean.setGiornoSettimana(rs.getString("GIORNOSETTIMANA"));
				bean.setOrario(rs.getString("ORARIO"));
				bean.setStato(rs.getInt("STATO"));
				bean.setIdPercorso(rs.getInt("PERCORSOFORMATIVO"));
				disps.add(bean);
			}

		} finally {
			closeResultSet(rs);
			closePreparedStatement(preparedStatement);
			closeConnection(connection);
		
		}
		return disps;
		
	}
	
	
		public ArrayList<DisponibilitaEntity> doRetrieveAllByStatus(int stato) throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ResultSet rs = null;
			ArrayList<DisponibilitaEntity> disps = new ArrayList<DisponibilitaEntity>();

			String selectSQL = "SELECT * FROM " + DisponibilitaDao.TABLE_NAME + "WHERE STATO = ?";

			if (disps != null && !disps.equals("")) {
				selectSQL += " ORDER BY GIORNOSETTIMANA";  
			}

			try {
				connection = getConnection();
				preparedStatement = connection.prepareStatement(selectSQL, Statement.RETURN_GENERATED_KEYS);
				preparedStatement.setInt(1, stato);

				rs = preparedStatement.executeQuery();

				while (rs.next()) {
					
					DisponibilitaEntity bean = new DisponibilitaEntity();

					bean.setIdDisp(rs.getInt("ID"));
					bean.setGiornoSettimana(rs.getString("GIORNOSETTIMANA"));
					bean.setOrario(rs.getString("ORARIO"));
					bean.setStato(rs.getInt("STATO"));
					bean.setIdPercorso(rs.getInt("PERCORSOFORMATIVO"));
					disps.add(bean);
				}

			} finally {
				closeResultSet(rs);
				closePreparedStatement(preparedStatement);
				closeConnection(connection);
			
			}
			return disps;
		}
		
		public ArrayList<DisponibilitaEntity> doRetrieveAllByTime(Time orario) throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ResultSet rs = null;
			ArrayList<DisponibilitaEntity> disps = new ArrayList<DisponibilitaEntity>();

			String selectSQL = "SELECT * FROM " + DisponibilitaDao.TABLE_NAME + "WHERE ORARIO = ?";

			if (disps != null && !disps.equals("")) {
				selectSQL += " ORDER BY GIORNOSETTIMANA";  
			}

			try {
				connection = getConnection();
				preparedStatement = connection.prepareStatement(selectSQL, Statement.RETURN_GENERATED_KEYS);
				preparedStatement.setTime(1, orario);

				rs = preparedStatement.executeQuery();

				while (rs.next()) {
					
					DisponibilitaEntity bean = new DisponibilitaEntity();
 
					bean.setIdDisp(rs.getInt("ID"));
					bean.setGiornoSettimana(rs.getString("GIORNOSETTIMANA"));
					bean.setOrario(rs.getString("ORARIO"));
					bean.setStato(rs.getInt("STATO"));
					bean.setIdPercorso(rs.getInt("PERCORSOFORMATIVO"));
					disps.add(bean);
				}

			} finally {
				closeResultSet(rs);
				closePreparedStatement(preparedStatement);
				closeConnection(connection);
			
			}
			return disps;
		}
		
		public ArrayList<DisponibilitaEntity> doRetrieveAllByDay(String giorno) throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ResultSet rs = null;
			ArrayList<DisponibilitaEntity> disps = new ArrayList<DisponibilitaEntity>();

			String selectSQL = "SELECT * FROM " + DisponibilitaDao.TABLE_NAME + " WHERE GIORNOSETTIMANA = ?";

			if (disps != null && !disps.equals("")) {
				selectSQL += " ORDER BY GIORNOSETTIMANA";  
			}

			try {
				connection = getConnection();
				preparedStatement = connection.prepareStatement(selectSQL, Statement.RETURN_GENERATED_KEYS);
				preparedStatement.setString(1, giorno);

				 rs = preparedStatement.executeQuery();

				while (rs.next()) {
					
					DisponibilitaEntity bean = new DisponibilitaEntity();

					bean.setIdDisp(rs.getInt("ID"));
					bean.setGiornoSettimana(rs.getString("GIORNOSETTIMANA"));
					bean.setOrario(rs.getString("ORARIO"));
					bean.setStato(rs.getInt("STATO"));
					bean.setIdPercorso(rs.getInt("PERCORSOFORMATIVO"));
					disps.add(bean);
				}

			} finally {
				closeResultSet(rs);
				closePreparedStatement(preparedStatement);
				closeConnection(connection);
			
			}
			return disps;
		}
		
		public ArrayList<DisponibilitaEntity> doRetrieveAllByPercorso(int percorso) throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ResultSet rs = null;
			ArrayList<DisponibilitaEntity> disps = new ArrayList<DisponibilitaEntity>();

			String selectSQL = "SELECT * FROM " + DisponibilitaDao.TABLE_NAME + " WHERE PERCORSOFORMATIVO = ?";

			/*if (disps != null && !disps.equals("")) {
				selectSQL += " ORDER BY GIORNOSETTIMANA";  
			}*/

			try {
				connection = getConnection();
				preparedStatement = connection.prepareStatement(selectSQL, Statement.RETURN_GENERATED_KEYS);
				preparedStatement.setInt(1, percorso);

				rs = preparedStatement.executeQuery();

				while (rs.next()) {
					
					DisponibilitaEntity bean = new DisponibilitaEntity();

					bean.setIdDisp(rs.getInt("ID"));
					bean.setGiornoSettimana(rs.getString("GIORNOSETTIMANA"));
					bean.setOrario(rs.getString("ORARIO"));
					bean.setStato(rs.getInt("STATO"));
					bean.setIdPercorso(rs.getInt("PERCORSOFORMATIVO"));
					disps.add(bean);
				}

			} finally {
				closeResultSet(rs);
				closePreparedStatement(preparedStatement);
				closeConnection(connection);
			
			}
			return disps;
		}
		
		public ArrayList<DisponibilitaEntity> doRetrieveAllAvailable(int percorso) throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ResultSet rs = null;
			ArrayList<DisponibilitaEntity> disps = new ArrayList<DisponibilitaEntity>();

			String selectSQL = "SELECT * FROM " + DisponibilitaDao.TABLE_NAME + " WHERE PERCORSOFORMATIVO = ? "
					+ " AND STATO = 1";

			/*if (disps != null && !disps.equals("")) {
				selectSQL += " ORDER BY GIORNOSETTIMANA";  
			}*/

			try {
				connection = getConnection();
				preparedStatement = connection.prepareStatement(selectSQL, Statement.RETURN_GENERATED_KEYS);
				preparedStatement.setInt(1, percorso);

				rs = preparedStatement.executeQuery();

				while (rs.next()) {
					
					DisponibilitaEntity bean = new DisponibilitaEntity();

					bean.setIdDisp(rs.getInt("ID"));
					bean.setGiornoSettimana(rs.getString("GIORNOSETTIMANA"));
					bean.setOrario(rs.getString("ORARIO"));
					bean.setStato(rs.getInt("STATO"));
					bean.setIdPercorso(rs.getInt("PERCORSOFORMATIVO"));
					disps.add(bean);
				}

			} finally {
				closeResultSet(rs);
				closePreparedStatement(preparedStatement);
				closeConnection(connection);
			
			}
			return disps;
		}
		
		
		public ArrayList<DisponibilitaEntity> doRetrieveByGiornoAndPercorsoFormativo(String giorno, int percorsoFormativo) throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ResultSet rs = null;
			ArrayList<DisponibilitaEntity> disps = new ArrayList<DisponibilitaEntity>();

			String selectSQL = "SELECT * FROM " + DisponibilitaDao.TABLE_NAME + " WHERE giornoSettimana = ? AND percorsoFormativo = ?";
			
			try {
				connection = getConnection();
				preparedStatement = connection.prepareStatement(selectSQL, Statement.RETURN_GENERATED_KEYS);
				preparedStatement.setString(1, giorno);
				preparedStatement.setInt(2, percorsoFormativo);

				rs = preparedStatement.executeQuery();

				while (rs.next()) {
					
					DisponibilitaEntity bean = new DisponibilitaEntity();

					bean.setIdDisp(rs.getInt("ID"));
					bean.setGiornoSettimana(rs.getString("GIORNOSETTIMANA"));
					bean.setOrario(rs.getString("ORARIO"));
					bean.setStato(rs.getInt("STATO"));
					bean.setIdPercorso(rs.getInt("PERCORSOFORMATIVO"));
					disps.add(bean);
				}

			} finally {
				closeResultSet(rs);
				closePreparedStatement(preparedStatement);
				closeConnection(connection);
			
			}
			return disps;
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
	
	
	


