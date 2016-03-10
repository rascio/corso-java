package it.r.corsi.java.jdbc.esercizio;

import java.util.Arrays;
import java.util.List;

import it.r.corsi.java.jdbc.esercizio.dao.UtenteDao;
import it.r.corsi.java.jdbc.esercizio.model.Utente;
import it.r.corsi.java.jdbc.esercizio.service.UtenzeService;

public class Main {

	public static void main(String[] args) {
		
		Integer user1Id = UtenzeService.registra("user1", "test");
		Integer user2Id = UtenzeService.registra("user2", "test");
		
		UtenzeService.registra("user3", "test");
		
		List<Integer> utentiDaDisabilitare = Arrays.asList(user1Id, user2Id);
		
		UtenzeService.disabilita(utentiDaDisabilitare);
		
		List<Utente> utenti = UtenteDao.findAll();
		
		for (Utente u : utenti) {
			System.out.println(u);
		}
	}
}
