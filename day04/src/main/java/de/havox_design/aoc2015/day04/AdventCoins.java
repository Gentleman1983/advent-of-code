package de.havox_design.aoc2015.day04;

import de.havox_design.aoc2015.utils.DataReader;

import java.util.List;

public class AdventCoins {
    private final String input;

    public AdventCoins(String fileName) {
        input = readData(fileName).get(0);
    }

    public static int solvePart1(String fileName) {
        AdventCoins instance = new AdventCoins(fileName);
        return instance.solvePart1();
    }

    public static int solvePart2(String fileName) {
        AdventCoins instance = new AdventCoins(fileName);
        return instance.solvePart2();
    }

    public int solvePart1() {
        return 0;
    }

    public int solvePart2() {
        return 0;
    }

    private List<String> readData(String fileName) {
        return DataReader.readData(fileName, MainClass.class);
    }
}
