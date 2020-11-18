package ooga.loader;

/**
 * Exception thrown when there is a failure to create Game objects from data. Must be handled.
 */
public class FactoryException extends Exception {

  public FactoryException(String message, Throwable cause) {
    super(message, cause);
  }

  public FactoryException(String message) {
    super(message);
  }
}
