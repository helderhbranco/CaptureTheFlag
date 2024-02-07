package Collections.Trees;

import Collections.Exceptions.ElementNotFoundException;
import Collections.Exceptions.EmptyCollectionException;
import Collections.Exceptions.NonComparableElementException;

/**
 * BinarySearchTreeADT defines the interface to a binary search tree.
 *
 * @param <T> the generic type of data element in this tree
 */
public interface BinarySearchTreeADT<T> extends BinaryTreeADT<T> {

    /**
     * Adds the specified element to the proper location in this tree.
     *
     * @param element the element to be added to this tree
     */
    void addElement(T element) throws NonComparableElementException;

    /**
     * Removes and returns the specified element from this tree.
     *
     * @param targetElement the element to be removed from this tree
     * @return the element removed from this tree
     * @throws EmptyCollectionException if the tree is empty
     * @throws ElementNotFoundException if the specified element is not in the tree
     */
    T removeElement(T targetElement) throws EmptyCollectionException, ElementNotFoundException;

    /**
     * Removes all occurences of the specified element from this tree.
     *
     * @param targetElement the element that the list will have all instances of it removed
     * @throws EmptyCollectionException if the tree is empty
     * @throws ElementNotFoundException if the specified element is not in the tree
     */
    void removeAllOccurrences(T targetElement) throws EmptyCollectionException, ElementNotFoundException;

    /**
     * Removes and returns the smallest element from this tree.
     *
     * @return the smallest element from this tree.
     * @throws EmptyCollectionException if the tree is empty
     */
    T removeMin() throws EmptyCollectionException;

    /**
     * Removes and returns the largest element from this tree.
     *
     * @return the largest element from this tree
     * @throws EmptyCollectionException if the tree is empty
     */
    T removeMax() throws EmptyCollectionException;

    /**
     * Returns a reference to the smallest element in this tree.
     *
     * @return a reference to the smallest element in this tree
     * @throws EmptyCollectionException if the tree is empty
     */
    T findMin() throws EmptyCollectionException;

    /**
     * Returns a reference to the largest element in this tree.
     *
     * @return a reference to the largest element in this tree
     * @throws EmptyCollectionException if the tree is empty
     */
    T findMax() throws EmptyCollectionException;
}
