package nl.topicus.onderwijs.eck2.v1.core;

import java.io.InputStream;

import nl.topicus.onderwijs.eck2.shared.core.ECK2ConversionException;
import nl.topicus.onderwijs.eck2.shared.webservice.GeenGegevensException;
import nl.topicus.onderwijs.eck2.shared.webservice.GeenWijzigingenException;
import nl.topicus.onderwijs.eck2.shared.webservice.InvalidLeerlinggegevensException;
import nl.topicus.onderwijs.eck2.shared.webservice.InvalidLeerlinggegevensRequestException;
import nl.topicus.onderwijs.eck2.v1.webservice.impl.Eck2LeerlinggegevensService.UniekeKeyType;
import nl.topicus.onderwijs.generated.uwlr.v1_0.Leerlinggegevens;
import nl.topicus.onderwijs.generated.uwlr.v1_0.Leerlinggegevensverzoek;

/**
 * Interface voor classes die {@link Leerlinggegevens} objecten kunnen leveren.
 *
 * @author tom.kirchjunger
 */
public interface Eck2LeerlinggegevensProvider
{
	/**
	 * Maakt een {@link Leerlinggegevens} structuur uit een stream van Eck2-xml. Doorgaans
	 * zal dit gebeuren door deze xml te unmarshallen naar Java-objecten.
	 *
	 * @param xmlStream
	 *            stream waaruit de te unmarshallen xml gelezen wordt.
	 */
	Leerlinggegevens createLeerlinggegevens(final InputStream xmlStream)
			throws ECK2ConversionException;

	/**
	 * Maakt een structuur van {@link Leerlinggegevens} op basis van een
	 * {@link Leerlinggegevensverzoek}, waarin staat welke gegevens gevraagd worden en van
	 * welke school dat is. In deze methode moeten de controles worden geïmplementeerd op
	 * de (verplichte) velden in het {@link Leerlinggegevensverzoek} en hun waarden.
	 *
	 * @param leerlinggegevensverzoek
	 *            het request waarin staat welke gegevens worden opgevraagd.
	 * @param supportCompatibilityMode
	 *            of compatibility mode actief is voor de leerlinggegevensservice
	 * @throws InvalidLeerlinggegevensRequestException
	 *             wordt gegooid als het ontvangen {@link Leerlinggegevensverzoek}
	 *             ongeldig is
	 * @throws InvalidLeerlinggegevensException
	 *             wordt gegooid als er tijdens het opbouwen van de
	 *             {@link Leerlinggegevens} structuur een probleem optreedt.
	 * @throws GeenWijzigingenException
	 *             wordt gegooid als er geen gewijzigde gegevens zijn en het bouwen van
	 *             leerlinggegevens direct gestaakt kan worden
	 * @throws GeenGegevensException
	 *             wordt gegooid als er om een of andere reden geen gegevens beschikbaar
	 *             zijn, anders dan dat er geen wijzigingen zijn
	 */
	Leerlinggegevens createLeerlinggegevens(final Leerlinggegevensverzoek leerlinggegevensverzoek,
			boolean supportCompatibilityMode, UniekeKeyType uniekeKeyType)
			throws InvalidLeerlinggegevensRequestException, InvalidLeerlinggegevensException,
			GeenWijzigingenException, GeenGegevensException;

}
