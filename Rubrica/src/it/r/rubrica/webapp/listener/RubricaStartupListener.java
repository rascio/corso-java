package it.r.rubrica.webapp.listener;

import it.r.rubrica.core.application.rubrica.service.RubricaService;
import it.r.rubrica.core.application.utenze.service.UtenzeService;
import it.r.rubrica.webapp.servlet.utils.ServletUtils;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

import org.codehaus.jackson.map.ObjectMapper;

@WebListener
public class RubricaStartupListener implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent evt) {
		
	}

	@Override
	public void contextInitialized(ServletContextEvent evt) {
//		try {
			/*
			 * Inizializza il Driver MySql
			 */
//			Class.forName("com.mysql.jdbc.Driver");
			/*
			 * Inizializza il Driver Oracle
			 */
//			Class.forName("oracle.jdbc.OracleDriver");
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException(e);
//		}
		
		ObjectMapper mapper = new ObjectMapper();
		DataSource dataSource = getDataSource();
		
		evt.getServletContext().setAttribute(ServletUtils.OBJECT_MAPPER_NAME, mapper);
//		evt.getServletContext().setAttribute("dataSource", dataSource);
		
		RubricaService rubricaService = new RubricaService(dataSource);
		UtenzeService utenzeService = new UtenzeService(dataSource);
		evt.getServletContext().setAttribute("rubricaService", rubricaService);
		evt.getServletContext().setAttribute("utenzeService", utenzeService);
		
	}

	private static DataSource getDataSource() {
		try {
			Context initContext = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			DataSource ds = (DataSource)envContext.lookup("jdbc/RubricaDB");
			return ds;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
