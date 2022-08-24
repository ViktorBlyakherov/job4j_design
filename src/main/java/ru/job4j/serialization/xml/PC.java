package ru.job4j.serialization.xml;

import java.util.Arrays;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.*;
import java.io.StringWriter;
import java.util.Arrays;

@XmlRootElement(name = "pc")
@XmlAccessorType(XmlAccessType.FIELD)
public class PC {
    @XmlAttribute
    private boolean isNotebook;
    @XmlAttribute
    private int memory;
    @XmlAttribute
    private String brand;
    private Complectation complectation;
    private String[] info;

    public PC() {

    }

    public PC(boolean isNotebook, int memory, String brand, Complectation complectation, String[] info) {
        this.isNotebook = isNotebook;
        this.memory = memory;
        this.brand = brand;
        this.complectation = complectation;
        this.info = info;
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
