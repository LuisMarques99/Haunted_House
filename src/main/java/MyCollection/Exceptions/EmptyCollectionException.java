package MyCollection.Exceptions;

/**
 * <h3>
 * <strong>Exception that represents an Empty Collection</strong>
 * </h3>
 *
 * @author Luis Marques
 */
public final class EmptyCollectionException extends Exception {
    /**
     * Constructs a new exception with the specified detail message.  The
     * cause is not initialized, and may subsequently be initialized by
     * a call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public EmptyCollectionException(String message) {
        super(message);
    }
}