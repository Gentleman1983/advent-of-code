package de.havox_design.aoc2017.day19;

import de.havox_design.aoc.utils.java.input.DataReader;

import java.util.List;

public class Day19 {
    private static final char ICON_EMPTY = ' ';
    private static final char ICON_PIPE_DOWN = '|';
    private final List<String> input;

    public Day19(String fileName) {
        input = readData(fileName);
    }

    public static String solvePart1(String fileName) {
        Day19 instance = new Day19(fileName);
        return instance.solvePart1();
    }

    public static long solvePart2(String fileName) {
        Day19 instance = new Day19(fileName);
        return instance.solvePart2();
    }

    public String solvePart1() {
        return processTubes(parseDiagram()).word();
    }

    public long solvePart2() {
        return processTubes(parseDiagram()).steps();
    }

    private Result processTubes(char[][] diagram) {
        final StringBuilder word = new StringBuilder();
        long stepCounter = 1L;
        Position currentPosition = detectStartPosition(diagram[0]);
        
        while (canMove(diagram, currentPosition)) {
            final Position nextPosition = detectNextPosition(diagram, currentPosition);
            currentPosition = nextPosition;

            if (Character.isLetter(diagram[currentPosition.getY()][currentPosition.getX()])) {
                word.append(diagram[currentPosition.getY()][currentPosition.getX()]);
            }

            stepCounter++;
        }
        
        return new Result(word.toString(), stepCounter);
    }

    private boolean canMove(final char[][] diagram, final Position pos) {
        return isNextPosition(diagram, pos.forward())
                || isNextPosition(diagram, pos.left().forward())
                || isNextPosition(diagram, pos.right().forward());
    }

    private Position detectNextPosition(final char[][] diagram, final Position position) {
        Position nextPosition = position.forward();
        if (isNextPosition(diagram, nextPosition)) {
            return nextPosition;
        }

        nextPosition = position.left().forward();
        if (isNextPosition(diagram, nextPosition)) {
            return nextPosition;
        }

        nextPosition = position.right().forward();
        if (isNextPosition(diagram, nextPosition)) {
            return nextPosition;
        }

        throw new IllegalStateException("Not able to detect a way to go from '" + position + "'.");
    }

    private boolean isNextPosition(char[][] diagram, Position target) {
        return getDiagramPosition(diagram, target) != ICON_EMPTY;
    }

    private char getDiagramPosition(final char[][] diagram, final Position position) {
        return isOnDiagram(diagram, position) ? diagram[position.getY()][position.getX()] : ICON_EMPTY;
    }

    private boolean isOnDiagram(final char[][] diagram, final Position position) {
        final int height = diagram.length;
        final int width = diagram[0].length;

        return position.getX() >= 0
                && position.getY() >= 0
                && position.getX() < width
                && position.getY() < height;
    }

    private Position detectStartPosition(final char... firstLine) {
        for (int x = 0; x < firstLine.length; x++) {
            if (firstLine[x] == ICON_PIPE_DOWN) {
                return new Position(x, 0, Direction.DOWN);
            }
        }

        throw new IllegalArgumentException("Could not detect maze start!");
    }

    private char[][] parseDiagram() {
        final int width = input
                .stream()
                .mapToInt(String::length)
                .max()
                .orElse(0);
        final int height = input.size();
        final char[][] diagram = new char[height][width];
        
        for (int y = 0; y < height; y++) {
            final String row = input.get(y);
            
            for (int x = 0; x < width; x++) {
                diagram[y][x] = x < row.length() ? row.charAt(x) : ICON_EMPTY;
            }
        }
        
        return diagram;
    }


    private List<String> readData(String fileName) {
        return DataReader.readData(fileName, MainClass.class);
    }
}
