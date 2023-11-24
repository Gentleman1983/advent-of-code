package de.havox_design.aoc2015.day08;

import de.havox_design.aoc2015.utils.DataReader;

import java.util.List;

public class Matchsticks {
    private final List<String> input;

    public Matchsticks(String fileName) {
        input = readData(fileName);
    }

    public static int solvePart1(String fileName) {
        Matchsticks instance = new Matchsticks(fileName);
        return instance.solvePart1();
    }

    public static int solvePart2(String fileName) {
        Matchsticks instance = new Matchsticks(fileName);
        return instance.solvePart2();
    }

    public int solvePart1() {
        return calculateCharactersOfCode() - calculateCharactersInMemory();
    }

    public int solvePart2() {
        return 42;
    }

    protected int calculateCharactersOfCode() {
        return 0;
    }

    protected int calculateCharactersInMemory() {
        return 0;
    }

    private List<String> readData(String fileName) {
        return DataReader.readData(fileName, MainClass.class);
    }
}
