package de.havox_design.aoc2025.day08;

import de.havox_design.aoc.utils.java.AoCFunctionality;

import java.util.List;

public class Day08 implements AoCFunctionality {
    private final List<String> input;

    public Day08(String fileName) {
        input = readData(fileName);
    }

    public static long processTask1(String fileName) {
        Day08 instance = new Day08(fileName);
        return instance.processTask1();
    }

    public static long processTask2(String fileName) {
        Day08 instance = new Day08(fileName);
        return instance.processTask2();
    }

    public long processTask1() {
        return 1L;
    }

    public long processTask2() {
        return 1L;
    }
}
