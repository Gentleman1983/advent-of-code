package de.havox_design.aoc2018.day10;

import de.havox_design.aoc.utils.java.AoCFunctionality;

public class Day10 implements AoCFunctionality {
    private final String input;

    public Day10(String fileName) {
        input = readString(fileName);
    }

    public static long processTask1(String fileName) {
        Day10 instance = new Day10(fileName);
        return instance.processTask1();
    }

    public static long processTask2(String fileName) {
        Day10 instance = new Day10(fileName);
        return instance.processTask2();
    }

    public long processTask1() {
        return 0;
    }

    public long processTask2() {
        return 0;
    }
}
