package excepciones;

/**
 * Excepción UsuarioYaExisteException.
 */

@SuppressWarnings("serial")
public class UsuarioYaExisteException extends Exception {

  public UsuarioYaExisteException(String message) {
    super(message);
  }
}
