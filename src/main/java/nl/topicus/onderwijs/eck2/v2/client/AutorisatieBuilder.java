package nl.topicus.onderwijs.eck2.v2.client;

import nl.topicus.onderwijs.generated.uwlr.v2_0.Autorisatie;

public class AutorisatieBuilder
{

	private String autorisatiesleutel;

	private String klantcode;

	private String klantnaam;

	public AutorisatieBuilder()
	{
	}

	public AutorisatieBuilder autorisatieSleutel(String autorisatiesleutel)
	{
		this.autorisatiesleutel = autorisatiesleutel;
		return this;
	}

	public AutorisatieBuilder klantcode(String klantcode)
	{
		this.klantcode = klantcode;
		return this;
	}

	public AutorisatieBuilder klantnaam(String klantnaam)
	{
		this.klantnaam = klantnaam;
		return this;
	}

	public Autorisatie build()
	{
		Autorisatie autorisatie = new Autorisatie();
		autorisatie.setAutorisatiesleutel(autorisatiesleutel);
		autorisatie.setKlantcode(klantcode);
		autorisatie.setKlantnaam(klantnaam);
		return autorisatie;
	}

}
