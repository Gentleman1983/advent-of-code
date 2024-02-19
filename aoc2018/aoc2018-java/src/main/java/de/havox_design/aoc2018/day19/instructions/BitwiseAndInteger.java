package de.havox_design.aoc2018.day19.instructions;

public class BitwiseAndInteger extends Instruction {
    public BitwiseAndInteger(int a, int b, int c) {
        super(a, b, c);
    }

    @Override
    public void apply(int[] registers) {
        registers[c] = registers[a] & b;
    }
}
