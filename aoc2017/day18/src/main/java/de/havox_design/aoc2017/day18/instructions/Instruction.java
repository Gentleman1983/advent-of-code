package de.havox_design.aoc2017.day18.instructions;

import de.havox_design.aoc2017.day18.State;

public interface Instruction {
    void execute(State state);
}
