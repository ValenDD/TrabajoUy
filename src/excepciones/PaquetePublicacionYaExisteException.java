package excepciones;

@SuppressWarnings("serial")
public class PaquetePublicacionYaExisteException extends Exception {
	public PaquetePublicacionYaExisteException(String string) {
		super(string);
	}
}
