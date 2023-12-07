package de.havox_design.aoc2016.day17;

import java.util.Arrays;

public enum Direction {
    UP('U'),
    DOWN('D'),
    LEFT('L'),
    RIGHT('R');

    private char symbol;

    Direction(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }

    public static Direction from(char symbol) {
        return Arrays
                .stream(values())
                .filter(dir -> dir.symbol == symbol)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unknown symbol '" + symbol + "'."));
    }
}
