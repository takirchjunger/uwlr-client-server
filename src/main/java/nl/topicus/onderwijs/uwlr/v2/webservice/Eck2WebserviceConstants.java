package nl.topicus.onderwijs.uwlr.v2.webservice;

import javax.xml.namespace.QName;

public final class Eck2WebserviceConstants {
  public static final String EDUSTANDAARD_AUTORISATIE_NS =
      "http://www.edustandaard.nl/leerresultaten/2/autorisatie";

  public static final String EDUSTANDAARD_LEERLINGGEGEVENS_NS =
      "http://www.edustandaard.nl/leerresultaten/2/leerlinggegevens";

  public static final QName AUTORISATIE_QNAME =
      new QName(EDUSTANDAARD_AUTORISATIE_NS, "autorisatie");

  public static final String XSD_VERSIE = "2.2";
}
