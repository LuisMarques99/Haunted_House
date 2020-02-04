package MyCollection.Entities;

/**
 * <h3>
 * <strong>Class that represents the structure of a {@link LinearNode Linear Node}</strong>
 * </h3>
 *
 * @param <T> Abstract Data Type
 * @author Luis Marques
 */
public class LinearNode<T> {
    /**
     * reference to the next node in the list
     */
    private LinearNode<T> next;
    /**
     * element stored at this node
     */
    private T element;

    /**
     * Creates an empty node.
     */
    public LinearNode() {
        next = null;
        element = null;
    }

    /**
     * Creates a node storing the specified element.
     *
     * @param element element to be stored
     */
    public LinearNode(T element) {
        next = null;
        this.element = element;
    }

    /**
     * Returns the node that follows this one.
     *
     * @return LinearNode<T> reference to the next node
     */
    public LinearNode<T> getNext() {
        return next;
    }

    /**
     * Sets the node that follows this one.
     *
     * @param next node to follow this one
     */
    public void setNext(LinearNode<T> next) {
        this.next = next;
    }

    /**
     * Returns the element stored in this node.
     *
     * @return T element stored at this node
     */
    public T getElement() {
        return element;
    }

    /**
     * Sets the element stored in this node
     *
     * @param element element to be stored in this node
     */
    public void setElement(T element) {
        this.element = element;
    }
}