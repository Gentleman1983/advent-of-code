package de.havox_design.aoc.utils.java.model.programs.duet.instructions;

import de.havox_design.aoc.utils.java.model.programs.duet.State;

public class Sound implements Instruction {
    private final String variableName;

    public Sound(final String variableName) {
        this.variableName = variableName;
    }

    @Override
    public void execute(final State state) {
        state.playSound(state.getValue(variableName));
    }
}
