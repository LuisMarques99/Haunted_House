package MyCollection.BinaryTree;

import java.util.Iterator;

import MyCollection.Exceptions.EmptyCollectionException;
import MyCollection.Queue.LinkedQueue;
import MyCollection.List.ArrayUnorderedList;

public class LinkedBinaryTree<T> implements BinaryTreeADT<T> {
    protected int count;
    protected BinaryTreeNode<T> root;

    /**
     * Create a empty tree
     */
    public LinkedBinaryTree() {
        count = 0;
        root = null;
    }

    /**
     * Creates a binary tree with the specified element as its root.
     *
     * @param element the element that will become the root of the new binary tree
     */
    public LinkedBinaryTree(T element) {
        count = 1;
        root = new BinaryTreeNode<T>(element);
    }

    @SuppressWarnings("unchecked")
    @Override
    public T getRoot() {
        // TODO Auto-generated method stub
        return (T) root;
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
        BinaryTreeNode<T> current = findAgain(targetElement, root);

        if (current == null) {
            return false;
        }
        return true;
    }

    @Override
    public T find(T targetElement) {
        // TODO Auto-generated method stub
        BinaryTreeNode<T> current = findAgain(targetElement, root);

        if (current == null)
            System.out.println("Element not found...");
        return current.element;
    }

    /**
     * Returns a reference to the specified target element if it is found in this binary tree.
     *
     * @param targetElement the element being sought in this tree
     * @param next          the element to begin searching from
     * @return
     */
    private BinaryTreeNode<T> findAgain(T targetElement, BinaryTreeNode<T> next) {
        // TODO Auto-generated method stub
        if (next == null)
            return null;
        if (next.element.equals(targetElement))
            return next;

        BinaryTreeNode<T> temp = findAgain(targetElement, next.left);

        if (temp == null)
            temp = findAgain(targetElement, next.right);
        return temp;
    }

    @Override
    public Iterator<T> iteratorInOrder() {
        // TODO Auto-generated method stub
        ArrayUnorderedList<T> tempList = new ArrayUnorderedList<T>();
        inorder(root, tempList);
        return tempList.iterator();
    }

    /**
     * Performs a recursive inorder traversal.
     *
     * @param node     the node to be used as the root for this traversal
     * @param tempList the temporary list for use in this traversal
     */
    private void inorder(BinaryTreeNode<T> node, ArrayUnorderedList<T> tempList) {
        // TODO Auto-generated method stub
        if (node != null) {
            inorder(node.left, tempList);
            tempList.addToRear(node.element);
            inorder(node.right, tempList);
        }
    }

    @Override
    public Iterator<T> iteratorPreOrder() {
        // TODO Auto-generated method stub
        ArrayUnorderedList<T> tempList = new ArrayUnorderedList<>();
        preorder(root, tempList);
        return tempList.iterator();
    }

    /**
     * Performs a recursive preorder traversal.
     *
     * @param node     the node to be used as the root for this traversal
     * @param tempList the temporary list for use in this traversal
     */
    private void preorder(BinaryTreeNode<T> node, ArrayUnorderedList<T> tempList) {
        // TODO Auto-generated method stub
        if (node != null) {
            tempList.addToRear(node.element);
            preorder(node.left, tempList);
            preorder(node.right, tempList);
        }
    }

    @Override
    public Iterator<T> iteratorPostOrder() {
        // TODO Auto-generated method stub
        ArrayUnorderedList<T> tempList = new ArrayUnorderedList<>();
        postorder(root, tempList);
        return tempList.iterator();
    }

    /**
     * Performs a recursive postorder traversal.
     *
     * @param node     the node to be used as the root for this traversal
     * @param tempList the temporary list for use in this traversal
     */
    private void postorder(BinaryTreeNode<T> node, ArrayUnorderedList<T> tempList) {
        // TODO Auto-generated method stub
        if (node != null) {
            postorder(node.left, tempList);
            postorder(node.right, tempList);
            tempList.addToRear(node.element);
        }
    }

    @Override
    public Iterator<T> iteratorLevelOrder() throws EmptyCollectionException {
        // TODO Auto-generated method stub
        ArrayUnorderedList<T> tempList = new ArrayUnorderedList<>();
        @SuppressWarnings("rawtypes")
        LinkedQueue<BinaryTreeNode> queue = new LinkedQueue<>();

        queue.enqueue(root);
        levelorder(tempList, queue);
        return tempList.iterator();
    }

    /**
     * Performs a recursive levelorder traversal.
     *
     * @param tempList the temporary list for use in this traversal
     * @param queue
     * @throws EmptyCollectionException
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    private void levelorder(ArrayUnorderedList<T> tempList, LinkedQueue<BinaryTreeNode> queue) throws EmptyCollectionException {
        // TODO Auto-generated method stub
        if (!queue.isEmpty()) {
            tempList.addToRear((T) queue.first().element);
            BinaryTreeNode rem = queue.dequeue();
            if (rem.left != null)
                queue.enqueue(rem.left);
            if (rem.right != null)
                queue.enqueue(rem.right);
            if (!queue.isEmpty())
                levelorder(tempList, queue);
        }
    }
}