package de.havox_design.aoc2018.day16;

@FunctionalInterface
public interface Instruction {
    Registers apply(final NumberedInstruction instruction, final Registers register);
}
