package main.java.excepciones;

/**
 * Excepción TipoPublicacionNoExisteException.
 */

@SuppressWarnings("serial")
public class TipoPublicacionNoExisteException
      extends Exception {

  public TipoPublicacionNoExisteException(String string) {
    super(string);
  }
}