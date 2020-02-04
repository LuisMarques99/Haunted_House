package MyCollection.List;

import MyCollection.Exceptions.EmptyCollectionException;
import MyCollection.Exceptions.ElementNotFoundException;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * <h3>
 * <strong>Abstract class that represents the structure of an {@link ArrayList Array List}</strong>
 * </h3>
 *
 * @param <T> Abstract Data Type
 * @author Luis Marques
 */
public abstract class ArrayList<T> implements ListADT<T> {

    /**
     * constant that represents the initial capacity of the array
     */
    protected static final int DEFAULT_CAPACITY = 100;

    /**
     * int that represents the number of elements in the array
     */
    protected int count;

    /**
     * int that represents the number of modifications in the array
     */
    protected int modCount;

    /**
     * int that represents the position of the first element in the array
     */
    protected int front;

    /**
     * int that represents the position of the first element in the array
     */
    protected int rear;

    /**
     * array of generic elements to represent the list
     */
    protected T[] list;

    /**
     * Creates an empty list using the default capacity
     */
    public ArrayList() {
        count = 0;
        modCount = 0;
        front = 0;
        rear = 0;
        list = (T[]) (new Object[DEFAULT_CAPACITY]);
    }

    /**
     * Creates an empty list using the initial capacity defined by the user
     *
     * @param initialCapacity int initial capacity
     */
    public ArrayList(int initialCapacity) {
        count = 0;
        modCount = 0;
        front = 0;
        rear = 0;
        list = (T[]) (new Object[initialCapacity]);
    }

    /**
     * Removes the first element from this list.
     *
     * @return the first element of this list
     */
    @Override
    public T removeFirst() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("List");
        }

        T removed = list[front];
        for (int i = 0; i < size(); i++) {
            list[i] = list[i + 1];
        }
        list[rear - 1] = null;
        rear--;
        count--;
        modCount++;

        return removed;
    }

    /**
     * Removes and returns the last element from this list.
     *
     * @return the last element of this list
     */
    @Override
    public T removeLast() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("List");
        }

        T removed = list[rear - 1];
        list[rear - 1] = null;
        rear--;
        count--;
        modCount++;

        return removed;
    }

    /**
     * Removes and returns the specific element of this list.
     *
     * @param element the element to be removed from this list
     * @return the element removed from this list
     */
    @Override
    public T remove(T element) throws EmptyCollectionException, ElementNotFoundException {
        if (isEmpty()) {
            throw new EmptyCollectionException("List");
        }

        if (element.equals(list[front])) {
            return removeFirst();
        }
        if (element.equals(list[rear - 1])) {
            return removeLast();
        }

        boolean found = false;
        int current = front;

        //Find the element
        while (list[current] != null && !found) {
            if (element.equals(list[current])) {
                found = true;
            }
            current++;
        }
        if (!found) {
            throw new ElementNotFoundException("List");
        }

        //Remove the element
        for (int i = current; i < size(); i++) {
            list[i] = list[i + 1];
        }
        list[rear - 1] = null;
        rear--;
        count--;
        modCount++;

        return list[current];
    }

    /**
     * Returns a reference to the first element of this list.
     *
     * @return a reference to the first element of this list
     */
    @Override
    public T first() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("List");
        }

        return list[front];
    }

    /**
     * Returns a reference of the last element of this list.
     *
     * @return a reference to the first element of this list
     */
    @Override
    public T last() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("List");
        }

        return list[rear - 1];
    }

    /**
     * Returns true if this list contains the specific target element.
     *
     * @param target the target that is being sought in the list
     * @return true if this list contains this element
     */
    @Override
    public boolean contains(T target) {
        for (T element : list) {
            if (target.equals(element)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns true if this list contains no elements.
     *
     * @return true if this list contains no elements
     */
    @Override
    public boolean isEmpty() {
        return (size() == 0);
    }

    /**
     * Returns the number of elements in this list.
     *
     * @return the integer representation of number of elements in this list
     */
    @Override
    public int size() {
        return count;
    }

    /**
     * Returns an iterator for the elements in this list.
     *
     * @return an iterator over the elements in this list
     */
    @Override
    public Iterator<T> iterator() {
        return new BasicIterator();
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append("[");
        Iterator<T> iterator = this.iterator();
        while (iterator.hasNext()) {
            string.append(" ").append(iterator.next());
        }
        string.append(" ]");
        return string.toString();
    }

    /**
     * Extends the capacity of the stack with more 100(DEFAULT_CAPACITY) positions
     */
    protected void extendCapacity() {
        T[] newList = (T[]) (new Object[list.length + DEFAULT_CAPACITY]);
        for (int i = 0; i < list.length; i++) {
            newList[i] = list[i];
        }
        list = newList;
    }

    /**
     * Returns the index of the element in the array or returns -1 if the element is not in the array
     *
     * @param element element to get the index from
     * @return int index of the element or -1 if the element is not in the array
     */
    protected int getElementIndex(T element) {
        int index = -1;
        for (int i = 0; i < size(); i++) {
            if (element.equals(list[i])) {
                index = i;
                break;
            }
        }
        return index;
    }

    /**
     * Private class that represents the structure of a {@link BasicIterator Basic Iterator}
     */
    private class BasicIterator implements Iterator {
        /**
         * int that represents the number expected modifications to make this iterator usable
         */
        private int expectedModCount;

        /**
         * int that represents the position of the Iterator
         */
        private int cursor;

        /**
         * Creates an iterator for the {@link ArrayList Array}
         */
        public BasicIterator() {
            expectedModCount = modCount;
            cursor = 0;
        }

        /**
         * Returns {@code true} if the iteration has more elements.
         * (In other words, returns {@code true} if {@link #next} would
         * return an element rather than throwing an exception.)
         *
         * @return {@code true} if the iteration has more elements
         */
        @Override
        public boolean hasNext() {
            return (cursor != rear);
        }

        /**
         * Returns the next element in the iteration.
         *
         * @return the next element in the iteration
         * @throws NoSuchElementException if the iteration has no more elements
         */
        @Override
        public T next() throws NoSuchElementException {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            T element = list[cursor];
            cursor++;

            return element;
        }

        /**
         * Removes from the underlying collection the last element returned
         * by this iterator (optional operation).  This method can be called
         * only once per call to {@link #next}.  The behavior of an iterator
         * is unspecified if the underlying collection is modified while the
         * iteration is in progress in any way other than by calling this
         * method.
         *
         * @throws UnsupportedOperationException if the {@code remove}
         *                                       operation is not supported by this iterator
         * @throws IllegalStateException         if the {@code next} method has not
         *                                       yet been called, or the {@code remove} method has already
         *                                       been called after the last call to the {@code next}
         *                                       method
         * @implSpec The default implementation throws an instance of
         * {@link UnsupportedOperationException} and performs no other action.
         */
        public void remove() {
        }
    }
}
