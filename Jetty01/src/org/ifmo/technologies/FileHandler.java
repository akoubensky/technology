package org.ifmo.technologies;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

public class FileHandler extends AbstractHandler {

	@Override
	public void handle(
			String target,
			Request baseRequest,
			HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		System.out.println("Handling request: " + target);
		baseRequest.setHandled(true);
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
		switch(request.getMethod()) {
			case "GET":
				String fileName = "." + target;
				try {
					BufferedReader reader = new BufferedReader(new FileReader(new File(fileName.replace('/', '\\'))));
					String line;
					while ((line = reader.readLine()) != null) {
						response.getWriter().println(line);
					}
					reader.close();
				} catch (IOException e) {
			        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			        response.getWriter().println("<h2>404 - PAGE NOT FOUND</h2>");
			        response.getWriter().format(
			        		"<p>Sorry, the requested page <code>%s</code> is not found</p>\n", target);
				}
				break;
			default:
		        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		        response.getWriter().println("Bad request");
		}
	}

}
