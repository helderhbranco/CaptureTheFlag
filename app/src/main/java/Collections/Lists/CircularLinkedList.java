package Collections.Lists;

import Collections.Exceptions.EmptyCollectionException;
import Collections.Exceptions.NoSuchElementException;

import java.util.Iterator;

public class CircularLinkedList<T> implements ListADT<T> {
    private Node<T> front;
    private Node<T> rear;
    private int size;

    public CircularLinkedList() {
        this.front = null;
        this.rear = null;
        this.size = 0;
    }

    public void add(T element) {
        Node<T> newNode = new Node<>(element);
        if (isEmpty()) {
            front = newNode;
            rear = newNode;
            front.setNext(rear);
            rear.setNext(front);
        } else {
            newNode.setNext(front);
            rear.setNext(newNode);
            rear = newNode;
        }
        size++;
    }

    @Override
    public T removeFirst() throws EmptyCollectionException {
        if (isEmpty()) throw new EmptyCollectionException();
        T removed = front.getElement();
        front = front.getNext();
        rear.setNext(front);
        size--;
        return removed;
    }

    @Override
    public T removeLast() throws EmptyCollectionException {
        if (isEmpty()) throw new EmptyCollectionException();
        T removed = rear.getElement();
        Node<T> current = front;
        while (current.getNext() != rear) {
            current = current.getNext();
        }
        current.setNext(front);
        rear = current;
        size--;
        return removed;
    }

    @Override
    public T remove(T element) throws EmptyCollectionException, NoSuchElementException {
        if (isEmpty()) throw new EmptyCollectionException();
        Node<T> current = front;
        while (current != rear && !current.getElement().equals(element)) {
            current = current.getNext();
        }
        if (current == rear && !current.getElement().equals(element)) throw new NoSuchElementException();
        if (current == front) return removeFirst();
        if (current == rear) return removeLast();
        T result = current.getElement();
        Node<T> previous = front;
        while (previous.getNext() != current) {
            previous = previous.getNext();
        }
        previous.setNext(current.getNext());
        size--;
        return result;
    }

    @Override
    public T first() throws EmptyCollectionException {
        if (isEmpty()) throw new EmptyCollectionException();
        return front.getElement();
    }

    @Override
    public T last() throws EmptyCollectionException {
        if (isEmpty()) throw new EmptyCollectionException();
        return rear.getElement();
    }

    @Override
    public boolean contains(T target) throws EmptyCollectionException {
        if (isEmpty()) throw new EmptyCollectionException();
        Node<T> current = front;
        while (current != rear && !current.getElement().equals(target)) {
            current = current.getNext();
        }
        return current.getElement().equals(target);
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
    public Iterator<T> iterator() {
        LinkedUnorderedList<T> list = new LinkedUnorderedList<>();
        Node<T> current = front;
        while (current != rear) {
            list.addToRear(current.getElement());
            current = current.getNext();
        }
        list.addToRear(current.getElement());
        return list.iterator();
    }

    @Override
    public String toString() {
        String result = getClass().getSimpleName() + " { ";
        if (!isEmpty()) {
            Node<T> current = front;
            while (current != rear) {
                result += current.getElement() + " ";
                current = current.getNext();
            }
            result += current.getElement() + " ";
        }
        return result + "}";
    }
}
