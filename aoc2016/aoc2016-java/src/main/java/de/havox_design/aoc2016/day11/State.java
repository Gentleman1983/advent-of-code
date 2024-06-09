package de.havox_design.aoc2016.day11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class State {
    public static final int FLOOR_COUNT = 4;
    private static final int TARGET_FLOOR = FLOOR_COUNT - 1;

    private int elevator;
    private final int[] items;

    public State(State state) {
        items = state.items.clone();
    }

    public State(List<String> input) {
        elevator = 0;

        List<String> types = getAllMatches(String.join(" ", input), "[^ ]*-compatible")
                .stream()
                .map(x -> x.split("-")[0])
                .distinct()
                .toList();

        items = new int[types.size() * 2];

        for (int f = 0; f < FLOOR_COUNT; f++) {
            String line = input.get(f);

            if (line.contains("nothing relevant")) {
                continue;
            }

            for (String generator : getAllMatches(line, "[^ ]* generator")) {
                int typeId = types.indexOf(generator.replace(" generator", ""));
                items[typeId * 2] = f;
            }

            for (String microchip : getAllMatches(line, "[^ ]*-compatible microchip")) {
                int typeId = types.indexOf(microchip.replace("-compatible microchip", ""));
                items[typeId * 2 + 1] = f;
            }
        }
    }

    public int getElevator() {
        return elevator;
    }

    public int getItemCount() {
        return items.length;
    }

    public int getFloor(int itemId) {
        return items[itemId];
    }

    public State move(int itemId, int floor) {
        State state = new State(this);
        state.elevator = floor;
        state.items[itemId] = floor;

        return state;
    }

    public boolean isTerminal() {
        return elevator == TARGET_FLOOR
                && IntStream
                .range(0, items.length)
                .allMatch(i -> getFloor(i) == TARGET_FLOOR);
    }

    public boolean isSafe() {
        for (int f = 0; f < FLOOR_COUNT; f++) {
            if (!containsGenerator(f)) {
                continue;
            }

            for (int i = 0; i < items.length; i += 2) {
                if (items[i + 1] == f && items[i] != f) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean containsGenerator(int floor) {
        for (int i = 0; i < items.length; i += 2) {
            if (items[i] == floor) {
                return true;
            }
        }

        return false;
    }

    private static List<String> getAllMatches(String str, String regex) {
        List<String> matches = new ArrayList<>();
        Matcher m = Pattern.compile("(" + regex + ")").matcher(str);

        while (m.find()) {
            matches.add(m.group(1));
        }

        return matches;
    }

    @Override
    public String toString() {
        return String.format("E: %d, Items: %s", elevator, Arrays.toString(items));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        State other = (State) o;

        return elevator == other.elevator &&
                Arrays.equals(getCanonicalRepresentationOfItems(), other.getCanonicalRepresentationOfItems());
    }

    @Override
    public int hashCode() {
        return elevator + Arrays.hashCode(getCanonicalRepresentationOfItems());
    }

    private int[] getCanonicalRepresentationOfItems() {
        int[] canonical = new int[items.length / 2];

        for (int i = 0; i < items.length; i += 2) {
            canonical[i / 2] = (items[i] << 8) | items[i + 1];
        }

        Arrays.sort(canonical);
        return canonical;
    }
}
