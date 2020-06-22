package nl.topicus.onderwijs.eck2.shared.core;

/**
 * Exception die gegooid wordt als er bij het converteren van Eck2-xml iets verkeerd gaat.
 */
public class ECK2ConversionException extends Exception
{

	private static final long serialVersionUID = 1L;

	public ECK2ConversionException(String message)
	{
		super(message);
	}

	public ECK2ConversionException(String message, Throwable cause)
	{
		super(message, cause);
	}

}
