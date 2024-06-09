package de.havox_design.aoc2016.day06;


import de.havox_design.aoc.utils.java.AoCFunctionality;

import static java.util.Map.Entry;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SignalsAndNoise implements AoCFunctionality {
    private final List<String> input;

    public SignalsAndNoise(String fileName) {
        input = readData(fileName);
    }

    public static String solvePart1(String fileName) {
        SignalsAndNoise instance = new SignalsAndNoise(fileName);

        return instance.solvePart1();
    }

    public static String solvePart2(String fileName) {
        SignalsAndNoise instance = new SignalsAndNoise(fileName);

        return instance.solvePart2();
    }

    public String solvePart1() {
        return detectMessage(Comparator.naturalOrder());
    }

    public String solvePart2() {
        return detectMessage(Comparator.reverseOrder());
    }

    private String detectMessage(Comparator<Long> comparator) {
        return detectMessage(input, comparator);
    }

    private String detectMessage(List<String> input, Comparator<Long> comparator) {
        return flip(input)
                .stream()
                .map(findMostUsedCharacter(comparator))
                .collect(Collectors.joining());
    }

    private List<String> flip(List<String> input) {
        return input.isEmpty() ?
                Collections.emptyList() :
                IntStream.range(0, input.get(0).length())
                .mapToObj(i ->
                        input
                                .stream()
                                .map(line -> String.valueOf(line.charAt(i)))
                                .collect(Collectors.joining())
                )
                .toList();
    }

    private static UnaryOperator<String> findMostUsedCharacter(Comparator<Long> comparator) {
        return value -> value
                .chars()
                .mapToObj(c -> String.valueOf((char) c))
                .collect(Collectors.groupingBy(v -> v, Collectors.counting()))
                .entrySet()
                .stream()
                .max(Entry.comparingByValue(comparator))
                .map(Entry::getKey)
                .orElseThrow();
    }
}
