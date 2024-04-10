package de.havox_design.aoc2019.day22;

import de.havox_design.aoc.utils.java.AoCFunctionality;

import java.util.List;

public class SlamShuffle implements AoCFunctionality {
    private static final String INSTRUCTION_CUT_N = "cut";
    private static final String INSTRUCTION_DEAL_INTO_NEW_STACK = "deal into new stack";
    private static final String INSTRUCTION_DEAL_WITH_INCREMENT = "deal with increment";
    private static final char INSTRUCTION_VALUE_DELIMITER = ' ';

    private final List<String> input;

    public SlamShuffle(String fileName) {
        input = readData(fileName);
    }

    public static long processTask1(String fileName) {
        SlamShuffle instance = new SlamShuffle(fileName);
        return instance.processTask1();
    }

    public static long processTask2(String fileName) {
        SlamShuffle instance = new SlamShuffle(fileName);
        return instance.processTask2();
    }

    public long processTask1() {
        int targetIndex = 2019;
        int numberOfCards = 10007;

        for (String instruction : input) {
            if (instruction.contains(INSTRUCTION_DEAL_INTO_NEW_STACK)) {
                targetIndex = numberOfCards - targetIndex - 1;
            } else if (instruction.contains(INSTRUCTION_DEAL_WITH_INCREMENT)) {
                targetIndex = Math.floorMod(targetIndex * extractInstructionValue(instruction), numberOfCards);
            } else if (instruction.contains(INSTRUCTION_CUT_N)) {
                targetIndex = Math.floorMod(targetIndex + (extractInstructionValue(instruction) * (-1)), numberOfCards);
            }
        }

        return targetIndex;
    }

    public long processTask2() {
        return 0;
    }

    private int extractInstructionValue(String instruction) {
        return Integer.parseInt(instruction.substring(instruction.lastIndexOf(INSTRUCTION_VALUE_DELIMITER) + 1));
    }
}
