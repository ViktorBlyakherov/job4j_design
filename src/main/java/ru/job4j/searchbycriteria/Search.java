package ru.job4j.searchbycriteria;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Search {

    private static void checkArgs(ArgsName names) {
        if (names.getSize() != 4) {
            throw new IllegalArgumentException("Use -d=DIRECTORY_START -n=MASK_NAME_REGEXP "
                    + "-t=MASK or NAME or REGEX -o=OUTPUT_FILE");
        }

        File file = new File(names.get("d"));
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not a directory %s", names.get("d")));
        }
        String tParam = names.get("t");
        if (!"mask".equals(tParam) && !"name".equals(tParam) && !"regex".equals(tParam)) {
            throw new IllegalArgumentException(String.format("Not a vaild value %s. Use mask or name or regex",
                    names.get("d")));
        }
    }

    private static void saveToFile(List<Path> paths, String outputFile) {
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(outputFile)
                ))) {
            for (Path path : paths) {
                out.println(path.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        ArgsName names = ArgsName.of(args);
        checkArgs(names);
        String findTemplate = "";

        if ("name".equals(names.get("t"))) {
            findTemplate = "glob:" + names.get("n");
        } else if ("regex".equals(names.get("t"))) {
            findTemplate = "regex:" + names.get("n");
        } else if ("mask".equals(names.get("t"))) {
            findTemplate = "glob:**" + names.get("n") + "*";
        }
        SearchFiles searcher = new SearchFiles(findTemplate);
        Files.walkFileTree(Path.of(names.get("d")), searcher);
        saveToFile(searcher.getPaths(), names.get("o"));
    }
}
