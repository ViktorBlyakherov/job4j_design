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
        int ind = -1;
        for (int i = index; i < data.length; i++) {
            if (data[i] % 2 == 0) {
                ind = i;
                break;
            }
        }
        boolean rsl;

        if (ind == -1) {
            rsl = false;
        } else {
            index = ind;
            rsl  = true;

        }
        return rsl;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[index++];
    }
}