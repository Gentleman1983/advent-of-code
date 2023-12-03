package de.havox_design.aoc2016.day17;

import de.havox_design.aoc2016.utils.input.DataReader;

import java.util.List;

public class Day17 {
    private final String input;

    public Day17(String fileName) {
        input = readData(fileName).get(0);
    }

    public static String solvePart1(String fileName) {
        Day17 instance = new Day17(fileName);
        return instance.solvePart1();
    }

    public static String solvePart2(String fileName) {
        Day17 instance = new Day17(fileName);
        return instance.solvePart2();
    }

    public String solvePart1() {
        return "";
    }

    public String solvePart2() {
        return "";
    }

    private List<String> readData(String fileName) {
        return DataReader.readData(fileName, MainClass.class);
    }
}
