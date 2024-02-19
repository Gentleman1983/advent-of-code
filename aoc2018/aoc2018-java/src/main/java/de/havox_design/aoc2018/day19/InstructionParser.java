package de.havox_design.aoc2018.day19;

import de.havox_design.aoc2018.day19.instructions.*;

public class InstructionParser {
    private static final int INDEX_OPCODE = 0;
    private static final int INDEX_REGISTER_A = 1;
    private static final int INDEX_REGISTER_B = 2;
    private static final int INDEX_REGISTER_C = 3;

    public Instruction parse(final String toParse) {
        String[] elements = toParse.split(" ");

        if(elements.length != 4) {
            throw new IllegalArgumentException("Could not parse " + toParse);
        }

        String opcode = elements[INDEX_OPCODE].trim();
        int a = Integer.parseInt(elements[INDEX_REGISTER_A].trim());
        int b = Integer.parseInt(elements[INDEX_REGISTER_B].trim());
        int c = Integer.parseInt(elements[INDEX_REGISTER_C].trim());

        return switch (opcode) {
            case "addi" -> new AddInteger(a, b, c);
            case "addr" -> new AddRegister(a, b, c);
            case "bani" -> new BitwiseAndInteger(a, b, c);
            case "banr" -> new BitwiseAndRegister(a, b, c);
            case "bori" -> new BitwiseOrInteger(a, b, c);
            case "borr" -> new BitwiseOrRegister(a, b, c);
            case "eqir" -> new EqualsIntegerRegister(a, b, c);
            case "eqri" -> new EqualsRegisterInteger(a, b, c);
            case "eqrr" -> new EqualsRegisterRegister(a, b, c);
            case "gtir" -> new GreaterThanIntegerRegister(a, b, c);
            case "gtri" -> new GreaterThanRegisterInteger(a, b, c);
            case "gtrr" -> new GreaterThanRegisterRegister(a, b, c);
            case "muli" -> new MultiplyInteger(a, b, c);
            case "mulr" -> new MultiplyRegister(a, b, c);
            case "seti" -> new SetInteger(a, b, c);
            case "setr" -> new SetRegister(a, b, c);
            default -> throw new IllegalArgumentException();
        };
    }
}
