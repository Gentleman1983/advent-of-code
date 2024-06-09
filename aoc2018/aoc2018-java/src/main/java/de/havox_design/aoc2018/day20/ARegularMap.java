package de.havox_design.aoc2018.day20;

import de.havox_design.aoc.utils.java.AoCFunctionality;
import de.havox_design.aoc.utils.kotlin.model.positions.Position2d;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class ARegularMap implements AoCFunctionality {
    private static final char ICON_DIRECTION_EAST = 'E';
    private static final char ICON_DIRECTION_NORTH = 'N';
    private static final char ICON_DIRECTION_SOUTH = 'S';
    private static final char ICON_DIRECTION_WEST = 'W';
    private static final char ICON_OPTION_DIVIDER = '|';
    private static final char ICON_OPTION_END = ')';
    private static final char ICON_OPTION_START = '(';

    private final Map<Position2d<Integer>, Integer> distances;

    public ARegularMap(String fileName) {
        distances = calculateDistances(readData(fileName));
    }

    public static int processTask1(String fileName) {
        ARegularMap instance = new ARegularMap(fileName);

        return instance.processTask1();
    }

    public static long processTask2(String fileName) {
        ARegularMap instance = new ARegularMap(fileName);

        return instance.processTask2();
    }

    public int processTask1() {
        return distances
                .values()
                .stream()
                .max(Integer::compare)
                .orElse(0);
    }


    public long processTask2() {
        return distances
                .values()
                .stream()
                .filter(passedDoors -> passedDoors >= 1000)
                .count();
    }

    @SuppressWarnings("squid:S1149")
    private Map<Position2d<Integer>, Integer> calculateDistances(List<String> inputs) {
        Stack<Position2d<Integer>> junction = new Stack<>();
        Position2d<Integer> current = new Position2d<>(0, 0);
        Position2d<Integer> previous = new Position2d<>(0, 0);
        Map<Position2d<Integer>, Integer> distanceMap = new HashMap<>();

        distanceMap.put(current, 0);

        for (char character : inputs.getFirst().toCharArray()) {
            switch (character) {
                case ICON_OPTION_START:
                    junction.push(new Position2d<>(current.getX(), current.getY()));
                    break;
                case ICON_OPTION_END:
                    current = junction.pop();
                    break;
                case ICON_OPTION_DIVIDER:
                    current = junction.peek();
                    break;
                default: {
                    current = switch (character) {
                        case ICON_DIRECTION_NORTH -> new Position2d<>(current.getX(), current.getY() - 1);
                        case ICON_DIRECTION_SOUTH -> new Position2d<>(current.getX(), current.getY() + 1);
                        case ICON_DIRECTION_EAST -> new Position2d<>(current.getX() + 1, current.getY());
                        case ICON_DIRECTION_WEST -> new Position2d<>(current.getX() - 1, current.getY());
                        default -> current;
                    };
                    final var proposed = distanceMap.get(previous) + 1;
                    distanceMap.compute(current, (k, v) -> (v == null) ? proposed : Math.min(v, proposed));
                    break;
                }
            }

            previous = new Position2d<>(current.getX(), current.getY());
        }

        return distanceMap;
    }

}
