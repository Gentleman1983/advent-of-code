package de.havox_design.aoc2019.day06;

import de.havox_design.aoc.utils.java.AoCFunctionality;

public class Day06 implements AoCFunctionality {
    private final String input;

    public Day06(String fileName) {
        input = readString(fileName);
    }

    public static long processTask1(String fileName) {
        Day06 instance = new Day06(fileName);
        return instance.processTask1();
    }

    public static long processTask2(String fileName) {
        Day06 instance = new Day06(fileName);
        return instance.processTask2();
    }

    public long processTask1() {
        return 0;
    }

    public long processTask2() {
        return 0;
    }
}
