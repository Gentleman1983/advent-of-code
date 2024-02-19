package de.havox_design.aoc2018.day19;

import de.havox_design.aoc.utils.java.AoCFunctionality;
import de.havox_design.aoc2018.day19.instructions.Instruction;

import java.util.List;

public class GoWithTheFlow implements AoCFunctionality {
    private final int indexIP;
    private final List<Instruction> instructions;

    public GoWithTheFlow(String fileName) {
        final IPRegisterParser ipIndexParser = new IPRegisterParser();
        final InstructionParser instructionParser = new InstructionParser();
        List<String> input = readData(fileName);

        indexIP = ipIndexParser
                .parse(input.getFirst());
        instructions = input
                .stream()
                .skip(1)
                .map(instructionParser::parse)
                .toList();
    }

    public static long processTask1(String fileName) {
        GoWithTheFlow instance = new GoWithTheFlow(fileName);
        return instance.processTask1();
    }

    public long processTask1() {
        final var registers = new int[6];
        final var max = instructions.size();

        while (registers[indexIP] < max) {
            instructions.get(registers[indexIP]).apply(registers);
            registers[indexIP]++;
        }

        return registers[0];
    }
}
