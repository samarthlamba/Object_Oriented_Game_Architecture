package ooga.engine.entities;

public class MethodNotFound  extends RuntimeException{
    public MethodNotFound(String message) {
        super(message);
    }

    public MethodNotFound(String message, Throwable cause) {
        super(message, cause);
    }
}
