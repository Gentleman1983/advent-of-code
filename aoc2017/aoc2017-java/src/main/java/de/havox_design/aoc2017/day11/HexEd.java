package de.havox_design.aoc2017.day11;


import de.havox_design.aoc.utils.java.AoCFunctionality;

import java.util.regex.Pattern;

public class HexEd implements AoCFunctionality {
    private static final Pattern INSTRUCTION_SPLITTER = Pattern.compile(",");

    private final String input;

    public HexEd(String fileName) {
        input = readData(fileName)
                .getFirst();
    }

    public static int solvePart1(String fileName) {
        HexEd instance = new HexEd(fileName);

        return instance.solvePart1();
    }

    public static long solvePart2(String fileName) {
        HexEd instance = new HexEd(fileName);

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
}
