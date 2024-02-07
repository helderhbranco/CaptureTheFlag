package Collections.Stacks;

import Collections.Exceptions.EmptyCollectionException;

public class ArrayStack<T> implements StackADT<T> {
    protected final static int DEFAULT_CAPACITY = 100;
    protected T[] stack;
    protected int top;

    public ArrayStack() {
        this.stack = (T[]) (new Object[DEFAULT_CAPACITY]);
        this.top = 0;
    }

    public ArrayStack(int initialCapacity) {
        this.stack = (T[]) (new Object[initialCapacity]);
        this.top = 0;
    }

    private void expandCapacity() {
        T[] larger = (T[]) (new Object[stack.length * 2]);
        for (int i = 0; i < stack.length; i++) {
            larger[i] = stack[i];
        }
        stack = larger;
    }

    @Override
    public void push(T element) {
        if (size() == stack.length) expandCapacity();
        stack[top] = element;
        top++;
    }

    @Override
    public T pop() throws EmptyCollectionException {
        if (isEmpty()) throw new EmptyCollectionException();
        this.top--;
        T result = stack[top];
        stack[top] = null;
        return result;
    }

    @Override
    public T peek() throws EmptyCollectionException {
        if (isEmpty()) throw new EmptyCollectionException();
        return stack[top - 1];
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public int size() {
        return top;
    }

    @Override
    public String toString() {
        String result = getClass().getSimpleName() + " { ";
        for (int i = 0; i < top; i++) {
            result += stack[i] + " ";
        }
        return result + "}";
    }
}
