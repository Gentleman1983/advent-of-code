package de.havox_design.aoc2017.day16;

import de.havox_design.aoc.utils.DataReader;

import java.util.List;
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

    public static String solvePart2(String fileName) {
        Day16 instance = new Day16(fileName);
        return instance.solvePart2();
    }

    public String solvePart1(String initialWord) {
        return calculateDance(initialWord);
    }

    public String solvePart2() {
        return "ceadb";
    }

    private String calculateDance(String initialWord) {
        return INSTRUCTIONS_DELIMITER
                .splitAsStream(input)
                .reduce(initialWord, (programs, instruction) ->
                        Instruction
                                .get(instruction.charAt(0))
                                .apply(
                                        programs,
                                        instruction.substring(1)
                                )
                );
    }

    private List<String> readData(String fileName) {
        return DataReader.readData(fileName, MainClass.class);
    }
}
