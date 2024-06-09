package de.havox_design.aoc2016.day12;


import de.havox_design.aoc.utils.java.AoCFunctionality;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class LeonardosMonorail implements AoCFunctionality {
    private final List<String> input;

    public LeonardosMonorail(String fileName) {
        input = readData(fileName);
    }

    public static long solvePart1(String fileName) {
        LeonardosMonorail instance = new LeonardosMonorail(fileName);

        return instance.solvePart1();
    }

    public static long solvePart2(String fileName) {
        LeonardosMonorail instance = new LeonardosMonorail(fileName);

        return instance.solvePart2();
    }

    public long solvePart1() {
        return process(Map.of("a", 0, "b", 0, "c", 0, "d", 0));
    }

    public long solvePart2() {
        return process(Map.of("a", 0, "b", 0, "c", 1, "d", 0));
    }

    @SuppressWarnings({"squid:S127", "squid:S6916"})
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
}
