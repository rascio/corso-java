package it.r.corsi.java.jdbc.eserciziothreadlocal.service;

import it.r.corsi.java.jdbc.esercizio.model.Ruolo;
import it.r.corsi.java.jdbc.esercizio.model.Utente;
import it.r.corsi.java.jdbc.eserciziothreadlocal.dao.UtenteDao;
import it.r.corsi.java.jdbc.eserciziothreadlocal.utils.Transaction;

import java.util.List;

public class UtenzeService {

	public static Integer registra(String username, String password) {
		assertNotNull(username, "username");
		assertNotNull(password, "password");
		
		Utente utente = new Utente();
		utente.setUsername(username);
		utente.setPassword(password);
		utente.setRuolo(Ruolo.USER);
		utente.setAbilitato(true);
		
		Transaction.begin();
		
		Integer id;
		try {
			id = UtenteDao.insert(utente);
		}
		catch (Exception e) {
			Transaction.rollback();
			throw new RuntimeException("Errore durante la registrazione per " + username, e);
		}
		Transaction.commit();
		return id;
	}
	
	public static void disabilita(List<Integer> utenteId) {
		assertNotNull(utenteId, "utenteId");
		
		Transaction.begin();
		try {
			List<Utente> utenti = UtenteDao.findAllByIds(utenteId);
			
			for (Utente utente : utenti) {
				utente.setAbilitato(false);
				
				UtenteDao.update(utente);
			}
		}
		catch (Exception e) {
			Transaction.rollback();
			throw new RuntimeException("Errore durante il disabilita degli utenti " + utenteId, e);
		}
		Transaction.commit();
	}
	
	public static Boolean login(String username, String password) {
		assertNotNull(username, "username");
		assertNotNull(password, "password");
		
		Transaction.begin();
		try {
			Utente utente = UtenteDao.findByUsername(username);
			
			return utente.getAbilitato() && password.equals(utente.getPassword());
		}
		finally {
			Transaction.close();
		}
	}
	
	private static void assertNotNull(Object o, String nome) {
		if (o == null) {
			throw new RuntimeException(nome + " è null");
		}
	}
}
