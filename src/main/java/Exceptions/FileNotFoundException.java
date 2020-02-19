package Exceptions;

/**
 * <h3>
 * <strong>Class that represents the structure of a {@link FileNotFoundException Invalid File Exception}</strong>
 * </h3>
 *
 * @author Luis Marques
 */
public class FileNotFoundException extends Exception {

    /**
     * Throws an exception with a message
     *
     * @param s String message
     */
    public FileNotFoundException(String s) {
        super(s);
    }
}
