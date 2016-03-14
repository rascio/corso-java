package it.r.rubrica.core.data.utils;

import it.r.rubrica.core.data.RubricaJdbcConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Transaction {

	/*
	 * Il ThreadLocal è un contenitore dove ogni Thread può metterci un valore che solo lui può riprendere
	 */
	private static final ThreadLocal<Connection> CONNECTION = new ThreadLocal<Connection>();
	
	/**
	 * Inizia una nuova transazione per questo thread
	 */
	public static void begin() {
		if (CONNECTION.get() != null) {
			throw new IllegalStateException("La connessione è già aperta");
		}
		Connection connection = RubricaJdbcConnection.openConnection();
		
		CONNECTION.set(connection);
	}
	
	/**
	 * Effettua il commit della transazione corrente
	 */
	public static void commit() {
		Connection connection = currentConnection();
		
		try {
			connection.commit();
		}
		catch (SQLException e) {
			throw new RuntimeException("Errore durante il commit", e);
		}
		finally {
			close();
		}
	}
	
	/**
	 * Effettua il rollback della transazione corrente
	 */
	public static void rollback() {
		Connection connection = currentConnection();
		
		try {
			connection.rollback();
		}
		catch (SQLException e) {
			throw new RuntimeException("Errore durante il rollback", e);
		}
		finally {
			close();
		}
	}
	
	public static PreparedStatement sql(String sql) throws SQLException {
		return currentConnection().prepareStatement(sql);
	}
	public static PreparedStatement sql(String sql, int statementType) throws SQLException {
		return currentConnection().prepareStatement(sql, statementType);
	}
	
	/**
	 * Restituisce una Connection precedentemente aperta con il 'begin()'
	 * @return
	 */
	public static Connection currentConnection() {
		Connection connection = CONNECTION.get();
		if (connection == null) {
			throw new IllegalStateException("La connessione non è mai stata aperta per questo thread. Chiamare Transaction.begin()");
		}
		return connection;
	}
	
	public static void close() {
		Connection connection = currentConnection();
		
		try {
			ConnectionUtils.close(connection);
		}
		finally {
			CONNECTION.remove();
		}
	}
}
