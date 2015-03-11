import java.io.IOException;
import java.io.StringWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;


public class XmlServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static DocumentBuilder docBuilder;
    
    static {
    	try {
			docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		} catch (ParserConfigurationException e) {
		}
    }
    
    private static Element createSimpleElement(Document doc, String tag, String text) {
    	Element element = doc.createElement(tag);
    	element.appendChild(doc.createTextNode(text));
    	return element;
    }
    
    private static Document createCountryDoc(CountryInfo countryInfo) throws ParserConfigurationException {
		Document countryDoc = docBuilder.newDocument();
		Element countryElement = countryDoc.createElement("country");
		countryElement.appendChild(createSimpleElement(countryDoc, "name", countryInfo.getName()));
		countryElement.appendChild(createSimpleElement(countryDoc, "iso", countryInfo.getIso()));
		countryElement.appendChild(createSimpleElement(countryDoc, "english", countryInfo.getEnglishName()));
		countryElement.appendChild(createSimpleElement(countryDoc, "location", countryInfo.getContinent()));
		countryDoc.appendChild(countryElement);
    	return countryDoc;
    }

    private static String toString(Document newDoc) throws TransformerFactoryConfigurationError, TransformerException {
    	DOMSource domSource = new DOMSource(newDoc);
    	Transformer transformer = TransformerFactory.newInstance().newTransformer();
    	StringWriter sw = new StringWriter();
    	StreamResult sr = new StreamResult(sw);
    	transformer.transform(domSource, sr);
    	return sw.toString();  
    }

    @Override
    protected void doGet(
    		HttpServletRequest request,
    		HttpServletResponse response) {
    	
    	String path = request.getPathInfo();
    	response.setStatus(HttpServletResponse.SC_OK);
    	if (path.startsWith("/") && path.length() == 3) {
    		CountryInfo country = DataLoader.instance.getCountry(path.substring(1));
    		if (country != null) {
	    		response.setContentType("text/xml");
	    		response.setCharacterEncoding("UTF8");
	    		try {
					response.getWriter().print(toString(createCountryDoc(country)));
				} catch (IOException | TransformerFactoryConfigurationError | TransformerException | ParserConfigurationException e) {
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
