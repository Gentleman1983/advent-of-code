package de.havox_design.aoc2018.day16;

public class InstructionParser {
    private static final String INSTRUCTION_NUMBER_SEPARATOR = " ";

    public NumberedInstruction parse(final String toParse) {
        String[] segments = toParse.split(INSTRUCTION_NUMBER_SEPARATOR);

        if(segments.length != 4) {
            throw new IllegalArgumentException(String.format("Could not parse instructions from line '%s'.", toParse));
        }

        final var opcode = Integer.parseInt(segments[0].trim());
        final var a = Integer.parseInt(segments[1].trim());
        final var b = Integer.parseInt(segments[2].trim());
        final var c = Integer.parseInt(segments[3].trim());

        return new NumberedInstruction(opcode, a, b, c);
    }
}
