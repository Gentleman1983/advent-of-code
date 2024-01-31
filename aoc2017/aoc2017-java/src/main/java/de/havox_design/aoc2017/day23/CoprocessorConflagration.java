package de.havox_design.aoc2017.day23;

import de.havox_design.aoc.utils.java.AoCFunctionality;
import de.havox_design.aoc2017.day18.Duet;
import de.havox_design.aoc.utils.java.programs.duet.State;
import de.havox_design.aoc.utils.java.programs.duet.instructions.Instruction;

import java.math.BigInteger;
import java.util.List;

public class CoprocessorConflagration implements AoCFunctionality {
    private final List<String> input;
    private final List<Instruction> program1;

    public CoprocessorConflagration(String fileName) {
        input = readData(fileName);
        program1 = new Duet(fileName).parseProgram(input);
    }

    public static long solvePart1(String fileName) {
        CoprocessorConflagration instance = new CoprocessorConflagration(fileName);
        return instance.solvePart1();
    }

    public static long solvePart2(String fileName) {
        CoprocessorConflagration instance = new CoprocessorConflagration(fileName);
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
}
