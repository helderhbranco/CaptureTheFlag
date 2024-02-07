package Collections.Exceptions;

/**
 * UnsupportedOperationException is thrown when an operation is not supported.
 */
public class UnsupportedOperationException extends RuntimeException {

    /**
     * Constructs a new UnsupportedOperationException with no detail message.
     */
    public UnsupportedOperationException() {
        super();
    }

    /**
     * Constructs a new UnsupportedOperationException with the specified detail message.
     *
     * @param message the detail message
     */
    public UnsupportedOperationException(String message) {
        super(message);
    }
}
