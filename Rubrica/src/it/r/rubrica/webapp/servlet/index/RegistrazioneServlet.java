package it.r.rubrica.webapp.servlet.index;

import it.r.rubrica.core.application.utenze.dto.input.RegistraUtenteCommand;
import it.r.rubrica.core.application.utenze.service.UtenzeService;
import it.r.rubrica.webapp.servlet.utils.RubricaServiceRegistry;
import it.r.rubrica.webapp.servlet.utils.ServletUtils;
import it.r.rubrica.webapp.servlet.utils.login.LoginUtils;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns="/registrazione")
public class RegistrazioneServlet extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		String nome = req.getParameter("nome");
		String cognome = req.getParameter("cognome");
		String email = req.getParameter("email");
		
		RegistraUtenteCommand cmd = new RegistraUtenteCommand(username, password, nome, cognome, email);
		
		UtenzeService utenzeService = RubricaServiceRegistry.utenzeService(req);
		
		utenzeService.registra(cmd);
		
		LoginUtils.login(req, username, password);
		
		ServletUtils.redirect("/home", req, resp);
	}

}
