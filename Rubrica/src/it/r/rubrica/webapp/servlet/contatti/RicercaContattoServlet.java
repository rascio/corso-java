package it.r.rubrica.webapp.servlet.contatti;

import it.r.rubrica.core.application.rubrica.dto.output.RiepilogoContatto;
import it.r.rubrica.core.application.rubrica.service.RubricaService;
import it.r.rubrica.core.application.utenze.dto.output.UserInfo;
import it.r.rubrica.webapp.servlet.utils.ServletUtils;
import it.r.rubrica.webapp.servlet.utils.login.LoginUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns="/contatti/ricerca")
public class RicercaContattoServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String testo = req.getParameter("testo");
		UserInfo currentUser = LoginUtils.userLogged(req);
		
		List<RiepilogoContatto> contatti = RubricaService.ricerca(currentUser.getUserId(), testo);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("list", contatti);
		
		ServletUtils.json(result, req, resp);
	}
}
