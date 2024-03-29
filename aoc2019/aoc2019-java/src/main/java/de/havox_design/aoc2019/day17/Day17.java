package de.havox_design.aoc2019.day17;

import de.havox_design.aoc.utils.java.AoCFunctionality;

public class Day17 implements AoCFunctionality {
    private final String input;

    public Day17(String fileName) {
        input = readString(fileName);
    }

    public static long processTask1(String fileName) {
        Day17 instance = new Day17(fileName);
        return instance.processTask1();
    }

    public static long processTask2(String fileName) {
        Day17 instance = new Day17(fileName);
        return instance.processTask2();
    }

    public long processTask1() {
        return 0;
    }

    public long processTask2() {
        return 0;
    }
}
