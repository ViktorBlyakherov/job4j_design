package ru.job4j.serialization.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

public class XMLExample {
    public static void main(String[] args) throws JAXBException {
        final ru.job4j.serialization.xml.Complectation complectation = new Complectation("1TB", "i7");

        final PC pc = new PC(true, 512, "Lenovo", complectation,
                new String[] {"Warranty 2 years", "Russian keyboard"});

        JAXBContext context = JAXBContext.newInstance(ru.job4j.serialization.xml.PC.class);
        /* Создаем сериализатор */
        Marshaller marshaller = context.createMarshaller();
        /* Указываем, что нам нужно форматирование */
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            /* Сериализуем */
            marshaller.marshal(pc, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        /* Для десериализации нам нужно создать десериализатор */
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            /* десериализуем */
            PC result = (PC) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }
    }
}
