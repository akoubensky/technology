package org.ifmo.technologies;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;

/**
 * Программа, запускающая сервер с обработчиком запросов типа ResourceHandler
 */
public class StartStaticServer {

	public static void main(String[] args) {
        Server server = new Server(8080);
        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setResourceBase(".");
        server.setHandler(resourceHandler);
        try {
			server.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
