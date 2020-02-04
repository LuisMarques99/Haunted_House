package MyCollection.Queue;

import MyCollection.Exceptions.EmptyCollectionException;


/**
 * <h3>
 * <strong>Class that represents the structure of an {@link ArrayQueue Array Queue}</strong>
 * </h3>
 *
 * @param <T> Abstract Data Type
 * @author Luis Marques
 */
public class ArrayQueue<T> implements QueueADT<T> {

    /**
     * constant that represents the initial capacity of the array
     */
    private final int DEFAULT_CAPACITY = 100;

    /**
     * int that represents the number of elements in the array
     */
    int count;

    /**
     * int that represents the position of the first element in the array
     */
    private int front;

    /**
     * int that represents the next available position in the array
     */
    private int rear;

    /**
     * array of generic elements to represent the queue
     */
    private T[] queue;

    /**
     * Creates an empty queue using the default capacity
     */
    public ArrayQueue() {
        count = 0;
        front = 0;
        rear = 0;
        queue = (T[]) (new Object[DEFAULT_CAPACITY]);
    }

    /**
     * Creates an empty queue using the initial capacity defined by the user
     *
     * @param initialCapacity int initial capacity
     */
    public ArrayQueue(int initialCapacity) {
        count = 0;
        front = 0;
        rear = 0;
        queue = (T[]) (new Object[initialCapacity]);
    }

    @Override
    public void enqueue(T element) {
        if (size() == queue.length)
            extendCapacity();

        queue[rear] = element;
        rear = (rear + 1) % queue.length;
        count++;
    }

    @Override
    public T dequeue() throws EmptyCollectionException {
        if (isEmpty())
            throw new EmptyCollectionException("Queue");

        T removed = queue[front];
        queue[front] = null;
        front = (front + 1) % queue.length;
        count--;

        return removed;
    }

    @Override
    public T first() throws EmptyCollectionException {
        if (isEmpty())
            throw new EmptyCollectionException("Queue");

        return queue[front];
    }

    @Override
    public boolean isEmpty() {
        if (size() == 0)
            return true;

        return false;
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append("-> Array: <-\n");
        for (int i = front; i < rear; i = (i + 1) % queue.length) {
            string.append("[").append(i).append("] -> ");
            string.append(queue[i]).append("\n");
        }
        return string.toString();
    }

    private void extendCapacity() {

    }
}
