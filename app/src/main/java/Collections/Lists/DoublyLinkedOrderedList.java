package Collections.Lists;

import Collections.Exceptions.NonComparableElementException;

public class DoublyLinkedOrderedList<T> extends DoublyLinkedList<T> implements OrderedListADT<T> {

    public DoublyLinkedOrderedList() {
        super();
    }

    @Override
    public void add(T element) throws NonComparableElementException {
        if (!(element instanceof Comparable)) {
            throw new NonComparableElementException();
        }
        if (this.isEmpty()) {
            head = tail = new DoublyNode<>(element);
        } else {
            DoublyNode<T> current = head;
            DoublyNode<T> previous = null;
            while (current != null && ((Comparable<T>) element).compareTo(current.getElement()) > 0) {
                previous = current;
                current = current.getNext();
            }
            DoublyNode<T> newNode = new DoublyNode<>(element);
            if (previous == null) {
                newNode.setNext(head);
                head.setPrevious(newNode);
                head = newNode;
            } else {
                newNode.setNext(current);
                newNode.setPrevious(previous);
                previous.setNext(newNode);
                if (current != null) {
                    current.setPrevious(newNode);
                } else {
                    tail = newNode;
                }
            }
        }
        count++;
        modCount++;
    }
}
