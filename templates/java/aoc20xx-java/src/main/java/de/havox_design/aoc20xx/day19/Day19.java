package de.havox_design.aoc2025.day19;

import de.havox_design.aoc.utils.java.AoCFunctionality;

import java.util.List;

public class Day19 implements AoCFunctionality {
    private final List<String> input;

    public Day19(String fileName) {
        input = readData(fileName);
    }

    public static long processTask1(String fileName) {
        Day19 instance = new Day19(fileName);
        return instance.processTask1();
    }

    public static long processTask2(String fileName) {
        Day19 instance = new Day19(fileName);
        return instance.processTask2();
    }

    public long processTask1() {
        return 1L;
    }

    public long processTask2() {
        return 1L;
    }
}
