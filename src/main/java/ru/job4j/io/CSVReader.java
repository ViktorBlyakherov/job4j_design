package ru.job4j.io;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringJoiner;

public class CSVReader {
    public static void handle(ArgsName argsName) throws Exception {
        checkArgs(argsName);
        String output = argsName.get("out");
        String[] filter = argsName.get("filter").split(",");

     }

    private static void checkArgs(ArgsName argsName) {
        if (argsName.getSize() != 4) {
            throw new IllegalArgumentException("Use java -jar target/csvReader.jar -path=file.csv "
                   + "-delimiter=\";\"  -out=stdout -filter=name,age");
        }

        if (!"stdout".equals(argsName.get("out"))) {
            File file = new File(argsName.get("out"));
            if (!file.exists()) {
                throw new IllegalArgumentException(String.format("Not exist %s", argsName.get("out")));
            }
        }

        File inputFile = new File(argsName.get("path"));
        if (!inputFile.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", argsName.get("path")));
        }

        if (argsName.get("delimiter").length() < 3) {
            throw new IllegalArgumentException(String.format("Invalid delimiter %s", argsName.get("delimiter")));
        }

        if (argsName.get("filter").length() == 0) {
            throw new IllegalArgumentException("There is no filter");
        }
    }
}