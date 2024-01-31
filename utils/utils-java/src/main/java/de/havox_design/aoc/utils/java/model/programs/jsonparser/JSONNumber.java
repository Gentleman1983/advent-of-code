package de.havox_design.aoc.utils.java.model.programs.jsonparser;

import java.io.Serial;
import java.util.List;
import java.util.Set;

public final class JSONNumber extends Number implements JSONEntity {
    @Serial
    private static final long serialVersionUID = 1L;

    private static final Set<Character> NUMBER_STARTERS = Set.of(
            '-',
            '0',
            '1',
            '2',
            '3',
            '4',
            '5',
            '6',
            '7',
            '8',
            '9'
    );

    private final Number number;

    public JSONNumber(Number number) {
        this.number = number;
    }

    @Override
    public int intValue() {
        return number.intValue();
    }

    @Override
    public long longValue() {
        return number.longValue();
    }

    @Override
    public float floatValue() {
        return number.floatValue();
    }

    @Override
    public double doubleValue() {
        return number.doubleValue();
    }

    @Override
    public String toString() {
        return number.toString();
    }

    @Override
    public List<Integer> getIntegersWithoutRed() {
        return List.of(number.intValue());
    }

    public static boolean canBeStartOfANumber(char c) {
        return NUMBER_STARTERS.contains(c);
    }

    public static boolean canBeInnerPartOfANumber(char c) {
        if (c == '-') {
            return false;
        }
        if (c == '.') {
            return true;
        }
        return NUMBER_STARTERS.contains(c);
    }

}
