package de.havox_design.aoc.utils.java.programs.duet.token;

import de.havox_design.aoc.utils.java.programs.duet.State;

import java.math.BigInteger;

public interface Token {
    BigInteger intValue(State state);
}
