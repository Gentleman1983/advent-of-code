package de.havox_design.aoc2016.day09;


import de.havox_design.aoc.utils.java.AoCFunctionality;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExplosivesInCyberspace implements AoCFunctionality {
    private static final Pattern PATTERN = Pattern.compile("(?<length>\\d+)x(?<repeats>\\d+)");
    private static final String MARKER_FORMAT = "(%dx%d)";

    private final String input;

    public ExplosivesInCyberspace(String fileName) {
        input = readData(fileName).getFirst();
    }

    public static long solvePart1(String fileName) {
        ExplosivesInCyberspace instance = new ExplosivesInCyberspace(fileName);

        return instance.solvePart1();
    }

    public static long solvePart2(String fileName) {
        ExplosivesInCyberspace instance = new ExplosivesInCyberspace(fileName);

        return instance.solvePart2();
    }

    public long solvePart1() {
        return process(input, false);
    }

    public long solvePart2() {
        return process(input, true);
    }

    @SuppressWarnings("squid:S127")
    private static long process(String input, boolean isNested) {
        long length = 0L;
        Marker marker = null;

        for (int i = 0; i < input.length(); ) {
            if (marker == null) {
                if (input.charAt(i) == MARKER_FORMAT.charAt(0)) {
                    marker = parseMarker(i, input);
                    i += String.format(MARKER_FORMAT, marker.size(), marker.times()).length();
                } else {
                    length++;
                    i++;
                }
            } else {
                length += (
                        isNested ?
                                process(input.substring(i, i + marker.size()), true) :
                                marker.size()
                ) * marker.times();
                i += marker.size();
                marker = null;
            }
        }

        return length;
    }

    private static Marker parseMarker(int index, String input) {
        return Optional
                .of(
                        PATTERN.matcher(
                                input.substring(index + 1, input.indexOf(MARKER_FORMAT.charAt(6), index + 1))
                        )
                )
                .filter(Matcher::matches)
                .map(
                        marker -> new Marker(
                                Integer.parseInt(marker.group("length")),
                                Integer.parseInt(marker.group("repeats"))
                        )
                )
                .orElseThrow();
    }
}
