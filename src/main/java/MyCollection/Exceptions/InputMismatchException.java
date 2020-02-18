package MyCollection.Exceptions;

/**
 * <h3>
 * <strong>Exception that represents a Null Element</strong>
 * </h3>
 *
 * @author Luis Marques
 */
public final class InputMismatchException extends Exception {
    /**
     * Constructs a new exception with the specified detail message.  The
     * cause is not initialized, and may subsequently be initialized by
     * a call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public InputMismatchException(String message) {
        super(message);
    }
}
