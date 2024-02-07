package Collections.Stacks;

import Collections.Exceptions.EmptyCollectionException;

/**
 * StackADT defines the interface to a stack collection.
 *
 * @param <T> the type of elements in this stack
 */
public interface StackADT<T> {

    /**
     * Adds one element to the top of this stack.
     *
     * @param element element to be pushed onto stack
     */
    void push(T element);

    /**
     * Removes and returns the top element from this stack.
     *
     * @return T element removed from the top of the stack
     * @throws EmptyCollectionException if the stack is empty
     */
    T pop() throws EmptyCollectionException;

    /**
     * Returns without removing the top element of this stack.
     *
     * @return T element on top of the stack
     * @throws EmptyCollectionException if the stack is empty
     */
    T peek() throws EmptyCollectionException;

    /**
     * Returns true if this stack contains no elements.
     *
     * @return boolean whether this stack is empty
     */
    boolean isEmpty();

    /**
     * Returns the number of elements in this stack.
     *
     * @return int number of elements in this stack
     */
    int size();

    /**
     * Returns a string representation of this stack.
     *
     * @return String representation of this stack
     */
    @Override
    String toString();
}
