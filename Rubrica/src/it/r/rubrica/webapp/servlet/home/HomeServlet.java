package it.r.rubrica.webapp.servlet.home;

import it.r.rubrica.webapp.servlet.utils.ServletUtils;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns="/home")
public class HomeServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		ServletUtils.render("home", req, resp);
	}
}
