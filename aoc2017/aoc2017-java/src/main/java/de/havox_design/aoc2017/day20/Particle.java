package de.havox_design.aoc2017.day20;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Particle {
    private static final Pattern ELEMENT_DELIMITER = Pattern.compile(",");
    private static final int ID_ACCELERATION = 2;
    private static final int ID_POSITION = 0;
    private static final int ID_VELOCITY = 1;
    private static final int ID_X = 0;
    private static final int ID_Y = 1;
    private static final int ID_Z = 2;
    private static final Pattern PARSER = Pattern.compile(
            "p=<(?<p>[,0-9-\\s]+)>,\\s+v=<(?<v>[,0-9-\\s]+)>,\\s+a=<(?<a>[,0-9-\\s]+)>"
    );

    private static final String PARSER_GROUP_ACCELERATION = "a";
    private static final String PARSER_GROUP_POSITION = "p";
    private static final String PARSER_GROUP_VELOCITY = "v";

    private final int[] accelerations;
    private final int[] xPva;
    private final int[] yPva;
    private final int[] zPva;

    Particle(int[] positions, int[] velocities, int[] accelerations) {
        this.accelerations = accelerations;
        this.xPva = new int[]{positions[ID_X], velocities[ID_X], accelerations[ID_X]};
        this.yPva = new int[]{positions[ID_Y], velocities[ID_Y], accelerations[ID_Y]};
        this.zPva = new int[]{positions[ID_Z], velocities[ID_Z], accelerations[ID_Z]};
    }

    double getTotalAcceleration() {
        return Math
                .sqrt(
                        Arrays
                                .stream(accelerations)
                                .map(i -> i * i)
                                .sum()
                );
    }

    Position positionAt(int t) {
        return new Position(
                positionAt(xPva, t),
                positionAt(yPva, t),
                positionAt(zPva, t)
        );
    }

    private static int positionAt(int[] pva, int t) {
        return pva[ID_POSITION] + pva[ID_VELOCITY] * t + pva[ID_ACCELERATION] * t * (t + 1) / 2;
    }

    static Particle from(String definition) {
        Matcher matcher = PARSER.matcher(definition);

        if (!matcher.matches()) {
            throw new IllegalArgumentException("Expected particle but got: " + definition);
        }

        return new Particle(
                toArray(matcher.group(PARSER_GROUP_POSITION)),
                toArray(matcher.group(PARSER_GROUP_VELOCITY)),
                toArray(matcher.group(PARSER_GROUP_ACCELERATION))
        );
    }

    private static int[] toArray(String input) {
        return ELEMENT_DELIMITER
                .splitAsStream(input)
                .map(String::trim)
                .mapToInt(Integer::parseInt)
                .toArray();
    }
}
