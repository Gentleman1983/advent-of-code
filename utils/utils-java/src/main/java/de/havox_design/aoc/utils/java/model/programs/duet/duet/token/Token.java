package de.havox_design.aoc.utils.java.model.programs.duet.duet.token;

import de.havox_design.aoc.utils.java.model.programs.duet.duet.State;

import java.math.BigInteger;

public interface Token {
    BigInteger intValue(State state);
}
