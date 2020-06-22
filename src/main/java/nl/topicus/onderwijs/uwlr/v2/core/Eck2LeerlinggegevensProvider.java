package nl.topicus.onderwijs.uwlr.v2.core;

import java.io.InputStream;
import nl.topicus.onderwijs.generated.uwlr.v2_2.Autorisatie;
import nl.topicus.onderwijs.generated.uwlr.v2_2.Leerlinggegevens;
import nl.topicus.onderwijs.generated.uwlr.v2_2.LeerlinggegevensVerzoek;
import nl.topicus.onderwijs.uwlr.shared.core.UwlrConversionException;
import nl.topicus.onderwijs.uwlr.shared.webservice.GeenGegevensException;
import nl.topicus.onderwijs.uwlr.shared.webservice.GeenWijzigingenException;
import nl.topicus.onderwijs.uwlr.shared.webservice.InvalidLeerlinggegevensException;
import nl.topicus.onderwijs.uwlr.shared.webservice.InvalidLeerlinggegevensRequestException;

public interface Eck2LeerlinggegevensProvider {
  Leerlinggegevens createLeerlinggegevens(final InputStream xmlStream)
      throws UwlrConversionException;

  Leerlinggegevens createLeerlinggegevens(
      final Autorisatie autorisatieHeader, final LeerlinggegevensVerzoek leerlinggegevensverzoek)
      throws InvalidLeerlinggegevensRequestException, InvalidLeerlinggegevensException,
          GeenWijzigingenException, GeenGegevensException;
}
