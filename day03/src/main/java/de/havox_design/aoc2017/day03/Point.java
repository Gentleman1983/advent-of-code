package de.havox_design.aoc2017.day03;

import static de.havox_design.aoc2017.day03.Direction.*;

import java.util.Set;

public record Point(int x, int y) {
    public Point delta(int dx, int dy) {
        return new Point(this.x + dx, this.y + dy);
    }

    public Set<Point> neighbors() {
        return Set.of(
                this.delta(DOWN.getDx(), DOWN.getDy()),
                this.delta(LEFT.getDx(), LEFT.getDy()),
                this.delta(RIGHT.getDx(), RIGHT.getDy()),
                this.delta(UP.getDx(), UP.getDy())
        );
    }
}
