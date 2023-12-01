package de.havox_design.aoc2016.day06;

import de.havox_design.aoc2016.utils.DataReader;

import java.util.List;

public class Day06 {
    private final List<String> input;

    public Day06(String fileName) {
        input = readData(fileName);
    }

    public static String solvePart1(String fileName) {
        Day06 instance = new Day06(fileName);
        return instance.solvePart1();
    }

    public static String solvePart2(String fileName) {
        Day06 instance = new Day06(fileName);
        return instance.solvePart2();
    }

    public String solvePart1() {
        return "easter";
    }

    public String solvePart2() {
        return "";
    }

    private List<String> readData(String fileName) {
        return DataReader.readData(fileName, MainClass.class);
    }
}
