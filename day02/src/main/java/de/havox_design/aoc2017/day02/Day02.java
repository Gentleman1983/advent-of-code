package de.havox_design.aoc2017.day02;

import de.havox_design.aoc.utils.DataReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day02 {
    private final List<String> input;

    public Day02(String fileName) {
        input = readData(fileName);
    }

    public static long solvePart1(String fileName) {
        Day02 instance = new Day02(fileName);
        return instance.solvePart1();
    }

    public static long solvePart2(String fileName) {
        Day02 instance = new Day02(fileName);
        return instance.solvePart2();
    }

    public long solvePart1() {
        return parseInput()
                .stream()
                .map(this::calculateChecksum)
                .reduce(0, Integer::sum);

    }

    public long solvePart2() {
        return parseInput()
                .stream()
                .mapToInt(row ->
                        row
                                .stream()
                                .flatMap(i ->
                                        row
                                                .stream()
                                                .filter(j ->
                                                        !j.equals(i)
                                                                && j % i == 0
                                                )
                                                .map(j ->
                                                        j / i
                                                )
                                )
                                .findFirst()
                                .orElseThrow(() -> new IllegalStateException("This should never happen!"))
                )
                .sum();
    }

    private int calculateChecksum(List<Integer> integers) {
        String errorMessage = "No data in row " + integers;

        int min = integers
                .stream()
                .min(Integer::compare)
                .orElseThrow(() -> new IllegalStateException(errorMessage));
        int max = integers
                .stream()
                .max(Integer::compare)
                .orElseThrow(() -> new IllegalStateException(errorMessage));

        return max - min;
    }

    private List<List<Integer>> parseInput() {
        List<List<Integer>> result = new ArrayList<>();

        for (String row : input) {
            String[] splittedRow = row.split("\\s+");

            List<Integer> numbers = Arrays
                    .stream(splittedRow)
                    .map(String::trim)
                    .map(Integer::parseInt)
                    .toList();

            result.add(numbers);
        }

        return result;
    }

    private List<String> readData(String fileName) {
        return DataReader.readData(fileName, MainClass.class);
    }
}
