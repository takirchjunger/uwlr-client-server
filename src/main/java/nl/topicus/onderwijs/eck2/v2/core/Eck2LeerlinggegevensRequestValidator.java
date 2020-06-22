package nl.topicus.onderwijs.eck2.v2.core;

import nl.topicus.onderwijs.eck2.shared.webservice.InvalidLeerlinggegevensRequestException;
import nl.topicus.onderwijs.generated.uwlr.v2_0.LeerlinggegevensVerzoek;

public interface Eck2LeerlinggegevensRequestValidator
{
	void validateRequest(final LeerlinggegevensVerzoek leerlinggegevensverzoek)
			throws InvalidLeerlinggegevensRequestException;

}
