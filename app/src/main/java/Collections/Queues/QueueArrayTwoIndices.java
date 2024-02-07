package Collections.Queues;

import Collections.Exceptions.EmptyCollectionException;

public class QueueArrayTwoIndices<T> implements QueueADT<T> {
    private final static int DEFAULT_CAPACITY = 100;
    private T[] queue;
    private int toAdd, toRemove;

    public QueueArrayTwoIndices() {
        this.queue = (T[]) (new Object[DEFAULT_CAPACITY]);
        this.toAdd = 0;
        this.toRemove = 0;
    }

    public QueueArrayTwoIndices(int initialCapacity) {
        this.queue = (T[]) (new Object[initialCapacity]);
        this.toAdd = 0;
        this.toRemove = 0;
    }

    private void expandCapacity() {
        T[] larger = (T[]) (new Object[queue.length * 2]);
        for (int i = 0; i < queue.length; i++) {
            larger[i] = queue[i];
        }
        queue = larger;
    }

    @Override
    public void enqueue(T element) {
        if (size() + 1 == queue.length) expandCapacity();
        queue[toAdd] = element;
        toAdd = (toAdd + 1) % queue.length;
    }

    @Override
    public T dequeue() throws EmptyCollectionException {
        if (isEmpty()) throw new EmptyCollectionException();
        T removed = queue[toRemove];
        queue[toRemove] = null;
        toRemove = (toRemove + 1) % queue.length;
        return removed;
    }

    @Override
    public T first() throws EmptyCollectionException {
        if (isEmpty()) throw new EmptyCollectionException();
        return queue[toRemove];
    }

    @Override
    public boolean isEmpty() {
        return toAdd == toRemove;
    }

    @Override
    public int size() {
        return (toAdd - toRemove + queue.length) % queue.length;
    }

    @Override
    public String toString() {
        String result = getClass().getSimpleName() + " { ";
        if (!isEmpty()) {
            int index = toRemove;
            while (index != toAdd) {
                result += queue[index] + " ";
                index = (index + 1) % queue.length;
            }
        }
        return result + "}";
    }
}
