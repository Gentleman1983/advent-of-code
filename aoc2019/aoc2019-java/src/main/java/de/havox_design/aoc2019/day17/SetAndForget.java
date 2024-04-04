package de.havox_design.aoc2019.day17;

import de.havox_design.aoc.utils.java.AoCFunctionality;
import de.havox_design.aoc.utils.java.model.computer.aoc2019.IntComputer;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.Stream;

public class SetAndForget implements AoCFunctionality {
    private static final String VALUE_DELIMITER = ",";
    private static final char ICON_OPEN_SPACE = '.';
    private static final char ICON_SCAFFOLD = '#';

    private final List<Long> input;

    public SetAndForget(String fileName) {
        input = Arrays
                .stream(readString(fileName).split(VALUE_DELIMITER))
                .map(value -> Long.parseLong(value.trim()))
                .toList();
    }

    public static long processTask1(String fileName) {
        SetAndForget instance = new SetAndForget(fileName);
        return instance.processTask1();
    }

    public static long processTask2(String fileName) {
        SetAndForget instance = new SetAndForget(fileName);
        return instance.processTask2();
    }

    public long processTask1() {
        return calculateSumOfAlignmentParameters();
    }

    public long processTask2() {
        return 0;
    }

    private long calculateSumOfAlignmentParameters() {
        BlockingQueue<Long> in = new LinkedBlockingQueue<>();
        BlockingDeque<Long> out = new LinkedBlockingDeque<>();
        IntComputer.runComputer(input, in, out, false);

        Map<Pair<Long, Long>, Character> grid = initializeGrid(out);

        return computeIntersections(grid);
    }

    private Map<Pair<Long, Long>, Character> initializeGrid(BlockingDeque<Long> out) {
        Map<Pair<Long, Long>, Character> grid = new HashMap<>();
        Pair<Long, Long> position = Pair.of(0L, 0L);
        Long symbol;

        while ((symbol = out.poll()) != null) {
            char c = (char) symbol.intValue();

            grid.put(Pair.of(position), c);
            position = Pair.of(position.getLeft() + 1, position.getRight());
            if (symbol == '\n') {
                position = Pair.of(0L, position.getRight() + 1);
            }
        }

        return grid;
    }

    private long computeIntersections(Map<Pair<Long, Long>, Character> grid) {
        long result = 0L;

        for (final var point : grid.keySet()) {
            if (isIntersection(point, grid)) {
                result += (point.getLeft() * point.getRight());
            }
        }
        return result;
    }

    private boolean isIntersection(Pair<Long, Long> point, Map<Pair<Long, Long>, Character> grid) {
        return Stream
                .of(
                        point,
                        Pair.of(point.getLeft() - 1, point.getRight()),
                        Pair.of(point.getLeft() + 1, point.getRight()),
                        Pair.of(point.getLeft(), point.getRight() - 1),
                        Pair.of(point.getLeft(), point.getRight() + 1)
                )
                .map(p -> grid.getOrDefault(p, ICON_OPEN_SPACE))
                .filter(c -> c != ICON_SCAFFOLD)
                .findAny()
                .isEmpty();
    }
}
