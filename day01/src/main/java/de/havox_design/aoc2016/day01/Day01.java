package de.havox_design.aoc2016.day01;

import de.havox_design.aoc2016.utils.DataReader;

import java.util.Arrays;
import java.util.List;

public class Day01 {
    private final String input;

    public Day01(String fileName) {
        input = readData(fileName);
    }

    public static long solvePart1(String fileName) {
        Day01 instance = new Day01(fileName);
        return instance.solvePart1();
    }

    public static long solvePart2(String fileName) {
        Day01 instance = new Day01(fileName);
        return instance.solvePart2();
    }

    public long solvePart1() {
        Point startPoint = new Point(0, 0);
        Direction startDirection = Direction.NORTH;

        Position position = new Position(startDirection, startPoint);

        for (String instruction : convertInputToInstructions()) {
            position = position.next(instruction);
        }

        return position
                .point()
                .getDistance();
    }

    public long solvePart2() {return 0L;
    }

    private List<String> convertInputToInstructions() {
        return Arrays
                .stream(input.split(", "))
                .toList();
    }

    private String readData(String fileName) {
        return DataReader.readData(fileName, MainClass.class).get(0);
    }
}
