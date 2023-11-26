package de.havox_design.aoc2015.day23;

public class State {
    public State() {
        super();
    }

    public State(int registerA, int registerB) {
        this();

        this.registerA = registerA;
        this.registerB = registerB;
    }

    int processCount = 0;
    int registerA = 0;
    int registerB = 0;
}
