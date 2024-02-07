package Collections.Trees;

import Collections.Queues.LinkedQueue;
import Collections.Exceptions.EmptyCollectionException;
import Collections.Exceptions.UnsupportedOperationException;
import Collections.Lists.LinkedUnorderedList;
import Collections.Exceptions.ElementNotFoundException;

import java.util.Iterator;

public class LinkedBinaryTree<T> implements BinaryTreeADT<T> {
    protected BinaryTreeNode<T> root;
    protected int size;

    public LinkedBinaryTree() {
        root = null;
        size = 0;
    }

    public LinkedBinaryTree(T element) {
        root = new BinaryTreeNode<>(element);
        size = 1;
    }

    public void setRoot(BinaryTreeNode<T> root) throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException();
        }
        this.root = root;
    }

    @Override
    public T getRootElement() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException();
        }
        return root.getElement();
    }

    @Override
    public BinaryTreeNode<T> getRootNode() throws EmptyCollectionException, UnsupportedOperationException {
        if (root == null) {
            throw new EmptyCollectionException();
        }
        return root;
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
    public boolean contains(T targetElement) throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException();
        }
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

        BinaryTreeNode<T> current = findAgain(targetElement, root);

        if (current == null) {
            throw new ElementNotFoundException(getClass().getSimpleName());
        }

        return current.getElement();
    }

    private BinaryTreeNode<T> findAgain(T targetElement, BinaryTreeNode<T> next) {
        if (next == null) {
            return null;
        }

        if (next.getElement().equals(targetElement)) {
            return next;
        }

        BinaryTreeNode<T> temp = findAgain(targetElement, next.getLeft());

        if (temp == null) {
            temp = findAgain(targetElement, next.getRight());
        }

        return temp;
    }

    @Override
    public String toString() {
        LinkedUnorderedList<T> tempList = new LinkedUnorderedList<>();

        inOrder(root, tempList);

        return tempList.toString();
    }

    @Override
    public Iterator<T> iteratorInOrder() {
        LinkedUnorderedList<T> tempList = new LinkedUnorderedList<>();

        inOrder(root, tempList);

        return tempList.iterator();
    }

    private void inOrder(BinaryTreeNode<T> node, LinkedUnorderedList<T> tempList) {
        if (node != null) {
            inOrder(node.getLeft(), tempList);
            tempList.addToRear(node.getElement());
            inOrder(node.getRight(), tempList);
        }
    }

    @Override
    public Iterator<T> iteratorPreOrder() {
        LinkedUnorderedList<T> tempList = new LinkedUnorderedList<>();

        preOrder(root, tempList);

        return tempList.iterator();
    }

    private void preOrder(BinaryTreeNode<T> node, LinkedUnorderedList<T> tempList) {
        if (node != null) {
            tempList.addToRear(node.getElement());
            preOrder(node.getLeft(), tempList);
            preOrder(node.getRight(), tempList);
        }
    }

    @Override
    public Iterator<T> iteratorPostOrder() {
        LinkedUnorderedList<T> tempList = new LinkedUnorderedList<>();

        postOrder(root, tempList);

        return tempList.iterator();
    }

    private void postOrder(BinaryTreeNode<T> node, LinkedUnorderedList<T> tempList) {
        if (node != null) {
            postOrder(node.getLeft(), tempList);
            postOrder(node.getRight(), tempList);
            tempList.addToRear(node.getElement());
        }
    }

    @Override
    public Iterator<T> iteratorLevelOrder() {
        LinkedUnorderedList<T> tempList = new LinkedUnorderedList<>();
        LinkedQueue<BinaryTreeNode<T>> queue = new LinkedQueue<>();

        if (!isEmpty()) {
            queue.enqueue(root);

            while (!queue.isEmpty()) {
                BinaryTreeNode<T> next = queue.dequeue();

                if (next.getLeft() != null) {
                    queue.enqueue(next.getLeft());
                }

                if (next.getRight() != null) {
                    queue.enqueue(next.getRight());
                }

                tempList.addToRear(next.getElement());
            }
        }

        return tempList.iterator();
    }
}
