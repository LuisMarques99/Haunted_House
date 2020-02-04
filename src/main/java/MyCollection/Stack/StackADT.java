package MyCollection.Stack;

import MyCollection.Exceptions.EmptyCollectionException;

/**
 * <h3>
 * <strong>Interface that implements the contract of a {@link StackADT StackADT} (Abstract Data Type Stack)</strong>
 * </h3>
 *
 * @param <T> Abstract Data Type
 * @author Luis Marques
 */
public interface StackADT<T> {
    /**
     * Adds one element to the top of this stack.
     *
     * @param element element to be pushed onto stack
     */
    public void push(T element);

    /**
     * Removes and returns the top element from this stack.
     *
     * @return element removed from the top of the stack
     */
    public T pop() throws EmptyCollectionException;

    /**
     * Returns without removing the top element of this stack.
     *
     * @return element on top of the stack
     */
    public T peek() throws EmptyCollectionException;

    /**
     * Returns true if this stack contains no elements.
     *
     * @return boolean whether or not this stack is empty
     */
    public boolean isEmpty();

    /**
     * Returns the number of elements in this stack.
     *
     * @return int number of elements in this stack
     */
    public int size();

    /**
     * Returns a string representation of this stack.
     *
     * @return String representation of this stack
     */
    @Override
    public String toString();
}