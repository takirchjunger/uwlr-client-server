package nl.topicus.onderwijs.uwlr.v2.client;

/** Exception die wordt gegooid bij fouten tijdens het mappen van Uwlr objecten naar entiteiten. */
public class UwlrEntiteitMappingException extends Exception {

  private static final long serialVersionUID = 1L;

  public UwlrEntiteitMappingException(String message) {
    super(message);
  }

  public UwlrEntiteitMappingException(String message, Throwable cause) {
    super(message, cause);
  }
}
