package de.havox_design.aoc2017.day19;


import de.havox_design.aoc.utils.java.AoCFunctionality;
import de.havox_design.aoc.utils.kotlin.model.directed_position.DirectedPosition;
import de.havox_design.aoc.utils.kotlin.model.coordinates.FourDirections;
import de.havox_design.aoc.utils.kotlin.model.positions.Position2d;

import java.util.List;

public class ASeriesOfTubes implements AoCFunctionality {
    private static final char ICON_EMPTY = ' ';
    private static final char ICON_PIPE_DOWN = '|';
    private final List<String> input;

    public ASeriesOfTubes(String fileName) {
        input = readData(fileName);
    }

    public static String solvePart1(String fileName) {
        ASeriesOfTubes instance = new ASeriesOfTubes(fileName);

        return instance.solvePart1();
    }

    public static long solvePart2(String fileName) {
        ASeriesOfTubes instance = new ASeriesOfTubes(fileName);

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
        DirectedPosition currentPosition = detectStartPosition(diagram[0]);
        
        while (canMove(diagram, currentPosition)) {
            final DirectedPosition nextPosition = detectNextPosition(diagram, currentPosition);
            currentPosition = nextPosition;

            if (Character.isLetter(diagram[currentPosition.getY()][currentPosition.getX()])) {
                word.append(diagram[currentPosition.getY()][currentPosition.getX()]);
            }

            stepCounter++;
        }
        
        return new Result(word.toString(), stepCounter);
    }

    private boolean canMove(final char[][] diagram, final DirectedPosition pos) {
        return isNextPosition(diagram, pos.forward())
                || isNextPosition(diagram, pos.left().forward())
                || isNextPosition(diagram, pos.right().forward());
    }

    private DirectedPosition detectNextPosition(final char[][] diagram, final DirectedPosition position) {
        DirectedPosition nextPosition = position.forward();
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

    private boolean isNextPosition(char[][] diagram, DirectedPosition target) {
        return getDiagramPosition(diagram, target) != ICON_EMPTY;
    }

    private char getDiagramPosition(final char[][] diagram, final DirectedPosition position) {
        return isOnDiagram(diagram, position) ? diagram[position.getY()][position.getX()] : ICON_EMPTY;
    }

    private boolean isOnDiagram(final char[][] diagram, final DirectedPosition position) {
        final int height = diagram.length;
        final int width = diagram[0].length;

        return position.getX() >= 0
                && position.getY() >= 0
                && position.getX() < width
                && position.getY() < height;
    }

    private DirectedPosition detectStartPosition(final char... firstLine) {
        for (int x = 0; x < firstLine.length; x++) {
            if (firstLine[x] == ICON_PIPE_DOWN) {
                return new DirectedPosition(new Position2d<>(x, 0), FourDirections.DOWN);
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
}
