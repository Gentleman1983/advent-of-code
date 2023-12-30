package de.havox_design.aoc2017.day21;

import de.havox_design.aoc.utils.DataReader;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day21 {
    private static final Supplier<Area> AREA_SUPPLIER = () -> new Area(Arrays.asList(".#.", "..#", "###"));

    private final List<String> input;

    public Day21(String fileName) {
        input = readData(fileName);
    }

    public static long solvePart1(String fileName, int iterations) {
        Day21 instance = new Day21(fileName);
        return instance.solvePart1(iterations);
    }

    public static long solvePart2(String fileName) {
        Day21 instance = new Day21(fileName);
        return instance.solvePart2();
    }

    public long solvePart1(int iterations) {
        return countNumberOfPixelsTurnedOnAfter(iterations);
    }

    public long solvePart2() {
        return countNumberOfPixelsTurnedOnAfter(18);
    }

    long countNumberOfPixelsTurnedOnAfter(int iterations) {
        var rules = input
                .stream()
                .map(Rule::from)
                .toList();

        return Stream
                .iterate(AREA_SUPPLIER.get(), a -> a.next(rules))
                .skip(iterations)
                .findFirst()
                .map(Area::countPixelsOn)
                .orElseThrow(() -> new IllegalArgumentException("Expected number of pixels."));
    }

    private List<String> readData(String fileName) {
        return DataReader.readData(fileName, MainClass.class);
    }
}
