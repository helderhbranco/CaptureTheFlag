package Collections.Trees;

import Collections.Exceptions.EmptyCollectionException;
import Collections.Exceptions.NoSuchElementException;
import Collections.Exceptions.NonComparableElementException;

public class ArrayBinarySearchTree<T> extends ArrayBinaryTree<T> implements BinarySearchTreeADT<T> {
    protected int height;
    protected int maxIndex;

    public ArrayBinarySearchTree() {
        super();
        height = 0;
        maxIndex = -1;
    }

    public ArrayBinarySearchTree (T element) {
        super(element);
        height = 1;
        maxIndex = 0;
    }

    private void expandCapacity() {
        T[] newTree = (T[]) (new Object[tree.length * 2]);
        for (int i = 0; i < tree.length; i++) {
            newTree[i] = tree[i];
        }
        tree = newTree;
    }

    @Override
    public void addElement(T element) throws NonComparableElementException {
        if (!(element instanceof Comparable)) {
            throw new NonComparableElementException();
        }
        if (tree.length < maxIndex * 2 + 3) {
            expandCapacity();
        }
        if (isEmpty()) {
            tree[0] = element;
            maxIndex = 0;
        } else {
            boolean added = false;
            int currentIndex = 0;
            while (!added) {
                if (((Comparable<T>) element).compareTo((tree[currentIndex]) ) < 0) {
                    // go left
                    if (tree[currentIndex * 2 + 1] == null) {
                        tree[currentIndex * 2 + 1] = element;
                        added = true;
                        if (currentIndex * 2 + 1 > maxIndex) {
                            maxIndex = currentIndex * 2 + 1;
                        }
                    } else {
                        currentIndex = currentIndex * 2 + 1;
                    }
                } else {
                    // go right
                    if (tree[currentIndex * 2 + 2] == null) {
                        tree[currentIndex * 2 + 2] = element;
                        added = true;
                        if (currentIndex * 2 + 2 > maxIndex) {
                            maxIndex = currentIndex * 2 + 2;
                        }
                    } else {
                        currentIndex = currentIndex * 2 + 2;
                    }
                }
            }
        }
        height = (int) (Math.log(maxIndex + 1) / Math.log(2)) + 1;
        size++;
    }

    @Override
    public T removeElement(T targetElement) throws EmptyCollectionException, NoSuchElementException {
        if (isEmpty()) {
            throw new EmptyCollectionException();
        }

        T removed = null;
        boolean found = false;

        for (int i = 0; (i <= maxIndex) && !found; i++) {
            if ((tree[i] != null) && targetElement.equals(tree[i])) {
                try {
                    found = true;
                    removed = tree[i];
                    try {
                        replacement(i);
                    } catch (NoSuchElementException e) {
                        tree[i] = null;
                    }
                    size--;
                } catch (NoSuchElementException e) {
                    tree[i] = null;
                }
            }
        }
        if (!found) {
            throw new NoSuchElementException();
        }
        return removed;
    }

    private void replacement(int targetIndex) throws NoSuchElementException {
        if (tree[targetIndex * 2 + 2] != null) {
            tree[targetIndex] = tree[targetIndex * 2 + 2];
            replacement(targetIndex * 2 + 2);
        } else if (tree[targetIndex * 2 + 1] != null) {
            tree[targetIndex] = tree[targetIndex * 2 + 1];
            replacement(targetIndex * 2 + 1);
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public void removeAllOccurrences(T targetElement) throws EmptyCollectionException, NoSuchElementException {
        removeElement(targetElement);
        while (contains(targetElement)) {
            removeElement(targetElement);
        }
    }

    @Override
    public T removeMin() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException();
        }
        return removeElement(findMin());
    }

    @Override
    public T removeMax() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException();
        }
        return removeElement(findMax());
    }

    @Override
    public T findMin() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException();
        }
        T min = tree[0];
        for (int i = 0; i <= maxIndex; i++) {
            if ((tree[i] != null) && (((Comparable<T>) tree[i]).compareTo(min) < 0)) {
                min = tree[i];
            }
        }
        return min;
    }

    @Override
    public T findMax() {
        if (isEmpty()) {
            throw new EmptyCollectionException();
        }
        T max = tree[0];
        for (int i = 0; i <= maxIndex; i++) {
            if ((tree[i] != null) && (((Comparable<T>) tree[i]).compareTo(max) > 0)) {
                max = tree[i];
            }
        }
        return max;
    }
}
