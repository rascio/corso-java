package it.r.rubrica.webapp.servlet.utils.login;

import java.io.IOException;
import java.util.Enumeration;

import it.r.rubrica.core.application.utenze.dto.output.LoginResult;
import it.r.rubrica.core.application.utenze.dto.output.UserInfo;
import it.r.rubrica.core.application.utenze.service.UtenzeService;
import it.r.rubrica.webapp.servlet.utils.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginUtils {
	
	private static final String LOGIN = "login";

	/**
	 * Autentica una request effettuando il login con le username e password passate
	 * @param req
	 * @param username
	 * @param password
	 * @return
	 * @throws LoginException
	 */
	public static LoginResult login(HttpServletRequest req, String username, String password) {
		LoginResult login = UtenzeService.login(username, password);
		
		System.out.println("login: " + login);
		if (login.getSuccess()) {
			req.getSession().setAttribute(LOGIN, login.getUser());
		}
		
		return login;
	}

	
	/**
	 * Restituisce l'utente attualmente loggato o lancia un eccezione nel caso la request
	 * non sia autenticata
	 * @param req
	 * @return
	 * @throws LoginException
	 */
	public static UserInfo userLogged(HttpServletRequest req){
		System.out.println("dump userLogged");
		dumpSession(req);
		return (UserInfo) req.getSession().getAttribute(LOGIN);
	}
	
	/**
	 * Controlla se la request è autenticata
	 * @param req
	 * @return
	 */
	public static boolean isUserLogged(HttpServletRequest req) {
		return req.getSession().getAttribute(LOGIN) != null;
	}
	
	public static void renderLoginPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ServletUtils.render("login", req, resp);
	}
	

	private static void dumpSession(HttpServletRequest req) {
		Enumeration<String> e = req.getSession().getAttributeNames();
		while(e.hasMoreElements()){
			String k = e.nextElement();
			
			System.out.println(k + ": " + req.getSession().getAttribute(k));
		}
	}
}
