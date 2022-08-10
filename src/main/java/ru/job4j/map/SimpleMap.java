package ru.job4j.map;

import java.util.*;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        boolean rsl = false;
        int ind = key == null ? 0 : key.hashCode();
        if (table[indexFor(hash(ind))] == null) {
            table[indexFor(hash(ind))] = new MapEntry<>(key, value);
            rsl = true;
            count++;
            modCount++;
            if (count >= table.length * LOAD_FACTOR) {
                expand();
            }
        }
        return rsl;
    }

    private int hash(int hashCode) {
        return hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return (table.length - 1) & hash;
    }

    private void expand() {
        int newSize = table.length * 2;
        MapEntry<K, V>[] newTable = new MapEntry[newSize];
        MapEntry<K, V>[] tempTable = table;
        table = newTable;
        for (MapEntry<K, V> element : tempTable) {
            if (element != null) {
                int ind = element.key == null ? 0 : element.key.hashCode();
                table[indexFor(hash(ind))] = element;
            }
        }
    }

    @Override
    public V get(K key) {
        int ind = key == null ? 0 : key.hashCode();
        return table[indexFor(hash(ind))] == null ? null : table[indexFor(hash(ind))].value;
    }

    @Override
    public boolean remove(K key) {
        boolean rsl = false;
        int ind = key == null ? 0 : key.hashCode();
        if (table[indexFor(hash(ind))] != null) {
            rsl = true;
            table[indexFor(hash(ind))] = null;
            count--;
            modCount++;
        }
        return rsl;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            private int expectedModCount = modCount;

            private int currentIndex;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return currentIndex < count;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                int countInd = 0;
                for (MapEntry<K, V> map : table) {
                    if (map != null) {
                        if (countInd == currentIndex) {
                            currentIndex++;
                            return map.key;
                        }
                        countInd++;
                    }
                }
                return null;
            }
        };
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

    }

}