package de.havox_design.aoc.utils.java.model.programs.duet.duet.instructions;

import de.havox_design.aoc.utils.java.model.programs.duet.duet.State;

public interface Instruction {
    void execute(State state);
}
