package it.r.rubrica.core.application.rubrica.service;

import it.r.rubrica.core.application.rubrica.dto.input.AggiungiContattoCommand;
import it.r.rubrica.core.application.rubrica.dto.input.RicercaContattiQuery;
import it.r.rubrica.core.application.rubrica.dto.output.DettaglioContatto;
import it.r.rubrica.core.application.rubrica.dto.output.RiepilogoContatto;
import it.r.rubrica.core.data.dao.ContattoDao;
import it.r.rubrica.core.data.model.Contatto;
import it.r.rubrica.core.data.utils.Transaction;
import it.r.rubrica.utils.RubricaBeanUtils;

import java.util.ArrayList;
import java.util.List;

public class RubricaService {

	public static List<RiepilogoContatto> contatti(Integer userId) {
		Transaction.begin();
		try {
			List<Contatto> contatti = ContattoDao.findAllByUserId(userId);
			List<RiepilogoContatto> riepilogo = new ArrayList<RiepilogoContatto>();
			
			for (Contatto c : contatti) {
				riepilogo.add(new RiepilogoContatto(c.getId(), c.getNome(), c.getCognome()));
			}
			
			return riepilogo;
		}
		finally {
			Transaction.close();
		}
	}
	public static List<RiepilogoContatto> ricerca(RicercaContattiQuery query) {
		Transaction.begin();
		try {
			List<Contatto> contatti = ContattoDao.findAllByUserIdAndTesto(query.getUserId(), query.getTesto());
			List<RiepilogoContatto> riepilogo = new ArrayList<RiepilogoContatto>();
			
			for (Contatto c : contatti) {
				riepilogo.add(new RiepilogoContatto(c.getId(), c.getNome(), c.getCognome()));
			}
			
			return riepilogo;
		}
		finally {
			Transaction.close();
		}
	}
	
	public static Integer inserisciContatto(AggiungiContattoCommand cmd) {
		Transaction.begin();
		
		Integer contattoId;
		try {
			Contatto contatto = new Contatto();
			contatto.setNome(cmd.getNome());
			contatto.setCognome(cmd.getCognome());
			contatto.setTelefono(cmd.getTelefono());
			contatto.setEmail(cmd.getEmail());
			contatto.setUtenteId(cmd.getUtenteId());
			
			contattoId = ContattoDao.insert(contatto);
		}
		catch (Exception e) {
			Transaction.rollback();
			throw new RuntimeException("Errore durante l'inserimento del contatto", e);
		}
		Transaction.commit();
		
		return contattoId;
	}
	
	public static DettaglioContatto dettaglio(Integer contattoId) {
		Transaction.begin();
		try {
			Contatto contatto = ContattoDao.findById(contattoId);
			if (contatto == null) {
				throw new RuntimeException("Contatto non trovato: " + contattoId);
			}
			DettaglioContatto dettaglioContatto = new DettaglioContatto();
			
			RubricaBeanUtils.copy(contatto, dettaglioContatto);
			
			return dettaglioContatto;
		}
		finally {
			Transaction.close();
		}
	}
}
