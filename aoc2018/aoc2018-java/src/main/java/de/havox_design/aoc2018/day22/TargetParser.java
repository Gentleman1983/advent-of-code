package de.havox_design.aoc2018.day22;

import de.havox_design.aoc.utils.kotlin.model.positions.Position2d;

public class TargetParser {
    private static final String COORDINATE_DELIMITER = ",";
    private static final String ERROR_MESSAGE = "Unable to parse input";
    private static final int INDEX_X_COORDINATE = 0;
    private static final int INDEX_Y_COORDINATE = 1;
    private static final String TARGET_PREFIX = "target:";

    public Position2d<Integer> parse(final String toParse) {
        try {
            if (toParse.startsWith(TARGET_PREFIX)) {
                String coordinatString = toParse.substring(TARGET_PREFIX.length()).trim();
                String[] coordinates = coordinatString.split(COORDINATE_DELIMITER);
                int x = Integer.parseInt(coordinates[INDEX_X_COORDINATE].trim());
                int y = Integer.parseInt(coordinates[INDEX_Y_COORDINATE].trim());

                return new Position2d<>(x, y);
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
