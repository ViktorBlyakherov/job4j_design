package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import static ru.job4j.io.Search.search;

public class Zip {

    public void packFiles(List<File> sources, File target) {
            try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
                for (File source : sources) {
                    zip.putNextEntry(new ZipEntry(source.getPath()));
                    try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                        zip.write(out.readAllBytes());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void checkArgs(ArgsName names) {
        if (names.get("d") == null || names.get("e") == null || names.get("o") == null) {
            throw new IllegalArgumentException("Invalid parameters. "
                    + "Use java -jar pack.jar -d=PATH -e=.EXT -o=ARCHIVE_NAME");
        }

        File file = new File(names.get("d"));
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsoluteFile()));
        }

        if (names.get("o").charAt(0) != '.') {
            throw new IllegalArgumentException(String.format("Not a file extension %s", names.get("o")));
        }

    }

    public static void main(String[] args) throws IOException {
        ArgsName names = ArgsName.of(args);
        checkArgs(names);
        Zip zip = new Zip();
        File target = new File(names.get("o"));
        List<Path> paths = Search.search(Path.of(names.get("d")), p -> !p.toFile().getName().endsWith(names.get("e")));
        zip.packFiles(paths.stream().map(Path::toFile).toList(), target);
    }
}