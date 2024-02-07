package Collections.HashTables;

import Collections.Exceptions.EmptyCollectionException;
import Collections.Exceptions.NoSuchElementException;
import Collections.Lists.LinkedUnorderedList;
import Collections.Lists.UnorderedListADT;

import java.util.Iterator;

public class HashSet<T> implements SetADT<T> {

    private final double LOAD_FACTOR = 0.75;
    private final static int DEFAULT_CAPACITY = 16;
    private UnorderedListADT<T>[] map;
    private int capacity;
    private int size;

    public HashSet() {
        this(DEFAULT_CAPACITY);
    }

    public HashSet(int initialCapacity) {
        capacity = initialCapacity;
        map = new UnorderedListADT[capacity];
        size = 0;
    }

    private void resize() {
        capacity *= 2;
        size = 0;
        UnorderedListADT<T>[] oldEntries = map;
        map = new LinkedUnorderedList[capacity];
        for (UnorderedListADT<T> entryList : oldEntries) {
            if (entryList != null) {
                for (T entry : entryList) {
                    add(entry);
                }
            }
        }
    }

    @Override
    public boolean add(T element) throws IllegalArgumentException {
        if (element == null) {
            throw new IllegalArgumentException("Element cannot be null");
        }
        int index = Math.abs(element.hashCode()) % capacity;
        if (map[index] == null) {
            map[index] = new LinkedUnorderedList<>();
        }
        if (map[index].contains(element))
            return false;
        map[index].addToRear(element);
        size++;
        if (size > capacity * LOAD_FACTOR) {
            resize();
        }
        return true;
    }

    @Override
    public T remove(T element) throws IllegalArgumentException, NoSuchElementException, EmptyCollectionException {
        if (element == null) {
            throw new IllegalArgumentException("Element cannot be null");
        }
        if (isEmpty()) {
            throw new EmptyCollectionException("Set is empty");
        }
        int index = Math.abs(element.hashCode()) % capacity;
        if (map[index] == null) {
            throw new NoSuchElementException("Element not found");
        }
        T removed = map[index].remove(element);
        size--;
        return removed;
    }

    @Override
    public T find(T element) throws IllegalArgumentException, EmptyCollectionException, NoSuchElementException {
        if (element == null) {
            throw new IllegalArgumentException("Element cannot be null");
        }
        if (isEmpty()) {
            throw new EmptyCollectionException("Set is empty");
        }
        int index = Math.abs(element.hashCode()) % capacity;
        if (map[index] == null) {
            throw new NoSuchElementException("Element not found");
        }
        for (T entry : map[index]) {
            if (entry.equals(element)) {
                return entry;
            }
        }
        throw new NoSuchElementException("Element not found");
    }

    @Override
    public boolean contains(T target) throws IllegalArgumentException {
        if (target == null) {
            throw new IllegalArgumentException("Element cannot be null");
        }
        if (isEmpty()) {
            return false;
        }
        int index = Math.abs(target.hashCode()) % capacity;
        if (map[index] == null) {
            return false;
        }
        for (T entry : map[index]) {
            if (entry.equals(target)) {
                return true;
            }
        }
        return false;
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
    public Iterator<T> iterator() {
        UnorderedListADT<T> list = new LinkedUnorderedList<>();
        for (UnorderedListADT<T> entryList : map) {
            if (entryList != null) {
                for (T entry : entryList) {
                    list.addToRear(entry);
                }
            }
        }
        return list.iterator();
    }
}
