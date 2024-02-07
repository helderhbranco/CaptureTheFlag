package Collections.Queues;

import Collections.Stacks.LinkedStack;
import Collections.Exceptions.EmptyCollectionException;

public class StackQueue<T> implements QueueADT<T> {
    private final LinkedStack<T> stack1;
    private final LinkedStack<T> stack2;

    public StackQueue() {
        this.stack1 = new LinkedStack<>();
        this.stack2 = new LinkedStack<>();
    }

    private void transfer() throws EmptyCollectionException {
        if (isEmpty()) throw new EmptyCollectionException();
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
    }

    @Override
    public void enqueue(T element) {
        stack1.push(element);
    }

    @Override
    public T dequeue() throws EmptyCollectionException {
        transfer();
        return stack2.pop();
    }

    @Override
    public T first() throws EmptyCollectionException {
        transfer();
        return stack2.peek();
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public int size() {
        return stack1.size() + stack2.size();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " { " +
                "stack1= " + stack1 +
                ", stack2= " + stack2 +
                " }";
    }
}
