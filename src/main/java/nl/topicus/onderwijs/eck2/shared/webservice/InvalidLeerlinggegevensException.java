package nl.topicus.onderwijs.eck2.shared.webservice;

import nl.topicus.onderwijs.generated.uwlr.v1_0.Leerlinggegevensverzoek;

/**
 * Exception die wordt gegooid als een ontvangen {@link Leerlinggegevensverzoek} ongeldig
 * is, omdat bijvoorbeeld verplichte velden missen of hun waarden niet voldoen aan het
 * verwachte format.
 */
public class InvalidLeerlinggegevensException extends Exception
{

	private static final long serialVersionUID = 1L;

	/**
	 * @param message
	 *            omschrijving van de opgetreden fout
	 */
	public InvalidLeerlinggegevensException(String message)
	{
		super(message);
	}

	/**
	 * @param message
	 *            omschrijving van de opgetreden fout
	 * @param throwable
	 *            gewrapte exception die ten grondslag ligt aan deze
	 *            {@link InvalidLeerlinggegevensRequestException}
	 */
	public InvalidLeerlinggegevensException(String message, Throwable throwable)
	{
		super(message, throwable);
	}

}
