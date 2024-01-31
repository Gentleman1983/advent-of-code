package de.havox_design.aoc.utils.java.model.programs.duet;

import java.math.BigInteger;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

public class State {
    private final int programId;
    private State otherState;
    private boolean running = true;
    private BigInteger sentCount = BigInteger.ZERO;
    private final Map<String, BigInteger> register = new TreeMap<>();
    private BigInteger currentSound;
    private int position;
    private BigInteger recoveredSound;
    private final Queue<BigInteger> queue = new LinkedList<>();
    private int multiplicationInstructionCount;

    public State(final int programId) {
        this.programId = programId;
        register.put("p", BigInteger.valueOf(programId));
    }

    public void setOtherState(final State otherState) {
        this.otherState = otherState;
        otherState.otherState = this;
    }

    public State getOtherState() {
        return otherState;
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

    public int getProgramId() {
        return programId;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(final boolean running) {
        this.running = running;
    }

    public BigInteger getSentCount() {
        return sentCount;
    }

    public void incrementSentCount() {
        sentCount = sentCount.add(BigInteger.ONE);
    }

    public void send(final BigInteger value) {
        otherState.queue.add(value);
        otherState.running = true;
        incrementSentCount();
    }

    public BigInteger receiveValue() {
        return queue.isEmpty() ? null : queue.poll();
    }

    public void countMultiplicationInstruction() {
        multiplicationInstructionCount++;
    }

    public int getMultiplicationInstructionCount() {
        return multiplicationInstructionCount;
    }
}
