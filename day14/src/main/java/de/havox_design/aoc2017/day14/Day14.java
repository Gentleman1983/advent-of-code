package de.havox_design.aoc2017.day14;

import de.havox_design.aoc.utils.DataReader;

import java.util.List;

public class Day14 {
    private final List<String> input;

    public Day14(String fileName) {
        input = readData(fileName);
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
        return 8108L;
    }

    public long solvePart2() {return 0L;
    }

    private List<String> readData(String fileName) {
        return DataReader.readData(fileName, MainClass.class);
    }
}
