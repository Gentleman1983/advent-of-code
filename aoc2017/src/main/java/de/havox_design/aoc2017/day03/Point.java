package de.havox_design.aoc2017.day03;

import static de.havox_design.aoc2017.day03.Direction.*;

import java.util.Set;

public record Point(int x, int y) {
    public Point delta(int dx, int dy) {
        return new Point(this.x + dx, this.y + dy);
    }

    public Set<Point> neighbors() {
        return Set.of(
                this.delta(Direction.DOWN.getDx(), Direction.DOWN.getDy()),
                this.delta(Direction.DOWN_LEFT.getDx(), Direction.DOWN_LEFT.getDy()),
                this.delta(Direction.DOWN_RIGHT.getDx(), Direction.DOWN_RIGHT.getDy()),
                this.delta(Direction.LEFT.getDx(), Direction.LEFT.getDy()),
                this.delta(Direction.RIGHT.getDx(), Direction.RIGHT.getDy()),
                this.delta(Direction.UP.getDx(), Direction.UP.getDy()),
                this.delta(Direction.UP_LEFT.getDx(), Direction.UP_LEFT.getDy()),
                this.delta(Direction.UP_RIGHT.getDx(), Direction.UP_RIGHT.getDy())
        );
    }
}
