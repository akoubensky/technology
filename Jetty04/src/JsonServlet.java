import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class JsonServlet extends HttpServlet {
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
	    		response.setContentType("application/json");
	    		response.setCharacterEncoding("UTF8");
	    		try {
	    			Gson gson = new Gson();
	    			gson.toJson(country, response.getWriter());
	    			if ("/BL".equals(path))
						try {
							Thread.sleep(5000);
						} catch (InterruptedException e) {
						}
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
