package it.r.rubrica.webapp.servlet.index;

import it.r.rubrica.core.application.utenze.dto.output.LoginResult;
import it.r.rubrica.webapp.servlet.utils.ServletUtils;
import it.r.rubrica.webapp.servlet.utils.login.LoginUtils;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns="/login")
public class LoginServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		LoginResult login = LoginUtils.login(req, username, password);
		
		if (login.getSuccess()) {
			ServletUtils.redirect("/home", req, resp);
		}
		else {
			LoginUtils.renderLoginPage(req, resp);
		}
	}
}
