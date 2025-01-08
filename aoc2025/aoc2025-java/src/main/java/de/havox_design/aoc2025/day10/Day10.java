package de.havox_design.aoc2025.day10;

import de.havox_design.aoc.utils.java.AoCFunctionality;

import java.util.List;

public class Day10 implements AoCFunctionality {
    private final List<String> input;

    public Day10(String fileName) {
        input = readData(fileName);
    }

    public static long processPart1(String fileName) {
        Day10 instance = new Day10(fileName);
        return instance.processPart1();
    }

    public static long processPart2(String fileName) {
        Day10 instance = new Day10(fileName);
        return instance.processPart2();
    }

    public long processPart1() {
        return 1L;
    }

    public long processPart2() {
        return 1L;
    }
}
