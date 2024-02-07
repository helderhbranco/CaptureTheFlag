package Collections.Lists;

import Collections.Exceptions.EmptyCollectionException;
import Collections.Exceptions.NoSuchElementException;

public class ArrayUnorderedList<T> extends ArrayList<T> implements UnorderedListADT<T> {

    public ArrayUnorderedList() {
        super();
    }

    public ArrayUnorderedList(int initialCapacity) {
        super(initialCapacity);
    }

    @Override
    public void addToFront(T element) {
        if (size() == list.length) expandCapacity();
        for (int shift = size(); shift > 0; shift--) {
            list[shift] = list[shift - 1];
        }
        list[0] = element;
        size++;
        modCount++;
    }

    @Override
    public void addToRear(T element) {
        if (size() == list.length) expandCapacity();
        list[size()] = element;
        size++;
        modCount++;
    }

    @Override
    public void addAfter(T element, T target) throws EmptyCollectionException, NoSuchElementException {
        if (isEmpty()) throw new EmptyCollectionException();
        if (size() == list.length) expandCapacity();
        int index = 0;
        while (index < size() && !target.equals(list[index])) {
            index++;
        }
        if (index == size) {
            throw new NoSuchElementException();
        }
        for (int shift = size; shift > index; shift--) {
            list[shift] = list[shift - 1];
        }
        list[index + 1] = element;
        size++;
        modCount++;
    }
}

