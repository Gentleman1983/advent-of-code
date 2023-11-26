package de.havox_design.aoc2017.day08;

import de.havox_design.aoc2017.utils.DataReader;

import java.util.List;

public class Day08 {
    private final List<String> input;

    public Day08(String fileName) {
        input = readData(fileName);
    }

    public static long solvePart1(String fileName) {
        Day08 instance = new Day08(fileName);
        return instance.solvePart1();
    }

    public static long solvePart2(String fileName) {
        Day08 instance = new Day08(fileName);
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
