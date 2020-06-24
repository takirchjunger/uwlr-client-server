package nl.topicus.onderwijs.uwlr.v2.webservice.impl;

import javax.jws.WebService;
import nl.topicus.onderwijs.generated.uwlr.v2_2.Autorisatie;
import nl.topicus.onderwijs.generated.uwlr.v2_2.LeerkrachtType;
import nl.topicus.onderwijs.generated.uwlr.v2_2.LeerlingType;
import nl.topicus.onderwijs.generated.uwlr.v2_2.Leerlinggegevens;
import nl.topicus.onderwijs.generated.uwlr.v2_2.LeerlinggegevensAntwoord;
import nl.topicus.onderwijs.generated.uwlr.v2_2.LeerlinggegevensVerzoek;
import nl.topicus.onderwijs.generated.uwlr.v2_2.UwlrEndpointInterfaceV2;
import nl.topicus.onderwijs.uwlr.shared.webservice.FaultType;
import nl.topicus.onderwijs.uwlr.shared.webservice.SoapFault;
import nl.topicus.onderwijs.uwlr.shared.webservice.GeenGegevensException;
import nl.topicus.onderwijs.uwlr.shared.webservice.GeenWijzigingenException;
import nl.topicus.onderwijs.uwlr.shared.webservice.InvalidLeerlinggegevensException;
import nl.topicus.onderwijs.uwlr.shared.webservice.InvalidRequestException;
import nl.topicus.onderwijs.uwlr.v2.core.AutorisatieValidator;
import nl.topicus.onderwijs.uwlr.v2.core.LeerlinggegevensProvider;
import nl.topicus.onderwijs.uwlr.v2.core.LeerlinggegevensRequestValidator;
import org.apache.cxf.interceptor.InInterceptors;
import org.apache.cxf.interceptor.OutFaultInterceptors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@WebService
@InInterceptors(
    interceptors = {
      "nl.topicus.onderwijs.uwlr.v2.webservice.interceptors.AutorisatieHeaderValidator"
    })
@OutFaultInterceptors(
    interceptors = {
      "nl.topicus.onderwijs.uwlr.shared.webservice.interceptors.FaultInterceptor"
    })
public class UwlrLeerlinggegevensService implements UwlrEndpointInterfaceV2 {
  private static final Logger LOG = LoggerFactory.getLogger(UwlrLeerlinggegevensService.class);

  @Autowired private LeerlinggegevensProvider leerlinggegevensProvider;

  @Autowired private AutorisatieValidator autorisatieValidator;

  @Autowired private LeerlinggegevensRequestValidator requestValidator;

  public UwlrLeerlinggegevensService() {}

  public UwlrLeerlinggegevensService(
      LeerlinggegevensProvider leerlinggegevensProvider,
      AutorisatieValidator autorisatieValidator,
      LeerlinggegevensRequestValidator requestValidator) {
    this.leerlinggegevensProvider = leerlinggegevensProvider;
    this.autorisatieValidator = autorisatieValidator;
    this.requestValidator = requestValidator;
  }

  @Override
  public LeerlinggegevensAntwoord haalLeerlinggegevens(
      LeerlinggegevensVerzoek msgpLeerlinggegevensVerzoek, Autorisatie autorisatieHeader) {
    try {
      requestValidator.validateRequest(msgpLeerlinggegevensVerzoek);
    } catch (InvalidRequestException e) {
      LOG.error(e.getMessage(), e);
      throw SoapFault.createSoapFaultForInvalidLeerlingRequestElement(e.getInvalidElement());
    }

    /** In de juiste volgorde valideren van de ontvangen autorisatieheader en klantgegevens. */
    if (!autorisatieValidator.isValidKlantidentificatie(autorisatieHeader))
      throw new SoapFault(
          FaultType.CLT_ONGELDIGE_KLANTIDENTIFICATIE,
          "De klantnaam en/of klantcode is ongeldig");
    else if (!autorisatieValidator.isValidAutorisatiesleutel(
        autorisatieHeader, msgpLeerlinggegevensVerzoek))
      throw new SoapFault(
          FaultType.CLT_AUTORISATIE_ONGELDIG,
          "Autorisatiesleutel is ongeldig of geeft geen toegang tot de gevraagde school");
    else if (!autorisatieValidator.isValidSchool(msgpLeerlinggegevensVerzoek))
      throw new SoapFault(
          FaultType.CLT_ONGELDIGE_KLANTIDENTIFICATIE,
          "De gegevens van de opgevraagde school zijn ongeldig");

    try {
      Leerlinggegevens leerlinggegevens =
          leerlinggegevensProvider.createLeerlinggegevens(
              autorisatieHeader, msgpLeerlinggegevensVerzoek);
      makeBackwardsCompatible(leerlinggegevens, msgpLeerlinggegevensVerzoek.getXsdversie());
      return UwlrSoapObjectFactory.createLeerlinggegevensResponse(leerlinggegevens);
    } catch (InvalidLeerlinggegevensException e) {
      LOG.error("Er is een fout opgetreden bij het samenstellen van de leerlinggegevens", e);
      throw new SoapFault(
          FaultType.SRV_INTERNE_FOUT,
          "Er is een fout opgetreden bij het samenstellen van de leerlinggegevens");
    } catch (InvalidRequestException e) {
      LOG.error(
          String.format(
              "Het ontvangen leerlinggegevens request is afgekeurd op het element %s",
              e.getInvalidElement()),
          e);
      throw SoapFault.createSoapFaultForInvalidLeerlingRequestElement(e.getInvalidElement());
    } catch (GeenWijzigingenException e) {
      return UwlrSoapObjectFactory.createGeenWijzigingenResponse();
    } catch (GeenGegevensException e) {
      return UwlrSoapObjectFactory.createGeenGegevensResponse();
    }
  }

  private void makeBackwardsCompatible(Leerlinggegevens leerlinggegevens, String xsdversie) {
    if ("2.1".equals(xsdversie)) {
      for (LeerlingType leerling : leerlinggegevens.getLeerlingen().getLeerling())
        leerling.setEckid(null);
      for (LeerkrachtType leerkracht : leerlinggegevens.getLeerkrachten().getLeerkracht())
        leerkracht.setEckid(null);
    }
  }
}
