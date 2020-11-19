package ooga.engine.games;

public class CouldNotWriteToFileException extends RuntimeException {

  public CouldNotWriteToFileException(String message) {
    super(message);
  }

  public CouldNotWriteToFileException(String message, Throwable cause) {
    super(message, cause);
  }
}
