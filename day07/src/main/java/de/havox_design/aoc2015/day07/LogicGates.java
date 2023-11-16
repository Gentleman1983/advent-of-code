package de.havox_design.aoc2015.day07;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

import de.havox_design.aoc2015.utils.DataReader;

public class LogicGates {
    public static final int NUMBER_OF_BITS = 16;
    private final List<String> input;
    private final Set<LogicGate> registeredGates = new CopyOnWriteArraySet<>();
    private final Map<String, Integer> variablesMap = new ConcurrentHashMap<>();

    public LogicGates(String fileName) {
        input = readData(fileName);
    }

    public static int solvePart1(String fileName) {
        LogicGates instance = new LogicGates(fileName);
        return instance.solvePart1();
    }

    public static int solvePart2(String fileName) {
        LogicGates instance = new LogicGates(fileName);
        return instance.solvePart2();
    }

    public int solvePart1() {
        parseInput();

        while (!registeredGates.isEmpty()) {
            for (LogicGate gate : registeredGates) {
                gate.runGate();
            }
        }

        return variablesMap.get("a");
    }

    public int solvePart2() {
        return 0;
    }

    private void parseInput() {
        for (String inputValue : this.input) {
            String[] parts = inputValue.split(" ");
            registerGate(parseInput(parts));
        }
    }

    private LogicGate parseInput(String... data) {
        if (data.length == 3) {
            return new SetGate(data[0], data[2], this);
        } else if (data.length == 4) {
            return new NotGate(data[1], data[3], this);
        } else if (data.length == 5) {
            return switch (data[1]) {
                case "AND" -> new AndGate(data[0], data[2], data[4], this);
                case "OR" -> new OrGate(data[0], data[2], data[4], this);
                case "RSHIFT" -> new RightShiftGate(data[0], data[2], data[4], this);
                case "LSHIFT" -> new LeftShiftGate(data[0], data[2], data[4], this);
                default -> throw new IllegalArgumentException("Unknown operator '" + data[1] + "'.");
            };
        } else {
            StringBuilder message = new StringBuilder();

            message.append("Unknown call '");

            for (int i = 0; i < data.length; i++) {
                if (i != 0) {
                    message.append(" ");
                }

                message.append(data[i]);
            }

            message.append("'.");

            throw new IllegalArgumentException(message.toString());
        }
    }

    protected Integer getValueForVariable(String variable) {
        return variablesMap.get(variable);
    }

    protected void updateVariable(String variable, Integer value) {
        variablesMap.put(variable, value);
    }

    protected void unregisterGate(LogicGate gate) {
        registeredGates.remove(gate);
    }

    private void registerGate(LogicGate gate) {
        registeredGates.add(gate);
    }

    private List<String> readData(String fileName) {
        return DataReader.readData(fileName, MainClass.class);
    }
}
