package de.havox_design.aoc2016.day13;

import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

public record Point(long x, long y) {
    public boolean isValid(long width, long height) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }

    public Collection<Point> neighbors() {
        return List.of(
                new Point(x - 1, y),
                new Point(x + 1, y),
                new Point(x, y - 1),
                new Point(x, y + 1));
    }

    public Collection<Point> neighbors(Predicate<Point> predicate) {
        return neighbors()
                .stream()
                .filter(predicate)
                .toList();
    }

    public Collection<Point> validNeighbors(long width, long height) {
        return neighbors(p -> p.isValid(width, height));
    }

    public Collection<Point> validNeighbors(long width, long height, Predicate<Point> predicate) {
        return neighbors(p -> p.isValid(width, height) && predicate.test(p));
    }

    public long dist(Point p) {
        return dist(this, p);
    }

    public static long dist(Point p1, Point p2) {
        return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
    }
}
