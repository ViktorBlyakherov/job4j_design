package ru.job4j.collection;

import java.util.*;

public class SimpleArrayList<T> implements SimpleList<T> {

    private T[] container;

    private int size;

    private int modCount;

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        if (size != 0 && size >= container.length) {
            grow();
        }
        container[size] = value;
        size++;
        modCount++;
    }

    @Override
    public T set(int index, T newValue) {
        Objects.checkIndex(index, size);
        T tempEl = container[index];
        container[index] = newValue;
        return tempEl;
    }

    @Override
    public T remove(int index) {
        Objects.checkIndex(index, size);
        T tempEl = container[index];
        if (size - 1 > index) {
            System.arraycopy(container, index + 1, container, index, size - index - 1);
        }
        container[size - 1] = null;
        size--;
        modCount++;
        return tempEl;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return container[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int expectedModCount = modCount;

            private int currentIndex;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return currentIndex < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return container[currentIndex++];
            }

        };
    }

    private void grow() {
        container = Arrays.copyOf(container, container.length * 2);
    }
}
