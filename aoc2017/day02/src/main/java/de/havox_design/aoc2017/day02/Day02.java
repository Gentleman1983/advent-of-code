package de.havox_design.aoc2017.day02;

import de.havox_design.aoc.utils.DataReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.ToIntFunction;
import java.util.stream.Stream;

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
                .mapToInt(calculateRow())
                .sum();
    }

    private static ToIntFunction<List<Integer>> calculateRow() {
        return row ->
                row
                        .stream()
                        .flatMap(rowHasEvenDivisor(row))
                        .findFirst()
                        .orElseThrow(() -> new IllegalStateException("This should never happen!"));
    }

    private static Function<Integer, Stream<Integer>> rowHasEvenDivisor(List<Integer> row) {
        return firstNumber ->
                row
                        .stream()
                        .filter(otherNumber ->
                                !otherNumber.equals(firstNumber)
                                        && otherNumber % firstNumber == 0
                        )
                        .map(otherNumber ->
                                otherNumber / firstNumber
                        );
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
