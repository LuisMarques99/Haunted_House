package MyCollection.List;

import MyCollection.Exceptions.ElementNotComparableException;

/**
 * <h3>
 * <strong>Class that represents the structure of an {@link ArrayOrderedList Ordered Array List}</strong>
 * </h3>
 *
 * @param <T> Abstract Data type
 * @author Luis Marques
 */
public class ArrayOrderedList<T> extends ArrayList<T> implements OrderedListADT<T> {

    /**
     * Creates an empty list using the default capacity
     */
    public ArrayOrderedList() {
        super();
    }

    /**
     * Creates an empty list using the initial capacity defined by the user
     *
     * @param initialCapacity int initial capacity
     */
    public ArrayOrderedList(int initialCapacity) {
        super(initialCapacity);
    }

    /**
     * Adds the specific element to this list at the proper location.
     *
     * @param element the element to be added to this list
     */
    @Override
    public void add(T element) throws ElementNotComparableException {
        if (!(element instanceof Comparable)) {
            throw new ElementNotComparableException();
        }

        if (size() == list.length) {
            extendCapacity();
        }

        boolean added = false;
        Comparable elemComp = (Comparable) element;

        for (int i = 0; i < size(); i++) {
            Comparable listComp = (Comparable) list[i];
            if ((elemComp.compareTo(listComp) < 0)) {
                for (int j = rear; j > i; j--) {
                    list[j] = list[j - 1];
                }
                list[i] = element;
                rear++;
                count++;
                modCount++;
                added = true;
                break;
            }
        }

        if (!added) {
            list[rear] = element;
            rear++;
            count++;
            modCount++;
            added = true;
        }
    }
}
