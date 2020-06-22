package nl.topicus.onderwijs.eck2.v2.core;

import java.io.InputStream;

import nl.topicus.onderwijs.eck2.shared.core.ECK2ConversionException;
import nl.topicus.onderwijs.eck2.shared.webservice.GeenGegevensException;
import nl.topicus.onderwijs.eck2.shared.webservice.GeenWijzigingenException;
import nl.topicus.onderwijs.eck2.shared.webservice.InvalidLeerlinggegevensException;
import nl.topicus.onderwijs.eck2.shared.webservice.InvalidLeerlinggegevensRequestException;
import nl.topicus.onderwijs.generated.uwlr.v2_0.Autorisatie;
import nl.topicus.onderwijs.generated.uwlr.v2_0.Leerlinggegevens;
import nl.topicus.onderwijs.generated.uwlr.v2_0.LeerlinggegevensVerzoek;

public interface Eck2LeerlinggegevensProvider
{
	Leerlinggegevens createLeerlinggegevens(final InputStream xmlStream)
			throws ECK2ConversionException;

	Leerlinggegevens createLeerlinggegevens(final Autorisatie autorisatieHeader,
			final LeerlinggegevensVerzoek leerlinggegevensverzoek)
			throws InvalidLeerlinggegevensRequestException, InvalidLeerlinggegevensException,
			GeenWijzigingenException, GeenGegevensException;

}
