package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Analizy {
    public void unavailable(String source, String target) {
        try (BufferedReader in = new BufferedReader(new FileReader(source)); PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
            String beginPeriod = "";
            String endPeriod = "";
            String period = "";
            for (String line = in.readLine(); line != null; line = in.readLine()) {
                int status = Integer.parseInt(line.substring(0, 3));
                period = line.substring(4);
                if (status == 400 || status == 500) {
                    if (beginPeriod.isEmpty()) {
                        beginPeriod = period;
                    }
                } else if (endPeriod.isEmpty() && !beginPeriod.isEmpty()) {
                    endPeriod = period;
                    out.println(beginPeriod + ";" + endPeriod + ";");
                    beginPeriod = "";
                    endPeriod = "";
                }
            }

            if (!beginPeriod.isEmpty() && endPeriod.isEmpty()) {
                out.println(beginPeriod + ";" + period + ";");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analizy an = new Analizy();
        an.unavailable("server.log", "unavailable.log");

    }
}