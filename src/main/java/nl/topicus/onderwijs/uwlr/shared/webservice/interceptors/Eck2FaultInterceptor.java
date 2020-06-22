package nl.topicus.onderwijs.uwlr.shared.webservice.interceptors;

import nl.topicus.onderwijs.generated.uwlr.v2_2.UwlrServiceInterfaceV2;
import nl.topicus.onderwijs.uwlr.shared.webservice.UwlrSoapFault;
import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.interceptor.AbstractSoapInterceptor;
import org.apache.cxf.phase.Phase;

/**
 * Interceptor die uitgaande berichten van de {@link UwlrServiceInterfaceV2} implementatie afvangt
 * en kijkt of er een {@link Exception} in het bericht verpakt zit. Als dit het geval is wordt de
 * {@link Exception} vervangen door een {@link UwlrSoapFault}, die voldoet aan de foutberichten uit
 * de Eck2 afspraak.
 */
public class Eck2FaultInterceptor extends AbstractSoapInterceptor {

  public Eck2FaultInterceptor() {
    super(Phase.PRE_LOGICAL);
  }

  @Override
  public void handleMessage(final SoapMessage message) {
    // Kijk of in de message een Exception verpakt zit
    Object exception = message.getContent(Exception.class);
    // Als er een exception is die niet van het type Eck2SoapFault is, dan moet deze
    // vertaald worden naar een Eck2SoapFault.
    if (exception != null && (!(message.getContent(Exception.class) instanceof UwlrSoapFault)))
      message.setContent(
          Exception.class, UwlrSoapFault.createSoapFault(((Exception) exception).getCause()));
  }
}
