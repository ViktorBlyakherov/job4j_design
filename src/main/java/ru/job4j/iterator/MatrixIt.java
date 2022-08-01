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
        boolean rsl = false;
//        int i = row;
//        int j = column;
//        while (i < data.length && j < data[i].length && !rsl) {
//            if (j == data[i].length) {
//                j = 0;
//                i++;
//            } else {
//                row = i;
//                column = j;
//                rsl = true;
//            }
//        }

        for (int i = row; i < data.length; i++) {
            for (int j = column; j < data[i].length; j++) {
                rsl = true;
                row = i;
                column = j;
                break;
            }
            if (rsl) {
                break;
            }
            column = 0;
        }
        return rsl;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[row][column++];
    }
}