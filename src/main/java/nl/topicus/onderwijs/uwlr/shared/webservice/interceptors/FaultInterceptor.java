package nl.topicus.onderwijs.uwlr.shared.webservice.interceptors;

import nl.topicus.onderwijs.generated.uwlr.v2_2.UwlrServiceInterfaceV2;
import nl.topicus.onderwijs.uwlr.shared.webservice.SoapFault;
import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.interceptor.AbstractSoapInterceptor;
import org.apache.cxf.phase.Phase;

/**
 * Interceptor die uitgaande berichten van de {@link UwlrServiceInterfaceV2} implementatie afvangt
 * en kijkt of er een {@link Exception} in het bericht verpakt zit. Als dit het geval is wordt de
 * {@link Exception} vervangen door een {@link SoapFault}, die voldoet aan de foutberichten uit de
 * UWLR afspraak.
 */
public class FaultInterceptor extends AbstractSoapInterceptor {

  public FaultInterceptor() {
    super(Phase.PRE_LOGICAL);
  }

  @Override
  public void handleMessage(final SoapMessage message) {
    // Kijk of in de message een Exception verpakt zit
    Object exception = message.getContent(Exception.class);
    // Als er een exception is die niet van het type SoapFault is, dan moet deze
    // vertaald worden naar een SoapFault.
    if (exception != null && (!(message.getContent(Exception.class) instanceof SoapFault)))
      message.setContent(
          Exception.class, SoapFault.createSoapFault(((Exception) exception).getCause()));
  }
}
