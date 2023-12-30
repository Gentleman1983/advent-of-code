package de.havox_design.aoc2017.day13;

import de.havox_design.aoc.utils.DataReader;

import java.util.List;
import java.util.stream.IntStream;

public class Day13 {
    private final List<String> input;

    public Day13(String fileName) {
        input = readData(fileName);
    }

    public static long solvePart1(String fileName) {
        Day13 instance = new Day13(fileName);
        return instance.solvePart1();
    }

    public static long solvePart2(String fileName) {
        Day13 instance = new Day13(fileName);
        return instance.solvePart2();
    }

    public long solvePart1() {
        return input
                .stream()
                .map(Layer::parse)
                .mapToInt(Layer::getSeverityPlusDelay)
                .sum();
    }

    public long solvePart2() {
        int maxDelay = Integer.MAX_VALUE;
        List<Layer> layers = input
                .stream()
                .map(Layer::parse)
                .toList();

        return IntStream
                .range(1, maxDelay)
                .filter(i -> layers.stream()
                        .allMatch(v -> v.getSeverityPlusDelay(i) == 0))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Expected delay less than " + maxDelay));
    }

    private List<String> readData(String fileName) {
        return DataReader.readData(fileName, MainClass.class);
    }
}
