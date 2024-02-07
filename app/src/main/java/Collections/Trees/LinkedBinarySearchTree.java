package Collections.Trees;

import Collections.Exceptions.ElementNotFoundException;
import Collections.Exceptions.EmptyCollectionException;
import Collections.Exceptions.NonComparableElementException;

public class LinkedBinarySearchTree<T> extends LinkedBinaryTree<T> implements BinarySearchTreeADT<T> {

    public LinkedBinarySearchTree() {
        super();
    }

    public LinkedBinarySearchTree(T element) {
        super(element);
    }

    @Override
    public void addElement(T element) throws NonComparableElementException {
        if (!(element instanceof Comparable)) {
            throw new NonComparableElementException();
        }
        BinaryTreeNode<T> temp = new BinaryTreeNode<>(element);
        if (isEmpty()) {
            root = temp;
        } else {
            BinaryTreeNode<T> current = root;
            boolean added = false;
            while (!added) {
                if (((Comparable)element).compareTo(current.getElement()) < 0) {
                    if (current.getLeft() == null) {
                        current.setLeft(temp);
                        added = true;
                    } else {
                        current = current.getLeft();
                    }
                } else {
                    if (current.getRight() == null) {
                        current.setRight(temp);
                        added = true;
                    } else {
                        current = current.getRight();
                    }
                }
            }
        }
        size++;
    }

    @Override
    public T removeElement(T targetElement) throws EmptyCollectionException, ElementNotFoundException {
        if (isEmpty()) {
            throw new EmptyCollectionException();
        }
        T removed = null;
        if (targetElement.equals(root.getElement())) {
            removed = root.getElement();
            root = replacement(root);
            size--;
        } else {
            if (!(targetElement instanceof Comparable)) {
                throw new NonComparableElementException();
            }
            BinaryTreeNode<T> current, parent = root;
            boolean found = false;
            if (((Comparable) targetElement).compareTo(root.getElement()) < 0) {
                current = root.getLeft();
            } else {
                current = root.getRight();
            }
            while (current != null && !found) {
                if (targetElement.equals(current.getElement())) {
                    found = true;
                    size--;
                    removed = current.getElement();
                    if (current == parent.getLeft()) {
                        parent.setLeft(replacement (current));
                    } else {
                        parent.setRight(replacement(current));
                    }
                } else {
                    parent = current;
                    if (((Comparable) targetElement).compareTo(current.getElement()) < 0) {
                        current = current.getLeft();
                    } else {
                        current = current.getRight();
                    }
                }
            }
            if (!found) {
                throw new ElementNotFoundException();
            }
        }
        return removed;
    }

    protected BinaryTreeNode<T> replacement(BinaryTreeNode<T> node) {
        BinaryTreeNode<T> replaced;
        if ((node.getLeft() == null) && (node.getRight() == null)) {
            replaced = null;
        } else if ((node.getLeft() != null) && (node.getRight() == null)) {
            replaced = node.getLeft();
        } else if ((node.getLeft() == null) && (node.getRight() != null)) {
            replaced = node.getRight();
        } else {
            BinaryTreeNode<T> current = node.getRight();
            BinaryTreeNode<T> parent = node;
            while (current.getLeft() != null) {
                parent = current;
                current = current.getLeft();
            }
            if (node.getRight() == current) {
                current.setLeft(node.getLeft());
            } else {
                parent.setLeft(current.getRight());
                current.setRight(node.getRight());
                current.setLeft(node.getLeft());
            }
            replaced = current;
        }
        return replaced;
    }

    @Override
    public void removeAllOccurrences(T targetElement) throws EmptyCollectionException, ElementNotFoundException {
        removeElement(targetElement);
        while (contains(targetElement)) {
            removeElement(targetElement);
        }
    }

    @Override
    public T removeMin() throws EmptyCollectionException {
        return removeElement(findMin());
    }

    @Override
    public T removeMax() throws EmptyCollectionException {
        return removeElement(findMax());
    }

    @Override
    public T findMin() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException();
        } else {
            BinaryTreeNode<T> current = root;
            while (current.getLeft() != null) {
                current = current.getLeft();
            }
            return current.getElement();
        }
    }

    @Override
    public T findMax() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException();
        } else {
            BinaryTreeNode<T> current = root;
            while (current.getRight() != null) {
                current = current.getRight();
            }
            return current.getElement();
        }
    }
}
