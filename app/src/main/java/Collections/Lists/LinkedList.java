package Collections.Lists;

import Collections.Exceptions.EmptyCollectionException;
import Collections.Exceptions.NoSuchElementException;

import java.util.Iterator;

public abstract class LinkedList<T> implements ListADT<T>, Iterable<T> {
    protected Node<T> head, tail;
    protected int size, modCount;

    public LinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    @Override
    public T removeFirst() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException();
        }
        T removed = head.getElement();
        head = head.getNext();
        size--;
        modCount++;
        return removed;
    }

    @Override
    public T removeLast() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException();
        }
        T removed = tail.getElement();
        Node<T> current = head;
        while (current.getNext() != tail) {
            current = current.getNext();
        }
        current.setNext(null);
        tail = current;
        size--;
        modCount++;
        return removed;
    }

    public T remove(T element) throws EmptyCollectionException, NoSuchElementException {
        if (isEmpty()) {
            throw new EmptyCollectionException();
        }
        if (!contains(element)) {
            throw new NoSuchElementException();
        }
        T removed;
        Node<T> current = head;
        if (current.getElement().equals(element)) {
            removed = removeFirst();
        } else if (tail.getElement().equals(element)) {
            removed = removeLast();
        } else {
            while (!current.getNext().getElement().equals(element)) {
                current = current.getNext();
            }
            removed = current.getNext().getElement();
            current.setNext(current.getNext().getNext());
            size--;
            modCount++;
        }
        return removed;
    }

    @Override
    public T first() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException();
        }
        return head.getElement();
    }

    @Override
    public T last() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException();
        }
        return tail.getElement();
    }

    @Override
    public boolean contains(T target) throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException();
        }
        Node<T> current = head;
        while (current != null) {
            if (current.getElement().equals(target)) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }


    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public String toString() {
        String result = getClass().getSimpleName() + " { ";
        if (!isEmpty()) {
            Node<T> current = head;
            while (current != null) {
                result += current.getElement() + " ";
                current = current.getNext();
            }
        }
        return result + "}";
    }

    public String print() {
        return getClass().getSimpleName() + " { " + print(head) + "}";
    }

    private String print(Node<T> current) {
        if (current == null) {
            return "";
        }
        return current.getElement() + " " + print(current.getNext());
    }

    public T[] toArray() {
        T[] result = (T[]) new Comparable[size()];
        Node<T> current = head;
        for (int i = 0; i < size(); i++) {
            result[i] = current.getElement();
            current = current.getNext();
        }
        return result;
    }

    public T get(int index) throws EmptyCollectionException, IndexOutOfBoundsException {
        if (isEmpty()) {
            throw new EmptyCollectionException();
        }
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException();
        }
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        return current.getElement();
    }

    public void set(int index, T target) throws EmptyCollectionException, IndexOutOfBoundsException {
        if (isEmpty()) {
            throw new EmptyCollectionException();
        }
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException();
        }
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        current.setElement(target);
    }

    public void replace(T existingElement, T newElement) throws EmptyCollectionException, NoSuchElementException {
        if (isEmpty()) throw new EmptyCollectionException();
        if (!contains(existingElement)) throw new NoSuchElementException();
        Node<T> current = head;
        while (current != null) {
            if (current.getElement().equals(existingElement)) {
                current.setElement(newElement);
            }
            current = current.getNext();
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new BasicIterator<T>() {};
    }

    private abstract class BasicIterator<E> implements Iterator<T> {
        private Node<T> current;
        private int expectedModCount;
        private boolean okToRemove;

        public BasicIterator() {
            this.current = head;
            this.expectedModCount = modCount;
            this.okToRemove = false;
        }

        public boolean hasNext() {
            return current != null;
        }

        public T next() {
            if (expectedModCount != modCount) {
                throw new java.util.ConcurrentModificationException();
            }
            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }
            T next = current.getElement();
            current = current.getNext();
            okToRemove = true;
            return next;
        }

        public void remove() {
            if (expectedModCount != modCount) {
                throw new java.util.ConcurrentModificationException();
            }
            if (!okToRemove) {
                throw new IllegalStateException();
            }
            LinkedList.this.remove(current.getElement());
            expectedModCount++;
            okToRemove = false;
        }
    }
}
