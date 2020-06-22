package nl.topicus.onderwijs.eck2.v1.webservice.impl;

import javax.jws.WebService;
import javax.servlet.http.HttpServletRequest;

import org.apache.cxf.interceptor.InInterceptors;
import org.apache.cxf.interceptor.OutFaultInterceptors;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.PhaseInterceptorChain;
import org.apache.cxf.transport.http.AbstractHTTPDestination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import nl.topicus.onderwijs.eck2.shared.webservice.Eck2FaultType;
import nl.topicus.onderwijs.eck2.shared.webservice.Eck2SoapFault;
import nl.topicus.onderwijs.eck2.shared.webservice.GeenGegevensException;
import nl.topicus.onderwijs.eck2.shared.webservice.GeenWijzigingenException;
import nl.topicus.onderwijs.eck2.shared.webservice.InvalidLeerlinggegevensException;
import nl.topicus.onderwijs.eck2.shared.webservice.InvalidLeerlinggegevensRequestException;
import nl.topicus.onderwijs.eck2.v1.core.Eck2AutorisatieValidator;
import nl.topicus.onderwijs.eck2.v1.core.Eck2LeerlinggegevensProvider;
import nl.topicus.onderwijs.eck2.v1.core.Eck2LeerlinggegevensRequestValidator;
import nl.topicus.onderwijs.generated.uwlr.v1_0.Autorisatie;
import nl.topicus.onderwijs.generated.uwlr.v1_0.Leerlinggegevensantwoord;
import nl.topicus.onderwijs.generated.uwlr.v1_0.Leerlinggegevensverzoek;
import nl.topicus.onderwijs.generated.uwlr.v1_0.UwlrEndpointInterface;

/**
 * Implementatie van de webservice interface voor Eck2 leerlinggegevens.
 */
@WebService
@InInterceptors(interceptors = {
	"nl.topicus.onderwijs.eck2.v1.webservice.interceptors.AutorisatieHeaderValidator"})
@OutFaultInterceptors(interceptors = {
	"nl.topicus.onderwijs.eck2.shared.webservice.interceptors.Eck2FaultInterceptor"})
public class Eck2LeerlinggegevensService implements UwlrEndpointInterface
{

	private static final Logger LOG = LoggerFactory.getLogger(Eck2LeerlinggegevensService.class);

	@Autowired
	private Eck2LeerlinggegevensProvider leerlinggegevensProvider;

	@Autowired
	private Eck2AutorisatieValidator autorisatieValidator;

	@Autowired
	private Eck2LeerlinggegevensRequestValidator requestValidator;

	/**
	 * Als deze service in compatibiliteitsmodus geïnitialiseerd is, dan betekent dit dat
	 * waar nodig zaken worden aangepast aan eck2 versie 0.94. Deze is nagenoeg identiek
	 * aan versie 1.0, maar vereist onder andere een andere waarde voor 'xsd-versie' in
	 * het request. Dit maakt het overbodig om twee webservices aan te bieden voor twee
	 * nagenoeg gelijke implementaties.
	 */
	private boolean supportCompatibilityMode = false;

	public enum UniekeKeyType
	{
		TRANSITIONAL,
		UUID;
	}

	public Eck2LeerlinggegevensService()
	{
	}

	public Eck2LeerlinggegevensService(boolean supportCompatibilityMode)
	{
		this.supportCompatibilityMode = supportCompatibilityMode;
	}

	@Override
	public Leerlinggegevensantwoord haalLeerlinggegevens(
			final Leerlinggegevensverzoek msgpLeerlinggegevensVerzoek,
			final Autorisatie autorisatieHeader)
	{
		try
		{
			requestValidator.validateRequest(msgpLeerlinggegevensVerzoek, supportCompatibilityMode);
		}
		catch (InvalidLeerlinggegevensRequestException e)
		{
			LOG.error(e.getMessage(), e);
			throw Eck2SoapFault
				.createSoapFaultForInvalidLeerlingRequestElement(e.getInvalidElement());
		}

		/**
		 * In de juiste volgorde valideren van de ontvangen autorisatieheader en
		 * klantgegevens.
		 */
		if (!autorisatieValidator.isValidKlantidentificatie(autorisatieHeader))
			throw new Eck2SoapFault(Eck2FaultType.CLT_ONGELDIGE_KLANTIDENTIFICATIE,
				"De klantnaam en/of klantcode is ongeldig");
		else if (!autorisatieValidator.isValidAutorisatiesleutel(autorisatieHeader,
			msgpLeerlinggegevensVerzoek))
			throw new Eck2SoapFault(Eck2FaultType.CLT_AUTORISATIE_ONGELDIG,
				"Autorisatiesleutel is ongeldig of geeft geen toegang tot de gevraagde school");
		else if (!autorisatieValidator.isValidSchool(msgpLeerlinggegevensVerzoek))
			throw new Eck2SoapFault(Eck2FaultType.CLT_ONGELDIGE_KLANTIDENTIFICATIE,
				"De gegevens van de opgevraagde school zijn ongeldig");

		try
		{
			return Eck2SoapObjectFactory.createLeerlinggegevensResponse(
				leerlinggegevensProvider.createLeerlinggegevens(msgpLeerlinggegevensVerzoek,
					supportCompatibilityMode, findUniekeKeyType()));
		}
		catch (InvalidLeerlinggegevensException e)
		{
			LOG.error("Er is een fout opgetreden bij het samenstellen van de leerlinggegevens", e);
			throw new Eck2SoapFault(Eck2FaultType.SRV_INTERNE_FOUT,
				"Er is een fout opgetreden bij het samenstellen van de leerlinggegevens");
		}
		catch (InvalidLeerlinggegevensRequestException e)
		{
			LOG.error(String.format(
				"Het ontvangen leerlinggegevens request is afgekeurd op het element %s",
				e.getInvalidElement()), e);
			throw Eck2SoapFault
				.createSoapFaultForInvalidLeerlingRequestElement(e.getInvalidElement());
		}
		catch (GeenWijzigingenException e)
		{
			return Eck2SoapObjectFactory.createGeenWijzigingenResponse();
		}
		catch (GeenGegevensException e)
		{
			return Eck2SoapObjectFactory.createGeenGegevensResponse();
		}
	}

	private UniekeKeyType findUniekeKeyType()
	{
		Message message = PhaseInterceptorChain.getCurrentMessage();
		HttpServletRequest httpRequest =
			(HttpServletRequest) message.get(AbstractHTTPDestination.HTTP_REQUEST);
		// Custom header met het soort uniekekey dat gewenst is. Dit is om de overgang van
		// de oude key naar de UUID mogelijk te maken zonder dat de hele keten hierdoor
		// omvalt.
		String hdrUniekeKeyType = httpRequest.getHeader("UniekeKeyType");
		if (hdrUniekeKeyType != null)
		{
			UniekeKeyType uniekeKeyType = UniekeKeyType.valueOf(hdrUniekeKeyType);
			if (uniekeKeyType != null)
				return uniekeKeyType;
		}
		return UniekeKeyType.TRANSITIONAL;
	}

	public boolean isSupportCompatibilityMode()
	{
		return supportCompatibilityMode;
	}

	public void setSupportCompatibilityMode(boolean supportCompatibilityMode)
	{
		this.supportCompatibilityMode = supportCompatibilityMode;
	}

}
