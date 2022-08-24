package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;

public class JsonExample {

    public static void main(String[] args) {
        final Complectation complectation = new Complectation("1TB", "i7");

        final PC pc = new PC(true, 512, "Lenovo", complectation,
                new String[] {"Warranty 2 years", "Russian keyboard"});

        /* Преобразуем объект person в json-строку. */
        final Gson gson = new GsonBuilder().create();
        String tmpStr = gson.toJson(pc);
        System.out.println(tmpStr);
        final PC tmpPC = gson.fromJson(tmpStr, PC.class);
        System.out.println(tmpPC);
    }
}
