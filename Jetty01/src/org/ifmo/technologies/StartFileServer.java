package org.ifmo.technologies;

import org.eclipse.jetty.server.Server;

/**
 * Программа, запускающая сервер с обработчиком запросов типа FileHandler
 */
public class StartFileServer {

	public static void main(String[] args) {
        Server server = new Server(8080);
        server.setHandler(new FileHandler());
        try {
			server.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
