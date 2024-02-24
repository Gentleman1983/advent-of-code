package de.havox_design.aoc2018.day21;

import de.havox_design.aoc.utils.java.AoCFunctionality;
import de.havox_design.aoc2018.day19.IPRegisterParser;
import de.havox_design.aoc2018.day19.InstructionParser;
import de.havox_design.aoc2018.day19.instructions.Instruction;

import java.util.List;

public class ChronalConversion implements AoCFunctionality {
    private final List<String> input;
    private final int indexIP;
    private final List<Instruction> instructions;

    public ChronalConversion(String fileName) {
        input = readData(fileName);

        final IPRegisterParser ipIndexParser = new IPRegisterParser();
        final InstructionParser instructionParser = new InstructionParser();
        this.indexIP = ipIndexParser.parse(input.getFirst());
        this.instructions = input
                .stream()
                .skip(1)
                .map(instructionParser::parse)
                .toList();
    }

    public static long processTask1(String fileName) {
        ChronalConversion instance = new ChronalConversion(fileName);
        return instance.processTask1();
    }

    public static long processTask2(String fileName) {
        ChronalConversion instance = new ChronalConversion(fileName);
        return instance.processTask2();
    }

    public long processTask1() {
        final var registers = new int[6];

        while (true) {
            if (registers[indexIP] == 28) {
                return registers[instructions.get(28).a];
            }
            instructions.get(registers[indexIP]).apply(registers);
            registers[indexIP]++;
        }
    }

    public long processTask2() {
        return 0;
    }
}
