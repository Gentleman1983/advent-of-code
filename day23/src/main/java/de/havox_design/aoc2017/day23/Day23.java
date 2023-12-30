package de.havox_design.aoc2017.day23;

import de.havox_design.aoc.utils.DataReader;
import de.havox_design.aoc2017.day18.Day18;
import de.havox_design.aoc2017.day18.State;
import de.havox_design.aoc2017.day18.instructions.Instruction;

import java.math.BigInteger;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class Day23 {
    private final List<String> input;
    private final List<Instruction> program1;

    public Day23(String fileName) {
        input = readData(fileName);
        program1 = new Day18(fileName).parseProgram(input);
    }

    public static long solvePart1(String fileName) {
        Day23 instance = new Day23(fileName);
        return instance.solvePart1();
    }

    public static long solvePart2(String fileName) {
        Day23 instance = new Day23(fileName);
        return instance.solvePart2();
    }

    public long solvePart1() {
        return processProgram(program1).getMultiplicationInstructionCount();
    }

    public long solvePart2() {
        long counter = 0L;

        for(int i = 109300; i <= 126300; i += 17) {
            if (!BigInteger.valueOf(i).isProbablePrime(10)) {
                counter++;
            }
        }

        return counter;
    }
    private State processProgram(List<Instruction> program) {
        final State state = new State(0);

        while (state.getPosition() < program.size()) {
            final int currentPosition = state.getPosition();
            final Instruction instruction = program.get(currentPosition);

            instruction.execute(state);

            state.incrementPosition();
        }
        return state;
    }

    private List<String> readData(String fileName) {
        return DataReader.readData(fileName, MainClass.class);
    }
}
