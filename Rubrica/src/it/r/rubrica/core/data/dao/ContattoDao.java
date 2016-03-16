package it.r.rubrica.core.data.dao;

import it.r.rubrica.core.data.model.Contatto;
import it.r.rubrica.core.data.utils.Transaction;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ContattoDao {

	public static void insert(Contatto contatto) {
		try (PreparedStatement insert = Transaction.sql("INSERT INTO contatti (id, nome, cognome, telefono, email, utente_id) VALUES (?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {

			insert.setString(1, contatto.getId());
			insert.setString(2, contatto.getNome());
			insert.setString(3, contatto.getCognome());
			insert.setString(4, contatto.getTelefono());
			insert.setString(5, contatto.getEmail());
			insert.setString(6, contatto.getUtenteId());

			insert.execute();

//			return ConnectionUtils.getGeneratedId(insert);
		}
		catch (SQLException e) {
			throw new RuntimeException("Errore durante l'insert di " + contatto, e);
		}
	}

	public static Contatto findById(String id) {
		try (PreparedStatement select = Transaction.sql("SELECT * FROM contatti WHERE id = ?")) {

			select.setString(1, id);

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
	
	public static List<Contatto> findAllByUserId(Integer userId) {
		try (PreparedStatement select = Transaction.sql("SELECT * FROM contatti WHERE utente_id = ?")) {
			
			select.setInt(1, userId);
			
			try (ResultSet resultSet = select.executeQuery()) {
			
				List<Contatto> contatti = new ArrayList<Contatto>();
				
				while (resultSet.next()) {
					contatti.add(transform(resultSet));
				}
				
				return contatti;
			}
		}
		catch (SQLException e) {
			throw new RuntimeException("Errore durante il recupero degli utenti", e);
		}
	}
	public static List<Contatto> findAllByUserIdAndTesto(String userId, String testo) {
		try (PreparedStatement select = Transaction.sql("SELECT * FROM contatti WHERE utente_id = ? AND (nome LIKE ? OR cognome LIKE ? OR email LIKE ? OR telefono LIKE ?)")) {
			String term = "%" + testo + "%";
			select.setString(1, userId);
			select.setString(2, term);
			select.setString(3, term);
			select.setString(4, term);
			select.setString(5, term);
			
			try (ResultSet resultSet = select.executeQuery()) {
				
				List<Contatto> contatti = new ArrayList<Contatto>();
				
				while (resultSet.next()) {
					contatti.add(transform(resultSet));
				}
				
				return contatti;
			}
		}
		catch (SQLException e) {
			throw new RuntimeException("Errore durante il recupero degli utenti", e);
		}
	}

	private static Contatto transform(ResultSet resultSet) throws SQLException {
		String id 			= resultSet.getString("id");
		String nome 			= resultSet.getString("nome");
		String cognome 		= resultSet.getString("cognome");
		String telefono 		= resultSet.getString("telefono");
		String email 		= resultSet.getString("email");
		String utente_id 	= resultSet.getString("utente_id");
		
		return new Contatto(id, nome, cognome, telefono, email, utente_id);
	}

}
