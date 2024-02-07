package Collections.HashTables;

import Collections.Exceptions.EmptyCollectionException;
import Collections.Exceptions.NoSuchElementException;

import java.util.Iterator;

/**
 * SetADT defines the interface to a set collection.
 *
 * @param <T> the generic type of element in this collection
 */
public interface SetADT<T> {

    /**
     * Adds the specified element to this set.
     *
     * @param element the element to be added to this set
     * @return boolean true if the element was added to this set
     */
    boolean add(T element) throws IllegalArgumentException;

    /**
     * Removes and returns the specified element from this set.
     *
     * @param element the element to be removed from this set
     * @return T the element removed from this set
     */
    T remove(T element) throws IllegalArgumentException, NoSuchElementException, EmptyCollectionException;

    /**
     * Returns a reference to the specified element if it is in this set.
     *
     * @param element the element to be found in this set
     * @return T a reference to the specified element if it is in this set
     */
    T find(T element) throws IllegalArgumentException, EmptyCollectionException, NoSuchElementException;

    /**
     * Returns true if this set contains the specified target element.
     *
     * @param target the target that is being sought in this set
     * @return boolean true if the set contains the target element
     */
    boolean contains(T target) throws IllegalArgumentException;

    /**
     * Returns true if this set contains no elements.
     *
     * @return boolean true if this set contains no elements
     */
    boolean isEmpty();

    /**
     * Returns the number of elements in this set.
     *
     * @return int the integer representation of size
     */
    int size();

    /**
     * Returns an iterator for the elements currently in this set.
     *
     * @return Iterator the iterator over this set
     */
    Iterator<T> iterator();

    /**
     * Returns a string representation of this set.
     *
     * @return String the string representation of this set
     */
    @Override
    String toString();
}
