package ru.job4j.io.duplicates;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private Map<FileProperty, List<String>> map = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty tmpFile = new FileProperty(file.toFile().length(), file.getFileName().toString());
        map.putIfAbsent(tmpFile, new ArrayList<>());
        map.get(tmpFile).add(file.toFile().getAbsolutePath());
        return super.visitFile(file, attrs);
    }

    public void printDuplicates() {
        for (FileProperty file : map.keySet()) {
            List<String> tmpList = map.get(file);
            if (tmpList.size() > 1) {
                System.out.println(file.getName() + " - " + file.getSize());
                for (String str : tmpList) {
                    System.out.println("    " + str);
                }
            }
        }
    }
}