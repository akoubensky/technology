package org.ifmo.technologies;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Сервлет, обрабатывающий только запросы типа GET и выдающий статические страницы,
 * читая файл, указанный в запросе.
 */
public class TestServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(
    		HttpServletRequest request,
    		HttpServletResponse response) {
    	
    	String path = request.getPathInfo();
    	response.setStatus(HttpServletResponse.SC_OK);
    	if (path.endsWith(".htm") || path.endsWith(".html")) {
    		response.setContentType("text/html; charset=UTF-8");
    	} else {
    		response.setContentType("text/plain");
    	}
    	
    	// Формируем относительное имя файла (относительно текущего каталога проекта)
		String fileName = "." + path;
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File(fileName.replace('/', '\\'))));
			String line;
			while ((line = reader.readLine()) != null) {
				writeString(response, line);
			}
			reader.close();
		} catch (IOException e) {
	        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
	        writeString(response, "<h2>404 - PAGE NOT FOUND</h2>");
			writeString(response, String.format(
					"<p>Sorry, the requested page <code>%s</code> is not found</p>\n",
					request.getPathTranslated()));
		}
    }
    
    // Запросы PUT, POST и DELETE не обрабатываются
    
    @Override
    protected void doPut(
    		HttpServletRequest request,
    		HttpServletResponse response) {
    	response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    	response.setContentType("text/plain");
    	writeString(response, "Not implemented");
    }
    
    @Override
    protected void doPost(
    		HttpServletRequest request,
    		HttpServletResponse response) {
    	response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    	response.setContentType("text/plain");
    	writeString(response, "Not implemented");
    }
    
    @Override
    protected void doDelete(
    		HttpServletRequest request,
    		HttpServletResponse response) {
    	response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    	response.setContentType("text/plain");
    	writeString(response, "Not implemented");
    }
    
    private void writeString(HttpServletResponse response, String message) {
    	try {
			response.getWriter().println(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}