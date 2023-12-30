package de.havox_design.aoc2017.day18.token;

import de.havox_design.aoc2017.day18.State;

import java.math.BigInteger;

public interface Token {
    BigInteger intValue(State state);
}
