package de.havox_design.aoc2015.day07;

import org.apache.commons.lang3.StringUtils;

public class SetGate implements LogicGate {
    private final LogicGates observer;
    private final String input;
    private final String output;

    public SetGate(String input, String output, LogicGates observer) {
        this.observer = observer;
        this.input = input;
        this.output = output;
    }

    @Override
    public LogicGates getObserver() {
        return observer;
    }

    @Override
    public String getOutput() {
        return output;
    }

    @Override
    public void process() {
        int value;

        if (StringUtils.isNumeric(input)) {
            value = Integer.parseInt(input);
        } else if (observer.getValueForVariable(input) != null) {
            value = observer.getValueForVariable(input);
        } else {
            return;
        }

        updateVariable(output, value);
    }

    @Override
    public String toString() {
        return getOutputString(input, output);
    }

    @Override
    public String getValueString() {
        String valueInput = getValueForVariable(input);
        String valueOutput = getValueForVariable(output);

        return getOutputString(valueInput, valueOutput);
    }

    private String getOutputString(String input, String output) {
        return input + " -> " + output;
    }
}
