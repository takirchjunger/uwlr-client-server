package nl.topicus.onderwijs.eck2.shared.webservice;

import nl.topicus.onderwijs.generated.uwlr.v1_0.CtpLeeg;
import nl.topicus.onderwijs.generated.uwlr.v1_0.CtpLeerkracht;
import nl.topicus.onderwijs.generated.uwlr.v1_0.CtpSchool;
import nl.topicus.onderwijs.generated.uwlr.v1_0.Leerlinggegevens;
import nl.topicus.onderwijs.generated.uwlr.v1_0.Leerlinggegevens.Leerkrachten;
import nl.topicus.onderwijs.generated.uwlr.v1_0.Leerlinggegevens.Leerlingen;
import nl.topicus.onderwijs.generated.uwlr.v1_0.ObjectFactory;

/**
 * Factory voor allerlei soorten objecten uit het Eck2-domein. Fungeert als extra laag op
 * de ObjectFactory classes die door CXF worden gegenereerd, om het instantiëren van deze
 * classes in projecten te vereenvoudigen.
 */
public final class Eck2ObjectFactory
{
	private final static ObjectFactory OBJECT_FACTORY = new ObjectFactory();

	private Eck2ObjectFactory()
	{
	}

	public static Leerlinggegevens createLeerlinggegevens()
	{
		return OBJECT_FACTORY.createLeerlinggegevens();
	}

	public static CtpSchool createSchool()
	{
		return OBJECT_FACTORY.createCtpSchool();
	}

	public static Leerlinggegevens.Groepen createLeerlingGroepen()
	{
		return OBJECT_FACTORY.createLeerlinggegevensGroepen();
	}

	public static Leerlinggegevens.Subgroepen createLeerlingSubgroepen()
	{
		return OBJECT_FACTORY.createLeerlinggegevensSubgroepen();
	}

	public static CtpLeerkracht.Groepen createLeerkrachtGroepen()
	{
		return OBJECT_FACTORY.createCtpLeerkrachtGroepen();
	}

	public static CtpLeeg createLeegElement()
	{
		return OBJECT_FACTORY.createCtpLeeg();
	}

	public static Leerlingen createLeerlingen()
	{
		return OBJECT_FACTORY.createLeerlinggegevensLeerlingen();
	}

	public static Leerkrachten createLeerkrachten()
	{
		return OBJECT_FACTORY.createLeerlinggegevensLeerkrachten();
	}

}
