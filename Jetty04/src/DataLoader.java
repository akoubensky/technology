import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DataLoader {
	private final Map<String, CountryInfo> countries = new HashMap<>();
	public final static DataLoader instance = new DataLoader();
	
	private DataLoader() {
		loadData();
	}
	
	private void loadData() {
		Document doc;
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder(); 
			doc = db.parse(new File("Jetty04/countries.xml"));
		} catch (SAXException | IOException | ParserConfigurationException e) {
			e.printStackTrace();
			return;
		}

		NodeList countryList = doc.getDocumentElement().getElementsByTagName("country");
		int size = countryList.getLength();
		for (int i = 0; i < size; i++) {
			Node country = countryList.item(i);
			NodeList countryContent = country.getChildNodes();
			int parSize = countryContent.getLength();
			String name = null;
			String fullName = null;
			String englishName = null;
			String alpha2 = null;
			String alpha3 = null;
			String iso = null;
			String continent = null;
			String continentPart = null;
			for (int j = 0; j < parSize; j++) {
				Node parameter = countryContent.item(j);
				if (parameter.getNodeType() == Node.ELEMENT_NODE) {
					String textContent = parameter.getFirstChild().getTextContent();
					switch (parameter.getNodeName()) {
					case "name": name = textContent; break;
					case "fullname": fullName = textContent; break;
					case "english": englishName = textContent; break;
					case "alpha2": alpha2 = textContent; break;
					case "alpha3": alpha3 = textContent; break;
					case "iso": iso = textContent; break;
					case "location": continent = textContent; break;
					case "location-presize": continentPart = textContent; break;
					}
				}
			}
			countries.put(alpha2, new CountryInfo(name, fullName, englishName, alpha2, alpha3, iso, continent, continentPart));
		}
	}
	
	public CountryInfo getCountry(String alpha2) {
		return countries.get(alpha2);
	}
}
