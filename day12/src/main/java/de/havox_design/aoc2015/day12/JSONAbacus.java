package de.havox_design.aoc2015.day12;

import de.havox_design.aoc2015.utils.DataReader;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class JSONAbacus {
    private final String input;

    public JSONAbacus(String fileName) {
        input = readData(fileName).get(0);
    }

    public static int solvePart1(String fileName) {
        JSONAbacus instance = new JSONAbacus(fileName);
        return instance.solvePart1();
    }

    public static int solvePart2(String fileName) {
        JSONAbacus instance = new JSONAbacus(fileName);
        return instance.solvePart2();
    }

    public int solvePart1() {
        return Arrays.stream(input.split("[^\\d-]+")).filter(Objects::nonNull).map(String::trim)
                .filter(s -> !s.isEmpty()).mapToInt(Integer::parseInt).sum();
    }

    public int solvePart2() {
        return 0;
    }

    private List<String> readData(String fileName) {
        return DataReader.readData(fileName, MainClass.class);
    }
}
