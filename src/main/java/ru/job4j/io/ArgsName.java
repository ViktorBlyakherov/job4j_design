package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException(String.format("Invalid argument name %s", key));
        }
        return values.get(key);
    }

    private void parse(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("There are no arguments");
        }
        for (String arg : args) {
            if (arg.length() < 4 || !arg.contains("=") || arg.charAt(0) != '-' || "-=".equals(arg.substring(0, 2))) {
                throw new IllegalArgumentException(String.format("Invalid argument format %s", arg));
            }
            int ind = arg.indexOf("=");
            values.put(arg.substring(1, ind), arg.substring(ind + 1));
        }
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}