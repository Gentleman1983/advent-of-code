package de.havox_design.aoc2018.day21;

import de.havox_design.aoc.utils.java.AoCFunctionality;
import de.havox_design.aoc2018.day19.IPRegisterParser;
import de.havox_design.aoc2018.day19.InstructionParser;
import de.havox_design.aoc2018.day19.instructions.Instruction;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class ChronalConversion implements AoCFunctionality {
    private final int indexIP;
    private final List<Instruction> instructions;

    public ChronalConversion(String fileName) {
        List<String> input = readData(fileName);
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
        final int[] registers = new int[6];

        while (true) {
            if (registers[indexIP] == 28) {
                return registers[instructions.get(28).a];
            }

            instructions
                    .get(registers[indexIP])
                    .apply(registers);

            registers[indexIP]++;
        }
    }

    public long processTask2() {
        final Set<Integer> seen = new LinkedHashSet<>();
        final int[] registers = new int[6];

        while (true) {
            if (
                    registers[indexIP] == 28 &&
                    !seen.add(registers[instructions.get(28).a])
            ) {
                return seen
                        .stream()
                        .skip(seen.size() - 1L)
                        .findAny()
                        .orElseThrow();
            }

            instructions
                    .get(registers[indexIP])
                    .apply(registers);

            registers[indexIP]++;
        }
    }
}
