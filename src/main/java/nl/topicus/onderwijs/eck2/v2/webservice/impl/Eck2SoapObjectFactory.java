package nl.topicus.onderwijs.eck2.v2.webservice.impl;

import javax.annotation.Nonnull;

import nl.topicus.onderwijs.generated.uwlr.v2_0.CtpLeeg;
import nl.topicus.onderwijs.generated.uwlr.v2_0.Leerlinggegevens;
import nl.topicus.onderwijs.generated.uwlr.v2_0.LeerlinggegevensAntwoord;
import nl.topicus.onderwijs.generated.uwlr.v2_0.ObjectFactory;

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

	public static LeerlinggegevensAntwoord createLeerlinggegevensResponse(
			@Nonnull final Leerlinggegevens leerlinggegevens)
	{
		final LeerlinggegevensAntwoord response = OBJECT_FACTORY.createLeerlinggegevensAntwoord();
		response.setLeerlinggegevens(leerlinggegevens);

		return response;
	}

	public static LeerlinggegevensAntwoord createGeenGegevensResponse()
	{
		final LeerlinggegevensAntwoord response = OBJECT_FACTORY.createLeerlinggegevensAntwoord();
		response.setGeengegevens(new CtpLeeg());

		return response;
	}

	public static LeerlinggegevensAntwoord createGeenWijzigingenResponse()
	{
		final LeerlinggegevensAntwoord response = OBJECT_FACTORY.createLeerlinggegevensAntwoord();
		response.setGeenwijzigingen(new CtpLeeg());

		return response;
	}

}
