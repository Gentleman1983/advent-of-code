package de.havox_design.aoc2016.day12;

import de.havox_design.aoc2016.utils.DataReader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Day12 {
    private final List<String> input;

    public Day12(String fileName) {
        input = readData(fileName);
    }

    public static long solvePart1(String fileName) {
        Day12 instance = new Day12(fileName);
        return instance.solvePart1();
    }

    public static long solvePart2(String fileName) {
        Day12 instance = new Day12(fileName);
        return instance.solvePart2();
    }

    public long solvePart1() {
        return process(Map.of("a", 0, "b", 0, "c", 0, "d", 0));
    }

    public long solvePart2() {
        return 0L;
    }

    private long process(Map<String, Integer> initial) {
        Map<String, Integer> registers = new HashMap<>(initial);

        for (int i = 0; i < input.size(); ) {
            String[] values = input.get(i).split(" ");
            i++;

            switch (values[0]) {
                case "cpy" -> registers.put(values[2], parseOrGet(values[1], registers));
                case "inc" -> registers.compute(values[1], (k, v) -> Objects.requireNonNull(v) + 1);
                case "dec" -> registers.compute(values[1], (k, v) -> Objects.requireNonNull(v) - 1);
                case "jnz" -> {
                    if (parseOrGet(values[1], registers) > 0) {
                        i += Integer.parseInt(values[2]) - 1;
                    }
                }
                default -> throw new IllegalArgumentException("Unknown instruction.");
            }
        }

        return registers.get("a");
    }

    private int parseOrGet(String value, Map<String, Integer> registers) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException nfe) {
            return registers.get(value);
        }
    }

    private List<String> readData(String fileName) {
        return DataReader.readData(fileName, MainClass.class);
    }
}
