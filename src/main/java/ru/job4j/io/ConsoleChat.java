package ru.job4j.io;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    private String getPhrase(List<String> phrases) {
        int ind = (int)  (Math.random() * phrases.size());
        return phrases.get(ind);
    }

    public void run() {
        List<String> phrases = readPhrases();
        List<String> dialog = new ArrayList<>();
        String userAnswer = "";
        boolean isStop = false;
        Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8);
        while (!OUT.equals(userAnswer)) {
            userAnswer = scanner.nextLine();
            if (STOP.equals(userAnswer)) {
                isStop = true;
            }

            if (CONTINUE.equals(userAnswer)) {
                isStop = false;
            }
            dialog.add(userAnswer);

            if (!isStop && !OUT.equals(userAnswer)) {
                String answer = getPhrase(phrases);
                System.out.println(answer);
                dialog.add(answer);
            }
        }
        saveLog(dialog);
    }

    private List<String> readPhrases() {
        return UsageEncoding.readFile(botAnswers);
    }

    private void saveLog(List<String> log) {
        UsageEncoding.writeDataInFile(path, log);
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("log.txt", "bot_answer.txt");
        cc.run();
    }
}