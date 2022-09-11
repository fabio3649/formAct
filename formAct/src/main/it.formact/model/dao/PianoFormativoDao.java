package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import model.entity.IscrizioneEntity;
import model.entity.PercorsoFormativoEntity;
import model.entity.PreferenzaStudenteEntity;
import pianoformativo.geneticalgorithm.Stato;

/**
 *  
 * Questa classe si occupa di interrogare il DB per la parte del modulo intelligente.
 *
 */
public class PianoFormativoDao implements DaoInterface { 
	
	private Connection getConnection() throws SQLException {
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");
			
			DataSource ds = (DataSource) envCtx.lookup("jdbc/formactds");
			return ds.getConnection();
			
		} 
		catch (NamingException e) {
			System.out.println("Error:" + e.getMessage());
			throw new SQLException(e);
		}
	}
	
	/**
	 * 
	 * Il seguente metodo restituisce lo spazio degli stati tramite i giorni liberi e le iscrizioni di uno studente:
	 * per ogni stato presente nel DB, esso verrà selezionato:
	 * - se è disponibile per almeno un giorno libero;
	 * - se è un percorso formativo che NON fa parte delle iscrizioni dello studente.
	 * 
	 * @param giorniLiberi: giorni liberi dello studente
	 * @param iscrizioni: iscrizioni dello studente
	 * @return un array di stati (spazio stati)
	 * @throws SQLException
	 */
	public ArrayList<Stato> doRetrieveSpazioStati(ArrayList<PreferenzaStudenteEntity> giorniLiberi, ArrayList<IscrizioneEntity> iscrizioni) {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		
		ArrayList<Stato> spazioStati = new ArrayList<Stato>();
		
		// se non sono presenti dei giorni liberi allora ritorno un array vuoto
		if (giorniLiberi == null || giorniLiberi.size() <= 0) {
			return spazioStati;
		}
		
		// interrogo il database che ritorna un array di stati che non hanno un percorso formativo che:
		// - non fa parte delle iscrizioni dello studente;
		// - non viene insegnato nei giorni liberi dello studente.
		// gli stati dell'array sono ordinati in modo pseudo-casuale
		String selectSQL = " SELECT * FROM PERCORSO_FORMATIVO INNER JOIN(DISPONIBILITA) ";
		selectSQL += " ON PERCORSO_FORMATIVO.IDPERCORSO_FORMATIVO = DISPONIBILITA.PERCORSOFORMATIVO ";
		selectSQL += " WHERE PERCORSO_FORMATIVO.VALIDATE = 1 AND DISPONIBILITA.STATO = 1 ";
		if (iscrizioni != null && iscrizioni.size() > 0) {
			for (int i = 0; i < iscrizioni.size(); i++) {
				selectSQL += " AND PERCORSO_FORMATIVO.IDPERCORSO_FORMATIVO != " + iscrizioni.get(i).getPercorsoFormativo() + " ";
			}
		}
		if (giorniLiberi != null && giorniLiberi.size() > 0) {
			selectSQL += " AND ( ";
			for (int i = 0; i < giorniLiberi.size(); i++) {
				selectSQL += " DISPONIBILITA.GIORNOSETTIMANA = '" + giorniLiberi.get(i).getDisponibilita() + "'";
				if (i < giorniLiberi.size() - 1) {
					selectSQL += " OR ";
				}
			}
			selectSQL += " ) ";
		}
		selectSQL += " ORDER BY RAND()";
		
		try { 
			connection = getConnection();
			preparedStatement = connection.prepareStatement(selectSQL, Statement.RETURN_GENERATED_KEYS);
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Stato stato = new Stato();
				PercorsoFormativoEntity percorsoFormativo = new PercorsoFormativoEntity();
				percorsoFormativo.setId(rs.getInt("idpercorso_formativo"));
				percorsoFormativo.setId_formatore(rs.getInt("formatore"));
				percorsoFormativo.setNome(rs.getString("nome"));
				percorsoFormativo.setCategoria(rs.getInt("ambito"));
				percorsoFormativo.setDescrizione(rs.getString("descrizione"));
				percorsoFormativo.setIndice_contenuti(rs.getString("indiceContenuti"));
				percorsoFormativo.setNum_lezioni(rs.getInt("numeroLezioni"));
				percorsoFormativo.setCosto(rs.getDouble("costo"));
				
				stato.setPercorsoFormativo(percorsoFormativo);
				stato.setGiorno(rs.getString("giornoSettimana"));
				String orario = rs.getString("orario");
				String ora = orario.substring(0,1);
				ora += (Character.compare(orario.charAt(1), ':') != 0) ? orario.charAt(1) : "";
				int posTwoPoints = orario.indexOf(":");
				String minuto = orario.substring(posTwoPoints + 1, posTwoPoints + 3);
				stato.setOrario(LocalTime.of(Integer.parseInt(ora), Integer.parseInt(minuto), 0));
				
				spazioStati.add(stato);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			closeResultSet(rs);
			closePreparedStatement(preparedStatement);
			closeConnection(connection);
		}
		return spazioStati;
		
	}
	
	/**
	 * Il seguente metodo restituisce gli interessi dello studente.
	 * 
	 * @param idStudente: identificativo studente
	 * @return gli interessi dello studente
	 * @throws SQLException
	 */
	public ArrayList<String> doRetrieveInteressiStudente(int idStudente) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		
		ArrayList<String> interessiStudente = new ArrayList<>();
		
		String selectSQL = " SELECT * FROM INTERESSE_STUDENTE INNER JOIN(INTERESSE) ";
		selectSQL += " ON INTERESSE_STUDENTE.INTERESSE = INTERESSE.IDINTERESSE ";
		selectSQL += " AND INTERESSE_STUDENTE.STUDENTE = ?";
		
		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(selectSQL, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setInt(1, idStudente);
			
			rs = preparedStatement.executeQuery();
						
			while (rs.next()) {
				String nomeInteresse = rs.getString("nome");
				interessiStudente.add(nomeInteresse);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			closeResultSet(rs);
			closePreparedStatement(preparedStatement);
			closeConnection(connection);
		}
		
		return interessiStudente;
	}
	
	/**
	 * Il seguente metodo restituisce tutte le categorie di percorsi formativi.
	 * 
	 * @return le categorie di percorsi formativi
	 * @throws SQLException
	 */
	public Map<Integer,String> doRetrieveCategorie() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		
		Map<Integer,String> categorie = new HashMap<Integer,String>();
		String selectSQL = "SELECT IDCATEGORIA, NOME FROM CATEGORIA ORDER BY IDCATEGORIA";
		
		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(selectSQL, Statement.RETURN_GENERATED_KEYS);
			
			rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				Integer idCategoria = rs.getInt("IDCATEGORIA");
				String nome = rs.getString("NOME");
				categorie.put(idCategoria, nome);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			closeResultSet(rs);
			closePreparedStatement(preparedStatement);
			closeConnection(connection);
		}
		
		return categorie;
	}
	
	public Map<Integer,String> doRetrieveFormatori() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		
		Map<Integer,String> formatori = new HashMap<Integer,String>();
		String selectSQL = "SELECT IDFORMATORE, NOME, COGNOME FROM FORMATORE ORDER BY IDFORMATORE";
		
		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(selectSQL, Statement.RETURN_GENERATED_KEYS);
			
			rs = preparedStatement.executeQuery();
			
			
			while (rs.next()) {
				Integer idFormatore = rs.getInt("IDFORMATORE");
				String nomeAndCognome = rs.getString("NOME") + " " + rs.getString("COGNOME");
				formatori.put(idFormatore, nomeAndCognome);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			closeResultSet(rs);
			closePreparedStatement(preparedStatement);
			closeConnection(connection);
		}
		
		return formatori;
	}
	
	protected final void closeConnection(Connection c) {
		if( c != null)
			try {
				c.close();
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	protected final void closePreparedStatement(PreparedStatement p) {
		if( p != null)
			try {
				p.close();
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	protected final void closeResultSet(ResultSet rs) {
		if( rs != null)
			try {
				rs.close();
			}
			catch (SQLException e) {
				e.printStackTrace();
			}			
	}

	
	
	
	
	@Override
	public int doSave(Object bean) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean doDelete(int id) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object doRetrieveByKey(int id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List doRetrieveAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(int id) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}
	
}









