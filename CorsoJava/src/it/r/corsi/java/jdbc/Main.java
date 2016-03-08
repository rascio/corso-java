package it.r.corsi.java.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
	public static void main(String[] args) {
		/*
		 * CREATE TABLE persone (id INTEGER AUTO_INCREMENT, nome VARCHAR(20), cognome VARCHAR(20), PRIMARY KEY(id));
		 */
		Connection connection = null;
		PreparedStatement insert = null;
		PreparedStatement select = null;
		ResultSet selectResultSet = null;
		try {
			connection = ConnectionUtils.getConnection("jdbc:mysql://localhost/test", "root", null);
			connection.setAutoCommit(false);
			
			insert = connection.prepareStatement("INSERT INTO persone (nome, cognome) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
			insert.setString(1, "Manuel");
			insert.setString(2, "Fantoni");
		
			insert.execute();
		
			int updated = insert.getUpdateCount();
			Integer idGenerato = ConnectionUtils.getGeneratedId(insert);
			System.out.println("Sono stati aggiornati " + updated + " record. Id generato: " + idGenerato);
			
			select = connection.prepareStatement("SELECT * FROM persone");
			selectResultSet = select.executeQuery();
			
			while (selectResultSet.next()) {
				Integer id = selectResultSet.getInt("id");
				String nome = selectResultSet.getString("nome");
				String cognome = selectResultSet.getString("cognome");
				
				System.out.println("Trovato " + nome + " " + cognome + " con id " + id);
			}
			connection.commit();
		} 
		catch (SQLException e) {
			ConnectionUtils.close(insert);
			ConnectionUtils.close(selectResultSet);
			ConnectionUtils.close(select);
			ConnectionUtils.close(connection);
			try {
				ConnectionUtils.rollback(connection);
			}
			catch (SQLException e1) {
				System.err.println("C'è stato un errore durante il rollback. Il rollback è stato causato da:");
				e.printStackTrace();
				
				throw new RuntimeException("Errore durante il rollback", e1);
			}
			throw new RuntimeException("Errore durante l'esecuzione di getUpdateCount()", e);
		}
		
	}

	
}
