package ooga.loader;

public class GameFactoryException extends RuntimeException {

  public GameFactoryException(String message, Throwable cause) {
    super(message, cause);
  }

  public GameFactoryException(String message) {
    super(message);
  }
}
