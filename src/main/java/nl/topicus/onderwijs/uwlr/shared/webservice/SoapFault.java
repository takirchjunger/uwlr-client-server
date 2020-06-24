package nl.topicus.onderwijs.uwlr.shared.webservice;

import javax.xml.bind.UnmarshalException;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamException;
import nl.topicus.onderwijs.uwlr.shared.webservice.Constants.LeerlinggegevensRequestElements;
import nl.topicus.onderwijs.uwlr.shared.webservice.interceptors.FaultInterceptor;
import org.apache.cxf.interceptor.Fault;

/**
 * Implementatie van {@link Fault} die bekend is met de mogelijke fouten die kunnen optreden bij
 * UWLR webservice communicatie.
 */
public class SoapFault extends org.apache.cxf.binding.soap.SoapFault {

  private static final long serialVersionUID = 1L;

  public SoapFault(final FaultType faultType) {
    this(faultType, faultType.getMessage());
  }

  public SoapFault(final FaultType faultType, String details) {
    super(faultType.getMessage(), new Exception(details), new QName("", faultType.getCode()));
  }

  /**
   * Geeft een passende {@link SoapFault} voor diverse exceptions die kunnen optreden in de UWLR
   * webservice. Kan bijvoorbeeld gebruikt worden door de {@link FaultInterceptor}.
   */
  public static SoapFault createSoapFault(final Throwable throwable) {
    // Veel voorkomende exceptions verpakken in een duidelijke foutmelding
    if (throwable.getCause() instanceof XMLStreamException)
      return new SoapFault(FaultType.CLT_ONGELDIG_BERICHT, "Bericht is geen geldige xml");
    else if (throwable.getCause() instanceof UnmarshalException)
      return new SoapFault(
          FaultType.CLT_ONGELDIG_BERICHT,
          "Fout bij het converteren van xml bericht: " + throwable.getMessage());
    else if (throwable.getCause() instanceof RuntimeException)
      return new SoapFault(FaultType.SRV_INTERNE_FOUT, throwable.getMessage());
    else return new SoapFault(FaultType.CLT_ONGELDIG_BERICHT, throwable.getMessage());
  }

  public static SoapFault createSoapFaultForInvalidLeerlingRequestElement(
      final LeerlinggegevensRequestElements invalidElement) {
    if (invalidElement == LeerlinggegevensRequestElements.XSD_VERSIE) {
      return createSoapFaultForInvalidLeerlingRequestElement(
          FaultType.CLT_XSD_VERSIE_ONGELDIG, invalidElement);
    }
    return createSoapFaultForInvalidLeerlingRequestElement(
        FaultType.CLT_ONGELDIG_BERICHT, invalidElement);
  }

  public static SoapFault createSoapFaultForInvalidLeerlingRequestElement(
      final FaultType faultType, final LeerlinggegevensRequestElements invalidElement) {
    return new SoapFault(
        faultType,
        String.format(
            "Het request is ongeldig omdat de waarde voor '%s' leeg is of niet voldoet aan het juiste formaat",
            invalidElement.getElementName()));
  }
}
