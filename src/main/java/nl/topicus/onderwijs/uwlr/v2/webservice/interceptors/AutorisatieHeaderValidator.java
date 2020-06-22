package nl.topicus.onderwijs.uwlr.v2.webservice.interceptors;

import javax.xml.namespace.QName;
import nl.topicus.onderwijs.uwlr.shared.webservice.interceptors.AbstractAutorisatieHeaderValidator;
import nl.topicus.onderwijs.uwlr.v2.webservice.Eck2WebserviceConstants;

public class AutorisatieHeaderValidator extends AbstractAutorisatieHeaderValidator {
  @Override
  protected QName getAutorisatieQname() {
    return Eck2WebserviceConstants.AUTORISATIE_QNAME;
  }

  @Override
  protected String getAutorisatieNamespace() {
    return Eck2WebserviceConstants.EDUSTANDAARD_AUTORISATIE_NS;
  }
}
