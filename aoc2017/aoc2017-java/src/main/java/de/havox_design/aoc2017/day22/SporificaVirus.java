package de.havox_design.aoc2017.day22;

import de.havox_design.aoc.utils.java.AoCFunctionality;
import de.havox_design.aoc.utils.java.model.arrays.BidirectionalGrowingArray;
import de.havox_design.aoc.utils.kotlin.model.coordinates.FourDirections;
import de.havox_design.aoc.utils.kotlin.model.positions.Position2d;
import de.havox_design.aoc.utils.kotlin.model.directed_position.DirectedPosition;

import java.util.List;

public class SporificaVirus implements AoCFunctionality {
    private static final char ICON_INFECTED = '#';

    private final List<String> input;
    private final VirusCollection viruses;

    public SporificaVirus(String fileName) {
        input = readData(fileName);
        viruses = buildViruses();
    }

    public static long solvePart1(String fileName, int iterations) {
        SporificaVirus instance = new SporificaVirus(fileName);
        return instance.solvePart1(iterations);
    }

    public static long solvePart2(String fileName, int iterations) {
        SporificaVirus instance = new SporificaVirus(fileName);
        return instance.solvePart2(iterations);
    }

    public long solvePart1(int iterations) {
        return calculateInfectedNodes(iterations, viruses.virus1());
    }

    public long solvePart2(int iterations) {
        return calculateInfectedNodes(iterations, viruses.virus2());
    }

    @SuppressWarnings("squid:S1121")
    private long calculateInfectedNodes(final int iterations, final VirusFunction virusFunction) {
        final BidirectionalGrowingArray<BidirectionalGrowingArray<State>> array = parseGrid();
        DirectedPosition currentPosition = getStartPosition(array);
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

    private DirectedPosition getStartPosition(BidirectionalGrowingArray<BidirectionalGrowingArray<State>> array) {
        return new DirectedPosition(new Position2d<>(array.get(0).size() / 2, array.size() / 2), FourDirections.UP);
    }

    private VirusCollection buildViruses() {
        return new VirusCollection(buildVirus1(), buildVirus2());
    }

    private VirusFunction buildVirus1() {
        return (currentPosition, currentState, currentRow, counter) -> {
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
    }

    private VirusFunction buildVirus2() {
        return (currentPosition, currentState, currentRow, counter) -> {
            switch (currentState) {
                case CLEAN:
                    currentRow.put(currentPosition.getX(), State.WEAKENED);
                    currentPosition = currentPosition.left();
                    break;
                case FLAGGED:
                    currentRow.put(currentPosition.getX(), State.CLEAN);
                    currentPosition = currentPosition.left().left();
                    break;
                case INFECTED:
                    currentRow.put(currentPosition.getX(), State.FLAGGED);
                    currentPosition = currentPosition.right();
                    break;
                case WEAKENED:
                    currentRow.put(currentPosition.getX(), State.INFECTED);
                    counter.increment();
                    break;
                default:
                    throw new IllegalStateException();
            }

            return currentPosition.forward();
        };
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
}
