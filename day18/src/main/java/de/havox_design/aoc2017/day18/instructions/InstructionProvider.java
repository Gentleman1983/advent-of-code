package de.havox_design.aoc2017.day18.instructions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InstructionProvider {
    public static final Pattern REGEX = Pattern.compile("([a-z]+)\\s+(\\w+)\\s*(-?\\w+)?");

    private InstructionProvider() {
    }

    public static Instruction createInstruction(String row) {
        return createInstruction(row, false);
    }

    public static Instruction createInstruction(String row, boolean isPart2) {
        final Matcher matcher = REGEX.matcher(row);

        if (!matcher.matches()) {
            throw new IllegalArgumentException("Input '" + row + "' does not match pattern " + REGEX.pattern());
        }

        return switch (matcher.group(1)) {
            case "snd" -> isPart2 ? new Send(matcher.group(2)) : new Sound(matcher.group(2));
            case "add" -> new Add(matcher.group(2), matcher.group(3));
            case "mul" -> new Multiply(matcher.group(2), matcher.group(3));
            case "mod" -> new Modulo(matcher.group(2), matcher.group(3));
            case "set" -> new Set(matcher.group(2), matcher.group(3));
            case "rcv" -> isPart2 ? new Receive(matcher.group(2)) : new Recover(matcher.group(2));
            case "jgz" -> new JumpIfGreaterZero(matcher.group(2), matcher.group(3));
            default -> throw new IllegalArgumentException(matcher.toString());
        };
    }
}
