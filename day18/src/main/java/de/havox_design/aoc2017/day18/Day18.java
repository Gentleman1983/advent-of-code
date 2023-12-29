package de.havox_design.aoc2017.day18;

import de.havox_design.aoc.utils.DataReader;
import de.havox_design.aoc2017.day18.instructions.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Day18 {
    private final List<Instruction> input;

    public Day18(String fileName) {
        input = parseProgram(readData(fileName));
    }

    public static BigInteger solvePart1(String fileName) {
        Day18 instance = new Day18(fileName);
        return instance.solvePart1();
    }

    public static BigInteger solvePart2(String fileName) {
        Day18 instance = new Day18(fileName);
        return instance.solvePart2();
    }

    public BigInteger solvePart1() {
        final State state = new State(0);

        while (state.getRecoveredSound() == null) {
            final int pos = state
                    .getPosition();
            final Instruction instruction = input
                    .get(pos);

            instruction
                    .execute(state);
            state
                    .incrementPosition();
        }

        return state
                .getRecoveredSound();
    }

    public BigInteger solvePart2() {
        return BigInteger.ZERO;
    }

    private List<Instruction> parseProgram(List<String> programCode) {
        final List<Instruction> program = new ArrayList<>();

        for (String row : programCode) {
            program
                    .add(InstructionProvider.createInstruction(row));
        }

        return program;
    }

    private List<String> readData(String fileName) {
        return DataReader.readData(fileName, MainClass.class);
    }
}
