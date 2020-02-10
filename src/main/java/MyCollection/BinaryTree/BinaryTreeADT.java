package MyCollection.BinaryTree;

import MyCollection.Exceptions.EmptyCollectionException;

import java.util.Iterator;

public interface BinaryTreeADT<T> {

    /**
     * Returns reference to root element
     *
     * @returns a reference to the root
     */
    public T getRoot();

    /**
     * Returns true if binary tree is empty and false otherwise
     *
     * @return true if != empty
     */
    public boolean isEmpty();

    /**
     * Returns the size of the tree
     *
     * @return number of elements in tree
     */
    public int size();

    /**
     * Returns true if tree contains the element being searched for
     *
     * @param targetElement the element being searched for
     * @return true if contains element
     */
    public boolean contains(T targetElement);

    /**
     * Returns reference to the specified element if it is found in tree
     *
     * @param targetElement element being searched for
     * @return reference to specified element
     */
    public T find(T targetElement);

    /**
     * Returns string representation of tree
     *
     * @return string
     */
    public String toString();

    /**
     * Performs an inorder traversal on this binary tree by calling an overloaded, recursive inorder method that starts with the root.
     *
     * @return an iterator over the elements of this binary tree
     */
    public Iterator<T> iteratorInOrder();

    /**
     * Performs a preorder traversal on this binary tree by calling an overloaded, recursive preorder method that starts with the root.
     *
     * @return an iterator over the elements of this binary tree
     */
    public Iterator<T> iteratorPreOrder();

    /**
     * Performs a postorder traversal on this binary tree by calling an overloaded, recursive postorder method that starts with the root.
     *
     * @return an iterator over the elements of this binary tree
     */
    public Iterator<T> iteratorPostOrder();

    /**
     * Performs a levelorder traversal on the binary tree, using a queue.
     *
     * @return an iterator over the elements of this binary tree
     */
    public Iterator<T> iteratorLevelOrder() throws EmptyCollectionException;
}	
