package de.havox_design.aoc2016.day17;

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
}
