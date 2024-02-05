package de.havox_design.aoc2018.day06;

import de.havox_design.aoc.utils.kotlin.model.positions.Position2d;

import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CoordinateParser {
    private static final String COORDINATE_FORMAT = "(\\d+), (\\d+)";
    private static final Pattern COORDINATE_PATTERN = Pattern.compile(COORDINATE_FORMAT);


    public List<Position2d<Integer>> parse(Collection<String> input) {
        return input
                .stream()
                .map(COORDINATE_PATTERN::matcher)
                .filter(Matcher::matches)
                .map(matcher ->
                        new Position2d<>(
                                Integer.parseInt(matcher.group(1)),
                                Integer.parseInt(matcher.group(2))
                        )
                )
                .toList();
    }
}
