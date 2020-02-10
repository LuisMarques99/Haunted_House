package MyCollection.Tree;

import MyCollection.BinaryTree.BinaryTreeNode;
import MyCollection.BinaryTree.LinkedBinaryTree;

public class LinkedBinarySearchTree<T> extends LinkedBinaryTree<T> implements BinarySearchTreeADT<T> {
    /**
     * Creates an empty binary search tree
     */
    public LinkedBinarySearchTree() {
        super();
    }

    /**
     * Creates a binary search with the specified element as its root.
     *
     * @param element the element that will be the root of the new binary search tree
     */
    public LinkedBinarySearchTree(T element) {
        super(element);
    }

    @Override
    public void addElement(T element) {
        // TODO Auto-generated method stub
        BinaryTreeNode<T> temp = new BinaryTreeNode<T>(element);
        @SuppressWarnings("unchecked")
        Comparable<T> comparableElement = (Comparable<T>) element;

        if (isEmpty())
            root = temp;
        else {
            BinaryTreeNode<T> current = root;
            boolean added = false;
            while (!added) {
                if (comparableElement.compareTo(current.getElement()) < 0) {
                    if (current.getLeft() == null) {
                        current.setLeft(temp);
                        added = true;
                    } else {
                        current = current.getLeft();
                    }
                } else {
                    if (current.getRight() == null) {
                        current.setRight(temp);
                        added = true;
                    } else {
                        current = current.getRight();
                    }
                }
            }
        }
        count++;
    }

    /**
     * Returns a reference to a node that will replace the one
     * specified for removal.  In the case where the removed node has
     * two children, the inorder successor is used as its replacement.
     *
     * @param node the node to be removeed
     * @return a reference to the replacing node
     */
    protected BinaryTreeNode<T> replacement(BinaryTreeNode<T> node) {
        BinaryTreeNode<T> result = null;
        if ((node.getLeft() == null) && (node.getRight() == null)) {
            result = null;
        } else if ((node.getLeft() != null) && (node.getRight() == null)) {
            result = node.getLeft();
        } else if ((node.getLeft() == null) && (node.getRight() != null)) {
            result = node.getRight();
        } else {
            BinaryTreeNode<T> current = node.getRight();
            BinaryTreeNode<T> parent = node;
            while (current.getLeft() != null) {
                parent = current;
                current = current.getLeft();
            }
            if (node.getRight() == current) {
                current.setLeft(node.getLeft());
            } else {
                parent.setLeft(current.getRight());
                current.setRight(node.getRight());
                current.setLeft(node.getLeft());
            }
            result = current;
        }
        return result;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public T removeElement(T targetElement) {
        // TODO Auto-generated method stub
        T result = null;
        if (!isEmpty()) {
            if (((Comparable) targetElement).equals(root.getElement())) {
                result = root.getElement();
                root = replacement(root);
                count--;
            } else {
                BinaryTreeNode<T> current, parent = root;
                boolean found = false;
                if (((Comparable) targetElement).compareTo(root.getElement()) < 0)
                    current = root.getLeft();
                else
                    current = root.getRight();

                while (current != null && !found) {
                    if (targetElement.equals(current.getElement())) {
                        found = true;
                        count--;
                        result = current.getElement();

                        if (current == parent.getLeft()) {
                            parent.setLeft(replacement(current));
                        } else {
                            parent.setRight(replacement(current));
                        }
                    } else {
                        parent = current;
                        if (((Comparable) targetElement).compareTo(current.getElement()) < 0)
                            current = current.getLeft();
                        else
                            current = current.getRight();
                    }
                } //while
                if (!found)
                    System.out.println("Element not found...");
            }
        } //end of outer if
        return result;
    }

    @Override
    public void removeAllOccurrences(T targetElement) {
        // TODO Auto-generated method stub
        System.out.println("removeAllOcurrences() not implemented yet.");
    }

    @Override
    public T removeMin() {
        // TODO Auto-generated method stub
        T result = null;
        if (!isEmpty()) {
            if (root.getLeft() == null) {
                result = root.getElement();
                root = root.getRight();
            } else if (root.getLeft() != null) {
                BinaryTreeNode<T> parent = root;
                BinaryTreeNode<T> current = root.getLeft();
                while (current.getLeft() != null) {
                    parent = parent.getLeft();
                    current = current.getLeft();
                }
                result = current.getElement();
                if (current.getRight() != null) {
                    parent.setLeft(current.getRight());
                    current.setRight(null);
                } else {
                    current = null;
                    parent.setLeft(null);
                }
            }
            count--;
        } else {
            System.out.println("EmptyCollectionException");
        }
        return result;
    }

    @Override
    public T removeMax() {
        // TODO Auto-generated method stub
        T result = null;

        if (!isEmpty()) {
            if (root.getRight() == null) {
                result = root.getElement();
                root = root.getLeft();
            } else if (root.getRight() != null) {
                BinaryTreeNode<T> parent = root;
                BinaryTreeNode<T> current = root.getRight();
                while (current.getRight() != null) {
                    parent = parent.getRight();
                    current = current.getRight();
                }
                result = current.getElement();
                if (current.getLeft() != null) {
                    parent.setRight(current.getLeft());
                    current.setLeft(null);
                } else {
                    current = null;
                    parent.setRight(null);
                }
            }
            count--;
        } else {
            System.out.println("Tree is empty...");
        }
        return result;
    }

    @Override
    public T findMin() {
        // TODO Auto-generated method stub
        T result = null;
        if (!isEmpty()) {
            if (root.getLeft() == null) {
                result = root.getElement();
            } else if (root.getLeft() != null) {
                BinaryTreeNode<T> current = root.getLeft();
                while (current.getLeft() != null) {
                    current = current.getLeft();
                }
                result = current.getElement();
            }
        } else {
            System.out.println("Tree is empty...");
        }
        return result;
    }

    @Override
    public T findMax() {
        // TODO Auto-generated method stub
        T result = null;
        if (!isEmpty()) {
            if (root.getRight() == null) {
                result = root.getElement();
            } else if (root.getRight() != null) {
                BinaryTreeNode<T> current = root.getRight();
                while (current.getRight() != null) {
                    current = current.getRight();
                }
                result = current.getElement();
            }
        } else {
            System.out.println("Tree is empty...");
        }
        return result;
    }
}