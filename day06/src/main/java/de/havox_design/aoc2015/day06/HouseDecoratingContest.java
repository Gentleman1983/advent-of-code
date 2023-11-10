package de.havox_design.aoc2015.day06;

import de.havox_design.aoc2015.utils.DataReader;

import java.util.List;

public class HouseDecoratingContest {
    private final List<String> input;

    public HouseDecoratingContest(String fileName) {
        input = readData(fileName);
    }

    public static int solvePart1(String fileName) {
        HouseDecoratingContest instance = new HouseDecoratingContest(fileName);
        return instance.solvePart1();
    }

    public static int solvePart2(String fileName) {
        HouseDecoratingContest instance = new HouseDecoratingContest(fileName);
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
