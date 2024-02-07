package Collections.Lists;

import Collections.Exceptions.EmptyCollectionException;

public class LinkedListSentinel<T> {
    private final Node<T> head;
    private Node<T> tail;
    private int size;

    public LinkedListSentinel() {
        this.tail = new Node<>(null);
        this.head = tail;
        this.size = 0;
    }

    public void add(T element) {
        Node<T> newNode = new Node<>(element);
        tail.setNext(newNode);
        tail = newNode;
        size++;
    }

    public void remove(int index) throws EmptyCollectionException, IndexOutOfBoundsException {
        if (isEmpty()) throw new EmptyCollectionException();
        if (index < 0 || index >= size()) throw new IndexOutOfBoundsException();
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        current.setNext(current.getNext().getNext());
        size--;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return size;
    }

    public T[] toArray() {
        T[] array = (T[]) new Object[size()];
        Node<T> current = head;
        for (int i = 0; i < size(); i++) {
            current = current.getNext();
            array[i] = current.getElement();
        }
        return array;
    }

    public String toString() {
        String result = getClass().getSimpleName() + " { ";
        if (!isEmpty()) {
            Node<T> current = head.getNext();
            do {
                result += current.getElement() + " ";
            } while ((current = current.getNext()) != null);
        }
        return result + "}";
    }
}
