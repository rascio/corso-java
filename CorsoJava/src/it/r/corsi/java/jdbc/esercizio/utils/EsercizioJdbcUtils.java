package it.r.corsi.java.jdbc.esercizio.utils;

import java.sql.Connection;

import it.r.corsi.java.jdbc.ConnectionUtils;

public class EsercizioJdbcUtils {
	
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
