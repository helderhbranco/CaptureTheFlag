package Collections.Stacks;

import Collections.Exceptions.EmptyCollectionException;

public class SmackStackArray<T> extends ArrayStack<T> implements SmackStackADT<T> {

    public SmackStackArray() {
        super();
    }

    public SmackStackArray(int initialCapacity) {
        super(initialCapacity);
    }

    @Override
    public T smack() throws EmptyCollectionException {
        if (isEmpty()) throw new EmptyCollectionException();
        T result = stack[0];
        for (int i = 0; i < top - 1; i++)
            stack[i] = stack[i + 1];
        top--;
        return result;
    }
}
