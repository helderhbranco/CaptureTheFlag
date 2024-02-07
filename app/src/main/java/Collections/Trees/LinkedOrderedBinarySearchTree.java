package Collections.Trees;

import Collections.Exceptions.EmptyCollectionException;
import Collections.Exceptions.NoSuchElementException;
import Collections.Exceptions.NonComparableElementException;
import Collections.Lists.OrderedListADT;

import java.util.Iterator;

public class LinkedOrderedBinarySearchTree<T> extends LinkedBinarySearchTree<T> implements OrderedListADT<T> {

    public LinkedOrderedBinarySearchTree() {
        super();
    }

    public LinkedOrderedBinarySearchTree(T element) {
        super(element);
    }

    @Override
    public T removeFirst() throws EmptyCollectionException {
        return removeMin();
    }

    @Override
    public T removeLast() throws EmptyCollectionException {
        return removeMax();
    }

    @Override
    public T remove(T element) throws EmptyCollectionException, NoSuchElementException {
        return removeElement(element);
    }

    @Override
    public T first() throws EmptyCollectionException {
        return findMin();
    }

    @Override
    public T last() throws EmptyCollectionException {
        return findMax();
    }

    @Override
    public Iterator<T> iterator() {
        return iteratorInOrder();
    }

    @Override
    public void add(T element) throws NonComparableElementException {
        if (!(element instanceof Comparable)) {
            throw new NonComparableElementException();
        }
        Comparable<T> comparableElement = (Comparable<T>) element;
        if (isEmpty()) {
            root = new BinaryTreeNode<>(element);
        } else {
            addElement(root, comparableElement);
        }
        size++;
    }

    private void addElement(BinaryTreeNode<T> root, Comparable<T> comparableElement) {
        if (comparableElement.compareTo(root.getElement()) < 0) {
            if (root.getLeft() == null) {
                root.setLeft((BinaryTreeNode<T>) new BinaryTreeNode<>(comparableElement));
            } else {
                addElement(root.getLeft(), comparableElement);
            }
        } else {
            if (root.getRight() == null) {
                root.setRight((BinaryTreeNode<T>) new BinaryTreeNode<>(comparableElement));
            } else {
                addElement(root.getRight(), comparableElement);
            }
        }
    }
}
