package de.havox_design.aoc2022.day17;

public enum Shape {
    SHAPE_MINUS,
    SHAPE_PLUS,
    SHAPE_BACKWARDS_L,
    SHAPE_I,
    SHAPE_SQUARE;

    public int[] spawn(int y) {
        return switch (this) {
            case SHAPE_MINUS -> new int[]{2, y + 4};
            case SHAPE_PLUS -> new int[]{3, y + 6};
            case SHAPE_BACKWARDS_L -> new int[]{4, y + 6};
            case SHAPE_I -> new int[]{2, y + 7};
            case SHAPE_SQUARE -> new int[]{2, y + 5};
        };
    }
}
