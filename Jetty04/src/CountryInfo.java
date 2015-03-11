
public class CountryInfo {
	private final String name;
	private final String fullName;
	private final String englishName;
	private final String alpha2;
	private final String alpha3;
	private final String iso;
	private final String continent;
	private final String continentPart;
	
	public CountryInfo(String name, String fullName, String englishName, String alpha2,
			           String alpha3, String iso, String continent, String continentPart) {
		this.name = name;
		this.fullName = fullName;
		this.englishName = englishName;
		this.alpha2 = alpha2;
		this.alpha3 = alpha3;
		this.iso = iso;
		this.continent = continent;
		this.continentPart = continentPart;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the fullName
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * @return the alpha2
	 */
	public String getAlpha2() {
		return alpha2;
	}

	/**
	 * @return the alpha3
	 */
	public String getAlpha3() {
		return alpha3;
	}

	/**
	 * @return the iso
	 */
	public String getIso() {
		return iso;
	}
	
	/**
	 * @return the continent
	 */
	public String getContinent() {
		return continent;
	}
	
	/**
	 * @return the continentPart
	 */
	public String getContinentPart() {
		return continentPart;
	}

	/**
	 * @return the englishName
	 */
	public String getEnglishName() {
		return englishName;
	}
}
