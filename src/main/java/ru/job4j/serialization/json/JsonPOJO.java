package ru.job4j.serialization.json;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonPOJO {
    public static void main(String[] args) {

        /* JSONObject напрямую методом put */
        final Complectation complectation = new Complectation("1TB", "i7");
        final PC pc = new PC(true, 512, "Lenovo", complectation,
                new String[] {"Warranty 2 years", "Russian keyboard"});

        JSONObject complectationJSON = new JSONObject();
        complectationJSON.put("hdd", complectation.getHdd());
        complectationJSON.put("processor", complectation.getProcessor());

        List<String> list = new ArrayList<>();
        list.add("Warranty 2 years");
        list.add("Russian keyboard");
        JSONArray jsonInfo = new JSONArray(list);

        JSONObject pcJSON = new JSONObject();
        pcJSON.put("isNotebook", pc.isNotebook());
        pcJSON.put("memory", pc.getMemory());
        pcJSON.put("brand", pc.getBrand());
        pcJSON.put("complectation", complectationJSON);
        pcJSON.put("info", jsonInfo);

        System.out.println(pcJSON.toString());

        System.out.println(new JSONObject(pc).toString());
    }
}
