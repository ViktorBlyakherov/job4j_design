package ru.job4j.serialization.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "complectation")
public class Complectation {
    @XmlAttribute
    private String hdd;
    @XmlAttribute
    private String processor;

    public Complectation() {

    }

    public Complectation(String hdd, String processor) {
        this.hdd = hdd;
        this.processor = processor;
    }

    @Override
    public String toString() {
        return "Complectation{"
                + "hdd='" + hdd + '\''
                + ", processor='" + processor + '\''
                + '}';
    }
}
