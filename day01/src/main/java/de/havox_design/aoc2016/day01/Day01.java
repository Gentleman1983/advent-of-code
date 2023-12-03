package de.havox_design.aoc2016.day01;

import de.havox_design.aoc2016.utils.input.DataReader;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        Position position = getStartPoint();

        for (String instruction : convertInputToInstructions()) {
            position = position.next(instruction);
        }

        return position
                .point()
                .getDistance();
    }

    public long solvePart2() {
        Position position = getStartPoint();
        Set<Point> visitedPoints = new HashSet<>();

        for (String instruction : convertInputToInstructions()) {
            Position nextPosition = position.next(instruction);

            List<Point> path = position.point().range(nextPosition.point());
            for(Point point: path) {
                if(
                        visitedPoints.contains(point)
                                && !point.equals(position.point())
                ) {
                    return point.getDistance();
                }

                visitedPoints.add(point);
            }

            position = nextPosition;
        }

        throw new IllegalStateException("Did not visit any position twice...");
    }

    private Position getStartPoint() {
        Point startPoint = new Point(0, 0);
        Direction startDirection = Direction.NORTH;

        return new Position(startDirection, startPoint);
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
