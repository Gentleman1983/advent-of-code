package de.havox_design.aoc2017.day18.token;

import java.math.BigInteger;

public class TokenProvider {
    private TokenProvider() {}

    public static Token createToken(final String value) {
        try {
            return new Constant(new BigInteger(value));
        } catch (final NumberFormatException nfe) {
            return new Variable(value);
        }
    }
}
