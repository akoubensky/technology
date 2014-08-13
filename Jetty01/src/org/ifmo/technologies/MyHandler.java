package org.ifmo.technologies;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;

public class MyHandler {

	public static void main(String[] args) {
        Server server = new Server(8080);
        server.setHandler(new AbstractHandler() {
        	@Override
            public void handle(
                    String target,
                    Request baseRequest,
                    HttpServletRequest request,
                    HttpServletResponse response)
                        throws IOException, ServletException {
                System.out.println("Handling request: " + target);
                baseRequest.setHandled(true);
        	}
        });
        try {
			server.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
