package de.havox_design.aoc2015.day24;

import java.util.*;

public class LengthSortedResult implements Iterable<Integer[]> {

    private final List<Integer[]>[] data;

    private int size;

    @SuppressWarnings("unchecked")
    public LengthSortedResult(int maxlength) {
        data = new List[maxlength];

        for (int i = 0; i < data.length; i++) {
            data[i] = new ArrayList<>();
        }
    }

    public void add(Integer[] pair) {
        data[pair.length].add(pair);
        size++;
    }

    @Override
    public Iterator<Integer[]> iterator() {
        return Arrays.stream(data).flatMap(Collection::stream).iterator();
    }

    public int size() {
        return size;
    }
}
