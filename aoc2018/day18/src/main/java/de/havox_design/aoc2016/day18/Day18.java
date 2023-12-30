package de.havox_design.aoc2016.day18;

import de.havox_design.utils.DataReader;

import java.util.List;

public class Day18 {
    private final List<String> input;

    public Day18(String fileName) {
        input = readData(fileName);
    }

    public static long solvePart1(String fileName) {
        Day18 instance = new Day18(fileName);
        return instance.solvePart1();
    }

    public static long solvePart2(String fileName) {
        Day18 instance = new Day18(fileName);
        return instance.solvePart2();
    }

    public long solvePart1() {
        return 0L;
    }

    public long solvePart2() {return 0L;
    }

    private List<String> readData(String fileName) {
        return DataReader.readData(fileName, MainClass.class);
    }
}
