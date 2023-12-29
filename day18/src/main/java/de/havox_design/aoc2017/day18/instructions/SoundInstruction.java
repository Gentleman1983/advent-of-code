package de.havox_design.aoc2017.day18.instructions;

import de.havox_design.aoc2017.day18.State;

public class SoundInstruction implements Instruction {

    private final String variableName;

    public SoundInstruction(final String variableName) {
        this.variableName = variableName;
    }

    @Override
    public void execute(final State state) {
        state.playSound(state.getValue(variableName));
    }
}
