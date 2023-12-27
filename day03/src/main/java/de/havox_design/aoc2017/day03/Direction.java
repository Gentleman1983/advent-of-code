package de.havox_design.aoc2017.day03;

public enum Direction {
    DOWN(0, -1),
    LEFT(-1, 0),
    RIGHT(1, 0),
    UP(0, 1);

    private final int dx;
    private final int dy;

    private Direction(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public int getDx() {
        return dx;
    }

    public int getDy() {
        return dy;
    }
}
