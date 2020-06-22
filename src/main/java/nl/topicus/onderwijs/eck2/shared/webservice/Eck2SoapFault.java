package nl.topicus.onderwijs.eck2.shared.webservice;

import javax.xml.bind.UnmarshalException;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamException;

import org.apache.cxf.binding.soap.SoapFault;
import org.apache.cxf.interceptor.Fault;

import nl.topicus.onderwijs.eck2.shared.webservice.Eck2WebserviceGenericConstants.LeerlinggegevensRequestElements;
import nl.topicus.onderwijs.eck2.shared.webservice.interceptors.Eck2FaultInterceptor;

/**
 * Implementatie van {@link Fault} die bekend is met de mogelijke fouten die kunnen
 * optreden bij Eck2 webservice communicatie.
 */
public class Eck2SoapFault extends SoapFault
{

	private static final long serialVersionUID = 1L;

	public Eck2SoapFault(final Eck2FaultType faultType)
	{
		this(faultType, faultType.getMessage());
	}

	public Eck2SoapFault(final Eck2FaultType faultType, String details)
	{
		super(faultType.getMessage(), new Exception(details), new QName("", faultType.getCode()));
	}

	/**
	 * Geeft een passende {@link Eck2SoapFault} voor diverse exceptions die kunnen
	 * optreden in de Eck2 webservice. Kan bijvoorbeeld gebruikt worden door de
	 * {@link Eck2FaultInterceptor}.
	 */
	public static Eck2SoapFault createSoapFault(final Throwable throwable)
	{
		// Veel voorkomende exceptions verpakken in een duidelijke foutmelding
		if (throwable.getCause() instanceof XMLStreamException)
			return new Eck2SoapFault(Eck2FaultType.CLT_ONGELDIG_BERICHT,
				"Bericht is geen geldige xml");
		else if (throwable.getCause() instanceof UnmarshalException)
			return new Eck2SoapFault(Eck2FaultType.CLT_ONGELDIG_BERICHT,
				"Fout bij het converteren van xml bericht: " + throwable.getMessage());
		else if (throwable.getCause() instanceof RuntimeException)
			return new Eck2SoapFault(Eck2FaultType.SRV_INTERNE_FOUT, throwable.getMessage());
		else
			return new Eck2SoapFault(Eck2FaultType.CLT_ONGELDIG_BERICHT, throwable.getMessage());
	}

	public static Eck2SoapFault createSoapFaultForInvalidLeerlingRequestElement(
			final LeerlinggegevensRequestElements invalidElement)
	{
		switch (invalidElement)
		{
			case XSD_VERSIE:
				return createSoapFaultForInvalidLeerlingRequestElement(
					Eck2FaultType.CLT_XSD_VERSIE_ONGELDIG, invalidElement);
			default:
				return createSoapFaultForInvalidLeerlingRequestElement(
					Eck2FaultType.CLT_ONGELDIG_BERICHT, invalidElement);
		}
	}

	public static Eck2SoapFault createSoapFaultForInvalidLeerlingRequestElement(
			final Eck2FaultType faultType, final LeerlinggegevensRequestElements invalidElement)
	{
		return new Eck2SoapFault(faultType,
			String.format(
				"Het request is ongeldig omdat de waarde voor '%s' leeg is of niet voldoet aan het juiste formaat",
				invalidElement.getElementName()));
	}

}
