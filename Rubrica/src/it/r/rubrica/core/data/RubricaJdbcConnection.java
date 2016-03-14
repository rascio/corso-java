package it.r.rubrica.core.data;

import it.r.rubrica.core.data.utils.ConnectionUtils;

import java.sql.Connection;

public class RubricaJdbcConnection {

	/*
	 * Parametri DB
	 */
	private static final String HOST = "localhost";
	private static final String DB   = "test";
	
	/*
	 * Parametri login
	 */
	private static final String USER 	= "root";
	private static final String PASSWORD = null;

	public static Connection openConnection() {
		return ConnectionUtils.getConnection("jdbc:mysql://" + HOST + "/" + DB, USER, PASSWORD, false);
	}
}
