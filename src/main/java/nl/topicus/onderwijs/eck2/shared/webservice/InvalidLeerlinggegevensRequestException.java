package nl.topicus.onderwijs.eck2.shared.webservice;

import nl.topicus.onderwijs.eck2.shared.webservice.Eck2WebserviceGenericConstants.LeerlinggegevensRequestElements;
import nl.topicus.onderwijs.generated.uwlr.v1_0.Leerlinggegevens;

/**
 * Exception die wordt gegooid als een opgebouwde Leerlinggegevens structuur ongeldig is.
 * Dit kan bijvoorbeeld het geval zijn als één of meer verplichte delen uit deze structuur
 * leeg zijn.
 */
public class InvalidLeerlinggegevensRequestException extends Exception
{

	private static final long serialVersionUID = 1L;

	/**
	 * Element of veld waarop de {@link Leerlinggegevens} structuur ongeldig is bevonden.
	 */
	private final LeerlinggegevensRequestElements invalidElement;

	/**
	 * @param message
	 *            omschrijving van de opgetreden fout
	 * @param invalidElement
	 *            Element of veld waarop de {@link Leerlinggegevens} structuur ongeldig is
	 *            bevonden.
	 */
	public InvalidLeerlinggegevensRequestException(String message,
			final LeerlinggegevensRequestElements invalidElement)
	{
		super(message);
		this.invalidElement = invalidElement;
	}

	/**
	 * @param message
	 *            omschrijving van de opgetreden fout
	 * @param element
	 *            Element of veld waarop de {@link Leerlinggegevens} structuur ongeldig is
	 *            bevonden.
	 * @param throwable
	 *            gewrapte exception die ten grondslag ligt aan deze
	 *            InvalidLeerlinggegevensException
	 */
	public InvalidLeerlinggegevensRequestException(String message,
			final LeerlinggegevensRequestElements element, final Throwable throwable)
	{
		super(message, throwable);
		this.invalidElement = element;
	}

	public LeerlinggegevensRequestElements getInvalidElement()
	{
		return invalidElement;
	}

}
