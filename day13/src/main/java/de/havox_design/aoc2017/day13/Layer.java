package de.havox_design.aoc2017.day13;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Layer {
    private static final Pattern PARSER = Pattern.compile(
            "(?<position>\\d+): (?<level>\\d+)");

    private final int position;
    private final int level;

    private Layer(int position, int level) {
        this.position = position;
        this.level = level;
    }

    int getSeverityPlusDelay() {
        return getSeverityPlusDelay(0);
    }

    int getSeverityPlusDelay(int delay) {
        return level == 1 || (delay + position) % (2 * (level - 1)) == 0 ? delay + position * level : 0;
    }

    static Layer parse(String layer) {
        Matcher matcher = PARSER.matcher(layer);

        if (!matcher.matches()) {
            throw new IllegalArgumentException("Expected layer but got: " + layer);
        }

        return new Layer(
                Integer.parseInt(matcher.group("position")),
                Integer.parseInt(matcher.group("level"))
        );
    }
}
