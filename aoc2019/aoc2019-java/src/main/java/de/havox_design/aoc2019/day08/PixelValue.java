package de.havox_design.aoc2019.day08;

public enum PixelValue {
    WHITE('0'),
    BLACK('1'),
    TRANSPARENT('2');

    private final char value;

    PixelValue(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }
}
