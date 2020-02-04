package MyCollection.List;

import MyCollection.Exceptions.ElementNotComparableException;

/**
 * <h3>
 * <strong>Interface to establish the contract of a {@link OrderedListADT Abstract Data Type Ordered List}</strong>
 * </h3>
 *
 * @param <T> Abstract Data Type
 * @author Luis Marques
 */
public interface OrderedListADT<T> extends ListADT<T> {
    /**
     * Adds the specific element to this list at the proper location.
     *
     * @param element the element to be added to this list
     */
    public void add(T element) throws ElementNotComparableException;
}
