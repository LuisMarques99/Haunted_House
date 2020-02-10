package MyCollection.BinaryTree;

import java.util.Iterator;

import MyCollection.Queue.LinkedQueue;
import MyCollection.List.ArrayUnorderedList;

public class ArrayBinaryTree<T> implements BinaryTreeADT<T> {
    protected int count;
    protected T[] tree;
    private final int CAPACITY = 50;

    /**
     * Creates an empty binary tree.
     */
    @SuppressWarnings("unchecked")
    public ArrayBinaryTree() {
        count = 0;
        tree = (T[]) new Object[CAPACITY];
    }

    /**
     * Creates a binary tree with the specified element as its root.
     *
     * @param element the element which will become the root of the new tree
     */
    @SuppressWarnings("unchecked")
    public ArrayBinaryTree(T element) {
        count = 1;
        tree = (T[]) new Object[CAPACITY];
        tree[0] = element;
    }

    @Override
    public T getRoot() {
        // TODO Auto-generated method stub
        return tree[0];
    }

    @Override
    public boolean isEmpty() {
        // TODO Auto-generated method stub
        if (count == 0)
            return true;
        else
            return false;
    }

    @Override
    public int size() {
        // TODO Auto-generated method stub
        return count;
    }

    @Override
    public boolean contains(T targetElement) {
        // TODO Auto-generated method stub
        for (int c = 0; c < tree.length; c++) {
            if (targetElement.equals(tree[c])) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns a reference to the specified target element if it is
     * found in this binary tree.  Throws a NoSuchElementException if
     * the specified target element is not found in the binary tree.
     *
     * @param targetElement the element being sought in the tree
     * @return true if the element is in the tree
     * @throws ElementNotFoundException if an element not found exception occurs
     */
    @Override
    public T find(T targetElement) {
        // TODO Auto-generated method stub
        T temp = null;
        boolean found = false;

        for (int c = 0; c < count && !found; c++)
            if (targetElement.equals(tree[c])) {
                found = true;
                temp = tree[c];
            }
        if (!found)
            System.out.println("Element not found...");
        return temp;
    }

    /**
     * Performs an inorder traversal on this binary tree by
     * calling an overloaded, recursive inorder method
     * that starts with the root.
     *
     * @return an iterator over the binary tree
     */
    @Override
    public Iterator<T> iteratorInOrder() {
        // TODO Auto-generated method stub
        ArrayUnorderedList<T> tempList = new ArrayUnorderedList<>();
        inorder(0, tempList);
        return tempList.iterator();
    }

    /**
     * Performs a recursive inorder traversal.
     *
     * @param node     the node used in the traversal
     * @param tempList the temporary list used in the traversal
     */
    private void inorder(int node, ArrayUnorderedList<T> tempList) {
        // TODO Auto-generated method stub
        if (node < tree.length)
            if (tree[node] != null) {
                inorder(node * 2 + 1, tempList);
                tempList.addToRear(tree[node]);
                inorder((node + 1) * 2, tempList);
            }
    }

    /**
     * Performs an preorder traversal on this binary tree by
     * calling an overloaded, recursive preorder method
     * that starts with the root.
     *
     * @return an iterator over the binary tree
     */
    @Override
    public Iterator<T> iteratorPreOrder() {
        // TODO Auto-generated method stub
        ArrayUnorderedList<T> tempList = new ArrayUnorderedList<>();
        preorder(0, tempList);
        return tempList.iterator();
    }

    /**
     * Performs a recursive preorder traversal.
     *
     * @param node     the node used in the traversal
     * @param tempList the temporary list used in the traversal
     */
    private void preorder(int node, ArrayUnorderedList<T> tempList) {
        // TODO Auto-generated method stub
        if (node < tree.length)
            if (tree[node] != null) {
                tempList.addToRear(tree[node]);
                preorder(node * 2 + 1, tempList);
                preorder((node + 1) * 2, tempList);
            }
    }

    /**
     * Performs an postorder traversal on this binary tree by
     * calling an overloaded, recursive portorder method
     * that starts with the root.
     *
     * @return an iterator over the binary tree
     */
    @Override
    public Iterator<T> iteratorPostOrder() {
        // TODO Auto-generated method stub
        ArrayUnorderedList<T> tempList = new ArrayUnorderedList<>();
        postorder(0, tempList);
        return tempList.iterator();
    }

    /**
     * Performs a recursive postorder traversal.
     *
     * @param node     the node used in the traversal
     * @param tempList the temporary list used in the traversal
     */
    private void postorder(int node, ArrayUnorderedList<T> tempList) {
        // TODO Auto-generated method stub
        if (node < tree.length)
            if (tree[node] != null) {
                postorder(node * 2 + 1, tempList);
                postorder((node + 1) * 2, tempList);
                tempList.addToRear(tree[node]);
            }
    }

    /**
     * Performs an levelorder traversal on this binary tree by
     * calling an overloaded, recursive levelorder method
     * that starts with the root.
     *
     * @return an iterator over the binary tree
     */
    @Override
    public Iterator<T> iteratorLevelOrder() {
        // TODO Auto-generated method stub
        ArrayUnorderedList<T> tempList = new ArrayUnorderedList<>();
        LinkedQueue<T> queue = new LinkedQueue<>();
        int node = 0;
        queue.enqueue(tree[node]);
        levelorder(0, tempList);

        return tempList.iterator();
    }

    /**
     * Performs a recursive levelorder traversal.
     *
     * @param node     the node used in the traversal
     * @param tempList the temporary list used in the traversal
     */
    private void levelorder(int node, ArrayUnorderedList<T> tempList) {
        // TODO Auto-generated method stub
        System.out.println("levelorder() not implemented yet.");
    }

}
