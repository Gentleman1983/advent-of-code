package de.havox_design.aoc2017.day03;

import de.havox_design.aoc.utils.DataReader;

import java.util.List;

public class Day03 {
    private final int input;

    public Day03(String fileName) {
        input = parseInput(readData(fileName));
    }

    public static long solvePart1(String fileName) {
        Day03 instance = new Day03(fileName);
        return instance.solvePart1();
    }

    public static long solvePart2(String fileName) {
        Day03 instance = new Day03(fileName);
        return instance.solvePart2();
    }

    public long solvePart1() {
        Point position = calculatePosition(input);
        return Math.abs(position.x()) + Math.abs(position.y());
    }

    public long solvePart2() {
        return 0L;
    }

    private Point calculatePosition(int input) {
        if(input == 1) {
            return new Point(0, 0);
        }

        int n = 1;

        while(StrictMath.pow(n, 2) < input) {
            n += 2;
        }

        int base = input - (int) StrictMath.pow(n - 2, 2) - 1;
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

    private List<String> readData(String fileName) {
        return DataReader.readData(fileName, MainClass.class);
    }
}
