package de.havox_design.aoc2017.day18.token;

import de.havox_design.aoc2017.day18.State;

import java.math.BigInteger;

public class Constant implements Token {

    private final BigInteger value;

    public Constant(final BigInteger value) {
        this.value = value;
    }

    @Override
    public BigInteger intValue(final State state) {
        return value;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
