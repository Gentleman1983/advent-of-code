package de.havox_design.aoc2025.day09;

import de.havox_design.aoc.utils.java.AoCFunctionality;

import java.util.List;

public class Day09 implements AoCFunctionality {
    private final List<String> input;

    public Day09(String fileName) {
        input = readData(fileName);
    }

    public static long processPart1(String fileName) {
        Day09 instance = new Day09(fileName);
        return instance.processPart1();
    }

    public static long processPart2(String fileName) {
        Day09 instance = new Day09(fileName);
        return instance.processPart2();
    }

    public long processPart1() {
        return 1L;
    }

    public long processPart2() {
        return 1L;
    }
}
