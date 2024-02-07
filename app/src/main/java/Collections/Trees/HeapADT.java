package Collections.Trees;

import Collections.Exceptions.EmptyCollectionException;

/**
 * HeapADT defines the interface to a heap collection.
 *
 * @param <T> the type of element in the heap
 */
public interface HeapADT<T> extends BinaryTreeADT<T> {

    /**
     * Adds the specified element to this heap in the appropriate position according to its key value
     *
     * @param element the element to be added to this heap
     */
    void addElement(T element);

    /**
     * Removes element with the lowest value from this heap
     *
     * @return the element with the lowest value from this heap
     */
    T removeMin() throws EmptyCollectionException;

    /**
     * Returns a reference to the element with the lowest value in this heap
     *
     * @return a reference to the element with the lowest value in this heap
     */
    T findMin() throws EmptyCollectionException;
}
