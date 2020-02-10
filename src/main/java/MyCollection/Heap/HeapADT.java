package MyCollection.Heap;

import MyCollection.Exceptions.EmptyCollectionException;
import MyCollection.BinaryTree.BinaryTreeADT;

public interface HeapADT<T> extends BinaryTreeADT<T> {

    /**
     * Adds the specified object to his heap
     *
     * @param obj the element to add to this heap
     */
    public void addElement(T obj);

    /**
     * Removes element with the lowest value from this heap.
     *
     * @return the element with the lowest value from this heap.
     * @throws EmptyCollectionException
     */
    public T removeMin() throws EmptyCollectionException;

    /**
     * Returns a reference to the element with the lowest value in this heap.
     *
     * @return the lowest element
     * @throws EmptyCollectionException
     */
    public T findMin() throws EmptyCollectionException;
}
