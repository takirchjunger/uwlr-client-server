package nl.topicus.onderwijs.eck2.v1.webservice;

import javax.xml.namespace.QName;

public final class Eck2WebserviceConstants
{
	public static final String EDUSTANDAARD_AUTORISATIE_NS =
		"http://www.edustandaard.nl/leerresultaten/1/autorisatie";

	public static final String EDUSTANDAARD_LEERLINGGEGEVENS_NS =
		"http://www.edustandaard.nl/leerresultaten/1/leerlinggegevens";

	public static final QName AUTORISATIE_QNAME =
		new QName(EDUSTANDAARD_AUTORISATIE_NS, "autorisatie");

	public static final String XSD_VERSIE = "1.0";
}
