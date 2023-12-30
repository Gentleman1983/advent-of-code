package de.havox_design.aoc2017.day11;

public enum Cardinal {
    N(0, 1, -1),
    NE(1, 0, -1),
    SE(1, -1, 0),
    S(0, -1, 1),
    SW(-1, 0, 1),
    NW(-1, 1, 0);

    private final int x;
    private final int y;
    private final int z;

    Cardinal(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }
}
