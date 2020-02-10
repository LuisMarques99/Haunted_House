package MyCollection.Tree;

import MyCollection.BinaryTree.ArrayBinaryTree;

/**
 * Toda esta classe necessita de revis�o! Nao se encontra tudo implementado e existem falhas nas que se encontram implementadas.
 *
 * @author Red
 */

public class ArrayBinarySearchTree<T> extends ArrayBinaryTree<T> implements BinarySearchTreeADT<T> {
    protected int height;
    protected int maxIndex;

    /**
     * Creates an empty binary search tree
     */
    public ArrayBinarySearchTree() {
        super();
        height = 0;
        maxIndex = -1;
    }

    /**
     * Creates a binary search with the specified element as its root
     *
     * @param element the element that will become the root of the new tree
     */
    public ArrayBinarySearchTree(T element) {
        super();
        height = 1;
        maxIndex = 0;
    }

    @Override
    public void addElement(T element) {
        // TODO Auto-generated method stub
        @SuppressWarnings("unchecked")
        Comparable<T> tempelement = (Comparable<T>) element;
        if (isEmpty()) {
            tree[0] = element;
            maxIndex = 0;
            count++;
        } else {
            {
                boolean added = false;
                int currentIndex = 0;
                while (!added) {
                    if (tempelement.compareTo((tree[currentIndex])) < 0) {
                        /**
                         * go left
                         */
                        if (tree[currentIndex * 2 + 1] == null) {
                            tree[currentIndex * 2 + 1] = element;
                            added = true;
                            if (currentIndex * 2 + 1 > maxIndex) {
                                maxIndex = currentIndex * 2 + 1;
                            }
                        } else {
                            currentIndex = currentIndex * 2 + 1;
                        }
                    } else {
                        /**
                         * go right
                         */
                        if (tree[currentIndex * 2 + 2] == null) {
                            tree[currentIndex * 2 + 2] = element;
                            added = true;
                            if (currentIndex * 2 + 2 > maxIndex) {
                                maxIndex = currentIndex * 2 + 2;
                            }
                        } else {
                            currentIndex = currentIndex * 2 + 2;
                        }
                    }
                }
            }
            height = (int) (Math.log(maxIndex + 1) / Math.log(2)) + 1;
            count++;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public T removeElement(T targetElement) {
        // TODO Auto-generated method stub
        boolean found = false;
        int pos;
        T rem = null;
        if (isEmpty()) {
            System.out.println("Tree is empty");
        } else {
            for (pos = 0; pos <= maxIndex && found == false; pos++) {
                if (((Comparable<T>) targetElement).equals(tree[pos])) {
                    found = true;
                    rem = tree[pos];
                    count--;
                }
            }
            maxIndex--;
            if (found) {
                for (int i = pos; i < maxIndex; i++) {
                    tree[i] = tree[i + 1];
                }
            }
        }
        return rem;
    }

    @Override
    public void removeAllOccurrences(T targetElement) {
        // TODO Auto-generated method stub
        removeElement(targetElement);
        try {
            while (!this.isEmpty()) {
                removeElement(targetElement);
            }
        } catch (Exception ex) {
            System.out.println("Element not found");
        }
    }

    @Override
    public T removeMin() {
        // TODO Auto-generated method stub
        T rem = tree[0];
        int currentIndex = 0, pos = 0;
        if (!isEmpty()) {
            if (tree[currentIndex * 2 + 1] == null) {
                rem = tree[0];
                pos = currentIndex;
            } else {
                while (tree[currentIndex * 2 + 1] != null) {
                    currentIndex++;
                }
                rem = tree[currentIndex * 2 + 1];
                pos = currentIndex;
            }
            maxIndex--;
            for (int i = pos; i < maxIndex; i++) {
                tree[i] = tree[i + 1];
            }
        }
        return (T) rem;
    }

    @Override
    public T removeMax() {
        // TODO Auto-generated method stub
        System.out.println("Not implemented yet.");
        return null;
    }

    @Override
    public T findMin() {
        // TODO Auto-generated method stub
        T min = tree[0];
        int currentIndex = 0;
        if (!isEmpty()) {
            if (tree[currentIndex * 2 + 1] == null) {
                min = tree[0];
            } else {
                while (tree[currentIndex * 2 + 1] != null) {
                    currentIndex++;
                }
                min = tree[currentIndex * 2 + 1];
            }
        }
        return (T) min;
    }

    @Override
    public T findMax() {
        // TODO Auto-generated method stub
        System.out.println("Not implemented yet.");
        /**
         T max = tree[0];
         int currentIndex = 0;
         if(isEmpty()) {
         System.out.println("Tree is empty.");
         }
         else {
         if(tree[(currentIndex * 2) + 2] == null) {
         max = tree[0];
         }
         else {
         while(tree[(currentIndex * 2) + 2] != null) {
         currentIndex++;
         }
         max = tree[(currentIndex * 2) + 2];
         }
         }
         return (T) max;
         */
        return null;
    }
}
