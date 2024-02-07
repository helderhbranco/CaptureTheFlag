package Collections.Lists;

/**
 * DoublyNode represents a node in a doubly linked collection.
 *
 * @param <T> the generic type of data element in this node
 */
public class DoublyNode<T> {
    private T element;
    private DoublyNode<T> next;
    private DoublyNode<T> previous;

    /**
     * Creates an empty node.
     */
    public DoublyNode() {
        this.element = null;
        this.next = null;
        this.previous = null;
    }

    /**
     * Creates a node storing the specified element.
     *
     * @param element the element to be stored within the new node
     */
    public DoublyNode(T element) {
        this.element = element;
        this.next = null;
        this.previous = null;
    }

    /**
     * Creates a node storing the specified element, previous and next node.
     *
     * @param element the element to be stored within the new node
     * @param previous the previous node
     * @param next the next node
     */
    public DoublyNode(T element, DoublyNode<T> previous, DoublyNode<T> next) {
        this.element = element;
        this.previous = previous;
        this.next = next;
    }

    /**
     * Returns the element stored in this node.
     *
     * @return T the element stored in this node
     */
    public T getElement() {
        return this.element;
    }

    /**
     * Sets the element stored in this node.
     *
     * @param node the element to be stored in this node
     */
    public void setElement(T node) {
        this.element = node;
    }

    /**
     * Returns the previous node in this node.
     *
     * @return DoublyNode the previous node in this node
     */
    public DoublyNode<T> getPrevious() {
        return this.previous;
    }

    /**
     * Sets the previous node in this node.
     *
     * @param previous the previous node in this node
     */
    public void setPrevious(DoublyNode<T> previous) {
        this.previous = previous;
    }

    /**
     * Returns the next node in this node.
     *
     * @return DoublyNode the next node in this node
     */
    public DoublyNode<T> getNext() {
        return this.next;
    }

    /**
     * Sets the next node in this node.
     *
     * @param next the next node in this node
     */
    public void setNext(DoublyNode<T> next) {
        this.next = next;
    }
}
