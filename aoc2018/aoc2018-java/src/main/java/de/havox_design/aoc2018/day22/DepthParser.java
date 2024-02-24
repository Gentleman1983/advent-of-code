package de.havox_design.aoc2018.day22;

public class DepthParser {
    private static final String DEPTH_PREFIX = "depth:";
    private static final String ERROR_MESSAGE = "Unable to parse input";

    public int parse(final String toParse) {
        try {
            if (toParse.startsWith(DEPTH_PREFIX)) {
                return Integer.parseInt(toParse.substring(DEPTH_PREFIX.length()).trim());
            }
        } catch (NumberFormatException nfe) {
            throw new IllegalArgumentException(buildErrorMessage(toParse), nfe);
        }

        throw new IllegalArgumentException(buildErrorMessage(toParse));
    }

    private String buildErrorMessage(String input) {
        return String.format("%s '%s',", ERROR_MESSAGE, input);
    }
}
