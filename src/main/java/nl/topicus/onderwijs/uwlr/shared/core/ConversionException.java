package nl.topicus.onderwijs.uwlr.shared.core;

/** Exception die gegooid wordt als er bij het converteren van UWLR xml iets verkeerd gaat. */
public class ConversionException extends Exception {

  private static final long serialVersionUID = 1L;

  public ConversionException(String message) {
    super(message);
  }

  public ConversionException(String message, Throwable cause) {
    super(message, cause);
  }
}
