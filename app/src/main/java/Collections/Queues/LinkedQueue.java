package Collections.Queues;

import Collections.Lists.Node;
import Collections.Exceptions.EmptyCollectionException;

public class LinkedQueue<T> implements QueueADT<T> {
    private Node<T> front;
    private Node<T> rear;
    private int size;

    public LinkedQueue() {
        this.front = null;
        this.rear = null;
        this.size = 0;
    }

    @Override
    public void enqueue(T element) {
        Node<T> newNode = new Node<>(element);
        if (isEmpty()) {
            front = newNode;
        } else {
            rear.setNext(newNode);
        }
        rear = newNode;
        size++;
    }

    @Override
    public T dequeue() throws EmptyCollectionException {
        if (isEmpty()) throw new EmptyCollectionException();
        T removed = front.getElement();
        front = front.getNext();
        size--;
        return removed;
    }

    @Override
    public T first() throws EmptyCollectionException {
        if (isEmpty()) throw new EmptyCollectionException();
        return front.getElement();
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
        Node<T> current = front;
        if (!isEmpty()) {
            do {
                result += current.getElement() + " ";
            } while ((current = current.getNext()) != null);
        }
        return result + "}";
    }
}
