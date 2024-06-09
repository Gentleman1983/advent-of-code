package de.havox_design.aoc2017.day13;


import de.havox_design.aoc.utils.java.AoCFunctionality;

import java.util.List;
import java.util.stream.IntStream;

public class PacketScanners implements AoCFunctionality {
    private final List<String> input;

    public PacketScanners(String fileName) {
        input = readData(fileName);
    }

    public static long solvePart1(String fileName) {
        PacketScanners instance = new PacketScanners(fileName);

        return instance.solvePart1();
    }

    public static long solvePart2(String fileName) {
        PacketScanners instance = new PacketScanners(fileName);

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
}
