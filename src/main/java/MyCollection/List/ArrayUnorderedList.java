package MyCollection.List;

import MyCollection.Exceptions.ElementNotFoundException;

import java.util.Iterator;

/**
 * <h3>
 * <strong>Class that represents the structure of an {@link ArrayUnorderedList Unordered Array List}</strong>
 * </h3>
 *
 * @param <T> Abstract Data Type
 * @author Luis Marques
 */
public class ArrayUnorderedList<T> extends ArrayList<T> implements UnorderedListADT<T> {

    /**
     * Creates an empty list using the default capacity
     */
    public ArrayUnorderedList() {
        super();
    }

    /**
     * Creates an empty list using the initial capacity defined by the user
     *
     * @param initialCapacity int initial capacity
     */
    public ArrayUnorderedList(int initialCapacity) {
        super(initialCapacity);
    }

    /**
     * Adds the specific element to the front of this list.
     *
     * @param element the element to be added to this list
     */
    @Override
    public void addToFront(T element) {
        if (size() == list.length) {
            extendCapacity();
        }

        for (int i = rear; i > 0; i--) {
            list[i] = list[i - 1];
        }
        list[front] = element;
        rear++;
        count++;
        modCount++;
    }

    /**
     * Adds the specific element to the rear of this list.
     *
     * @param element the element to be added to this list
     */
    @Override
    public void addToRear(T element) {
        if (size() == list.length) {
            extendCapacity();
        }

        list[rear] = element;
        rear++;
        count++;
        modCount++;
    }

    /**
     * Adds the specific element after a particular element already in this list.
     *
     * @param element the element to be added to this list
     * @param target  the element to be referenced to add the specific element
     */
    @Override
    public void addAfter(T element, T target) throws ElementNotFoundException {
        if (size() == list.length) {
            extendCapacity();
        }

        if (!contains(target)) {
            throw new ElementNotFoundException("List");
        }

        int index = getElementIndex(target) + 1;

        for (int i = rear; i > index; i--) {
            list[i] = list[i - 1];
        }
        list[index] = element;
        rear++;
        count++;
        modCount++;
    }
}
