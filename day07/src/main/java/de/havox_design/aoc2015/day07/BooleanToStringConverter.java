package de.havox_design.aoc2015.day07;

import static de.havox_design.aoc2015.day07.LogicGates.NUMBER_OF_BITS;

import org.apache.commons.lang3.StringUtils;

public class BooleanToStringConverter {

    private BooleanToStringConverter() {
    }

    public static BooleanToStringConverter getInstance() {
        return SingletonHelper.INSTANCE;
    }

    private static class SingletonHelper {
        private static final BooleanToStringConverter INSTANCE = new BooleanToStringConverter();
    }

    public boolean[] convert(String string) {
        if (!StringUtils.isNumeric(string)) {
            throw new IllegalArgumentException("String is no number.");
        }

        return convert(Integer.parseInt(string));
    }

    public boolean[] convert(int value) {
        boolean[] binary = new boolean[NUMBER_OF_BITS];

        if (value > powerFunction(2, NUMBER_OF_BITS) - 1 || value < 0) {
            throw new IllegalArgumentException("The value '" + value + "' is not between 0 and 65535.");
        }

        for (int i = NUMBER_OF_BITS - 1; i >= 0; i--) {
            if (value >= powerFunction(2, i)) {
                value -= powerFunction(2, i);
                binary[NUMBER_OF_BITS - 1 - i] = true;
            } else {
                binary[NUMBER_OF_BITS - 1 - i] = false;
            }
        }

        return binary;
    }

    public int convert(boolean[] binary) {
        int value = 0;

        for (int i = NUMBER_OF_BITS - 1; i >= 0; i--) {
            if (binary[NUMBER_OF_BITS - 1 - i]) {
                value += powerFunction(2, i);
            }
        }

        return value;
    }

    private int powerFunction(int base, int exponent) {
        int result = 1;
        for (int i = 0; i < exponent; i++) {
            result = base * result;
        }
        return result;
    }
}
