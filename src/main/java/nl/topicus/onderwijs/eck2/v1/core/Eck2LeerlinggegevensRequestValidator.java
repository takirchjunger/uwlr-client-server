package nl.topicus.onderwijs.eck2.v1.core;

import nl.topicus.onderwijs.eck2.shared.webservice.InvalidLeerlinggegevensRequestException;
import nl.topicus.onderwijs.generated.uwlr.v1_0.Leerlinggegevensverzoek;

/**
 * Interface voor classes die een {@link Leerlinggegevensverzoek} kunnen valideren. Dat
 * wil zeggen dat tenminste gecontroleerd moet worden of de volgende gegevens in het
 * verzoek geldig zijn: brincode, dependancecode, schoolkey, schooljaar en xsd-versie.
 * 
 * 
 * @author tom.kirchjunger
 */
public interface Eck2LeerlinggegevensRequestValidator
{

	/**
	 * Valideer een {@link Leerlinggegevensverzoek}
	 */
	void validateRequest(final Leerlinggegevensverzoek leerlinggegevensverzoek,
			boolean supportCompatibilityMode) throws InvalidLeerlinggegevensRequestException;
}
