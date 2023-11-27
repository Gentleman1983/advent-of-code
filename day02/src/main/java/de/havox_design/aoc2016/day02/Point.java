package de.havox_design.aoc2016.day02;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

public record Point(int x, int y) {
    public int getDistance() {
        return Math.abs(x) + Math.abs(y);
    }

    public List<Point> range(Point other) {
        List<Point> result = new ArrayList<>();
        if (x == other.x) {
            for(int i : getRange(y, other.y)) {
                result.add(new Point(x, i));
            }
        } else if (y == other.y) {
            for(int i : getRange(x, other.x)) {
                result.add(new Point(i, y));
            }
        } else {
            throw new IllegalArgumentException("Only moves along the edges allowed!");
        }

        return result;
    }

    private int[] getRange(int a, int b) {
        int lower = Math.min(a, b);
        int upper = Math.max(a, b);

        return IntStream.range(lower, upper).toArray();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Point point = (Point) o;
        return x == point.x && y == point.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
