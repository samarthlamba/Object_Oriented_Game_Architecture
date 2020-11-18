package ooga.loader;

/**
 * Runtime exception thrown by keybinder class. should be caught and displayed via alert
 */
public class KeyBindingException extends RuntimeException {

  public KeyBindingException(String message) {
    super(message);
  }
}
