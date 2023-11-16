package de.havox_design.aoc2015.day07;

public interface LogicGate {
    LogicGates getObserver();

    String getOutput();

    void process();

    default void runGate() {
        this.process();
        if (getObserver().getValueForVariable(getOutput()) != null) {
            getObserver().unregisterGate(this);
        }
    }

    default void updateVariable(String name, int value) {
        getObserver().updateVariable(name, value);
    }
}
