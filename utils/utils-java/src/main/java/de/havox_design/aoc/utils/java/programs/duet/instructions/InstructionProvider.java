package de.havox_design.aoc.utils.java.programs.duet.instructions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InstructionProvider {
    public static final Pattern REGEX = Pattern.compile("([a-z]+)\\s+(\\w+)\\s*(-?\\w+)?");
    public static final String IDENTIFIER_ADD = "add";
    public static final String IDENTIFIER_JUMP_GREATER_ZERO = "jgz";
    public static final String IDENTIFIER_JUMP_NOT_ZERO = "jnz";
    public static final String IDENTIFIER_MODULO = "mod";
    public static final String IDENTIFIER_MULTIPLY = "mul";
    public static final String IDENTIFIER_RECOVER_RECEIVE = "rcv";
    public static final String IDENTIFIER_SET = "set";
    public static final String IDENTIFIER_SOUND_SEND = "snd";
    public static final String IDENTIFIER_SUBSTRACT = "sub";

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
            case IDENTIFIER_SOUND_SEND -> isPart2 ? new Send(matcher.group(2)) : new Sound(matcher.group(2));
            case IDENTIFIER_ADD -> new Add(matcher.group(2), matcher.group(3));
            case IDENTIFIER_MULTIPLY -> new Multiply(matcher.group(2), matcher.group(3));
            case IDENTIFIER_MODULO -> new Modulo(matcher.group(2), matcher.group(3));
            case IDENTIFIER_SET -> new Set(matcher.group(2), matcher.group(3));
            case IDENTIFIER_RECOVER_RECEIVE -> isPart2 ? new Receive(matcher.group(2)) : new Recover(matcher.group(2));
            case IDENTIFIER_JUMP_GREATER_ZERO -> new JumpIfGreaterZero(matcher.group(2), matcher.group(3));
            case IDENTIFIER_JUMP_NOT_ZERO -> new JumpIfNotZero(matcher.group(2), matcher.group(3));
            case IDENTIFIER_SUBSTRACT -> new Substract(matcher.group(2), matcher.group(3));
            default -> throw new IllegalArgumentException(matcher.toString());
        };
    }
}
