package de.havox_design.aoc2019.day08;

import de.havox_design.aoc.utils.java.AoCFunctionality;
import org.apache.commons.lang3.tuple.Pair;

import java.util.*;
import java.util.function.ToLongFunction;

import static java.util.Comparator.comparingLong;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingByConcurrent;

public class SpaceImageFormat implements AoCFunctionality {
    private final String input;

    public SpaceImageFormat(String fileName) {
        input = readString(fileName);
    }

    public static long processTask1(String fileName) {
        SpaceImageFormat instance = new SpaceImageFormat(fileName);
        return instance.processTask1();
    }

    public static long processTask2(String fileName) {
        SpaceImageFormat instance = new SpaceImageFormat(fileName);
        return instance.processTask2();
    }

    public long processTask1() {
        return solve(this::computeResultFirst);
    }

    public long processTask2() {
        return 0;
    }

    private long solve(ToLongFunction<List<List<List<Character>>>> computeResult) {
        int rows = input.length() < 20 ? 2 : 6;
        int columns = input.length() < 20 ? 2 : 25;
        List<List<List<Character>>> image = new ArrayList<>();
        Iterator<Character> inputChars = input
                .chars()
                .mapToObj(c -> (char) c)
                .iterator();

        while (inputChars.hasNext()) {
            image.add(createLayer(inputChars, rows, columns));
        }

        return computeResult.applyAsLong(image);
    }

    private List<List<Character>> createLayer(Iterator<Character> input, int rows, int columns) {
        List<List<Character>> matrix = new ArrayList<>();

        for (int i = 0; i < rows; i++) {
            List<Character> row = new ArrayList<>();

            for (int j = 0; j < columns; j++) {
                row.add(input.next());
            }

            matrix.add(row);
        }

        return matrix;
    }

    private Pair<Long, Long> computeLayerValue(final List<List<Character>> layer) {
        Map<Character, Long> frequencies = layer
                .parallelStream()
                .flatMap(Collection::stream)
                .collect(groupingByConcurrent(identity(), counting()));
        long zeroes = frequencies.getOrDefault('0', 0L);
        long value = frequencies.getOrDefault('1', 0L) * frequencies.getOrDefault('2', 0L);

        return Pair.of(zeroes, value);
    }

    private long computeResultFirst(final List<List<List<Character>>> image) {
        return image
                .stream()
                .map(this::computeLayerValue)
                .min(comparingLong(Pair::getLeft))
                .map(Pair::getRight)
                .orElseThrow();
    }
}
