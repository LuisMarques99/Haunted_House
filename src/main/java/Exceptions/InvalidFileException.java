package Exceptions;

/**
 * <h3>
 * <strong>Class that represents the structure of a {@link InvalidFileException Invalid File Exception}</strong>
 * </h3>
 */
public class InvalidFileException extends Exception {

    /**
     * Throws an exception with a message
     *
     * @param s String message
     */
    public InvalidFileException(String s) {
        super(s);
    }
}
