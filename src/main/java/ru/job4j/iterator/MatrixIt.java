package ru.job4j.iterator;


import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int row = 0;
    private int column = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        for (int i = row; i < data.length; i++) {
            for (int j = column; j < data[i].length; j++) {
                return true;
            }
            column = 0;
        }
        return false;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }

        for (int i = row; i < data.length; i++) {
            for (int j = column; j < data[i].length; j++) {

                if (column == data[i].length - 1) {
                    column = 0;
                    row = i + 1;
                } else {
                    row = i;
                    column = j + 1;
                }
                return data[i][j];
            }
            column = 0;
        }
        return null;
    }
}