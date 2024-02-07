package Collections.Lists;

import Collections.Exceptions.EmptyCollectionException;
import Collections.Exceptions.NoSuchElementException;

/**
 * UnoorderedListADT defines the interface to an unordered list collection.
 *
 * @param <T> the type of elements in this list
 */
public interface UnorderedListADT<T> extends ListADT<T> {

    /**
     * Adds the specified element to the front of this list.
     *
     * @param element the element to be added to the front of this list
     */
    void addToFront(T element);

    /**
     * Adds the specified element to the rear of this list.
     *
     * @param element the element to be added to the rear of this list
     */
    void addToRear(T element);

    /**
     * Adds the specified element after the specified target.
     *
     * @param element the element to be added after the target
     * @param target  the target is the item that the element is added after
     * @throws NoSuchElementException if the target is not found
     */
    void addAfter(T element, T target) throws EmptyCollectionException, NoSuchElementException;

}
