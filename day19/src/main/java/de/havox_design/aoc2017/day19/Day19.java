package de.havox_design.aoc2017.day19;

import de.havox_design.aoc.utils.DataReader;

import java.util.List;

public class Day19 {
    private final List<String> input;

    public Day19(String fileName) {
        input = readData(fileName);
    }

    public static String solvePart1(String fileName) {
        Day19 instance = new Day19(fileName);
        return instance.solvePart1();
    }

    public static long solvePart2(String fileName) {
        Day19 instance = new Day19(fileName);
        return instance.solvePart2();
    }

    public String solvePart1() {
        return "ABCDEF";
    }

    public long solvePart2() {return 0L;
    }

    private List<String> readData(String fileName) {
        return DataReader.readData(fileName, MainClass.class);
    }
}
