package MyCollection.Queue;

import MyCollection.Exceptions.EmptyCollectionException;


/**
 * <h3>
 * <strong>Interface that implements the contract of a {@link QueueADT QueueADT} (Abstract Data Type)</strong>
 * </h3>
 *
 * @param <T> Abstract Data Type
 * @author Luis Marques
 */
public interface QueueADT<T> {
    /**
     * Adds one element to the rear of this queue.
     *
     * @param element the element to be added to the rear of this queue
     */
    public void enqueue(T element);

    /**
     * Removes and returns the element at the front of this queue.
     *
     * @return the element at the front of this queue
     * @throws EmptyCollectionException Empty Collection Exception
     */
    public T dequeue() throws EmptyCollectionException;

    /**
     * Returns without removing the element at the front of this queue.
     *
     * @return the first element in this queue
     * @throws EmptyCollectionException Empty Collection Exception
     */
    public T first() throws EmptyCollectionException;

    /**
     * Returns true if this queue has no elements.
     *
     * @return true if this queue is empty
     */
    public boolean isEmpty();

    /**
     * Returns the number of elements in this queue
     *
     * @return the integer representation of the size of this queue
     */
    public int size();

    /**
     * Returns a string representation of this queue.
     *
     * @return the string representation of this queue
     */
    public String toString();
}
