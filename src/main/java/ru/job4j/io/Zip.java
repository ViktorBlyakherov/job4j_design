package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import static ru.job4j.io.Search.search;

public class Zip {

    public void packFiles(List<Path> sources, File target) {
            try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
                for (Path source : sources) {
                    zip.putNextEntry(new ZipEntry(source.toString()));
                    try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source.toFile()))) {
                        zip.write(out.readAllBytes());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    private static void checkArgs(ArgsName names) {
        File file = new File(names.get("d"));
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", names.get("d")));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", names.get("d")));
        }

        if (names.get("e").charAt(0) != '.') {
            throw new IllegalArgumentException(String.format("Not a file extension %s", names.get("o")));
        }

    }

    public static void main(String[] args) throws IOException {
        if (args.length != 3) {
            throw new IllegalArgumentException("Invalid parameters. "
                    + "Use java -jar pack.jar -d=PATH -e=.EXT -o=ARCHIVE_NAME");
        }
        ArgsName names = ArgsName.of(args);
        checkArgs(names);
        Zip zip = new Zip();
        File target = new File(names.get("o"));
        List<Path> paths = Search.search(Path.of(names.get("d")), p -> !p.toFile().getName().endsWith(names.get("e")));
        zip.packFiles(paths, target);
    }
}