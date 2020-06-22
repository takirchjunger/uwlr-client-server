package nl.topicus.onderwijs.eck2.shared.webservice;

/**
 * Types foutberichten zoals gespecificeerd in de Eck2-afspraak.
 */
public enum Eck2FaultType
{

	/** Serverfouten: */
	SRV_INTERNE_FOUT("Server.InterneFout", "Er is een interne fout opgetreden"),
	SRV_TIJDELIJK_NIET_BESCHIKBAAR("Server.TijdelijkNietBeschikbaar",
			"Server is tijdelijk niet beschikbaar"),
	/** Clientfouten: */
	CLT_ONGELDIG_BERICHT("Client.OngeldigBericht", "Inhoud van het bericht is ongeldig"),
	CLT_ONGELDIGE_KLANTIDENTIFICATIE("Client.OngeldigeKlantIdentificatie",
			"Klantidentificatie onjuist"),
	CLT_AUTORISATIE_ONGELDIG("Client.AutorisatieOngeldig", "Autorisatie is ongeldig"),
	CLT_VOCABULAIRE_TERM_ONGELDIG("Client.VocabulaireTermOngeldig", "Vocabulairterm ongeldig"),
	CLT_XSD_VERSIE_ONGELDIG("Client.XsdVersieOngeldig", "De opgegeven XSD-versie is onjuist"),
	CLT_LEERLING_ONGELDIG("Client.LeerlingOngeldig", "Leerling ongeldig"),

	/** Niet van toepassing voor leerlinggegevens, maar alvast gedefinieerd: */
	CLT_TOETSNORMERING_ONGELDIG("Client.ToetsNormeringOngeldig", "Toetsnormering ongeldig"),
	CLT_SCORE_ONGELDIG("Client.ScoreOngeldig", "Score ongeldig"),
	CLT_ELD_RESULTAAT_ONGELDIG("Client.EldResultaatOngeldig", "ELD resultaat ongeldig");

	private String code;

	private String message;

	private Eck2FaultType(String code, String message)
	{
		this.code = code;
		this.message = message;
	}

	public String getCode()
	{
		return code;
	}

	public String getMessage()
	{
		return message;
	}

}
