package de.havox_design.aoc.utils.java.model.programs.duet.instructions;

import de.havox_design.aoc.utils.java.model.programs.duet.State;

import java.math.BigInteger;

public class Receive implements Instruction {
    private final String varName;
    public Receive(final String value) {
        varName = value;
    }

    @Override
    public void execute(final State state) {
        final BigInteger value = state.receiveValue();
        if (value == null) {
            state.setRunning(false);
        } else {
            state.setValue(varName, value);
        }
    }
}
