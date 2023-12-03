package de.havox_design.aoc2016.day24;

import de.havox_design.aoc2016.utils.input.DataReader;

import java.util.List;

public class Day24 {
    private final List<String> input;

    public Day24(String fileName) {
        input = readData(fileName);
    }

    public static long solvePart1(String fileName) {
        Day24 instance = new Day24(fileName);
        return instance.solvePart1();
    }

    public static long solvePart2(String fileName) {
        Day24 instance = new Day24(fileName);
        return instance.solvePart2();
    }

    public long solvePart1() {
        return 0L;
    }

    public long solvePart2() {
        return 0L;
    }

    private List<String> readData(String fileName) {
        return DataReader.readData(fileName, MainClass.class);
    }
}
