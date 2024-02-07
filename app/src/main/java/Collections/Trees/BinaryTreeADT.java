package Collections.Trees;

import Collections.Exceptions.ElementNotFoundException;
import Collections.Exceptions.EmptyCollectionException;
import Collections.Exceptions.UnsupportedOperationException;

import java.util.Iterator;

/**
 * BinaryTreeADT defines the interface to a binary tree data structure.
 *
 * @param <T> the generic type of data element in this tree
 */
public interface BinaryTreeADT<T> {

    /**
     * Returns the value of the root element
     *
     * @return T the value of the root element
     */
    T getRootElement() throws EmptyCollectionException;


    /**
     * Returns a reference to the root node
     *
     * @return BinaryTreeNode a reference to the root node
     * @throws EmptyCollectionException if the tree is empty
     * @throws UnsupportedOperationException if the operation is not supported
     */
    BinaryTreeNode<T> getRootNode() throws EmptyCollectionException, UnsupportedOperationException;

    /**
     * Returns true if this binary tree is empty and false otherwise
     *
     * @return true if this binary tree is empty
     */
    boolean isEmpty();

    /**
     * Returns the number of elements in this binary tree
     *
     * @return the integer number of elements in this tree
     */
    int size();

    /**
     * Returns true if the binary tree contains an element that matches the specified element and false otherwise
     *
     * @param targetElement the element being sought in this tree
     * @return true if the element in is this tree
     */
    boolean contains(T targetElement) throws EmptyCollectionException;

    /**
     * Returns a reference to the specified element if it is found in this binary tree
     *
     * @param targetElement the element being sought in this tree
     * @throws EmptyCollectionException if the tree is empty
     * @throws ElementNotFoundException if the specified element is not found
     * @return T a reference to the specified element if it is found in this tree
     */
    T find(T targetElement) throws EmptyCollectionException, ElementNotFoundException;

    /**
     * Returns the string representation of this binary tree
     *
     * @return a string representation of this binary tree
     */
    String toString();

    /**
     * Performs an inorder traversal on this binary tree by calling an inorder method that starts with the root
     *
     * @return an iterator over the elements of this binary tree
     */
    Iterator<T> iteratorInOrder();

    /**
     * Performs a preorder traversal on this binary tree by calling a preorder method that starts with the root
     *
     * @return an iterator over the elements of this binary tree
     */
    Iterator<T> iteratorPreOrder();

    /**
     * Performs a postorder traversal on this binary tree by calling a postorder method that starts with the root
     *
     * @return an iterator over the elements of this binary tree
     */
    Iterator<T> iteratorPostOrder();

    /**
     * Performs a levelorder traversal on this binary tree, using a temporary queue
     *
     * @return an iterator over the elements of this binary tree
     */
    Iterator<T> iteratorLevelOrder();
}
