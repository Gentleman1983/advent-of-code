package de.havox_design.aoc2015.day07;

import java.util.logging.Logger;

public interface LogicGate {
    static final Logger LOGGER = Logger.getLogger(LogicGate.class.getName());

    LogicGates getObserver();

    String getOutput();

    void process();

    String getValueString();

    default void runGate() {
        this.process();
        if (getObserver().getValueForVariable(getOutput()) != null) {
            getObserver().unregisterGate(this);

            LOGGER.fine(() -> "Unregistered Gate '" + this + "' with value '" + getValueString() + "'.");
        }
    }

    default void updateVariable(String name, int value) {
        getObserver().updateVariable(name, value);
    }

    default String getValueForVariable(String variable) {
        return getObserver().getValueForVariable(variable) == null ?
                variable :
                String.valueOf(getObserver().getValueForVariable(variable));
    }
}
