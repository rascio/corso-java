package it.r.rubrica.webapp.servlet.utils;

import javax.servlet.http.HttpServletRequest;

import it.r.rubrica.core.application.rubrica.service.RubricaService;
import it.r.rubrica.core.application.utenze.service.UtenzeService;

public class RubricaServiceRegistry {

	public static RubricaService rubricaService(HttpServletRequest req) {
		return (RubricaService) req.getServletContext().getAttribute("rubricaService");
	}
	
	public static UtenzeService utenzeService(HttpServletRequest req) {
		return (UtenzeService) req.getServletContext().getAttribute("utenzeService");
	}
}
