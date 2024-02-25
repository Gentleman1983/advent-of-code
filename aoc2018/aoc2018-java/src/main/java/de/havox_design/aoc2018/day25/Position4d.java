package de.havox_design.aoc2018.day25;

public record Position4d(int x, int y, int z, int s) {
    public int manhattanDistance(Position4d other) {
        return Math.abs(x - other.x) +
                Math.abs(y - other.y) +
                Math.abs(z - other.z) +
                Math.abs(s - other.s);
    }
}
