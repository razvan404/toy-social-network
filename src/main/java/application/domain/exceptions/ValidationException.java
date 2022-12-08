package application.domain.exceptions;

/**
 * The throwable class <b>ValidationException</b> is thrown when an entity is not valid.
 */
public class ValidationException extends RuntimeException {
    public ValidationException(String message) {
        super(message);
    }

}
