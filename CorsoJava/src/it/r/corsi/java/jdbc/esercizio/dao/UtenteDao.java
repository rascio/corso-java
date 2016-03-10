package it.r.corsi.java.jdbc.esercizio.dao;

import it.r.corsi.java.jdbc.ConnectionUtils;
import it.r.corsi.java.jdbc.esercizio.model.Ruolo;
import it.r.corsi.java.jdbc.esercizio.model.Utente;
import it.r.corsi.java.jdbc.esercizio.utils.EsercizioJdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UtenteDao {

	public static Integer insert(Utente utente) {
		Connection connection = null;
		PreparedStatement insert = null;
		try {
			connection = EsercizioJdbcUtils.openConnection();
			
			insert = connection.prepareStatement("INSERT INTO utenti (username, password, ruolo, abilitato) VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			insert.setString(1, utente.getUsername());
			insert.setString(2, utente.getPassword());
			insert.setString(3, utente.getRuolo().toString());
			insert.setBoolean(4, utente.getAbilitato());
			
			insert.execute();
			
			connection.commit();
			
			return ConnectionUtils.getGeneratedId(insert);
		}
		catch (SQLException e) {
			ConnectionUtils.rollback(connection);
			throw new RuntimeException("Errore durante l'inserimento dell'utente " + utente, e);
		}
		finally {
			ConnectionUtils.close(connection, insert);
		}
	}
	
	public static void update(Utente utente) {
		Connection connection = null;
		PreparedStatement update = null;
		try {
			connection = EsercizioJdbcUtils.openConnection();
			
			update = connection.prepareStatement("UPDATE utenti SET username = ?, password = ?, ruolo = ?, abilitato = ? WHERE id = ?");
			update.setString(1, utente.getUsername());
			update.setString(2, utente.getPassword());
			update.setString(3, utente.getRuolo().toString());
			update.setBoolean(4, utente.getAbilitato());
			update.setInt(5, utente.getId());
			
			update.execute();
			
			connection.commit();
		}
		catch (SQLException e) {
			ConnectionUtils.rollback(connection);
			throw new RuntimeException("Errore durante l'inserimento dell'utente " + utente, e);
		}
		finally {
			ConnectionUtils.close(connection, update);
		}
	}
	
	public static Utente findById(Integer id) {
		Connection connection = null;
		PreparedStatement select = null;
		ResultSet resultSet = null;
		try {
			connection = EsercizioJdbcUtils.openConnection();
			
			select = connection.prepareStatement("SELECT * FROM utente WHERE id = ?");
			select.setInt(1, id);
			
			resultSet = select.executeQuery();
			
			if (resultSet.next()) {
				return transform(resultSet);
			}
			else {
				return null;
			}
		}
		catch (SQLException e) {
			ConnectionUtils.rollback(connection);
			throw new RuntimeException("Errore durante il recupero dell'utente con id " + id, e);
		}
		finally {
			ConnectionUtils.close(connection, select, resultSet);
		}
	}
	
	public static List<Utente> findAllByIds(List<Integer> ids) {
		Connection connection = null;
		PreparedStatement select = null;
		ResultSet resultSet = null;
		try {
			connection = EsercizioJdbcUtils.openConnection();
			
			select = connection.prepareStatement("SELECT * FROM utenti WHERE id IN (" + placeholders(ids.size()) + ")");
			for (int i = 0 ; i < ids.size() ; i++) {
				select.setInt(i + 1, ids.get(i));
			}
			
			resultSet = select.executeQuery();
			
			List<Utente> utenti = new ArrayList<Utente>();
			
			while (resultSet.next()) {
				utenti.add(transform(resultSet));
			}
			
			return utenti;
		}
		catch (SQLException e) {
			ConnectionUtils.rollback(connection);
			throw new RuntimeException("Errore durante il recupero degli utenti " + ids, e);
		}
		finally {
			ConnectionUtils.close(connection, select, resultSet);
		}
	}
	
	public static Utente findByUsername(String username) {
		Connection connection = null;
		PreparedStatement select = null;
		ResultSet resultSet = null;
		try {
			connection = EsercizioJdbcUtils.openConnection();
			
			select = connection.prepareStatement("SELECT * FROM utenti WHERE username = ?");
			select.setString(1, username);
			
			resultSet = select.executeQuery();
			
			if (resultSet.next()) {
				return transform(resultSet);
			}
			else {
				return null;
			}
		}
		catch (SQLException e) {
			ConnectionUtils.rollback(connection);
			throw new RuntimeException("Errore durante il recupero dell'utente con username " + username, e);
		}
		finally {
			ConnectionUtils.close(connection, select, resultSet);
		}
	}
	
	public static List<Utente> findAll() {
		Connection connection = null;
		PreparedStatement select = null;
		ResultSet resultSet = null;
		try {
			connection = EsercizioJdbcUtils.openConnection();
			
			select = connection.prepareStatement("SELECT * FROM utenti");
			
			resultSet = select.executeQuery();
			
			List<Utente> utenti = new ArrayList<Utente>();
			
			while (resultSet.next()) {
				utenti.add(transform(resultSet));
			}
			
			return utenti;
		}
		catch (SQLException e) {
			ConnectionUtils.rollback(connection);
			throw new RuntimeException("Errore durante il recupero degli utenti", e);
		}
	}

	private static Utente transform(ResultSet resultSet) throws SQLException {
		Integer pk        = resultSet.getInt("id");
		String username   = resultSet.getString("username");
		String password   = resultSet.getString("password");
		String ruolo      = resultSet.getString("ruolo");
		Boolean abilitato = resultSet.getBoolean("abilitato");
		
		Utente utente = new Utente(pk, username, password, Ruolo.valueOf(ruolo), abilitato);
		
		return utente;
	}
	
	private static String placeholders(int n) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0 ; i < n ; i++) {
			builder.append("?, ");
		}
		return builder.substring(0, builder.length() - 2);
	}
}
