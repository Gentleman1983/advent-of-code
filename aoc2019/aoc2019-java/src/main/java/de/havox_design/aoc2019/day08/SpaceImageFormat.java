package de.havox_design.aoc2019.day08;

import de.havox_design.aoc.utils.java.AoCFunctionality;
import org.apache.commons.lang3.tuple.Pair;

import java.util.*;
import java.util.function.Function;

import static de.havox_design.aoc2019.day08.PixelValue.*;
import static java.util.Comparator.comparingLong;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingByConcurrent;

public class SpaceImageFormat implements AoCFunctionality {
    private static final Map<Character, PixelValue> PIXEL_VALUES = Map.of('0', WHITE, '1', BLACK);

    private final String input;

    public SpaceImageFormat(String fileName) {
        input = readString(fileName);
    }

    public static long processTask1(String fileName) {
        SpaceImageFormat instance = new SpaceImageFormat(fileName);

        return instance.processTask1();
    }

    public static String processTask2(String fileName) {
        SpaceImageFormat instance = new SpaceImageFormat(fileName);

        return instance.processTask2();
    }

    public long processTask1() {
        return (long) process(this::computeResultFirst);
    }

    public String processTask2() {
        return ((String) process(this::computeResultSecond))
                .replace('0', ' ')
                .replace('1', '#');
    }

    private Object process(Function<List<List<List<Character>>>, ?> computeResult) {
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

        return computeResult.apply(image);
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

    private Pair<Long, Long> computeLayerValue(List<List<Character>> layer) {
        Map<Character, Long> frequencies = layer
                .parallelStream()
                .flatMap(Collection::stream)
                .collect(groupingByConcurrent(identity(), counting()));
        long zeroes = frequencies.getOrDefault('0', 0L);
        long value = frequencies.getOrDefault('1', 0L) * frequencies.getOrDefault('2', 0L);

        return Pair.of(zeroes, value);
    }

    private long computeResultFirst(List<List<List<Character>>> image) {
        return image
                .stream()
                .map(this::computeLayerValue)
                .min(comparingLong(Pair::getLeft))
                .map(Pair::getRight)
                .orElseThrow();
    }

    private String computeResultSecond(List<List<List<Character>>> image) {
        StringBuilder result = new StringBuilder();

        for (int row = 0; row < image.getFirst().size(); row++) {
            result.append("\n");

            for (int column = 0; column < image.getFirst().getFirst().size(); column++) {
                result.append(findPixel(image, row, column));
            }
        }

        return result.toString();
    }

    private Character findPixel(List<List<List<Character>>> image, int row, int column) {
        return image
                .stream()
                .map(layer -> layer.get(row).get(column))
                .filter(PIXEL_VALUES::containsKey)
                .findFirst()
                .map(PIXEL_VALUES::get)
                .orElseThrow()
                .getValue();
    }
}
