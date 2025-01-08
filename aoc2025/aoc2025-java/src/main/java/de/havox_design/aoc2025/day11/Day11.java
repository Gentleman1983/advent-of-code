package de.havox_design.aoc2025.day11;

import de.havox_design.aoc.utils.java.AoCFunctionality;

import java.util.List;

public class Day11 implements AoCFunctionality {
    private final List<String> input;

    public Day11(String fileName) {
        input = readData(fileName);
    }

    public static long processPart1(String fileName) {
        Day11 instance = new Day11(fileName);
        return instance.processPart1();
    }

    public static long processPart2(String fileName) {
        Day11 instance = new Day11(fileName);
        return instance.processPart2();
    }

    public long processPart1() {
        return 1L;
    }

    public long processPart2() {
        return 1L;
    }
}
