package it.r.rubrica.webapp.listener;

import it.r.rubrica.webapp.servlet.utils.ServletUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.codehaus.jackson.map.ObjectMapper;

@WebListener
public class RubricaStartupListener implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent evt) {
		
	}

	@Override
	public void contextInitialized(ServletContextEvent evt) {
		/*
		 * Inizializza il Driver MySql
		 */
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
		
		ObjectMapper mapper = new ObjectMapper();
		
		evt.getServletContext().setAttribute(ServletUtils.OBJECT_MAPPER_NAME, mapper);
		
	}

}
