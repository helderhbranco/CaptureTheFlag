package Collections.Lists;

import Collections.Exceptions.NonComparableElementException;

public class LinkedOrderedList<T> extends LinkedList<T> implements OrderedListADT<T> {

    @Override
    public void add(T element) throws NonComparableElementException {
        if (!(element instanceof Comparable)) throw new NonComparableElementException();
        Comparable<T> comparableElement = (Comparable<T>) element;
        Node<T> newNode = new Node<>(element);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            Node<T> current = head;
            Node<T> previous = null;
            while (current != null && comparableElement.compareTo(current.getElement()) > 0) {
                previous = current;
                current = current.getNext();
            }
            if (previous == null) {
                newNode.setNext(head);
                head = newNode;
            } else {
                newNode.setNext(current);
                previous.setNext(newNode);
            }
            if (newNode.getNext() == null) {
                tail = newNode;
            }
        }
        size++;
        modCount++;
    }
}
