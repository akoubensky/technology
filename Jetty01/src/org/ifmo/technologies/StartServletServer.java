package org.ifmo.technologies;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;

/**
 * Программа, запускающая сервер в качестве контейнера для сервлета TestServlet
 */
public class StartServletServer {

	public static void main(String[] args) {
        Server server = new Server(8080);
        ServletHandler handler = new ServletHandler();
        handler.addServletWithMapping(TestServlet.class, "/*");
        server.setHandler(handler);
        try {
			server.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
