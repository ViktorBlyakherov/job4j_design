package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        String name = "Petr Arsentev";
        int age = 33;
        boolean isWork = false;
        byte childs = 3;
        short height = 170;
        long account = 234234;
        float weight = 90.5f;
        double amount = 100000.4f;
        char sex = 'M';
        LOG.debug("User info name : {}, age : {}", name, age);
        LOG.debug("Sex : {}, isWork : {}, childs : {}", sex, isWork, childs);
        LOG.debug("Height : {}, weight : {}", height, weight);
        LOG.debug("Account : {}, amount : {}", account, amount);

    }
}