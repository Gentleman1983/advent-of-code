package de.havox_design.aoc2016.day20;

public record Range(long lowerBond, long upperBond) {
    private static final String VALUE_DELIMITER = "-";
    private static final int LOWER_BOND = 0;
    private static final int UPPER_BOND = 1;

    Range(String line) {
        this(
                Long.parseLong(line.split(VALUE_DELIMITER)[LOWER_BOND]),
                Long.parseLong(line.split(VALUE_DELIMITER)[UPPER_BOND])
        );
    }

    boolean contains(long i) {
        return i >= lowerBond
                && i <= upperBond;
    }
}
