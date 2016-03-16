package it.r.rubrica.core.data.dao;

import it.r.rubrica.core.data.model.Ruolo;
import it.r.rubrica.core.data.model.Utente;
import it.r.rubrica.core.data.utils.Transaction;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UtenteDao {

	public static void insert(Utente utente) {
		try (PreparedStatement insert = Transaction.sql("INSERT INTO utenti (id, username, password, ruolo, abilitato, contatto_id) VALUES (?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
			insert.setString (1, utente.getId());
			insert.setString (2, utente.getUsername());
			insert.setString (3, utente.getPassword());
			insert.setString (4, utente.getRuolo().toString());
			insert.setBoolean(5, utente.getAbilitato());
			insert.setString (6, utente.getContattoId());
			
			insert.execute();
			
//			return ConnectionUtils.getGeneratedId(insert);
		}
		catch (SQLException e) {
			throw new RuntimeException("Errore durante l'insert di " + utente, e);
		}
	}
	
	public static void update(Utente utente) {
		try (PreparedStatement update = Transaction.sql("UPDATE utenti SET username = ?, password = ?, ruolo = ?, abilitato = ? WHERE id = ?")) {

			update.setString(1, utente.getUsername());
			update.setString(2, utente.getPassword());
			update.setString(3, utente.getRuolo().toString());
			update.setBoolean(4, utente.getAbilitato());
			update.setString(5, utente.getId());
			
			update.execute();
		}
		catch (SQLException e) {
			throw new RuntimeException("Errore durante update dell'utente " + utente, e);
		}
	}
	
	public static Utente findById(Integer id) {
		try (PreparedStatement select = Transaction.sql("SELECT * FROM utenti WHERE id = ?")) {

			select.setInt(1, id);
			
			try (ResultSet resultSet = select.executeQuery()) {
				if (resultSet.next()) {
					return transform(resultSet);
				}
				else {
					return null;
				}
			}
		}
		catch (SQLException e) {
			throw new RuntimeException("Errore durante il recupero dell'utente con id " + id, e);
		}
	}
	
	public static List<Utente> findAllByIds(List<Integer> ids) {
		try (
			PreparedStatement select = Transaction.sql("SELECT * FROM utenti WHERE id IN (" + placeholders(ids.size()) + ")")
		)
		{

			for (int i = 0 ; i < ids.size() ; i++) {
				select.setInt(i + 1, ids.get(i));
			}
			
			try (ResultSet resultSet = select.executeQuery()) {
				List<Utente> utenti = new ArrayList<Utente>();
				
				while (resultSet.next()) {
					utenti.add(transform(resultSet));
				}
				
				return utenti;
			}
		}
		catch (SQLException e) {
			throw new RuntimeException("Errore durante il recupero degli utenti " + ids, e);
		}
	}
	
	public static Utente findByUsername(String username) {
		try (PreparedStatement select = Transaction.sql("SELECT * FROM utenti WHERE username = ?")) {

			select.setString(1, username);
			
			try (ResultSet resultSet = select.executeQuery()) {
			
				if (resultSet.next()) {
					return transform(resultSet);
				}
				else {
					return null;
				}
			}
		}
		catch (SQLException e) {
			throw new RuntimeException("Errore durante il recupero dell'utente con username " + username, e);
		}
	}
	
	public static List<Utente> findAll() {
		try (PreparedStatement select = Transaction.sql("SELECT * FROM utenti")) {
			
			try (ResultSet resultSet = select.executeQuery()) {
			
				List<Utente> utenti = new ArrayList<Utente>();
				
				while (resultSet.next()) {
					utenti.add(transform(resultSet));
				}
				
				return utenti;
			}
		}
		catch (SQLException e) {
			throw new RuntimeException("Errore durante il recupero degli utenti", e);
		}
	}
	
	/*
	public static Integer maxColonna() {
		try (PreparedStatement select = Transaction.sql("SELECT MAX(colonna) as max FROM utenti WHERE username = ?")) {

			try (ResultSet resultSet = select.executeQuery()) {
			
				if (resultSet.next()) {
					Integer max = resultSet.getInt("max");
					
					return max;
				}
				else {
					return null;
				}
			}
		}
		catch (SQLException e) {
			throw new RuntimeException("Errore durante il recupero dell'utente con username " + username, e);
		}
	}
	*/
	private static Utente transform(ResultSet resultSet) throws SQLException {
		String pk         = resultSet.getString("id");
		String username   = resultSet.getString("username");
		String password   = resultSet.getString("password");
		String ruolo      = resultSet.getString("ruolo");
		Boolean abilitato = resultSet.getBoolean("abilitato");
		String contattoId = resultSet.getString("contatto_id");
		
		Utente utente = new Utente(pk, username, password, Ruolo.valueOf(ruolo), abilitato, contattoId);
		
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
