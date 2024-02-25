package de.havox_design.aoc2018.day23;

import de.havox_design.aoc.utils.kotlin.model.positions.Position3d;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NanobotParser {
    private static final String NANOBOT_PATTERN = "pos=<(?<x>-?\\d+),(?<y>-?\\d+),(?<z>-?\\d+)>,\\s+r=(?<r>-?\\d+)";

    public List<Nanobot> parse(List<String> input) {
        return input
                .stream()
                .map(this::parseLine)
                .toList();
    }

    private Nanobot parseLine(String input) {
        Pattern pattern = Pattern.compile(NANOBOT_PATTERN);
        Matcher matcher = pattern.matcher(input);

        if(matcher.matches()) {
            Position3d<Long> position = new Position3d<>(
                    Long.parseLong(matcher.group("x")),
                    Long.parseLong(matcher.group("y")),
                    Long.parseLong(matcher.group("z"))
            );
            long range = Long.parseLong(matcher.group("r"));

            return new Nanobot(position, range);
        }
        throw new IllegalArgumentException("Expected the input '" + input + "' to match pattern '" + NANOBOT_PATTERN + "'.");
    }
}
