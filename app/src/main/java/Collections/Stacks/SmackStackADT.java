package Collections.Stacks;

import Collections.Exceptions.EmptyCollectionException;

/**
 * SmackStackADT represents a smack stack.
 *
 * @param <T> the type of elements in this stack
 */
public interface SmackStackADT<T> extends StackADT<T> {

    /**
     * Removes and returns the bottom element from this stack.
     *
     * @return T element removed from the bottom of the stack
     * @throws EmptyCollectionException if the stack is empty
     */
    T smack() throws EmptyCollectionException;
}
