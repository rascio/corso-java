package it.r.rubrica.webapp.servlet.contatti;

import it.r.rubrica.core.application.rubrica.dto.output.DettaglioContatto;
import it.r.rubrica.core.application.rubrica.service.RubricaService;
import it.r.rubrica.webapp.servlet.utils.ServletUtils;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns="/contatti/dettaglio")
public class DettaglioContattoServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		Integer contattoId = Integer.parseInt(req.getParameter("contattoId"));
		
		DettaglioContatto dettaglio = RubricaService.dettaglio(contattoId);
		
		req.setAttribute("dettaglio", dettaglio);
		
		ServletUtils.render("/contatti/dettaglio", req, resp);
	}

}
