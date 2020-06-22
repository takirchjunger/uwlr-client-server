package nl.topicus.onderwijs.uwlr.shared.webservice;

import nl.topicus.onderwijs.generated.uwlr.v2_2.LeerlinggegevensVerzoek;

/**
 * Exception die wordt gegooid als een ontvangen {@link LeerlinggegevensVerzoek} ongeldig is, omdat
 * bijvoorbeeld verplichte velden missen of hun waarden niet voldoen aan het verwachte format.
 */
public class InvalidLeerlinggegevensException extends Exception {

  private static final long serialVersionUID = 1L;

  /** @param message omschrijving van de opgetreden fout */
  public InvalidLeerlinggegevensException(String message) {
    super(message);
  }

  /**
   * @param message omschrijving van de opgetreden fout
   * @param throwable gewrapte exception die ten grondslag ligt aan deze {@link
   *     InvalidLeerlinggegevensRequestException}
   */
  public InvalidLeerlinggegevensException(String message, Throwable throwable) {
    super(message, throwable);
  }
}
