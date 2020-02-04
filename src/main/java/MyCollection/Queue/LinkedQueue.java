package MyCollection.Queue;

import MyCollection.Entities.LinearNode;
import MyCollection.Exceptions.EmptyCollectionException;


/**
 * <h3>
 * <strong>Class that represents the structure of a {@link LinkedQueue Linked Queue}</strong>
 * </h3>
 *
 * @param <T> Abstract Data Type
 * @author Luis Marques
 */
public class LinkedQueue<T> implements QueueADT<T> {
    /**
     * int that represents the number of elements of the {@link LinkedQueue Linked Queue}
     */
    private int count;

    /**
     * {@link LinearNode Linear Node} that represents the front nod of the {@link LinkedQueue linked Queue}
     */
    private LinearNode<T> front;

    /**
     * {@link LinearNode Linear Node} that represents the rear nod of the {@link LinkedQueue linked Queue}
     */
    private LinearNode<T> rear;

    /**
     * Creates an empty {@link LinkedQueue Linked Queue}
     */
    public LinkedQueue() {
        count = 0;
        front = null;
        rear = null;
    }

    /**
     * Adds one element to the rear of this {@link LinkedQueue LinkedQueue}
     *
     * @param element the element to be added to the rear of this queue
     */
    @Override
    public void enqueue(T element) {
        LinearNode<T> tempNode = new LinearNode<T>(element);
        if (front == null)
            front = tempNode;
        else
            rear.setNext(tempNode);
        rear = tempNode;
        count++;
    }

    /**
     * Removes and returns the element at the front of this {@link LinkedQueue LinkedQueue}
     *
     * @return element removed at the front of this {@link LinkedQueue LinkedQueue}
     * @throws EmptyCollectionException Empty Collection Exception
     */
    @Override
    public T dequeue() throws EmptyCollectionException {
        if (isEmpty())
            throw new EmptyCollectionException("Queue");

        T removed = front.getElement();
        front = front.getNext();
        count--;
        return removed;
    }

    /**
     * Returns without removing the element at the front of this queue.
     *
     * @return the first element in this queue
     * @throws EmptyCollectionException Empty Collection Exception
     */
    @Override
    public T first() throws EmptyCollectionException {
        if (isEmpty())
            throw new EmptyCollectionException("Queue");

        return front.getElement();
    }

    /**
     * Returns true if this queue has no elements.
     *
     * @return true if this queue is empty
     */
    @Override
    public boolean isEmpty() {
        if (size() == 0)
            return true;

        return false;
    }

    /**
     * Returns the number of elements in this queue
     *
     * @return the integer representation of the size of this queue
     */
    @Override
    public int size() {
        return count;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append("-> Linked Queue: <-");
        LinearNode<T> tempNode = front;
        while (tempNode != null) {
            string.append("\n").append(tempNode.getElement());
            tempNode = tempNode.getNext();
        }

        return string.toString();
    }
}
