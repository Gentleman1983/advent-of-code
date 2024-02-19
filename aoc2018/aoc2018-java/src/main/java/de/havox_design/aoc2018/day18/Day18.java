package de.havox_design.aoc2018.day18;

import de.havox_design.aoc.utils.java.AoCFunctionality;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Day18 implements AoCFunctionality {
    private static final char SYMBOL_LUMBERYARD = '#';
    private static final char SYMBOL_OPEN = '.';
    private static final char SYMBOL_TREES = '|';

    private final List<String> input;

    public Day18(String fileName) {
        input = readData(fileName);
    }

    public static long processTask1(String fileName) {
        Day18 instance = new Day18(fileName);
        return instance.processTask1();
    }

    public static long processTask2(String fileName) {
        Day18 instance = new Day18(fileName);
        return instance.processTask2();
    }

    public long processTask1() {
        char[][] map = initializeMap(input);

        for (int minute = 0; minute < 10; minute++) {
            map = processMinute(map);
        }

        return calculateResourceScore(map);
    }

    public long processTask2() {
        return 0;
    }

    private long calculateResourceScore(char[][] map) {
        long trees = 0L;
        long lumberyards = 0L;

        for (char[] row : map) {
            for (int column = 0; column < map[0].length; column++) {
                if (row[column] == SYMBOL_TREES) {
                    trees++;
                } else if (row[column] == SYMBOL_LUMBERYARD) {
                    lumberyards++;
                }
            }
        }

        return trees * lumberyards;
    }

    private char[][] processMinute(char[][] map) {
        char[][] newMap = new char[map.length][map[0].length];

        for (int row = 0; row < map.length; row++) {
            for (int column = 0; column < map[0].length; column++) {
                List<Character> surroundingTiles = getSurroundingTiles(map, row, column);

                char currentTile = map[row][column];

                newMap[row][column] = getNextTile(currentTile, surroundingTiles);
            }
        }

        return newMap;
    }

    private char getNextTile(char currentTile, List<Character> surroundingTiles) {
        if (currentTile == SYMBOL_OPEN) {
            return surroundingTiles
                    .stream()
                    .filter(symbol -> symbol == SYMBOL_TREES)
                    .count() >= 3 ? SYMBOL_TREES : SYMBOL_OPEN;
        } else if (currentTile == SYMBOL_TREES) {
            return surroundingTiles
                    .stream()
                    .filter(symbol -> symbol == SYMBOL_LUMBERYARD)
                    .count() >= 3 ? SYMBOL_LUMBERYARD : SYMBOL_TREES;
        } else {
            if (
                    surroundingTiles.stream().anyMatch(symbol -> symbol == SYMBOL_LUMBERYARD) &&
                    surroundingTiles.stream().anyMatch(symbol -> symbol == SYMBOL_TREES)
            ) {
                return SYMBOL_LUMBERYARD;
            } else {
                return SYMBOL_OPEN;
            }
        }
    }

    private List<Character> getSurroundingTiles(char[][] map, int row, int column) {
        List<Character> surroundingTiles = new ArrayList<>();

        getTile(map, row - 1, column - 1).ifPresent(surroundingTiles::add);
        getTile(map, row - 1, column).ifPresent(surroundingTiles::add);
        getTile(map, row - 1, column + 1).ifPresent(surroundingTiles::add);

        getTile(map, row, column - 1).ifPresent(surroundingTiles::add);
        getTile(map, row, column + 1).ifPresent(surroundingTiles::add);

        getTile(map, row + 1, column - 1).ifPresent(surroundingTiles::add);
        getTile(map, row + 1, column).ifPresent(surroundingTiles::add);
        getTile(map, row + 1, column + 1).ifPresent(surroundingTiles::add);

        return surroundingTiles;
    }

    private Optional<Character> getTile(char[][] map, int row, int column) {
        try {
            char symbol = map[row][column];
            return Optional.of(symbol);
        } catch (ArrayIndexOutOfBoundsException e) {
            return Optional.empty();
        }
    }

    private char[][] initializeMap(List<String> inputs) {
        char[][] map = new char[inputs.size()][inputs.getFirst().length()];

        for (int row = 0; row < inputs.size(); row++) {
            char[] currentRow = inputs.get(row).toCharArray();
            System.arraycopy(currentRow, 0, map[row], 0, currentRow.length);
        }

        return map;
    }
}
