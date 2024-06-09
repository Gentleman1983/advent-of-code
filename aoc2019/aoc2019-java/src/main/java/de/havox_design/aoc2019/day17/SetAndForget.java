package de.havox_design.aoc2019.day17;

import static de.havox_design.aoc.utils.kotlin.model.coordinates.FourDirections.*;

import de.havox_design.aoc.utils.java.AoCFunctionality;
import de.havox_design.aoc.utils.java.model.computer.aoc2019.IntComputer;
import de.havox_design.aoc.utils.kotlin.model.coordinates.FourDirections;
import org.apache.commons.lang3.tuple.Pair;

import java.util.*;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.function.UnaryOperator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class SetAndForget implements AoCFunctionality {
    private static final String VALUE_DELIMITER = ",";
    private static final char ICON_DIRECTION_DOWN = 'v';
    private static final char ICON_DIRECTION_LEFT = '<';
    private static final char ICON_DIRECTION_RIGHT = '>';
    private static final char ICON_DIRECTION_UP = '^';
    private static final String ICON_EMPTY = "";
    private static final String ICON_FUNCTION_A = "A";
    private static final String ICON_FUNCTION_B = "B";
    private static final String ICON_FUNCTION_C = "C";
    private static final String ICON_LEFT = "L";
    private static final char ICON_OPEN_SPACE = '.';
    private static final String ICON_RIGHT = "R";
    private static final char ICON_SCAFFOLD = '#';
    @SuppressWarnings("squid:S5998")
    private static final Pattern REGEXP = Pattern.compile(
            "(.{1,21})(?:\\1)*(.{1,21})(?:\\1|\\2)*(.{1,21})(?:\\1|\\2|\\3)*"
    );
    private static final Map<FourDirections, UnaryOperator<Pair<Long, Long>>> MOVE_POSITION = Map.of(
            UP, pos -> Pair.of(pos.getLeft(), pos.getRight() - 1),
            DOWN, pos -> Pair.of(pos.getLeft(), pos.getRight() + 1),
            LEFT, pos -> Pair.of(pos.getLeft() - 1, pos.getRight()),
            RIGHT, pos -> Pair.of(pos.getLeft() + 1, pos.getRight())
    );

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
        return calculateCollectedDust();
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

    private long calculateCollectedDust() {
        BlockingQueue<Long> in = new LinkedBlockingQueue<>();
        BlockingDeque<Long> out = new LinkedBlockingDeque<>();
        IntComputer.runComputer(input, in, out, false);
        Map<Pair<Long, Long>, Character> grid = initializeGrid(out);
        String[] commands = findCommands(findMovements(grid));
        Arrays
                .stream(commands)
                .map(String::chars)
                .flatMap(IntStream::boxed)
                .forEach(c -> in.add(c.longValue()));
        List<Long> program = new ArrayList<>(input);

        program.set(0, 2L);

        IntComputer.runComputer(program, in, out, false);

        return out.removeLast();
    }

    private String[] findCommands(String movements) {
        Matcher match = REGEXP.matcher(movements);

        if (!match.matches()) {
            throw new IllegalArgumentException();
        }

        String functionA = match
                .group(1)
                .replaceAll(",$", ICON_EMPTY);
        String functionB = match
                .group(2)
                .replaceAll(",$", ICON_EMPTY);
        String functionC = match
                .group(3)
                .replaceAll(",$", ICON_EMPTY);
        String mainRoutine = movements
                .replaceAll(functionA, ICON_FUNCTION_A)
                .replaceAll(functionB, ICON_FUNCTION_B)
                .replaceAll(functionC, ICON_FUNCTION_C)
                .replaceAll(",$", ICON_EMPTY);
        String[] commands = new String[5];

        commands[0] = mainRoutine + '\n';
        commands[1] = functionA + '\n';
        commands[2] = functionB + '\n';
        commands[3] = functionC + '\n';
        commands[4] = "n\n";

        return commands;
    }

    private String findMovements(Map<Pair<Long, Long>, Character> grid) {
        StringBuilder builder = new StringBuilder();
        Pair<Long, Long> droid = Pair
                .of(
                        grid
                                .entrySet()
                                .stream()
                                .filter(p -> directionFor(p.getValue()) != null)
                                .map(Map.Entry::getKey)
                                .findFirst()
                                .orElseThrow()
                );
        FourDirections direction = directionFor(grid.get(droid));
        boolean stop = false;
        long steps = 0;

        while (!stop) {
            if (grid.getOrDefault(MOVE_POSITION.get(direction).apply(droid), ICON_OPEN_SPACE) == ICON_SCAFFOLD) {
                droid = MOVE_POSITION.get(direction).apply(droid);
                steps++;
            } else {
                if (steps > 0) {
                    builder.append(VALUE_DELIMITER)
                            .append(steps)
                            .append(VALUE_DELIMITER);
                    steps = 0;
                }

                assert direction != null;

                if (grid.getOrDefault(
                        MOVE_POSITION.get(direction.turnRight()).apply(droid),
                        ICON_OPEN_SPACE) == ICON_SCAFFOLD) {
                    direction = direction.turnRight();
                    builder.append(ICON_RIGHT);
                } else if (grid.getOrDefault(
                        MOVE_POSITION.get(direction.turnLeft()).apply(droid),
                        ICON_OPEN_SPACE) == ICON_SCAFFOLD) {
                    direction = direction.turnLeft();
                    builder.append(ICON_LEFT);
                } else {
                    stop = true;
                }
            }
        }

        return builder.toString();
    }

    private FourDirections directionFor(char symbol) {
        return switch (symbol) {
            case ICON_DIRECTION_UP -> UP;
            case ICON_DIRECTION_RIGHT -> RIGHT;
            case ICON_DIRECTION_LEFT -> LEFT;
            case ICON_DIRECTION_DOWN -> DOWN;
            default -> null;
        };
    }
}
