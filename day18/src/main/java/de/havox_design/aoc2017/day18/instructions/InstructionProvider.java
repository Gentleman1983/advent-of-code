package de.havox_design.aoc2017.day18.instructions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InstructionProvider {
    public static final Pattern REGEX = Pattern.compile("([a-z]+)\\s+(\\w+)\\s*(-?\\w+)?");

    private InstructionProvider() {
    }

    public static Instruction createInstruction(String row) {
        final Matcher matcher = REGEX.matcher(row);

        if (!matcher.matches()) {
            throw new IllegalArgumentException("Input '" + row + "' does not match pattern " + REGEX.pattern());
        }

        return switch (matcher.group(1)) {
            case "snd" -> new SoundInstruction(matcher.group(2));
            case "add" -> new AddInstruction(matcher.group(2), matcher.group(3));
            case "mul" -> new MulInstruction(matcher.group(2), matcher.group(3));
            case "mod" -> new ModInstruction(matcher.group(2), matcher.group(3));
            case "set" -> new SetInstruction(matcher.group(2), matcher.group(3));
            case "rcv" -> new RecoverInstruction(matcher.group(2));
            case "jgz" -> new JgzInstruction(matcher.group(2), matcher.group(3));
            default -> throw new IllegalArgumentException(matcher.toString());
        };
    }
}
