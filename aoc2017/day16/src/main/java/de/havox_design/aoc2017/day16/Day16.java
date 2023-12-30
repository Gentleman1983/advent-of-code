package de.havox_design.aoc2017.day16;

import de.havox_design.aoc.utils.java.input.DataReader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Day16 {
    private static final Pattern INSTRUCTIONS_DELIMITER = Pattern.compile(",");

    private final String input;

    public Day16(String fileName) {
        input = readData(fileName).get(0);
    }

    public static String solvePart1(String fileName, String initialWord) {
        Day16 instance = new Day16(fileName);
        return instance.solvePart1(initialWord);
    }

    public static String solvePart2(String fileName, String initialWord, int iterations) {
        Day16 instance = new Day16(fileName);
        return instance.solvePart2(initialWord, iterations);
    }

    public String solvePart1(String initialWord) {
        return calculateDance(initialWord);
    }

    public String solvePart2(String initialWord, int iterations) {
        return calculateDanceFor(initialWord, iterations);
    }

    private String calculateDance(String initialWord) {
        return calculateDance(initialWord, INSTRUCTIONS_DELIMITER.splitAsStream(input));
    }

    private static String calculateDance(String initialWord, Stream<String> instructionStream) {
        return instructionStream
                .reduce(initialWord, (programs, instruction) ->
                        Instruction
                                .get(instruction.charAt(0))
                                .apply(programs, instruction.substring(1))
                );
    }

    private String calculateDanceFor(String initialWord, int iterations) {
        List<String> instructionList = INSTRUCTIONS_DELIMITER
                .splitAsStream(input)
                .toList();
        Map<String, String> cache = new HashMap<>();
        String previousWord = initialWord;

        for (int i = 0; i < iterations; i++) {
            previousWord = cache
                    .computeIfAbsent(
                            previousWord,
                            word -> calculateDance(word, instructionList.stream())
                    );
        }
        return previousWord;
    }

    private List<String> readData(String fileName) {
        return DataReader.readData(fileName, MainClass.class);
    }
}
