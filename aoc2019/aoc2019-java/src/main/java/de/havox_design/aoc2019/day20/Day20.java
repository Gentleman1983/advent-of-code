package de.havox_design.aoc2019.day20;

import de.havox_design.aoc.utils.java.AoCFunctionality;

public class Day20 implements AoCFunctionality {
    private final String input;

    public Day20(String fileName) {
        input = readString(fileName);
    }

    public static long processTask1(String fileName) {
        Day20 instance = new Day20(fileName);
        return instance.processTask1();
    }

    public static long processTask2(String fileName) {
        Day20 instance = new Day20(fileName);
        return instance.processTask2();
    }

    public long processTask1() {
        return 0;
    }

    public long processTask2() {
        return 0;
    }
}
