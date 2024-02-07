package Collections.Trees;

import Collections.Exceptions.ElementNotFoundException;
import Collections.Exceptions.EmptyCollectionException;
import Collections.Exceptions.UnsupportedOperationException;
import Collections.Lists.ArrayUnorderedList;

import java.util.Iterator;

public class ArrayBinaryTree<T> implements BinaryTreeADT<T> {
    protected final int DEFAULT_CAPACITY = 100;
    protected T[] tree;
    protected int size;

    public ArrayBinaryTree() {
        tree = (T[]) (new Object[DEFAULT_CAPACITY]);
        size = 0;
    }

    public ArrayBinaryTree(T element) {
        tree = (T[]) (new Object[DEFAULT_CAPACITY]);
        tree[0] = element;
        size = 1;
    }

    @Override
    public T getRootElement() {
        return tree[0];
    }

    @Override
    public BinaryTreeNode<T> getRootNode() throws EmptyCollectionException, UnsupportedOperationException {
        throw new UnsupportedOperationException();
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
    public boolean contains(T targetElement) {
        try {
            find(targetElement);
            return true;
        } catch (ElementNotFoundException ignored) {
            return false;
        }
    }

    @Override
    public T find(T targetElement) throws EmptyCollectionException, ElementNotFoundException {
        if (isEmpty()) {
            throw new EmptyCollectionException();
        }

        T element = null;
        boolean found = false;

        for (int i = 0; i < size && !found; i++) {
            if (tree[i].equals(targetElement)) {
                found = true;
                element = tree[i];
            }
        }

        if (!found) {
            throw new ElementNotFoundException();
        }

        return element;
    }

    public int findIndex(T targetElement) throws EmptyCollectionException, ElementNotFoundException {
        if (isEmpty()) {
            throw new EmptyCollectionException();
        }

        int index = 0;
        boolean found = false;

        for (int i = 0; i < size && !found; i++) {
            if (tree[i].equals(targetElement)) {
                found = true;
                index = i;
            }
        }

        if (!found) {
            throw new ElementNotFoundException();
        }

        return index;
    }

    @Override
    public String toString() {
        String result = getClass().getSimpleName() + " { ";
        for (int i = 0; i < size; i++) {
            result += tree[i] + " ";
        }
        return result + "}";

    }

    @Override
    public Iterator<T> iteratorInOrder() {
        ArrayUnorderedList<T> tempList = new ArrayUnorderedList<>();

        inOrder(0, tempList);

        return tempList.iterator();
    }

    private void inOrder(int node, ArrayUnorderedList<T> tempList) {
        if (node < size) {
            inOrder(node * 2 + 1, tempList);
            tempList.addToRear(tree[node]);
            inOrder(node * 2 + 2, tempList);
        }
    }

    @Override
    public Iterator<T> iteratorPreOrder() {
        ArrayUnorderedList<T> tempList = new ArrayUnorderedList<>();

        preOrder(0, tempList);

        return tempList.iterator();
    }

    private void preOrder(int node, ArrayUnorderedList<T> tempList) {
        if (node < size) {
            tempList.addToRear(tree[node]);
            preOrder(node * 2 + 1, tempList);
            preOrder(node * 2 + 2, tempList);
        }
    }

    @Override
    public Iterator<T> iteratorPostOrder() {
        ArrayUnorderedList<T> tempList = new ArrayUnorderedList<>();

        postOrder(0, tempList);

        return tempList.iterator();
    }

    private void postOrder(int node, ArrayUnorderedList<T> tempList) {
        if (node < size) {
            postOrder(node * 2 + 1, tempList);
            postOrder(node * 2 + 2, tempList);
            tempList.addToRear(tree[node]);
        }
    }

    @Override
    public Iterator<T> iteratorLevelOrder() {
        ArrayUnorderedList<T> tempList = new ArrayUnorderedList<>();

        for (int i = 0; i < size; i++) {
            tempList.addToRear(tree[i]);
        }

        return tempList.iterator();
    }
}
