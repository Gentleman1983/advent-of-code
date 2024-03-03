package de.havox_design.aoc20xx.day13;

import de.havox_design.aoc.utils.java.AoCFunctionality;

import java.util.List;

public class Day13 implements AoCFunctionality {
    private final List<String> input;

    public Day13(String fileName) {
        input = readData(fileName);
    }

    public static long processTask1(String fileName) {
        Day13 instance = new Day13(fileName);
        return instance.processTask1();
    }

    public static long processTask2(String fileName) {
        Day13 instance = new Day13(fileName);
        return instance.processTask2();
    }

    public long processTask1() {
        return 1L;
    }

    public long processTask2() {
        return 1L;
    }
}
