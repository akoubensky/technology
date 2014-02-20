package org.ifmo.technologies;

import org.eclipse.jetty.server.Server;

public class StartFileServer {

	public static void main(String[] args) {
        Server server = new Server(8080);
        server.setHandler(new FileHandler());
        try {
			server.start();
			server.join();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
