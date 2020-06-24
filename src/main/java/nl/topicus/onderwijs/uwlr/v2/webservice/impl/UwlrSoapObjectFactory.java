package nl.topicus.onderwijs.uwlr.v2.webservice.impl;

import nl.topicus.onderwijs.generated.uwlr.v2_2.CtpLeeg;
import nl.topicus.onderwijs.generated.uwlr.v2_2.Leerlinggegevens;
import nl.topicus.onderwijs.generated.uwlr.v2_2.LeerlinggegevensAntwoord;
import nl.topicus.onderwijs.generated.uwlr.v2_2.ObjectFactory;

/**
 * Factory voor objecten die betrekking hebben op UWRL Soap-berichten. Fungeert als extra laag op de
 * ObjectFactory classes die door CXF worden gegenereerd, om het instantiëren van deze classes in
 * projecten te vereenvoudigen.
 */
public final class UwlrSoapObjectFactory {
  private static final ObjectFactory OBJECT_FACTORY = new ObjectFactory();

  private UwlrSoapObjectFactory() {}

  public static LeerlinggegevensAntwoord createLeerlinggegevensResponse(
      final Leerlinggegevens leerlinggegevens) {
    final LeerlinggegevensAntwoord response = OBJECT_FACTORY.createLeerlinggegevensAntwoord();
    response.setLeerlinggegevens(leerlinggegevens);

    return response;
  }

  public static LeerlinggegevensAntwoord createGeenGegevensResponse() {
    final LeerlinggegevensAntwoord response = OBJECT_FACTORY.createLeerlinggegevensAntwoord();
    response.setGeengegevens(new CtpLeeg());

    return response;
  }

  public static LeerlinggegevensAntwoord createGeenWijzigingenResponse() {
    final LeerlinggegevensAntwoord response = OBJECT_FACTORY.createLeerlinggegevensAntwoord();
    response.setGeenwijzigingen(new CtpLeeg());

    return response;
  }
}
