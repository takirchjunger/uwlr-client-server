package nl.topicus.onderwijs.uwlr.shared.core;

/** Exception die gegooid wordt als er bij het converteren van Eck2-xml iets verkeerd gaat. */
public class UwlrConversionException extends Exception {

  private static final long serialVersionUID = 1L;

  public UwlrConversionException(String message) {
    super(message);
  }

  public UwlrConversionException(String message, Throwable cause) {
    super(message, cause);
  }
}
