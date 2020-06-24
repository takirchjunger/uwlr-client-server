package nl.topicus.onderwijs.uwlr.v2.webservice.interceptors;

import javax.xml.namespace.QName;
import nl.topicus.onderwijs.uwlr.shared.webservice.interceptors.AbstractAutorisatieHeaderValidator;
import nl.topicus.onderwijs.uwlr.v2.webservice.UwlrConstants;

public class AutorisatieHeaderValidator extends AbstractAutorisatieHeaderValidator {
  @Override
  protected QName getAutorisatieQname() {
    return UwlrConstants.AUTORISATIE_QNAME;
  }

  @Override
  protected String getAutorisatieNamespace() {
    return UwlrConstants.EDUSTANDAARD_AUTORISATIE_NS;
  }
}
