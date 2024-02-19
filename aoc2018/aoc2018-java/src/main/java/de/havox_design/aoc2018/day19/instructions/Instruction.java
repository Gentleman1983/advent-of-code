package de.havox_design.aoc2018.day19.instructions;

public abstract class Instruction {
    public final int a;
    public final int b;
    public final int c;

    protected Instruction(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public int getA() {
        return a;
    }

    public int getB() {
        return b;
    }

    public int getC() {
        return c;
    }

    public abstract void apply(final int[] registers);
}
