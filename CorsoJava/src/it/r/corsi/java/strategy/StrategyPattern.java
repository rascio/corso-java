package it.r.corsi.java.strategy;

import it.r.corsi.java.jdbc.ConnectionUtils;
import it.r.corsi.java.jdbc.esercizio.utils.EsercizioJdbcUtils;
import it.r.corsi.java.lezione4.Persona;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StrategyPattern {

	public static void main(String[] args) {
		List<Persona> persone = Arrays.asList(
			new Persona("rascioni", null),
			new Persona("a", null),
			new Persona("b", null),
			new Persona("c", null)
		);
		
		List<String> cognomi = transformToCognomi(persone);
		
		System.out.println(cognomi);
		
		Strategy<Persona, String> getCognome = new Strategy<Persona, String>() {
			
			@Override
			public String execute(Persona i) {
				return i.getCognome();
			}
		};
		
		Strategy<Persona, String> getVia = new Strategy<Persona, String>() {
			
			@Override
			public String execute(Persona i) {
				return i.getIndirizzo().getVia();
			}
		};
		
		List<String> cognomi2 = transform(persone, getCognome);
		List<String> vie = transform(persone, getVia);
		
		List<String> parole = Arrays.asList(
				"casa",
				"citta",
				"televisore",
				"telefono"
		);
		
		Strategy<String, Integer> length = new Strategy<String, Integer>() {
			
			@Override
			public Integer execute(String i) {
				return i.length();
			}
		};
		List<Integer> lengths = transform(parole, length);
		Integer l = length.execute("lkjsdaksjdlò");
		
		/*
			var parole = ["ciao", "parole", "computer"]
			
			var length = function(parola) {
				return parola.length;
			};
			
			var lunghezze = transform(parole, length);
		 
		 
		 
		 */
		
		executeInTransaction(new Strategy<Connection, Object>() {
			
			@Override
			public Object execute(Connection connection) {
				try {
					connection.prepareStatement("INSERT INTO persone blabla").execute();
					connection.prepareStatement("INSERT INTO persone blabla").execute();
					connection.prepareStatement("INSERT INTO persone blabla").execute();
					connection.prepareStatement("INSERT INTO persone blabla").execute();
					
					return null;
				}
				catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
		});
	}

	private static List<String> transformToCognomi(List<Persona> persone) {
		List<String> cognomi = new ArrayList<String>();
		
		for (Persona p : persone) {
			String cognome = p.getCognome();
			cognomi.add(cognome);
		}
		return cognomi;
	}
	
	private static List<String> transformToCitta(List<Persona> persone) {
		List<String> citta = new ArrayList<String>();
		
		for (Persona p : persone) {
			String cognome = p.getIndirizzo().getCitta();
			citta.add(cognome);
		}
		return citta;
	}
	
	private static <I, O> List<O> transform(List<I> persone, Strategy<I, O> strategy) {
		List<O> risultato = new ArrayList<O>();
		
		for (I p : persone) {
			O singoloRisultato = strategy.execute(p);
			risultato.add(singoloRisultato);
		}
		return risultato;
	}
	
	public static void executeInTransaction(Strategy<Connection, Object> s) {
		Connection connection = null;
		try {
			connection = EsercizioJdbcUtils.openConnection();
			
			s.execute(connection);
			
			connection.commit();
		}
		catch(Exception e) {
			ConnectionUtils.rollback(connection);
			throw new RuntimeException(e);
		}
		finally {
			ConnectionUtils.close(connection);
		}
		
	}
}
