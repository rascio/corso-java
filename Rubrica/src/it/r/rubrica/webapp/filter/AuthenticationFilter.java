package it.r.rubrica.webapp.filter;

import it.r.rubrica.core.application.utenze.dto.output.UserInfo;
import it.r.rubrica.webapp.servlet.utils.login.LoginUtils;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(urlPatterns={
	"/home",
	"/contatti/crea",
	"/contatti/dettaglio"
})
public class AuthenticationFilter implements Filter{

	public static final String USER_INFO = "currentUser";

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		UserInfo user = LoginUtils.userLogged(req);
		
		if (user == null) {
			LoginUtils.renderLoginPage(req, res);
		}
		else {
			req.setAttribute(USER_INFO, user);
			
			chain.doFilter(req, res);
		}
	}

	@Override
	public void destroy() {
		
	}
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}

}
