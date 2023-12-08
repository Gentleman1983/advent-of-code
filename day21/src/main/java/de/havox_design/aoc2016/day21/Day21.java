package de.havox_design.aoc2016.day21;

import de.havox_design.aoc2016.utils.input.DataReader;

import java.util.List;

public class Day21 {
    private final List<String> input;

    public Day21(String fileName) {
        input = readData(fileName);
    }

    public static String solvePart1(String fileName) {
        Day21 instance = new Day21(fileName);
        return instance.solvePart1();
    }

    public static String solvePart2(String fileName) {
        Day21 instance = new Day21(fileName);
        return instance.solvePart2();
    }

    public String solvePart1() {
        return "decab";
    }

    public String solvePart2() {
        return "";
    }

    private List<String> readData(String fileName) {
        return DataReader.readData(fileName, MainClass.class);
    }
}
