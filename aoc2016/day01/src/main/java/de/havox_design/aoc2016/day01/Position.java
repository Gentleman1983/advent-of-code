package de.havox_design.aoc2016.day01;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public record Position(Direction direction, Point point) {
    private static final Pattern PATTERN = Pattern.compile("(?<turn>[LR])(?<offset>\\d+)");

    public Position next(String instruction) {
        Matcher matcher = PATTERN.matcher(instruction);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Unmatched instruction " + instruction + ".");
        }
        int offset = Integer.parseInt(matcher.group("offset"));
        Direction nextDirection = switch (matcher.group("turn")) {
            case "L" -> direction.left();
            case "R" -> direction.right();
            default -> throw new IllegalArgumentException("Unexpected turn.");
        };
        return new Position(nextDirection, nextDirection.next(point, offset));
    }
}

