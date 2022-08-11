package ru.job4j.question;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        int added = 0;
        int changed = 0;
        int deleted = 0;

        Set<Integer> idSet = new TreeSet<>();

        Map<Integer, User> mapPrev = previous.stream().collect(Collectors.toMap(k -> k.getId(), v -> v));
        idSet.addAll(mapPrev.keySet().stream().toList());

        Map<Integer, User> mapCurr = current.stream().collect(Collectors.toMap(k -> k.getId(), v -> v));
        idSet.addAll(mapCurr.keySet().stream().toList());

        for (int id : idSet) {
            if (mapPrev.containsKey(id) && !mapCurr.containsKey(id)) {
                deleted++;
            } else if (!mapPrev.containsKey(id) && mapCurr.containsKey(id)) {
                added++;
            } else {
                if (!mapCurr.get(id).equals(mapPrev.get(id))) {
                    changed++;
                }
            }
        }
        return new Info(added, changed, deleted);
    }

}