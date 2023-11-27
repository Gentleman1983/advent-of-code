package de.havox_design.aoc2016.day01;

import java.util.List;
import java.util.function.IntFunction;
import java.util.stream.IntStream;

public record Point(int x, int y) {
    int getDistance() {
        return Math.abs(x) + Math.abs(y);
    }

    List<Point> range(Point other) {
        if (x == other.x) {
            return ranger(y, other.y, i -> new Point(x, i));
        } else if (y == other.y) {
            return ranger(x, other.x, i -> new Point(i, y));
        } else {
            throw new IllegalArgumentException("Incorrect range");
        }
    }

    private List<Point> ranger(int a, int b, IntFunction<Point> mapper) {
        return (
                a < b ?
                        IntStream
                                .range(a, b) :
                        IntStream
                                .range(-1 * b, -1 * a)
                                .map(i -> -1 * i))
                .mapToObj(mapper)
                .toList();
    }
}
