package de.havox_design.aoc2017.day16;

import java.util.function.BinaryOperator;

public enum Instruction implements BinaryOperator<String> {
    SPIN((programs, instruction) -> {
        int offset = programs.length() - Integer.parseInt(instruction);

        return programs.substring(offset) + programs.substring(0, offset);
    }),
    EXCHANGE((programs, instruction) -> {
        int slash = instruction
                .indexOf('/');
        int aIndex = Integer
                .parseInt(instruction.substring(0, slash));
        int bIndex = Integer
                .parseInt(instruction.substring(1 + slash));

        return swap(programs, aIndex, bIndex);
    }),
    PARTNER((programs, instruction) -> {
        int aIndex = programs
                .indexOf(instruction.charAt(0));
        int bIndex = programs
                .indexOf(instruction.charAt(2));

        return swap(programs, aIndex, bIndex);
    });

    private static final char IDENTIFIER_EXCHANGE = 'x';
    private static final char IDENTIFIER_PARTNER = 'p';
    private static final char IDENTIFIER_SPIN = 's';

    private final BinaryOperator<String> operator;

    Instruction(BinaryOperator<String> operator) {
        this.operator = operator;
    }

    static Instruction get(char prefix) {
        switch (prefix) {
            case IDENTIFIER_SPIN:
                return SPIN;
            case IDENTIFIER_EXCHANGE:
                return EXCHANGE;
            case IDENTIFIER_PARTNER:
                return PARTNER;
            default:
                throw new IllegalArgumentException("Expected value of: sX, xA/B, pA/B");
        }
    }

    private static String swap(String programs, int aIndex, int bIndex) {
        char[] chars = programs
                .toCharArray();
        char temp = chars[aIndex];

        chars[aIndex] = chars[bIndex];
        chars[bIndex] = temp;

        return new String(chars);
    }

    @Override
    public String apply(String programs, String instruction) {
        return operator
                .apply(programs, instruction);
    }
}
