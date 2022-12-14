package ru.job4j.serialization.json;

import java.util.Arrays;

public class PC {
    private boolean isNotebook;
    private int memory;

    private String brand;
    private Complectation complectation;
    private String[] info;

    public PC(boolean isNotebook, int memory, String brand, Complectation complectation, String[] info) {
        this.isNotebook = isNotebook;
        this.memory = memory;
        this.brand = brand;
        this.complectation = complectation;
        this.info = info;
    }

    public boolean isNotebook() {
        return isNotebook;
    }

    public int getMemory() {
        return memory;
    }

    public String getBrand() {
        return brand;
    }

    public Complectation getComplectation() {
        return complectation;
    }

    public String[] getInfo() {
        return info;
    }

    @Override
    public String toString() {
        return "PC{"
                + "isNotebook=" + isNotebook
                + ", memory=" + memory
                + ", brand='" + brand + '\''
                + ", complectation=" + complectation
                + ", info=" + Arrays.toString(info)
                + '}';
    }
}
