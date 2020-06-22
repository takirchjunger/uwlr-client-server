package nl.topicus.onderwijs.uwlr.shared.webservice;

public final class UwlrConstants {
  private UwlrConstants() {}

  /** Elementen in een leerlinggegevens request */
  public enum LeerlinggegevensRequestElements {
    SCHOOLJAAR("schooljaar"),

    BRIN_CODE("brincode"),

    DEPENDANCE_CODE("dependancecode"),

    XSD_VERSIE("xsdVersie"),

    LAATST_ONTVANGEN_GEGEVENS("laatstOntvangenGegevens");

    private final String elementName;

    LeerlinggegevensRequestElements(String elementName) {
      this.elementName = elementName;
    }

    public String getElementName() {
      return elementName;
    }
  }

  public enum AutorisatieHeaderElements {
    AUTORISATIESLEUTEL("autorisatiesleutel"),
    KLANTNAAM("klantnaam"),
    KLANTCODE("klantcode");

    private final String elementName;

    AutorisatieHeaderElements(String elementName) {
      this.elementName = elementName;
    }

    public String getElementName() {
      return elementName;
    }
  }
}
