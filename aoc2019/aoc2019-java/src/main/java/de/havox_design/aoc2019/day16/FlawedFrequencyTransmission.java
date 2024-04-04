package de.havox_design.aoc2019.day16;

import de.havox_design.aoc.utils.java.AoCFunctionality;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FlawedFrequencyTransmission implements AoCFunctionality {
    private final List<Integer> input;
    private final List<Integer> basePattern = List.of(0, 1, 0, -1);

    public FlawedFrequencyTransmission(String fileName) {
        String symbols = readString(fileName);

        input = IntStream
                .range(0, symbols.length())
                .mapToObj(i -> Integer.valueOf(symbols.substring(i, i + 1)))
                .toList();
    }

    public static String processTask1(String fileName) {
        FlawedFrequencyTransmission instance = new FlawedFrequencyTransmission(fileName);
        return instance.processTask1();
    }

    public static String processTask2(String fileName) {
        FlawedFrequencyTransmission instance = new FlawedFrequencyTransmission(fileName);
        return instance.processTask2();
    }

    public String processTask1() {
        return processTask1(100);
    }

    public String processTask1(int phases) {
        return computeFirstSymbolsAfterPhases(phases);
    }

    public String processTask2() {
        return computeRepeatedSignalAfterPhases(100);
    }

    private String computeFirstSymbolsAfterPhases(int phases) {
        List<Integer> inputNumbers = new ArrayList<>(input);
        int patternLength = basePattern.size();

        for (int i = 0; i < phases; i++) {
            List<Integer> output = new ArrayList<>();

            for (int j = 0; j < inputNumbers.size(); j++) {
                int sum = calculateSum(j, inputNumbers, patternLength);
                output.add(Math.abs(sum % 10));
            }
            inputNumbers = output;
        }

        return inputNumbers
                .stream()
                .limit(8)
                .map(String::valueOf)
                .collect(Collectors.joining());
    }

    private int calculateSum(int j, List<Integer> inputNumbers, int patternLength) {
        int patternIndex = 0;
        int repeatAmount = j + 1;
        int sum = 0;

        for (int k = 0; k < inputNumbers.size(); k++) {
            if (k == 0) {
                repeatAmount--;
            }

            if (repeatAmount == 0) {
                patternIndex = (patternIndex + 1) % patternLength;
                repeatAmount = j + 1;
            }
            sum += inputNumbers.get(k) * basePattern.get(patternIndex);
            repeatAmount--;
        }
        return sum;
    }

    private String computeRepeatedSignalAfterPhases(int phases) {
        List<Integer> inputNumbers = repeatedList(new ArrayList<>(input));
        int offset = Integer
                .parseInt(
                        inputNumbers
                                .stream()
                                .limit(7)
                                .map(String::valueOf)
                                .collect(Collectors.joining())
                );

        for (int i = 0; i < phases; i++) {
            calculateOutput(inputNumbers, offset);
        }

        return inputNumbers
                .subList(offset, offset + 8)
                .stream()
                .map(String::valueOf)
                .collect(Collectors.joining());
    }

    private List<Integer> repeatedList(List<Integer> nums) {
        List<Integer> output = new ArrayList<>();

        for (int i = 0; i < 10000; i++) {
            output.addAll(nums);
        }

        return output;
    }

    private void calculateOutput(List<Integer> input, int offset) {
        int lastIndex = input.size() - 1;
        int sum = 0;

        for (int i = lastIndex; i >= offset; i--) {
            int result = (input.get(i) + sum) % 10;

            sum = input.get(i) + sum;
            input.set(i, result);
        }
    }
}
