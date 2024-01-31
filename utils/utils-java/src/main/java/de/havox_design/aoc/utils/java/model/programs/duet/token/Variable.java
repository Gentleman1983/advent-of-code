package de.havox_design.aoc.utils.java.model.programs.duet.token;

import de.havox_design.aoc.utils.java.model.programs.duet.State;

import java.math.BigInteger;

public class Variable implements Token {
    private final String name;

    public Variable(final String name) {
        this.name = name;
    }

    @Override
    public BigInteger intValue(final State state) {
        return state.getValue(name);
    }

    @Override
    public String toString() {
        return name;
    }
}
