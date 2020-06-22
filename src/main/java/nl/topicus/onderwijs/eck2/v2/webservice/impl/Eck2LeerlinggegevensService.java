package nl.topicus.onderwijs.eck2.v2.webservice.impl;

import javax.jws.WebService;

import nl.topicus.onderwijs.eck2.shared.webservice.Eck2FaultType;
import nl.topicus.onderwijs.eck2.shared.webservice.Eck2SoapFault;
import nl.topicus.onderwijs.eck2.shared.webservice.GeenGegevensException;
import nl.topicus.onderwijs.eck2.shared.webservice.GeenWijzigingenException;
import nl.topicus.onderwijs.eck2.shared.webservice.InvalidLeerlinggegevensException;
import nl.topicus.onderwijs.eck2.shared.webservice.InvalidLeerlinggegevensRequestException;
import nl.topicus.onderwijs.eck2.v2.core.Eck2AutorisatieValidator;
import nl.topicus.onderwijs.eck2.v2.core.Eck2LeerlinggegevensProvider;
import nl.topicus.onderwijs.eck2.v2.core.Eck2LeerlinggegevensRequestValidator;
import nl.topicus.onderwijs.generated.uwlr.v2_0.Autorisatie;
import nl.topicus.onderwijs.generated.uwlr.v2_0.LeerkrachtType;
import nl.topicus.onderwijs.generated.uwlr.v2_0.LeerlingType;
import nl.topicus.onderwijs.generated.uwlr.v2_0.Leerlinggegevens;
import nl.topicus.onderwijs.generated.uwlr.v2_0.LeerlinggegevensAntwoord;
import nl.topicus.onderwijs.generated.uwlr.v2_0.LeerlinggegevensVerzoek;
import nl.topicus.onderwijs.generated.uwlr.v2_0.UwlrEndpointInterfaceV2;
import org.apache.cxf.interceptor.InInterceptors;
import org.apache.cxf.interceptor.OutFaultInterceptors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@WebService
@InInterceptors(interceptors = {
	"nl.topicus.onderwijs.eck2.v2.webservice.interceptors.AutorisatieHeaderValidator"})
@OutFaultInterceptors(interceptors = {
	"nl.topicus.onderwijs.eck2.shared.webservice.interceptors.Eck2FaultInterceptor"})
public class Eck2LeerlinggegevensService implements UwlrEndpointInterfaceV2
{
	private static final Logger LOG = LoggerFactory.getLogger(Eck2LeerlinggegevensService.class);

	@Autowired
	private Eck2LeerlinggegevensProvider leerlinggegevensProvider;

	@Autowired
	private Eck2AutorisatieValidator autorisatieValidator;

	@Autowired
	private Eck2LeerlinggegevensRequestValidator requestValidator;

	public Eck2LeerlinggegevensService()
	{
	}

	public Eck2LeerlinggegevensService(Eck2LeerlinggegevensProvider leerlinggegevensProvider,
			Eck2AutorisatieValidator autorisatieValidator,
			Eck2LeerlinggegevensRequestValidator requestValidator)
	{
		this.leerlinggegevensProvider = leerlinggegevensProvider;
		this.autorisatieValidator = autorisatieValidator;
		this.requestValidator = requestValidator;
	}

	@Override
	public LeerlinggegevensAntwoord haalLeerlinggegevens(
			LeerlinggegevensVerzoek msgpLeerlinggegevensVerzoek, Autorisatie autorisatieHeader)
	{
		try
		{
			requestValidator.validateRequest(msgpLeerlinggegevensVerzoek);
		}
		catch (InvalidLeerlinggegevensRequestException e)
		{
			LOG.error(e.getMessage(), e);
			throw Eck2SoapFault
				.createSoapFaultForInvalidLeerlingRequestElement(e.getInvalidElement());
		}

		/**
		 * In de juiste volgorde valideren van de ontvangen autorisatieheader en klantgegevens.
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
			Leerlinggegevens leerlinggegevens = leerlinggegevensProvider
				.createLeerlinggegevens(autorisatieHeader, msgpLeerlinggegevensVerzoek);
			makeBackwardsCompatible(leerlinggegevens, msgpLeerlinggegevensVerzoek.getXsdversie());
			return Eck2SoapObjectFactory.createLeerlinggegevensResponse(leerlinggegevens);
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

	private void makeBackwardsCompatible(Leerlinggegevens leerlinggegevens, String xsdversie)
	{
		if ("2.1".equals(xsdversie))
		{
			for (LeerlingType leerling : leerlinggegevens.getLeerlingen().getLeerling())
				leerling.setEckid(null);
			for (LeerkrachtType leerkracht : leerlinggegevens.getLeerkrachten().getLeerkracht())
				leerkracht.setEckid(null);
		}
	}

}
