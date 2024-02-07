package Collections.Stacks;

import Collections.Lists.Node;
import Collections.Exceptions.EmptyCollectionException;

public class LinkedStack<T> implements StackADT<T> {
    protected Node<T> top;
    protected int size;

    public LinkedStack() {
        this.top = null;
        this.size = 0;
    }

    @Override
    public void push(T element) {
        Node<T> newNode = new Node<>(element);
        newNode.setNext(top);
        top = newNode;
        size++;
    }

    @Override
    public T pop() throws EmptyCollectionException {
        if (isEmpty()) throw new EmptyCollectionException();
        T removed = top.getElement();
        top = top.getNext();
        size--;
        return removed;
    }

    @Override
    public T peek() throws EmptyCollectionException {
        if (isEmpty()) throw new EmptyCollectionException();
        return top.getElement();
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        String result = getClass().getSimpleName() + " { ";
        if (!isEmpty()) {
            Node<T> current = top;
            do {
                result += current.getElement() + " ";
            } while ((current = current.getNext()) != null);
        }
        return result + "}";
    }
}
