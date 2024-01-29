package de.havox_design.aoc2018.day23;

import de.havox_design.aoc.utils.java.AoCFunctionality;

public class Day23 implements AoCFunctionality {
    private final String input;

    public Day23(String fileName) {
        input = readString(fileName);
    }

    public static long processTask1(String fileName) {
        Day23 instance = new Day23(fileName);
        return instance.processTask1();
    }

    public static long processTask2(String fileName) {
        Day23 instance = new Day23(fileName);
        return instance.processTask2();
    }

    public long processTask1() {
        return 0;
    }

    public long processTask2() {
        return 0;
    }
}
