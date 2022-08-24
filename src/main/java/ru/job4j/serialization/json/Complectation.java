package ru.job4j.serialization.json;

public class Complectation {
    private String hdd;

    private String processor;

    public Complectation(String hdd, String processor) {
        this.hdd = hdd;
        this.processor = processor;
    }

    public String getHdd() {
        return hdd;
    }

    public String getProcessor() {
        return processor;
    }

    @Override
    public String toString() {
        return "Complectation{"
                + "hdd='" + hdd + '\''
                + ", processor='" + processor + '\''
                + '}';
    }
}
