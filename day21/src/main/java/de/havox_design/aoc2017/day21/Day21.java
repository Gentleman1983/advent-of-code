package de.havox_design.aoc2017.day21;

import de.havox_design.aoc.utils.DataReader;

import java.util.List;

public class Day21 {
    private final List<String> input;

    public Day21(String fileName) {
        input = readData(fileName);
    }

    public static long solvePart1(String fileName, int iterations) {
        Day21 instance = new Day21(fileName);
        return instance.solvePart1(iterations);
    }

    public static long solvePart2(String fileName) {
        Day21 instance = new Day21(fileName);
        return instance.solvePart2();
    }

    public long solvePart1(int iterations) {
        return 0L;
    }

    public long solvePart2() {return 0L;
    }

    private List<String> readData(String fileName) {
        return DataReader.readData(fileName, MainClass.class);
    }
}
