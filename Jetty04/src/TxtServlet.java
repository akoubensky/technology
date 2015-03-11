import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class TxtServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(
    		HttpServletRequest request,
    		HttpServletResponse response) {
    	
    	String path = request.getPathInfo();
    	response.setStatus(HttpServletResponse.SC_OK);
    	if (path.startsWith("/") && path.length() == 3) {
    		CountryInfo country = DataLoader.instance.getCountry(path.substring(1));
    		if (country != null) {
	    		response.setContentType("text/plain");
	    		response.setCharacterEncoding("UTF8");
	    		try {
					response.getWriter().format("Name: %s\nIso code: %s\nEnglish name: %s\nLocation: %s", 
							country.getName(), country.getIso(), country.getEnglishName(), country.getContinent());
				} catch (IOException e) {
					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				}
    		} else {
    			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    		}
		} else {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    	}
    }
}
