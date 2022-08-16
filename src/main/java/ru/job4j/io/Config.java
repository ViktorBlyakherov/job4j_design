package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {

    private final String path;
    final Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    private boolean isCorrectLine(String line) {
        boolean rsl = true;
        if (line.length() < 3 || !line.contains("=") || line.indexOf("=") == 0
                || line.indexOf("=") == line.length() - 1) {
            rsl  = false;
        }
        return rsl;
    }

    private String getKey(String line) {
        int ind = line.indexOf("=");
        return line.substring(0, ind);
    }

    private String getValue(String line) {
        int ind = line.indexOf("=");
        return line.substring(ind + 1, line.length());
    }

    public void load() {
        try (BufferedReader in = new BufferedReader(new FileReader(path))) {
            for (String line = in.readLine(); line != null; line = in.readLine()) {
                if (!line.isBlank() && line.charAt(0) != '#') {
                    if (!isCorrectLine(line)) {
                        throw new IllegalArgumentException("Invalid format: " + line);
                    }
                    values.put(getKey(line), getValue(line));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        return values.getOrDefault(key, null);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("app.properties"));
        System.out.println(new Config("app.properties").values);
    }

}
