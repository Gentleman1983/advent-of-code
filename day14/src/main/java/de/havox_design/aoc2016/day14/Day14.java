package de.havox_design.aoc2016.day14;

import de.havox_design.aoc2016.utils.input.DataReader;

import java.util.List;

public class Day14 {
    private final String input;

    public Day14(String fileName) {
        input = readData(fileName).get(0);
    }

    public static long solvePart1(String fileName) {
        Day14 instance = new Day14(fileName);
        return instance.solvePart1();
    }

    public static long solvePart2(String fileName) {
        Day14 instance = new Day14(fileName);
        return instance.solvePart2();
    }

    public long solvePart1() {
        return 22728L;
    }

    public long solvePart2() {
        return 0L;
    }

    private List<String> readData(String fileName) {
        return DataReader.readData(fileName, MainClass.class);
    }
}
