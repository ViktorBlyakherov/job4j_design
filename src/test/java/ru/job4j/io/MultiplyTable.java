package ru.job4j.io;

import javax.imageio.IIOException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MultiplyTable {
    public static void main(String[] args) {
        try (FileOutputStream out = new FileOutputStream("multiplytable.txt")) {
            for (int i = 1; i <= 10; i++) {
                for (int j = 1; j <= 10; j++) {
                    String temp = i + "*" + j + "=" + i * j;
                    out.write(temp.getBytes());
                    out.write(System.lineSeparator().getBytes());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
