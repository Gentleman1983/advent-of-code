package de.havox_design.aoc2018.day25;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PositionParser {
    private static final String POSITION_PATTERN = "^(?<x>-?\\d+),\\s*(?<y>-?\\d+),\\s*(?<z>-?\\d+),\\s*(?<s>-?\\d+)$";

    public Position4d parseLine(String line) {
        Pattern pattern = Pattern.compile(POSITION_PATTERN);
        Matcher matcher = pattern.matcher(line);

        if (matcher.matches()) {
            int x = Integer.parseInt(matcher.group("x").trim());
            int y = Integer.parseInt(matcher.group("y").trim());
            int z = Integer.parseInt(matcher.group("z").trim());
            int s = Integer.parseInt(matcher.group("s").trim());

            return new Position4d(x, y, z, s);
        }
        throw new IllegalArgumentException("Expected the input '" + line + "' to match pattern '" + POSITION_PATTERN + "'.");
    }
}
