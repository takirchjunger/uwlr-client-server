package nl.topicus.onderwijs.uwlr.shared.webservice;

import javax.xml.bind.UnmarshalException;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamException;
import nl.topicus.onderwijs.uwlr.shared.webservice.UwlrConstants.LeerlinggegevensRequestElements;
import nl.topicus.onderwijs.uwlr.shared.webservice.interceptors.Eck2FaultInterceptor;
import org.apache.cxf.binding.soap.SoapFault;
import org.apache.cxf.interceptor.Fault;

/**
 * Implementatie van {@link Fault} die bekend is met de mogelijke fouten die kunnen optreden bij
 * Eck2 webservice communicatie.
 */
public class UwlrSoapFault extends SoapFault {

  private static final long serialVersionUID = 1L;

  public UwlrSoapFault(final UwlrFaultType faultType) {
    this(faultType, faultType.getMessage());
  }

  public UwlrSoapFault(final UwlrFaultType faultType, String details) {
    super(faultType.getMessage(), new Exception(details), new QName("", faultType.getCode()));
  }

  /**
   * Geeft een passende {@link UwlrSoapFault} voor diverse exceptions die kunnen optreden in de Eck2
   * webservice. Kan bijvoorbeeld gebruikt worden door de {@link Eck2FaultInterceptor}.
   */
  public static UwlrSoapFault createSoapFault(final Throwable throwable) {
    // Veel voorkomende exceptions verpakken in een duidelijke foutmelding
    if (throwable.getCause() instanceof XMLStreamException)
      return new UwlrSoapFault(UwlrFaultType.CLT_ONGELDIG_BERICHT, "Bericht is geen geldige xml");
    else if (throwable.getCause() instanceof UnmarshalException)
      return new UwlrSoapFault(
          UwlrFaultType.CLT_ONGELDIG_BERICHT,
          "Fout bij het converteren van xml bericht: " + throwable.getMessage());
    else if (throwable.getCause() instanceof RuntimeException)
      return new UwlrSoapFault(UwlrFaultType.SRV_INTERNE_FOUT, throwable.getMessage());
    else return new UwlrSoapFault(UwlrFaultType.CLT_ONGELDIG_BERICHT, throwable.getMessage());
  }

  public static UwlrSoapFault createSoapFaultForInvalidLeerlingRequestElement(
      final LeerlinggegevensRequestElements invalidElement) {
    if (invalidElement == LeerlinggegevensRequestElements.XSD_VERSIE) {
      return createSoapFaultForInvalidLeerlingRequestElement(
          UwlrFaultType.CLT_XSD_VERSIE_ONGELDIG, invalidElement);
    }
    return createSoapFaultForInvalidLeerlingRequestElement(
        UwlrFaultType.CLT_ONGELDIG_BERICHT, invalidElement);
  }

  public static UwlrSoapFault createSoapFaultForInvalidLeerlingRequestElement(
      final UwlrFaultType faultType, final LeerlinggegevensRequestElements invalidElement) {
    return new UwlrSoapFault(
        faultType,
        String.format(
            "Het request is ongeldig omdat de waarde voor '%s' leeg is of niet voldoet aan het juiste formaat",
            invalidElement.getElementName()));
  }
}
