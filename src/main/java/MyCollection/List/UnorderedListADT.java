package MyCollection.List;

import MyCollection.Exceptions.ElementNotFoundException;

/**
 * <h3>
 * <strong>Interface to establish the contract of a {@link UnorderedListADT Abstract Data Type Unordered List}</strong>
 * </h3>
 *
 * @param <T> Abstract Data Type
 * @author Luis Marques
 */
public interface UnorderedListADT<T> extends ListADT<T> {
    /**
     * Adds the specific element to the front of this list.
     *
     * @param element the element to be added to this list
     */
    public void addToFront(T element);

    /**
     * Adds the specific element to the rear of this list.
     *
     * @param element the element to be added to this list
     */
    public void addToRear(T element);

    /**
     * Adds the specific element after a particular element already in this list.
     *
     * @param element the element to be added to this list
     * @param target  the element to be referenced to add the specific element
     */
    public void addAfter(T element, T target) throws ElementNotFoundException;
}
