package de.havox_design.aoc2018.day16;

import java.util.Arrays;
import java.util.List;

public class SamplesParser {
    private static final String REGISTER_CLOSE_SUFFIX = "]";
    private static final String REGISTER_NUMBER_SEPARATOR = ", ";
    private static final String REGISTER_OPEN_PREFIX_AFTER = "After:  [";
    private static final String REGISTER_OPEN_PREFIX_BEFORE = "Before: [";
    private static final int ROW_ID_AFTER = 2;
    private static final int ROW_ID_BEFORE = 0;
    private static final int ROW_ID_INSTRUCTION = 1;

    private final InstructionParser instructionParser = new InstructionParser();

    public Sample parse(final List<String> toParse) {
        Registers before = parseBefore(toParse.get(ROW_ID_BEFORE));
        NumberedInstruction instruction = instructionParser.parse(toParse.get(ROW_ID_INSTRUCTION));
        Registers after = parserAfter(toParse.get(ROW_ID_AFTER));

        return new Sample(
                before,
                after,
                instruction
        );
    }

    private Registers parseBefore(final String toParse) {
        if(toParse.startsWith(REGISTER_OPEN_PREFIX_BEFORE) && toParse.endsWith(REGISTER_CLOSE_SUFFIX)) {
            String registerString = toParse.substring(REGISTER_OPEN_PREFIX_BEFORE.length(), toParse.length() - REGISTER_CLOSE_SUFFIX.length());

            return new Registers(parseRegisters(registerString));
        }

        throw new IllegalArgumentException("Invalid before register string: " + toParse);
    }

    private Registers parserAfter(final String toParse) {
        if(toParse.startsWith(REGISTER_OPEN_PREFIX_AFTER) && toParse.endsWith(REGISTER_CLOSE_SUFFIX)) {
            String registerString = toParse.substring(REGISTER_OPEN_PREFIX_AFTER.length(), toParse.length() - REGISTER_CLOSE_SUFFIX.length());

            return new Registers(parseRegisters(registerString));
        }

        throw new IllegalArgumentException("Invalid after register string: " + toParse);
    }

    private List<Integer> parseRegisters(final String registerString) {
        return Arrays
                .stream(registerString.split(REGISTER_NUMBER_SEPARATOR))
                .map(s -> Integer.parseInt(s.trim()))
                .toList();
    }
}
