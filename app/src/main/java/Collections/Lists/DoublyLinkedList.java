package Collections.Lists;

import Collections.Exceptions.EmptyCollectionException;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoublyLinkedList<T> implements ListADT<T>, Iterable<T> {
    protected DoublyNode<T> head;
    protected DoublyNode<T> tail;
    protected int count, modCount;

    public DoublyLinkedList() {
        this.head = null;
        this.tail = null;
        this.count = 0;
        this.modCount = 0;
    }

    @Override
    public T removeFirst() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException();
        }
        T removed = head.getElement();
        head = head.getNext();
        count--;
        if (isEmpty()) {
            tail = null;
        } else {
            head.setPrevious(null);
        }
        modCount++;
        return removed;
    }

    @Override
    public T removeLast() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException();
        }
        T removed = tail.getElement();
        tail = tail.getPrevious();
        count--;
        if (isEmpty()) {
            head = null;
        } else {
            tail.setNext(null);
        }
        modCount++;
        return removed;
    }

    @Override
    public T remove(T element) throws EmptyCollectionException, NoSuchElementException {
        if (isEmpty()) {
            throw new EmptyCollectionException();
        }
        boolean found = false;
        DoublyNode<T> current = head;
        while (current != null && !found) {
            if (element.equals(current.getElement())) {
                found = true;
            } else {
                current = current.getNext();
            }
        }
        if (!found) {
            throw new NoSuchElementException();
        }
        if (size() == 1) {
            head = tail = null;
        } else if (current.equals(head)) {
            head = current.getNext();
            head.setPrevious(null);
        } else if (current.equals(tail)) {
            tail = current.getPrevious();
            tail.setNext(null);
        } else {
            current.getPrevious().setNext(current.getNext());
            current.getNext().setPrevious(current.getPrevious());
        }
        count--;
        modCount++;
        return current.getElement();
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
        if (isEmpty()) throw new EmptyCollectionException();
        DoublyNode<T> current = head;
        while (current != null) {
            if (target.equals(current.getElement())) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public String toString() {
        String result = getClass().getSimpleName() + " { ";
        if (!isEmpty()) {
            DoublyNode<T> current = head;
            while (current != null) {
                result += current.getElement() + " ";
                current = current.getNext();
            }
        }
        return result + "}";
    }

    public DoublyLinkedUnorderedList<T> reverse() {
        DoublyLinkedUnorderedList<T> result = new DoublyLinkedUnorderedList<>();
        DoublyNode<T> current = head;
        while (current != null) {
            result.addToFront(current.getElement());
            current = current.getNext();
        }
        return result;
    }

    public String printForward() {
        return getClass().getSimpleName() + " { " + printForward(head) + "}";
    }

    public String printForward(DoublyNode<T> node) {
        if (node == null) return "";
        return node.getElement() + " " + printForward(node.getNext());
    }

    public String printBackwards() {
        return getClass().getSimpleName() + " { " + printBackwards(tail) + "}";
    }

    public String printBackwards(DoublyNode<T> node) {
        if (node == null) return "";
        return node.getElement() + " " + printBackwards(node.getPrevious());
    }

    @Override
    public Iterator<T> iterator() {
        return new BasicIterator<T>(){};
    }

    public abstract class BasicIterator<E> implements Iterator<T> {
        protected DoublyNode<T> current;
        protected int expectedModCount;
        protected boolean okToRemove;

        public BasicIterator() {
            this.current = head;
            this.expectedModCount = modCount;
            this.okToRemove = false;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            if (modCount != expectedModCount) {
                throw new java.util.ConcurrentModificationException();
            }
            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }
            T result = current.getElement();
            current = current.getNext();
            okToRemove = true;
            return result;
        }

        @Override
        public void remove() {
            if (modCount != this.expectedModCount) {
                throw new java.util.ConcurrentModificationException();
            }
            if (!okToRemove) {
                throw new java.util.NoSuchElementException();
            }
            DoublyLinkedList.this.remove(current.getPrevious().getElement());
            expectedModCount++;
            okToRemove = false;
        }
    }
}
