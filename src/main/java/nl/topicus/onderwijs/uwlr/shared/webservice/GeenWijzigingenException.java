package nl.topicus.onderwijs.uwlr.shared.webservice;

/**
 * Exception die wordt gegooid als er geen wijzigingen zijn en het bouwen van leerlinggegevens
 * onmiddellijk gestaakt kan worden.
 */
public class GeenWijzigingenException extends Exception {

  private static final long serialVersionUID = 1L;

  public GeenWijzigingenException() {
    super();
  }
}
