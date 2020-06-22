package nl.topicus.onderwijs.eck2.shared.webservice;

public final class Eck2WebserviceGenericConstants
{
	private Eck2WebserviceGenericConstants()
	{
	}

	/**
	 * Elementen in een leerlinggegevens request
	 */
	public enum LeerlinggegevensRequestElements
	{
		SCHOOLJAAR("schooljaar"),

		BRIN_CODE("brincode"),

		DEPENDANCE_CODE("dependancecode"),

		XSD_VERSIE("xsdVersie"),

		LAATST_ONTVANGEN_GEGEVENS("laatstOntvangenGegevens");

		private final String elementName;

		private LeerlinggegevensRequestElements(String elementName)
		{
			this.elementName = elementName;
		}

		public String getElementName()
		{
			return elementName;
		}

	}

	public enum AutorisatieHeaderElements
	{
		AUTORISATIESLEUTEL("autorisatiesleutel"),
		KLANTNAAM("klantnaam"),
		KLANTCODE("klantcode");

		private final String elementName;

		private AutorisatieHeaderElements(String elementName)
		{
			this.elementName = elementName;
		}

		public String getElementName()
		{
			return elementName;
		}
	}

}
