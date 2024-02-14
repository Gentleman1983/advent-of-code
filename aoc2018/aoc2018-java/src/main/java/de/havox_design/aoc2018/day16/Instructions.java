package de.havox_design.aoc2018.day16;

public enum Instructions {
    ADDR((i, r) -> r.with(i.c(), r.getRegister(i.a()) + r.getRegister(i.b()))),
    ADDI((i, r) -> r.with(i.c(), r.getRegister(i.a()) + i.b())),
    MULR((i, r) -> r.with(i.c(), r.getRegister(i.a()) * r.getRegister(i.b()))),
    MULI((i, r) -> r.with(i.c(), r.getRegister(i.a()) * i.b())),
    BANR((i, r) -> r.with(i.c(), r.getRegister(i.a()) & r.getRegister(i.b()))),
    BANI((i, r) -> r.with(i.c(), r.getRegister(i.a()) & i.b())),
    BORR((i, r) -> r.with(i.c(), r.getRegister(i.a()) | r.getRegister(i.b()))),
    BORI((i, r) -> r.with(i.c(), r.getRegister(i.a()) | i.b())),
    SETR((i, r) -> r.with(i.c(), r.getRegister(i.a()))),
    SETI((i, r) -> r.with(i.c(), i.a())),
    GTIR((i, r) -> r.with(i.c(), i.a() > r.getRegister(i.b()) ? 1 : 0)),
    GTRI((i, r) -> r.with(i.c(), r.getRegister(i.a()) > i.b() ? 1 : 0)),
    GTRR((i, r) -> r.with(i.c(), r.getRegister(i.a()) > r.getRegister(i.b()) ? 1 : 0)),
    EQIR((i, r) -> r.with(i.c(), i.a() == r.getRegister(i.b()) ? 1 : 0)),
    EQRI((i, r) -> r.with(i.c(), r.getRegister(i.a()) == i.b() ? 1 : 0)),
    EQRR((i, r) -> r.with(i.c(), r.getRegister(i.a()) == r.getRegister(i.b()) ? 1 : 0));

    private final Instruction instruction;

    Instructions(Instruction instruction) {
        this.instruction = instruction;
    }

    public Instruction getInstruction() {
        return this.instruction;
    }

    public Registers apply(final NumberedInstruction instruction, final Registers register) {
        return this.getInstruction().apply(instruction, register);
    }
}
