package it.r.rubrica.webapp.servlet.utils;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class ServletUtils{
	
	public static final String OBJECT_MAPPER_NAME = "rubrica.mapper";
	private static final ObjectMapper MAPPER = new ObjectMapper();

	public static void render(String name, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/WEB-INF/pages/" + name + ".jsp");
		
		dispatcher.forward(req, resp);
	}
	
	public static void json(Object value, HttpServletRequest req, HttpServletResponse resp) throws IOException {
		ObjectMapper mapper = getObjectMapper(req);
		
		resp.setContentType("application/json");
		mapper.writeValue(resp.getWriter(), value);
	}
	
	private static ObjectMapper getObjectMapper(HttpServletRequest req) {
		ObjectMapper mapper = (ObjectMapper) req.getServletContext().getAttribute(OBJECT_MAPPER_NAME);
		if (mapper == null) {
			throw new RuntimeException("ObjectMapper non configurato");
		}
		return mapper;
	}

	public static void redirect(String path, HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.sendRedirect(req.getContextPath() + path);
	}
}
