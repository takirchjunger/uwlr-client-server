package nl.topicus.onderwijs.uwlr.v2.core;

import nl.topicus.onderwijs.generated.uwlr.v2_2.LeerlinggegevensVerzoek;
import nl.topicus.onderwijs.uwlr.shared.webservice.InvalidLeerlinggegevensRequestException;

public interface Eck2LeerlinggegevensRequestValidator {
  void validateRequest(final LeerlinggegevensVerzoek leerlinggegevensverzoek)
      throws InvalidLeerlinggegevensRequestException;
}
