package de.havox_design.aoc2017.day11;

import de.havox_design.aoc.utils.java.input.DataReader;

import java.util.List;
import java.util.regex.Pattern;

public class Day11 {
    private static final Pattern INSTRUCTION_SPLITTER = Pattern.compile(",");

    private final String input;

    public Day11(String fileName) {
        input = readData(fileName)
                .get(0);
    }

    public static int solvePart1(String fileName) {
        Day11 instance = new Day11(fileName);
        return instance.solvePart1();
    }

    public static long solvePart2(String fileName) {
        Day11 instance = new Day11(fileName);
        return instance.solvePart2();
    }

    public int solvePart1() {
        return traceSteps()
                .countStepsFromOrigin();
    }

    public int solvePart2() {
        return traceSteps()
                .getFurthest();
    }

    public Counter traceSteps() {
        return INSTRUCTION_SPLITTER
                .splitAsStream(input)
                .map(step -> Cardinal.valueOf(step.toUpperCase()))
                .reduce(new Counter(), Counter::moveTo, (a, b) -> {
                    throw new UnsupportedOperationException();
                });
    }

    private List<String> readData(String fileName) {
        return DataReader.readData(fileName, MainClass.class);
    }
}
