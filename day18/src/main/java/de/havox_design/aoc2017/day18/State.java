package de.havox_design.aoc2017.day18;

import java.math.BigInteger;
import java.util.Map;
import java.util.TreeMap;

public class State {

    private final int programId;

    private final Map<String, BigInteger> register = new TreeMap<>();

    private BigInteger currentSound;

    private int position;

    private BigInteger recoveredSound;

    private int multiplicationInstructionCount;

    public State(final int programId) {
        this.programId = programId;
        register.put("p", BigInteger.valueOf(programId));
    }

    public void playSound(final BigInteger value) {
        this.currentSound = value;
    }

    public BigInteger getValue(final String variableName) {
        final BigInteger value = register.get(variableName);

        return value == null ? BigInteger.ZERO : value;
    }

    public void setValue(final String variableName, final BigInteger newValue) {
        register.put(variableName, newValue);
    }

    public BigInteger getCurrentSound() {
        return currentSound;
    }

    public void jump(final int intValue) {
        this.position += intValue - 1;
    }

    public int getPosition() {
        return position;
    }

    public void incrementPosition() {
        position++;
    }

    public BigInteger getRecoveredSound() {
        return recoveredSound;
    }

    public void recoverSound(final BigInteger sound) {
        recoveredSound = sound;
    }

    public void countMultiplicationInstruction() {
        multiplicationInstructionCount++;
    }
}
