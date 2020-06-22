package nl.topicus.onderwijs.eck2.v1.core;

import nl.topicus.onderwijs.eck2.shared.webservice.Eck2FaultType;
import nl.topicus.onderwijs.generated.uwlr.v1_0.Autorisatie;
import nl.topicus.onderwijs.generated.uwlr.v1_0.Leerlinggegevensverzoek;

/**
 * Interface voor classes die autorisatiegegevens kunnen valideren van Eck2 requests.
 *
 * @author tom.kirchjunger
 */
public interface Eck2AutorisatieValidator
{
	/**
	 * Controleert of een autorisatiesleutel geldig is én toegang geeft tot een gegeven
	 * school. Een autorisatiesleutel is geldig als deze bekend is in combinatie met de
	 * klantcode en klantnaam. Het niet slagen van deze validatie moet resulteren in een
	 * fout van het type {@link Eck2FaultType#CLT_AUTORISATIE_ONGELDIG}.
	 *
	 * @param autorisatie
	 *            autorisatiegegevens uit de header van het bericht.
	 * @param request
	 *            request met daarin de gegevens van de school.
	 */
	boolean isValidAutorisatiesleutel(final Autorisatie autorisatie,
			final Leerlinggegevensverzoek request);

	/**
	 * Controleert of de klantcode en klantnaam bekend zijn en bij elkaar horen. Hierbij
	 * wordt niet gekeken of de autorisatiesleutel correct is, omdat hiervoor
	 * verschillende foutmeldingen moeten kunnen worden gegeven. Gebruik daarom in een
	 * volgende validatiestap
	 * {@link Eck2AutorisatieValidator#isValidAutorisatiesleutel(Autorisatie, Leerlinggegevensverzoek)}
	 *
	 * Het niet slagen van deze validatie moet resulteren in een fout van het type
	 * {@link Eck2FaultType#CLT_ONGELDIGE_KLANTIDENTIFICATIE}.
	 *
	 * @param autorisatie
	 *            autorisatiegegevens uit de header van het bericht.
	 */
	boolean isValidKlantidentificatie(final Autorisatie autorisatie);

	/**
	 * Controleert of een request geldige schoolgegevens bevat. Het niet slagen van deze
	 * validatie moet resulteren in een fout van het type
	 * {@link Eck2FaultType#CLT_ONGELDIGE_KLANTIDENTIFICATIE}.
	 *
	 * @param request
	 *            request met daarin de gegevens van de school
	 */
	boolean isValidSchool(final Leerlinggegevensverzoek request);
}
