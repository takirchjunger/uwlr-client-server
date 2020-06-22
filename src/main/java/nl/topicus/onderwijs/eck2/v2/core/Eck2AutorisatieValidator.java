package nl.topicus.onderwijs.eck2.v2.core;

import nl.topicus.onderwijs.generated.uwlr.v2_0.Autorisatie;
import nl.topicus.onderwijs.generated.uwlr.v2_0.LeerlinggegevensVerzoek;

public interface Eck2AutorisatieValidator
{
	boolean isValidAutorisatiesleutel(final Autorisatie autorisatie,
			final LeerlinggegevensVerzoek request);

	boolean isValidKlantidentificatie(final Autorisatie autorisatie);

	boolean isValidSchool(final LeerlinggegevensVerzoek request);

}
