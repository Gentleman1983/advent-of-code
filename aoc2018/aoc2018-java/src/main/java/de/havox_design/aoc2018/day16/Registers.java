package de.havox_design.aoc2018.day16;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public record Registers(List<Integer> registers) {
    public int getRegister(final int index) {
        return this.registers.get(index);
    }

    public void setRegister(final int index, final int value) {
        this.registers.set(index, value);
    }

    public Registers with(final int register, final int value) {
        final List<Integer> result = new ArrayList<>(registers);

        result.set(register, value);

        return new Registers(Collections.unmodifiableList(result));
    }
}
