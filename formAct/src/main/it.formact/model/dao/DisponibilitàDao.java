package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import model.entity.Disponibilit‡Entity;


public class Disponibilit‡Dao implements DaoInterface{
	
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
                                    
	private static final String TABLE_NAME = "disponibilit‡";
	
	// creazione id studente dinamico
	public int nextId() throws SQLException {
		
		ArrayList<Disponibilit‡Entity> disps = (ArrayList<Disponibilit‡Entity>) this.doRetrieveAll();
		if(disps.size()==0)
			return 1;
		int next = (disps.get(disps.size()-1).getIdDisp())+1;
		
		return next;

	}

	
	public void doSave(Object bean) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		Disponibilit‡Entity disp = (Disponibilit‡Entity) bean;
		
		String insertSQL = "INSERT INTO " + Disponibilit‡Dao.TABLE_NAME
				+ " (ID, GIORNOSETTIMANA, ORARIO, STATO, PERCORSOFORMATIVO)"
				+ " VALUES (?, ?, ?, ?, ?)";
		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setInt(1, this.nextId());
			preparedStatement.setString(2, disp.getGiornoSettimana());
			preparedStatement.setTime(3, Time.valueOf(disp.getOrario()));
			preparedStatement.setInt(4, disp.getStato());
			preparedStatement.setInt(5, disp.getIdPercorso());
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

		String deleteSQL = "DELETE FROM " + Disponibilit‡Dao.TABLE_NAME + " WHERE ID = ?";
        
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

		
		
		Disponibilit‡Entity bean = new Disponibilit‡Entity();
        
		String selectSQL = "SELECT * FROM " + Disponibilit‡Dao.TABLE_NAME + " WHERE ID = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, id);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean.setIdDisp(rs.getInt("ID"));
				bean.setGiornoSettimana(rs.getString("GIORNOSETTIMANA"));
				
				bean.setOrario(rs.getObject("ORARIO", LocalTime.class));
				bean.setStato(rs.getInt("STATO"));
				bean.setIdPercorso(rs.getInt("PERCORSOFORMATIVO"));
	       
			    
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
	

	
	public ArrayList<Disponibilit‡Entity> doRetrieveAll() throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ArrayList<Disponibilit‡Entity> disps = new ArrayList<Disponibilit‡Entity>();

		String selectSQL = "SELECT * FROM " + Disponibilit‡Dao.TABLE_NAME;

		if (disps != null && !disps.equals("")) {
			selectSQL += " ORDER BY GIORNOSETTIMANA";  
		}

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				
				Disponibilit‡Entity bean = new Disponibilit‡Entity();

				bean.setIdDisp(rs.getInt("ID"));
				bean.setGiornoSettimana(rs.getString("GIORNOSETTIMANA"));
				bean.setOrario(rs.getObject("ORARIO", LocalTime.class));
				bean.setStato(rs.getInt("STATO"));
				bean.setIdPercorso(rs.getInt("PERCORSOFORMATIVO"));
				disps.add(bean);
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
		return disps;
		
	}
	
	
		public ArrayList<Disponibilit‡Entity> doRetrieveAllByStatus(int stato) throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;

			ArrayList<Disponibilit‡Entity> disps = new ArrayList<Disponibilit‡Entity>();

			String selectSQL = "SELECT * FROM " + Disponibilit‡Dao.TABLE_NAME + "WHERE STATO = ?";

			if (disps != null && !disps.equals("")) {
				selectSQL += " ORDER BY GIORNOSETTIMANA";  
			}

			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);
				preparedStatement.setInt(1, stato);

				ResultSet rs = preparedStatement.executeQuery();

				while (rs.next()) {
					
					Disponibilit‡Entity bean = new Disponibilit‡Entity();

					bean.setIdDisp(rs.getInt("ID"));
					bean.setGiornoSettimana(rs.getString("GIORNOSETTIMANA"));
					bean.setOrario(rs.getObject("ORARIO", LocalTime.class));
					bean.setStato(rs.getInt("STATO"));
					bean.setIdPercorso(rs.getInt("PERCORSOFORMATIVO"));
					disps.add(bean);
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
			return disps;
		}
		
		public ArrayList<Disponibilit‡Entity> doRetrieveAllByTime(Time orario) throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;

			ArrayList<Disponibilit‡Entity> disps = new ArrayList<Disponibilit‡Entity>();

			String selectSQL = "SELECT * FROM " + Disponibilit‡Dao.TABLE_NAME + "WHERE ORARIO = ?";

			if (disps != null && !disps.equals("")) {
				selectSQL += " ORDER BY GIORNOSETTIMANA";  
			}

			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);
				preparedStatement.setTime(1, orario);

				ResultSet rs = preparedStatement.executeQuery();

				while (rs.next()) {
					
					Disponibilit‡Entity bean = new Disponibilit‡Entity();

					bean.setIdDisp(rs.getInt("ID"));
					bean.setGiornoSettimana(rs.getString("GIORNOSETTIMANA"));
					bean.setOrario(rs.getObject("ORARIO", LocalTime.class));
					bean.setStato(rs.getInt("STATO"));
					bean.setIdPercorso(rs.getInt("PERCORSOFORMATIVO"));
					disps.add(bean);
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
			return disps;
		}
		
		public ArrayList<Disponibilit‡Entity> doRetrieveAllByDay(String giorno) throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;

			ArrayList<Disponibilit‡Entity> disps = new ArrayList<Disponibilit‡Entity>();

			String selectSQL = "SELECT * FROM " + Disponibilit‡Dao.TABLE_NAME + " WHERE GIORNOSETTIMANA = ?";

			if (disps != null && !disps.equals("")) {
				selectSQL += " ORDER BY GIORNOSETTIMANA";  
			}

			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);
				preparedStatement.setString(1, giorno);

				ResultSet rs = preparedStatement.executeQuery();

				while (rs.next()) {
					
					Disponibilit‡Entity bean = new Disponibilit‡Entity();

					bean.setIdDisp(rs.getInt("ID"));
					bean.setGiornoSettimana(rs.getString("GIORNOSETTIMANA"));
					bean.setOrario(rs.getObject("ORARIO", LocalTime.class));
					bean.setStato(rs.getInt("STATO"));
					bean.setIdPercorso(rs.getInt("PERCORSOFORMATIVO"));
					disps.add(bean);
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
			return disps;
		}
		
		public ArrayList<Disponibilit‡Entity> doRetrieveAllByPercorso(int percorso) throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;

			ArrayList<Disponibilit‡Entity> disps = new ArrayList<Disponibilit‡Entity>();

			String selectSQL = "SELECT * FROM " + Disponibilit‡Dao.TABLE_NAME + " WHERE PERCORSOFORMATIVO = ?";

			/*if (disps != null && !disps.equals("")) {
				selectSQL += " ORDER BY GIORNOSETTIMANA";  
			}*/

			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);
				preparedStatement.setInt(1, percorso);

				ResultSet rs = preparedStatement.executeQuery();

				while (rs.next()) {
					
					Disponibilit‡Entity bean = new Disponibilit‡Entity();

					bean.setIdDisp(rs.getInt("ID"));
					bean.setGiornoSettimana(rs.getString("GIORNOSETTIMANA"));
					bean.setOrario(rs.getObject("ORARIO", LocalTime.class));
					bean.setStato(rs.getInt("STATO"));
					bean.setIdPercorso(rs.getInt("PERCORSOFORMATIVO"));
					disps.add(bean);
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
			return disps;
		}
		
		
		public ArrayList<Disponibilit‡Entity> doRetrieveByGiornoAndPercorsoFormativo(String giorno, int percorsoFormativo) throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;

			ArrayList<Disponibilit‡Entity> disps = new ArrayList<Disponibilit‡Entity>();

			String selectSQL = "SELECT * FROM " + Disponibilit‡Dao.TABLE_NAME + " WHERE giornoSettimana = ? AND percorsoFormativo = ?";
			
			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);
				preparedStatement.setString(1, giorno);
				preparedStatement.setInt(2, percorsoFormativo);

				ResultSet rs = preparedStatement.executeQuery();

				while (rs.next()) {
					
					Disponibilit‡Entity bean = new Disponibilit‡Entity();

					bean.setIdDisp(rs.getInt("ID"));
					bean.setGiornoSettimana(rs.getString("GIORNOSETTIMANA"));
					bean.setOrario(rs.getObject("ORARIO", LocalTime.class));
					bean.setStato(rs.getInt("STATO"));
					bean.setIdPercorso(rs.getInt("PERCORSOFORMATIVO"));
					disps.add(bean);
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
			return disps;
		}
		
	}
	
	
	


