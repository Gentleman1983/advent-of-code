package de.havox_design.aoc2018.day06;

import de.havox_design.aoc.utils.java.AoCFunctionality;
import de.havox_design.aoc.utils.kotlin.model.positions.Position2d;

import java.util.*;

public class ChronalCoordinates implements AoCFunctionality {
    private static final String ILLEGAL_STATE_MESSAGE = "This should never happen...";

    private final List<Position2d<Integer>> input;

    public ChronalCoordinates(String fileName) {
        CoordinateParser parser = new CoordinateParser();

        input = parser.parse(readData(fileName));
    }

    public static long processTask1(String fileName) {
        ChronalCoordinates instance = new ChronalCoordinates(fileName);
        return instance.processTask1();
    }

    public static long processTask2(String fileName) {
        ChronalCoordinates instance = new ChronalCoordinates(fileName);
        return instance.processTask2();
    }

    public long processTask1() {
        int maxX = input
                .stream()
                .mapToInt(Position2d::getX)
                .max()
                .orElseThrow(() -> new IllegalStateException(ILLEGAL_STATE_MESSAGE));
        int maxY = input
                .stream()
                .mapToInt(Position2d::getY)
                .max()
                .orElseThrow(() -> new IllegalStateException(ILLEGAL_STATE_MESSAGE));

        Position2d<Integer>[][] map = calculateCoordinateDistances(maxX, maxY);
        Set<Position2d<Integer>> candidates = calculateNonInfiniteCandidates(maxX, maxY, map);
        Map<Position2d<Integer>, Long> counts = countCandidates(maxX, maxY, map, candidates);

        return counts
                .values()
                .stream()
                .max(Comparator.naturalOrder())
                .orElseThrow(() -> new IllegalStateException(ILLEGAL_STATE_MESSAGE));
    }

    public long processTask2() {
        return 0;
    }

    private Position2d<Integer> detectNearestCoordinate(int x, int y) {
        Map<Position2d<Integer>, Integer> distances = new HashMap<>();
        for (Position2d<Integer> coordinate : this.input) {
            int distance = Math.abs(x - coordinate.getX()) + Math.abs(y - coordinate.getY());
            distances.put(coordinate, distance);
        }

        int minDistance = distances
                .values()
                .stream()
                .min(Comparator.naturalOrder())
                .orElseThrow(() -> new IllegalStateException(ILLEGAL_STATE_MESSAGE));

        boolean multipleMinDistances = distances
               .values()
                .stream()
                .filter(distance -> distance == minDistance)
                .count() > 1;

        Position2d<Integer> minCoordinate = distances
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() == minDistance)
                .map(Map.Entry::getKey)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(ILLEGAL_STATE_MESSAGE));

        return multipleMinDistances ? null : minCoordinate;
    }

    @SuppressWarnings("unchecked")
    private Position2d<Integer>[][] calculateCoordinateDistances(int maxX, int maxY) {
        Position2d<Integer>[][] map = new Position2d[maxX + 1][maxY + 1];
        for (int x = 0; x <= maxX; x++) {
            for (int y = 0; y <= maxY; y++) {
                map[x][y] = this.detectNearestCoordinate(x, y);
            }
        }
        return map;
    }

    private Set<Position2d<Integer>> calculateNonInfiniteCandidates(int maxX, int maxY, Position2d<Integer>[][] map) {
        Set<Position2d<Integer>> candidates = new HashSet<>(input);
        for (int x = 0; x <= maxX; x++) {
            int minY = 0;

            candidates.remove(map[x][minY]);
            candidates.remove(map[x][maxY]);
        }
        for (int y = 0; y <= maxY; y++) {
            int minX = 0;

            candidates.remove(map[minX][y]);
            candidates.remove(map[maxX][y]);
        }
        return candidates;
    }

    private static Map<Position2d<Integer>, Long> countCandidates(
            int maxX,
            int maxY,
            Position2d<Integer>[][] map,
            Set<Position2d<Integer>> candidates
    ) {
        Map<Position2d<Integer>, Long> counts = new HashMap<>();
        for (int x = 0; x <= maxX; x++) {
            for (int y = 0; y <= maxY; y++) {
                Position2d<Integer> coordinate = map[x][y];
                if (candidates.contains(coordinate)) {
                    counts.put(coordinate, counts.getOrDefault(coordinate, 0L) + 1);
                }
            }
        }
        return counts;
    }
}
