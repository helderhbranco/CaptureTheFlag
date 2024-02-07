package Collections.Lists;

/**
 * Node represents a node in a linked collection.
 *
 * @param <T> the generic type of data element in this node
 */
public class Node<T> {
    private T element;
    private Node<T> next;

    public Node(T element) {
        this.element = element;
        this.next = null;
    }

    public T getElement() {
        return this.element;
    }

    public void setElement(T element) {
        this.element = element;
    }

    public Node<T> getNext() {
        return next;
    }

    public void setNext(Node<T> nextNode) {
        this.next = nextNode;
    }
}
