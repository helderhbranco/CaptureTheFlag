package Collections.Trees;

import Collections.Exceptions.EmptyCollectionException;

public class ArrayHeap<T> extends ArrayBinaryTree<T> implements HeapADT<T> {

    public ArrayHeap() {
        super();
    }

    @Override
    public void addElement(T element) {
        if (size == tree.length)
            expandCapacity();
        tree[size] = element;
        size++;
        if (size > 1) {
            heapifyAdd();
        }
    }

    private void expandCapacity() {
        T[] newTree = (T[]) (new Object[tree.length * 2]);
        for (int i = 0; i < tree.length; i++) {
            newTree[i] = tree[i];
        }
        tree = newTree;
    }

    private void heapifyAdd() {
        T temp;
        int next = size - 1;

        temp = tree[next];

        while ((next != 0) && (((Comparable)temp).compareTo(tree[(next - 1) / 2]) < 0)) {
            tree[next] = tree[(next - 1) / 2];
            next = (next - 1) / 2;
        }
        tree[next] = temp;
    }

    @Override
    public T removeMin() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException();
        }
        T minElement = tree[0];
        tree[0] = tree[size - 1];
        heapifyRemove();
        size--;

        return minElement;
    }

    private void heapifyRemove() {
        T temp;
        int node = 0;
        int left = 1;
        int right = 2;
        int next;

        if ((tree[left] == null) && (tree[right] == null)) {
            next = size;
        } else if (tree[left] == null) {
            next = right;
        } else if (tree[right] == null) {
            next = left;
        } else if (((Comparable)tree[left]).compareTo(tree[right]) < 0) {
            next = left;
        } else {
            next = right;
        }
        temp = tree[node];
        while ((next < size) && (((Comparable) tree[next]).compareTo(temp) < 0)) {
            tree[node] = tree[next];
            node = next;
            left = 2 * node + 1;
            right = 2 * (node + 1);
            if ((tree[left] == null) && (tree[right] == null)) {
                next = size;
            } else if (tree[left] == null) {
                next = right;
            } else if (tree[right] == null) {
                next = left;
            } else if (((Comparable) tree[left]).compareTo(tree[right]) < 0) {
                next = left;
            }
            else {
                next = right;
            }
        }
        tree[node] = temp;
    }

    @Override
    public T findMin() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException();
        }
        return tree[0];
    }
}
