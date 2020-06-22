package nl.topicus.onderwijs.eck2.v1.webservice.impl;

import javax.annotation.Nonnull;

import nl.topicus.onderwijs.eck2.shared.webservice.Eck2ObjectFactory;
import nl.topicus.onderwijs.generated.uwlr.v1_0.Leerlinggegevens;
import nl.topicus.onderwijs.generated.uwlr.v1_0.Leerlinggegevensantwoord;
import nl.topicus.onderwijs.generated.uwlr.v1_0.ObjectFactory;

/**
 * Factory voor objecten die betrekking hebben op Eck2 Soap-berichten. Fungeert als extra
 * laag op de ObjectFactory classes die door CXF worden gegenereerd, om het instantiëren
 * van deze classes in projecten te vereenvoudigen.
 */
public final class Eck2SoapObjectFactory
{
	private final static ObjectFactory OBJECT_FACTORY = new ObjectFactory();

	private Eck2SoapObjectFactory()
	{
	}

	public static Leerlinggegevensantwoord createLeerlinggegevensResponse(
			@Nonnull final Leerlinggegevens leerlinggegevens)
	{
		final Leerlinggegevensantwoord response = OBJECT_FACTORY.createLeerlinggegevensantwoord();
		response.setLeerlinggegevens(leerlinggegevens);

		return response;
	}

	public static Leerlinggegevensantwoord createGeenGegevensResponse()
	{
		final Leerlinggegevensantwoord response = OBJECT_FACTORY.createLeerlinggegevensantwoord();
		response.setGeengegevens(Eck2ObjectFactory.createLeegElement());

		return response;
	}

	public static Leerlinggegevensantwoord createGeenWijzigingenResponse()
	{
		final Leerlinggegevensantwoord response = OBJECT_FACTORY.createLeerlinggegevensantwoord();
		response.setGeenwijzigingen(Eck2ObjectFactory.createLeegElement());

		return response;
	}

}
