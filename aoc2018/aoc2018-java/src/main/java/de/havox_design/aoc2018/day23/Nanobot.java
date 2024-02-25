package de.havox_design.aoc2018.day23;

import de.havox_design.aoc.utils.kotlin.model.positions.Position3d;

public record Nanobot(Position3d<Long> position, long range) {
    public long calculateManhattanDistanceTo(Nanobot other) {
        return Math.abs(other.position.getX() - position.getX()) +
                Math.abs(other.position.getY() - position.getY()) +
                Math.abs(other.position.getZ() - position.getZ());
    }

    public boolean isInRange(Nanobot other) {
        return calculateManhattanDistanceTo(other) <= range;
    }
}
