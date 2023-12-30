package de.havox_design.aoc2016.day23;

import de.havox_design.aoc2016.utils.DataReader;

import java.util.List;

public class Day23 {
    private final List<String> input;

    public Day23(String fileName) {
        input = readData(fileName);
    }

    public static long solvePart1(String fileName) {
        Day23 instance = new Day23(fileName);
        return instance.solvePart1();
    }

    public static long solvePart2(String fileName) {
        Day23 instance = new Day23(fileName);
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
