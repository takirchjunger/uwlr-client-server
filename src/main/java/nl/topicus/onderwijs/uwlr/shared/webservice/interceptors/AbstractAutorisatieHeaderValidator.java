package nl.topicus.onderwijs.uwlr.shared.webservice.interceptors;

import javax.xml.namespace.QName;
import nl.topicus.onderwijs.uwlr.shared.webservice.UwlrFaultType;
import nl.topicus.onderwijs.uwlr.shared.webservice.UwlrSoapFault;
import nl.topicus.onderwijs.uwlr.shared.webservice.UwlrConstants.AutorisatieHeaderElements;
import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.interceptor.AbstractSoapInterceptor;
import org.apache.cxf.binding.soap.interceptor.EndpointSelectionInterceptor;
import org.apache.cxf.binding.soap.interceptor.ReadHeadersInterceptor;
import org.apache.cxf.headers.Header;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.Phase;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * Interceptor/validator die controleert of er een autorisatieheader in het SOAP-bericht zit, met de
 * verplichte gegevens. Er wordt <strong>niet</strong> gecontroleerd of de ontvangen gegevens
 * toegang geven tot de gevraagde informatie. Deze validator/interceptor vangt requests zonder
 * volledige autorisatie in een vroeg stadium af om verdere verwerking te voorkomen en om een
 * duidelijke foutmelding te kunnen formuleren.
 *
 * <p>Na het passeren van deze interceptor kan er vanuit worden gegaan dat er een volledige
 * autorisatieheader in het SOAP-bericht zit die gereed is om te gebruiken voor
 * autorisatiedoeleinden.
 */
public abstract class AbstractAutorisatieHeaderValidator extends AbstractSoapInterceptor {
  public AbstractAutorisatieHeaderValidator() {
    super(Phase.READ);
    addAfter(ReadHeadersInterceptor.class.getName());
    addAfter(EndpointSelectionInterceptor.class.getName());
  }

  @Override
  public void handleMessage(final SoapMessage message) throws Fault {
    Header header = message.getHeader(getAutorisatieQname());
    if (header == null)
      throw new UwlrSoapFault(UwlrFaultType.CLT_AUTORISATIE_ONGELDIG, "Autorisatieheader is leeg");

    Element element = (Element) header.getObject();

    assertAutorisatieElementIsValid(element, AutorisatieHeaderElements.AUTORISATIESLEUTEL);
    assertAutorisatieElementIsValid(element, AutorisatieHeaderElements.KLANTNAAM);
    assertAutorisatieElementIsValid(element, AutorisatieHeaderElements.KLANTCODE);
  }

  /** Controleert of een bepaald {@link Element} bestaat in de autorisatieheader. */
  private void assertAutorisatieElementIsValid(
      final Element element, final AutorisatieHeaderElements headerElement) {
    NodeList node =
        element.getElementsByTagNameNS(getAutorisatieNamespace(), headerElement.getElementName());

    if (node == null
        || node.getLength() != 1
        || node.item(0) == null
        || "".equals(node.item(0).getTextContent()))
      throw new UwlrSoapFault(
          UwlrFaultType.CLT_AUTORISATIE_ONGELDIG,
          String.format(
              "Ongeldig of ontbrekend element '%s' in autorisatieheader",
              headerElement.getElementName()));
  }

  protected abstract QName getAutorisatieQname();

  protected abstract String getAutorisatieNamespace();
}
