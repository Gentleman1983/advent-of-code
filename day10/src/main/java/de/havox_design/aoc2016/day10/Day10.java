package de.havox_design.aoc2016.day10;

import de.havox_design.aoc2016.utils.DataReader;

import java.util.List;

public class Day10 {
    private final List<String> input;

    public Day10(String fileName) {
        input = readData(fileName);
    }

    public static long solvePart1(String fileName) {
        Day10 instance = new Day10(fileName);
        return instance.solvePart1();
    }

    public static long solvePart2(String fileName) {
        Day10 instance = new Day10(fileName);
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
