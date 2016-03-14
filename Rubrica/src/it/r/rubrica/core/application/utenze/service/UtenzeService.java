package it.r.rubrica.core.application.utenze.service;

import it.r.rubrica.core.application.utenze.dto.input.RegistraUtenteCommand;
import it.r.rubrica.core.application.utenze.dto.output.LoginResult;
import it.r.rubrica.core.application.utenze.dto.output.UserInfo;
import it.r.rubrica.core.data.dao.ContattoDao;
import it.r.rubrica.core.data.dao.UtenteDao;
import it.r.rubrica.core.data.model.Contatto;
import it.r.rubrica.core.data.model.Ruolo;
import it.r.rubrica.core.data.model.Utente;
import it.r.rubrica.core.data.utils.RubricaDataConstants;
import it.r.rubrica.core.data.utils.Transaction;

import java.util.List;

public class UtenzeService {

	public static Integer registra(RegistraUtenteCommand cmd) {
		assertNotNull(cmd.getUsername(), "username");
		assertNotNull(cmd.getPassword(), "password");

		Transaction.begin();
		
		Integer id;
		try {
			
			/*
			 * Ogni volta che un utente si registra viene creato il contatto dell'utente
			 * trà i contatti dell'admin di sistema
			 */
			Contatto contatto = new Contatto();
			contatto.setNome(cmd.getNome());
			contatto.setCognome(cmd.getCognome());
			contatto.setEmail(cmd.getEmail());
			contatto.setUtenteId(RubricaDataConstants.ADMIN_ID);
			
			Integer contattoId = ContattoDao.insert(contatto);
			
			Utente utente = new Utente();
			utente.setUsername(cmd.getUsername());
			utente.setPassword(cmd.getPassword());
			utente.setRuolo(Ruolo.USER);
			utente.setAbilitato(true);
			utente.setContattoId(contattoId);
			
			id = UtenteDao.insert(utente);
		}
		catch (Exception e) {
			Transaction.rollback();
			throw new RuntimeException("Errore durante la registrazione per " + cmd.getUsername(), e);
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
	
	public static LoginResult login(String username, String password) {
		assertNotNull(username, "username");
		assertNotNull(password, "password");
		
		Transaction.begin();
		try {
			Utente utente = UtenteDao.findByUsername(username);
			
			if (utente != null && utente.getAbilitato() && password.equals(utente.getPassword())) {
				Contatto contatto = ContattoDao.findById(utente.getContattoId());
				
				UserInfo userInfo = new UserInfo();
				userInfo.setUserId(utente.getId());
				userInfo.setUsername(utente.getUsername());
				userInfo.setNome(contatto.getNome());
				userInfo.setCognome(contatto.getCognome());
				
				return new LoginResult(userInfo);
			}
			else {
				return new LoginResult();
			}
		}
		finally {
			Transaction.close();
		}
	}
	
	public static UserInfo userInfo(Integer utenteId) {
		assertNotNull(utenteId, "utenteId");
		
		Transaction.begin();
		try {
			Utente utente     = UtenteDao.findById(utenteId);
			Contatto contatto = ContattoDao.findById(utente.getContattoId());
			
			UserInfo userInfo = new UserInfo();
			userInfo.setUsername(utente.getUsername());
			userInfo.setNome(contatto.getNome());
			userInfo.setCognome(contatto.getCognome());
			
			return userInfo;
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
