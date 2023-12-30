package de.havox_design.aoc2016.day09;

import de.havox_design.aoc.utils.java.input.DataReader;

import java.util.List;

public class Day09 {
    private final List<String> input;

    public Day09(String fileName) {
        input = readData(fileName);
    }

    public static long solvePart1(String fileName) {
        Day09 instance = new Day09(fileName);
        return instance.solvePart1();
    }

    public static long solvePart2(String fileName) {
        Day09 instance = new Day09(fileName);
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
