package de.havox_design.aoc2017.day11;

import de.havox_design.aoc.utils.kotlin.model.positions.Position3d;

public enum Cardinal {
    N(new Position3d<>(0, 1, -1)),
    NE(new Position3d<>(1, 0, -1)),
    SE(new Position3d<>(1, -1, 0)),
    S(new Position3d<>(0, -1, 1)),
    SW(new Position3d<>(-1, 0, 1)),
    NW(new Position3d<>(-1, 1, 0));

    private final Position3d<Integer> position;

    Cardinal(Position3d<Integer> position) {
        this.position = position;
    }

    public int getX() {
        return position.getX();
    }

    public int getY() {
        return position.getY();
    }

    public int getZ() {
        return position.getZ();
    }
}
