package de.havox_design.aoc2017.day23;

import de.havox_design.aoc.utils.DataReader;
import de.havox_design.aoc2017.day18.Day18;
import de.havox_design.aoc2017.day18.State;
import de.havox_design.aoc2017.day18.instructions.Instruction;

import java.util.List;

public class Day23 {
    private final List<String> input;
    private final List<Instruction> program;

    public Day23(String fileName) {
        input = readData(fileName);
        program = new Day18(fileName).parseProgram(input);
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
        final State state = new State(0);

        while (state.getPosition() < program.size()) {
            final int currentPosition = state.getPosition();
            final Instruction instruction = program.get(currentPosition);

            instruction.execute(state);

            state.incrementPosition();
        }
        return state.getMultiplicationInstructionCount();
    }

    public long solvePart2() {return 0L;
    }

    private List<String> readData(String fileName) {
        return DataReader.readData(fileName, MainClass.class);
    }
}
