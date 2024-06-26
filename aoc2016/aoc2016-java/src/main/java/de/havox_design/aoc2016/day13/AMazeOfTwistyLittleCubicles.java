package de.havox_design.aoc2016.day13;

import de.havox_design.aoc.utils.java.AoCFunctionality;
import de.havox_design.aoc.utils.java.search.BreadthFirstSearch;
import de.havox_design.aoc.utils.java.search.PathResult;

import java.util.Map;

public class AMazeOfTwistyLittleCubicles implements AoCFunctionality {
    private final Long input;

    public AMazeOfTwistyLittleCubicles(String fileName) {
        input = Long.parseLong(readData(fileName).getFirst());
    }

    public static long solvePart1(String fileName) {
        AMazeOfTwistyLittleCubicles instance = new AMazeOfTwistyLittleCubicles(fileName);

        return instance.solvePart1(1, 1, 31, 39);
    }

    public static long solvePart2(String fileName) {
        AMazeOfTwistyLittleCubicles instance = new AMazeOfTwistyLittleCubicles(fileName);

        return instance.solvePart2(1, 1, 50);
    }

    public long solvePart1(int startColumn, int startRow, int endColumn, int endRow) {
        Point target = new Point(endColumn, endRow);

        return process(startColumn, startRow)
                .get(target)
                .getDistance();
    }

    public long solvePart2(int startColumn, int startRow, int maxDistance) {
        return process(startColumn, startRow)
                .values()
                .stream()
                .filter(res -> res.getDistance() <= maxDistance)
                .count();
    }

    private Map<Point, PathResult<Point>> process(int startColumn, int startRow) {
        Point start = new Point(startColumn, startRow);
        int mapSize = 52;

        return BreadthFirstSearch.run(
                start,
                point -> point.validNeighbors(mapSize, mapSize, p -> isNotWall(p, input))
        );
    }

    private boolean isNotWall(Point point, long shift) {
        return (Long.bitCount(getMagicNumber(point.x(), point.y(), shift)) & 1) == 0;
    }

    private long getMagicNumber(long x, long y, long shift) {
        return x * (x + 3) + y * (x + x + y + 1) + shift;
    }
}
