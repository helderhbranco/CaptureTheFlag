package Collections.Queues;

import Collections.Exceptions.EmptyCollectionException;

public class CircularArrayQueue<T> implements QueueADT<T> {

    private final static int DEFAULT_CAPACITY = 100;
    private T[] queue;
    private int front, rear, count;

    public CircularArrayQueue() {
        this.queue = (T[]) (new Object[DEFAULT_CAPACITY]);
        this.front = 0;
        this.rear = 0;
        this.count = 0;
    }

    public CircularArrayQueue(int initialCapacity) {
        this.queue = (T[]) (new Object[initialCapacity]);
        this.front = 0;
        this.rear = 0;
        this.count = 0;
    }

    private void expandCapacity() {
        T[] larger = (T[]) (new Object[queue.length * 2]);
        for (int index = front; index < rear; index++) {
            larger[index] = queue[index];
        }
        queue = larger;
    }

    @Override
    public void enqueue(T element) {
        if (size() == queue.length - 1) expandCapacity();
        queue[rear] = element;
        rear = (rear + 1) % queue.length;
        count++;
    }

    @Override
    public T dequeue() throws EmptyCollectionException {
        if (isEmpty()) throw new EmptyCollectionException();
        T removed = queue[front];
        queue[front] = null;
        front = (front + 1) % queue.length;
        count--;
        return removed;
    }

    @Override
    public T first() throws EmptyCollectionException {
        if (isEmpty()) throw new EmptyCollectionException();
        return queue[front];
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
            int current = front;
            do {
                result += queue[current] + " ";
                current = (current + 1) % queue.length;
            } while (current != rear);
        }
        return result + "}";
    }
}
