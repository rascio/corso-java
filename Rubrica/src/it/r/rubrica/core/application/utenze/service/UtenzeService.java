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
import java.util.UUID;

import javax.sql.DataSource;

public class UtenzeService {

	private DataSource dataSource;
	
	public UtenzeService(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public String registra(RegistraUtenteCommand cmd) {
		assertNotNull(cmd.getUsername(), "username");
		assertNotNull(cmd.getPassword(), "password");

		Transaction.begin(this.dataSource);
		
		String id = UUID.randomUUID().toString();
		try {
			
			/*
			 * Ogni volta che un utente si registra viene creato il contatto dell'utente
			 * trà i contatti dell'admin di sistema
			 */
			Contatto contatto = new Contatto();
			contatto.setId(UUID.randomUUID().toString());
			contatto.setNome(cmd.getNome());
			contatto.setCognome(cmd.getCognome());
			contatto.setEmail(cmd.getEmail());
			contatto.setUtenteId(RubricaDataConstants.ADMIN_ID);
			
			ContattoDao.insert(contatto);
			
			Utente utente = new Utente();
			utente.setId(id);
			utente.setUsername(cmd.getUsername());
			utente.setPassword(cmd.getPassword());
			utente.setRuolo(Ruolo.USER);
			utente.setAbilitato(true);
			utente.setContattoId(contatto.getId());
			
			UtenteDao.insert(utente);
		}
		catch (Exception e) {
			Transaction.rollback();
			throw new RuntimeException("Errore durante la registrazione per " + cmd.getUsername(), e);
		}
		Transaction.commit();
		return id;
	}
	
	public void disabilita(List<Integer> utenteId) {
		assertNotNull(utenteId, "utenteId");
		
		Transaction.begin(this.dataSource);
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
	
	public LoginResult login(String username, String password) {
		assertNotNull(username, "username");
		assertNotNull(password, "password");
		
		Transaction.begin(this.dataSource);
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
	
	public UserInfo userInfo(Integer utenteId) {
		assertNotNull(utenteId, "utenteId");
		
		Transaction.begin(this.dataSource);
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
