package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Analizy {
    public void unavailable(String source, String target) {
        List<String> rsl = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(source))) {
            String beginPeriod = "";
            String endPeriod = "";
            String period = "";
            for (String line = in.readLine(); line != null; line = in.readLine()) {
                int status = Integer.parseInt(line.substring(0, 3));
                period = line.substring(4, line.length());
                if (status == 400 || status == 500) {
                    if (beginPeriod.isEmpty()) {
                        beginPeriod = period;
                    }
                } else if (endPeriod.isEmpty() && !beginPeriod.isEmpty()) {
                    endPeriod = period;
                    rsl.add(beginPeriod + ";" + endPeriod + ";");
                    beginPeriod = "";
                    endPeriod = "";
                }
            }

            if (!beginPeriod.isEmpty() && endPeriod.isEmpty()) {
                rsl.add(beginPeriod + ";" + period + ";");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
            for (String str : rsl) {
                out.println(str);
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