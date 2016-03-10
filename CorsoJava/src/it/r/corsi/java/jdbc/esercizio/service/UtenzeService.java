package it.r.corsi.java.jdbc.esercizio.service;

import java.util.List;

import it.r.corsi.java.jdbc.esercizio.dao.UtenteDao;
import it.r.corsi.java.jdbc.esercizio.model.Ruolo;
import it.r.corsi.java.jdbc.esercizio.model.Utente;

public class UtenzeService {

	public static Integer registra(String username, String password) {
		assertNotNull(username, "username");
		assertNotNull(password, "password");
		
		Utente utente = new Utente();
		utente.setUsername(username);
		utente.setPassword(password);
		utente.setRuolo(Ruolo.USER);
		utente.setAbilitato(true);
		
		return UtenteDao.insert(utente);
	}
	
	public static void disabilita(List<Integer> utenteId) {
		assertNotNull(utenteId, "utenteId");
		
		List<Utente> utenti = UtenteDao.findAllByIds(utenteId);
		
		for (Utente utente : utenti) {
			utente.setAbilitato(false);
			
			UtenteDao.update(utente);
		}
	}
	
	public static Boolean login(String username, String password) {
		assertNotNull(username, "username");
		assertNotNull(password, "password");
		
		Utente utente = UtenteDao.findByUsername(username);
		
		return password.equals(utente.getPassword());
	}
	
	private static void assertNotNull(Object o, String nome) {
		if (o == null) {
			throw new RuntimeException(nome + " è null");
		}
	}
}
