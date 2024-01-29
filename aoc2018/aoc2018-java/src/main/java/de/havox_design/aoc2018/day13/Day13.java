package de.havox_design.aoc2018.day13;

import de.havox_design.aoc.utils.java.AoCFunctionality;

public class Day13 implements AoCFunctionality {
    private final String input;

    public Day13(String fileName) {
        input = readString(fileName);
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
        return 0;
    }

    public long processTask2() {
        return 0;
    }
}
