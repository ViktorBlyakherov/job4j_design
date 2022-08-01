package ru.job4j.iterator;


import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenNumbersIterator implements Iterator<Integer> {

    private int[] data;
    private int index = 0;

    public EvenNumbersIterator(int[] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        return findNextEvenIndex() != -1;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        index = findNextEvenIndex();
        return data[index++];
    }

    private int findNextEvenIndex() {
        int rsl = -1;
        for (int i = index; i < data.length; i++) {
            if (data[i] % 2 == 0) {
                rsl = i;
                break;
            }
        }
        return rsl;
    }
}