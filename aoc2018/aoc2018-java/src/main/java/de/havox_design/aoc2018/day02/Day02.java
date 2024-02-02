package de.havox_design.aoc2018.day02;

import de.havox_design.aoc.utils.java.AoCFunctionality;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day02 implements AoCFunctionality {
    private final List<String> input;

    public Day02(String fileName) {
        input = readData(fileName);
    }

    public static long processTask1(String fileName) {
        Day02 instance = new Day02(fileName);
        return instance.processTask1();
    }

    public static long processTask2(String fileName) {
        Day02 instance = new Day02(fileName);
        return instance.processTask2();
    }

    public long processTask1() {
        long twos = 0L;
        long threes = 0L;

        for(String line : input) {
            Map<Character, Long> counts = countSymbols(line);

            if (counts.containsValue(2L)) {
                twos++;
            }

            if (counts.containsValue(3L)) {
                threes++;
            }
        }

        return twos * threes;
    }

    public long processTask2() {
        return 0;
    }

    private Map<Character, Long> countSymbols(String line) {
        Map<Character, Long> result = new HashMap<>();

        for(char c : line.toCharArray()) {
            long oldCount = result.getOrDefault(c, 0L);
            result.put(c, oldCount + 1);
        }

        return result;
    }
}
