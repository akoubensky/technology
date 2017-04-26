import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletHandler;


public class StartServer {

	public static void main(String[] args) {
        Server server = new Server(8081);
        HandlerList handlers = new HandlerList();
        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setResourceBase("./Jetty04/ajax");
        ServletHandler countriesHandler = new ServletHandler();
        countriesHandler.addServletWithMapping(NameServlet.class, "/countries/name/*");
        countriesHandler.addServletWithMapping(TxtServlet.class, "/countries/txt/*");
        countriesHandler.addServletWithMapping(XmlServlet.class, "/countries/xml/*");
        countriesHandler.addServletWithMapping(JsonServlet.class, "/countries/json/*");
        handlers.setHandlers(new Handler[] { resourceHandler, countriesHandler, new DefaultHandler() });
        server.setHandler(handlers);
        try {
			server.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
