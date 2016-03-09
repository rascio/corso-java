package it.r.corsi.java.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionUtils {
	public static Connection getConnection(String url, String username, String password) {
		try {
			return DriverManager.getConnection(url, username, password);
		} 
		catch (SQLException e) {
			throw new RuntimeException("C'è stato un errore durante il getConnection", e);
		}
	}
	
	public static Integer getGeneratedId(Statement statement) throws SQLException{
		/*
		 * Costrutto try-with-resource
		 * 
		 * Si può utilizzare con le classi che implementano Autocloseable.
		 * Tutte le variabili dichiarate all'interno delle parentesi del try()
		 * verranno automaticamente chiuse all'uscita
		 */
		try (ResultSet rs = statement.getGeneratedKeys()) {
			if (rs != null) {
				if (rs.next()) {
					return rs.getInt(1);
				}
			}
			throw new RuntimeException("Nessun id generato per lo statement: " + statement);
		}
	}
	
	public static void rollback(Connection connection) {
		if (connection != null) {
			try {
				connection.rollback();
			} 
			catch (SQLException e) {
				throw new RuntimeException("Errore durante il rollback", e);
			}
		}
	}
	
	public static void close(Connection connection) {
		if (connection != null) {
			try {
				if (!connection.isClosed()) connection.close();
			}
			catch (SQLException e) {
				throw new RuntimeException("Errore durante la chiusura della connection", e);
			}
		}
	}
	
	public static void close(Statement statement) {
		if (statement != null) {
			try {
				if (!statement.isClosed()) statement.close();
			}
			catch (SQLException e) {
				throw new RuntimeException("Errore durante la chiusura dello statement", e);
			}
		}
	}
	
	public static void close(ResultSet resultSet) {
		if (resultSet != null) {
			try {
				if (!resultSet.isClosed()) resultSet.close();
			}
			catch (SQLException e) {
				throw new RuntimeException("Errore durante la chiusura del result set", e);
			}
		}
	}
	
	public static void close(Connection connection, Statement statement) {
		close(connection);
		close(statement);
	}
	
	public static void close(Connection connection, Statement statement, ResultSet resultSet) {
		close(connection, statement);
		close(resultSet);
	}
}
