package nl.topicus.onderwijs.eck2.shared.webservice;

/**
 * Exception die wordt gegooid als er geen gegevens beschikbaar zijn en het bouwen van
 * leerlinggegevens onmiddellijk gestaakt kan worden.
 */
public class GeenGegevensException extends Exception
{

	private static final long serialVersionUID = 1L;

	public GeenGegevensException()
	{
		super();
	}

}
