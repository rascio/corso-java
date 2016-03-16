package it.r.rubrica.webapp.servlet.contatti;

import it.r.rubrica.core.application.rubrica.dto.input.AggiungiContattoCommand;
import it.r.rubrica.core.application.rubrica.service.RubricaService;
import it.r.rubrica.core.application.utenze.dto.output.UserInfo;
import it.r.rubrica.webapp.servlet.utils.RubricaServiceRegistry;
import it.r.rubrica.webapp.servlet.utils.ServletUtils;
import it.r.rubrica.webapp.servlet.utils.login.LoginUtils;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns="/contatti/crea")
public class CreaContattoServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String nome = req.getParameter("nome");
		String cognome = req.getParameter("cognome");
		String telefono = req.getParameter("telefono");
		String email = req.getParameter("email");
		
		UserInfo currentUser = LoginUtils.userLogged(req);
		
		AggiungiContattoCommand cmd = new AggiungiContattoCommand(nome, cognome, telefono, email, currentUser.getUserId());
		
		RubricaService rubricaService = RubricaServiceRegistry.rubricaService(req);
		
		rubricaService.inserisciContatto(cmd);
		
		ServletUtils.redirect("/home", req, resp);
	}
}
