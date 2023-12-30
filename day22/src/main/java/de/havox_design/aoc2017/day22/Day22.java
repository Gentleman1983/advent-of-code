package de.havox_design.aoc2017.day22;

import de.havox_design.aoc.utils.DataReader;
import de.havox_design.aoc2017.day19.Direction;
import de.havox_design.aoc2017.day19.Position;

import java.util.List;

public class Day22 {
    private static final char ICON_INFECTED = '#';

    private final List<String> input;

    public Day22(String fileName) {
        input = readData(fileName);
    }

    public static long solvePart1(String fileName, int iterations) {
        Day22 instance = new Day22(fileName);
        return instance.solvePart1(iterations);
    }

    public static long solvePart2(String fileName, int iterations) {
        Day22 instance = new Day22(fileName);
        return instance.solvePart2(iterations);
    }

    public long solvePart1(int iterations) {
        final VirusFunction virus = (currentPosition, currentState, currentRow, counter) -> {
            if (currentState == State.INFECTED) {
                currentPosition = currentPosition.right();
                currentRow.put(currentPosition.getX(), State.CLEAN);
            } else {
                currentPosition = currentPosition.left();
                currentRow.put(currentPosition.getX(), State.INFECTED);
                counter.increment();
            }
            return currentPosition.forward();
        };

        return calc(iterations, virus);
    }

    public long solvePart2(int iterations) {
        return 0L;
    }

    Integer calc(final int iterations, final VirusFunction virusFunction) {
        final BidirectionalGrowingArray<BidirectionalGrowingArray<State>> array = parseGrid();
        Position currentPosition = getStartPosition(array);
        final Counter infections = new Counter();

        for (int i = 0; i < iterations; i++) {
            BidirectionalGrowingArray<State> currentRow = array.get(currentPosition.getY());

            if (currentRow == null) {
                array.put(currentPosition.getY(), currentRow = new BidirectionalGrowingArray<>(State[]::new));
            }

            State currentState = currentRow.get(currentPosition.getX());

            if (currentState == null) {
                currentRow.put(currentPosition.getX(), currentState = State.CLEAN);
            }

            currentPosition = virusFunction.apply(currentPosition, currentState, currentRow, infections);
        }

        return infections.getValue();
    }

    private static Position getStartPosition(BidirectionalGrowingArray<BidirectionalGrowingArray<State>> array) {
        return new Position(array.get(0).size() / 2, array.size() / 2, Direction.UP);
    }

    private BidirectionalGrowingArray<BidirectionalGrowingArray<State>> parseGrid() {
        final BidirectionalGrowingArray<BidirectionalGrowingArray<State>> array =
                new BidirectionalGrowingArray<>(BidirectionalGrowingArray[]::new);

        for (String dataRow : input) {
            final BidirectionalGrowingArray<State> row = new BidirectionalGrowingArray<>(State[]::new);
            array.put(array.size(), row);

            for (int column = 0; column < dataRow.length(); column++) {
                row.put(column, dataRow.charAt(column) == ICON_INFECTED ? State.INFECTED : State.CLEAN);
            }
        }
        return array;
    }

    private List<String> readData(String fileName) {
        return DataReader.readData(fileName, MainClass.class);
    }
}
