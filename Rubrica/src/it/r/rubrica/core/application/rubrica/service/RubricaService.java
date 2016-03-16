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
import java.util.UUID;

import javax.sql.DataSource;

public class RubricaService {
	
	private DataSource dataSource;

	public RubricaService(DataSource dataSource) {
		if (dataSource == null) {
			throw new RuntimeException("Il datasource non può essere null");
		}
		this.dataSource = dataSource;
	}
	
	public List<RiepilogoContatto> contatti(Integer userId) {
		Transaction.begin(this.dataSource);
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
	public List<RiepilogoContatto> ricerca(RicercaContattiQuery query) {
		Transaction.begin(this.dataSource);
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
	
	public String inserisciContatto(AggiungiContattoCommand cmd) {
		Transaction.begin(this.dataSource);
		
		String contattoId = UUID.randomUUID().toString();
		try {
			Contatto contatto = new Contatto();
			contatto.setId(contattoId);
			contatto.setNome(cmd.getNome());
			contatto.setCognome(cmd.getCognome());
			contatto.setTelefono(cmd.getTelefono());
			contatto.setEmail(cmd.getEmail());
			contatto.setUtenteId(cmd.getUtenteId());
			
			ContattoDao.insert(contatto);
		}
		catch (Exception e) {
			Transaction.rollback();
			throw new RuntimeException("Errore durante l'inserimento del contatto", e);
		}
		Transaction.commit();
		
		return contattoId;
	}
	
	public DettaglioContatto dettaglio(String contattoId) {
		Transaction.begin(this.dataSource);
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
