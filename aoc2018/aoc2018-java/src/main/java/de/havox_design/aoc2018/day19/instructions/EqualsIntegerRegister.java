package de.havox_design.aoc2018.day19.instructions;

public class EqualsIntegerRegister extends Instruction {
    public EqualsIntegerRegister(int a, int b, int c) {
        super(a, b, c);
    }

    @Override
    public void apply(int[] registers) {
        registers[c] = a == registers[b] ? 1 : 0;
    }
}
