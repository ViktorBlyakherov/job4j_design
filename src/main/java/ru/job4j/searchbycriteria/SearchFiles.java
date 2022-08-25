package ru.job4j.searchbycriteria;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static java.nio.file.FileVisitResult.CONTINUE;

public class SearchFiles implements FileVisitor<Path> {

    private List<Path> paths = new ArrayList<>();
    private String typeSearch;
    private String mask;

    public SearchFiles(String typeSearch, String mask) {
        this.typeSearch = typeSearch;
        this.mask = mask;
    }

    public List<Path> getPaths() {
        return paths;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        return CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if ("name".equals(typeSearch)) {
            if (file.getFileName().toString().equals(mask)) {
                paths.add(file);
            }
        } else if ("regex".equals(typeSearch)) {
            FileSystem fs = FileSystems.getDefault();
            PathMatcher pm = fs.getPathMatcher("regex:" + mask);
            if (pm.matches(file)) {
                paths.add(file);
            }
        } else if ("mask".equals(typeSearch)) {
            FileSystem fs = FileSystems.getDefault();
            PathMatcher pm = fs.getPathMatcher("glob:**" + mask + "*");
            if (pm.matches(file)) {
                paths.add(file);
            }
        }
        return CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
        return CONTINUE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
        return CONTINUE;
    }
}
