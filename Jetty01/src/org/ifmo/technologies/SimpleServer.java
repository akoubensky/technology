package org.ifmo.technologies;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.DefaultHandler;

public class SimpleServer {
	public static void main(String[] args) {
		Server server = new Server(8080);
		server.setHandler(new DefaultHandler());
		try {
			server.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
