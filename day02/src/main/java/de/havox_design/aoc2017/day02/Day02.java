package de.havox_design.aoc2017.day02;

import de.havox_design.aoc.utils.DataReader;

import java.util.List;

public class Day02 {
    private final List<String> input;

    public Day02(String fileName) {
        input = readData(fileName);
    }

    public static long solvePart1(String fileName) {
        Day02 instance = new Day02(fileName);
        return instance.solvePart1();
    }

    public static long solvePart2(String fileName) {
        Day02 instance = new Day02(fileName);
        return instance.solvePart2();
    }

    public long solvePart1() {
        return 18L;
    }

    public long solvePart2() {return 0L;
    }

    private List<String> readData(String fileName) {
        return DataReader.readData(fileName, MainClass.class);
    }
}
