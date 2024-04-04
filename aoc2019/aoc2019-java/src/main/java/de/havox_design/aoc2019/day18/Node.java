package de.havox_design.aoc2019.day18;

import de.havox_design.aoc.utils.kotlin.model.positions.Position2d;

import java.util.Objects;

public class Node {
    private final Position2d<Integer> pos;
    private final int keys;
    private final int steps;

    public Node(Position2d<Integer> pos, int steps, int keys) {
        this.pos = pos;
        this.steps = steps;
        this.keys = keys;
    }

    public int steps() {
        return steps;
    }

    public Position2d<Integer> pos() {
        return pos;
    }

    public int keys() {
        return keys;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Node tmp)) {
            return false;
        }

        return this.keys == tmp.keys && this.pos.equals(tmp.pos);

    }

    @Override
    public int hashCode() {
        return Objects.hash(keys, pos);
    }
}
