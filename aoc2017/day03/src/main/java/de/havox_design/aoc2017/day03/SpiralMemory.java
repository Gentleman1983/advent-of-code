package de.havox_design.aoc2017.day03;


import de.havox_design.aoc.utils.java.AoCFunctionality;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpiralMemory implements AoCFunctionality {
    private final int input;

    public SpiralMemory(String fileName) {
        input = parseInput(readData(fileName));
    }

    public static long solvePart1(String fileName) {
        SpiralMemory instance = new SpiralMemory(fileName);
        return instance.solvePart1();
    }

    public static long solvePart2(String fileName) {
        SpiralMemory instance = new SpiralMemory(fileName);
        return instance.solvePart2();
    }

    public int solvePart1() {
        Point position = calculatePosition(input);
        return Math.abs(position.x()) + Math.abs(position.y());
    }

    public long solvePart2() {
        return calculateValueLargerThanInput(2);
    }

    private int calculateValueLargerThanInput(int value) {
        Point origin = new Point(0, 0);
        Map<Point, Integer> map = new HashMap<>();

        map.put(origin, 1);

        return calculateValueLargerThanInput(value, map);
    }

    private int calculateValueLargerThanInput(int value, Map<Point, Integer> map) {
        Point point = calculatePosition(value);
        int result = point
                .neighbors()
                .stream()
                .map(p -> {
                            if (map.containsKey(p)) {
                                return map.get(p);
                            } else {
                                return 0;
                            }
                        }
                )
                .reduce(Integer::sum)
                .orElseThrow(() -> new IllegalStateException("This should not happen."));

        if (result > input) {
            return result;
        } else {
            map.put(point, result);
            return calculateValueLargerThanInput(value + 1, map);
        }
    }

    private Point calculatePosition(int input) {
        if (input == 1) {
            return new Point(0, 0);
        }

        int n = 1;

        while (StrictMath.pow(n, 2) < input) {
            n += 2;
        }

        int base = input - (int) StrictMath.pow((double) n - 2, 2) - 1;
        int size = n - 1;
        int half = size / 2;
        int quadrant = base / size;
        int offset = base % size;

        return switch (quadrant) {
            case 0 -> new Point(half, offset + 1 - half);
            case 1 -> new Point(half - 1 - offset, half);
            case 2 -> new Point(-half, half - 1 - offset);
            case 3 -> new Point(offset + 1 - half, -half);
            default -> throw new IllegalStateException("Unexpected value: " + quadrant);
        };
    }

    private int parseInput(List<String> input) {
        return Integer.parseInt(input.get(0));
    }
}
