package de.havox_design.aoc2017.day18;

import de.havox_design.aoc.utils.java.AoCFunctionality;
import de.havox_design.aoc.utils.java.programs.duet.State;
import de.havox_design.aoc.utils.java.programs.duet.instructions.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Duet implements AoCFunctionality {
    private final List<Instruction> program1;
    private final List<Instruction> program2;

    public Duet(String fileName) {
        program1 = parseProgram(readData(fileName));
        program2 = parseProgram(readData(fileName), true);
    }

    public static BigInteger solvePart1(String fileName) {
        Duet instance = new Duet(fileName);
        return instance.solvePart1();
    }

    public static BigInteger solvePart2(String fileName) {
        Duet instance = new Duet(fileName);
        return instance.solvePart2();
    }

    public BigInteger solvePart1() {
        final State state = new State(0);

        while (state.getRecoveredSound() == null) {
            final int pos = state
                    .getPosition();
            final Instruction instruction = program1
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
        final State state0 = new State(0);
        final State state1 = new State(1);

        state0.setOtherState(state1);

        while (state0.isRunning() || state1.isRunning()) {
            executeStep(program2, state0);
            executeStep(program2, state1);
        }

        return state1.getSentCount();
    }

    private void executeStep(final List<Instruction> program, final State state) {
        if (state.isRunning()) {
            final int position = state.getPosition();
            final Instruction instruction = program.get(position);

            instruction.execute(state);

            if (state.isRunning()) {
                state.incrementPosition();
            }
        }
    }

    public List<Instruction> parseProgram(List<String> programCode) {
        return parseProgram(programCode, false);
    }

    private List<Instruction> parseProgram(List<String> programCode, boolean isPart2) {
        final List<Instruction> program = new ArrayList<>();

        for (String row : programCode) {
            program
                    .add(InstructionProvider.createInstruction(row, isPart2));
        }

        return program;
    }
}
