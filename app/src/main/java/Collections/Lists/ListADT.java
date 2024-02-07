package Collections.Lists;

import Collections.Exceptions.EmptyCollectionException;
import Collections.Exceptions.NoSuchElementException;

import java.util.Iterator;

/**
 * ListADT defines the interface to a general list collection.
 *
 * @param <T> the type of elements in this list
 */
public interface ListADT<T> extends Iterable<T> {



    /**
     * Removes and returns the first element from this list.
     *
     * @return T the first element from this list
     * @throws EmptyCollectionException if the list is empty
     */
    T removeFirst() throws EmptyCollectionException;

    /**
     * Removes and returns the last element from this list.
     *
     * @return T the last element from this list
     * @throws EmptyCollectionException if the list is empty
     */
    T removeLast() throws EmptyCollectionException;

    /**
     * Removes and returns the specified element.
     *
     * @param element the element to be removed and returned from the list
     * @return T the removed element
     * @throws EmptyCollectionException if the list is empty
     * @throws NoSuchElementException   if the target element is not found
     */
    T remove(T element) throws EmptyCollectionException, NoSuchElementException;

    /**
     * Returns a reference to the first element in this list.
     *
     * @return T a reference to the first element in this list
     * @throws EmptyCollectionException if the list is empty
     */
    T first() throws EmptyCollectionException;

    /**
     * Returns a reference to the last element in this list.
     *
     * @return T a reference to the last element in this list
     * @throws EmptyCollectionException if the list is empty
     */
    T last() throws EmptyCollectionException;

    /**
     * Returns true if this list contains the specified target element.
     *
     * @param target the target that is being sought in the list
     * @return true if the list contains this element
     * @throws EmptyCollectionException if the list is empty
     */
    boolean contains(T target) throws EmptyCollectionException;

    /**
     * Returns true if this list contains no elements.
     *
     * @return true if this list contains no elements
     */
    boolean isEmpty();

    /**
     * Returns the number of elements in this list.
     *
     * @return the integer representation of number of elements in this list
     */
    int size();

    /**
     * Returns an iterator for the elements in this list.
     *
     * @return Iterator an iterator over the elements in this list
     */
    Iterator<T> iterator();

    /**
     * Returns a string representation of this list.
     *
     * @return the string representation of this list
     */
    @Override
    String toString();
}
