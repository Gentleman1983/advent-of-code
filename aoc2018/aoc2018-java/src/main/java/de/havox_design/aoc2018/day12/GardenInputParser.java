package de.havox_design.aoc2018.day12;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GardenInputParser {
    private static final String EXCEPTION_MESSAGE = "Could not parse input file...";
    private static final String INITIAL_STATE_FORMAT = "initial state: ([#|.]+)";
    private static final String SPREAD_FORMAT = "([#|.]+) => ([#|.])";
    private static final Pattern INITIAL_STATE_PATTERN = Pattern.compile(INITIAL_STATE_FORMAT);
    private static final Pattern SPREAD_PATTERN = Pattern.compile(SPREAD_FORMAT);

    public GardenInput parse(List<String> input) {
        Matcher initialStateMatcher = INITIAL_STATE_PATTERN.matcher(input.getFirst());

        if (initialStateMatcher.matches()) {
            List<Boolean> initialState = Arrays
                    .stream(initialStateMatcher.group(1).split("(?!^)"))
                    .map(s -> s.equals("#"))
                    .toList();
            Map<List<Boolean>, Boolean> patterns = new HashMap<>();

            for (int i = 2; i < input.size(); i++) {
                Matcher spreadMatcher = SPREAD_PATTERN.matcher(input.get(i));

                if (!spreadMatcher.matches()) {
                    throw new IllegalArgumentException(EXCEPTION_MESSAGE);
                }

                List<Boolean> key = Arrays
                        .stream(spreadMatcher.group(1).split("(?!^)"))
                        .map(s -> s.equals("#"))
                        .toList();
                Boolean value = spreadMatcher.group(2).equals("#");

                patterns.put(key, value);
            }

            return new GardenInput(initialState, patterns);
        }

        throw new IllegalArgumentException(EXCEPTION_MESSAGE);
    }
}
