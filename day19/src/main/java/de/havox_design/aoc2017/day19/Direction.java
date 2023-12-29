package de.havox_design.aoc2017.day19;

public enum Direction {
    UP,
    RIGHT,
    DOWN,
    LEFT;

    public Direction left() {
        return switch (this) {
            case LEFT -> DOWN;
            case DOWN -> RIGHT;
            case RIGHT -> UP;
            case UP -> LEFT;
        };
    }

    public Direction right() {
        return switch (this) {
            case LEFT -> UP;
            case DOWN -> LEFT;
            case RIGHT -> DOWN;
            case UP -> RIGHT;
        };
    }
}
