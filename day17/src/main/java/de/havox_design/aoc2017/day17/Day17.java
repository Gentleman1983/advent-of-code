package de.havox_design.aoc2017.day17;

import de.havox_design.aoc.utils.DataReader;

import java.util.List;

public class Day17 {
    private final List<String> input;

    public Day17(String fileName) {
        input = readData(fileName);
    }

    public static long solvePart1(String fileName) {
        Day17 instance = new Day17(fileName);
        return instance.solvePart1();
    }

    public static long solvePart2(String fileName) {
        Day17 instance = new Day17(fileName);
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
