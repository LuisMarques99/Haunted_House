package MyCollection.List;

import MyCollection.Exceptions.EmptyCollectionException;
import MyCollection.Exceptions.ElementNotFoundException;

import java.util.Iterator;

/**
 * <h3>
 * <strong>Interface to establish the contract of a {@link ListADT Abstract Data Type List}</strong>
 * </h3>
 *
 * @param <T> Abstract Data Type
 * @author Luis Marques
 */
public interface ListADT<T> extends Iterable<T> {
    /**
     * Removes the first element from this list.
     *
     * @return the first element of this list
     */
    public T removeFirst() throws EmptyCollectionException;

    /**
     * Removes and returns the last element from this list.
     *
     * @return the last element of this list
     */
    public T removeLast() throws EmptyCollectionException;

    /**
     * Removes and returns the specific element of this list.
     *
     * @param element the element to be removed from this list
     * @return the element removed from this list
     */
    public T remove(T element) throws EmptyCollectionException, ElementNotFoundException;

    /**
     * Returns a reference to the first element of this list.
     *
     * @return a reference to the first element of this list
     */
    public T first() throws EmptyCollectionException;

    /**
     * Returns a reference of the last element of this list.
     *
     * @return a reference to the first element of this list
     */
    public T last() throws EmptyCollectionException;

    /**
     * Returns true if this list contains the specific target element.
     *
     * @param target the target that is being sought in the list
     * @return true if this list contains this element
     */
    public boolean contains(T target);

    /**
     * Returns true if this list contains no elements.
     *
     * @return true if this list contains no elements
     */
    public boolean isEmpty();

    /**
     * Returns the number of elements in this list.
     *
     * @return the integer representation of number of elements in this list
     */
    public int size();

    /**
     * Returns an iterator for the elements in this list.
     *
     * @return an iterator over the elements in this list
     */
    public Iterator<T> iterator();

    /**
     * Returns a string representation of this list.
     *
     * @return a string representation of this list
     */
    @Override
    public String toString();
}
