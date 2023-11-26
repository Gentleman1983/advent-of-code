package de.havox_design.aoc2015.day23;

import de.havox_design.aoc2015.utils.DataReader;

import java.util.List;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Turing {
    private static final Pattern PATTERN = Pattern.compile("(\\w{3}) ([ab]|[+-]?\\d+)(, ([+-]?\\d+))?");

    private final List<String> input;

    public Turing(String fileName) {
        input = readData(fileName);
    }

    public static int solvePart1(String fileName) {
        Turing instance = new Turing(fileName);
        return instance.solvePart1();
    }

    public static int solvePart2(String fileName) {
        Turing instance = new Turing(fileName);
        return instance.solvePart2();
    }

    public int solvePart1() {
        List<Operation> operations = tokenStream(input, PATTERN, this::parseOperation).toList();
        State state = new State();
        while (state.processCount < operations.size()) {
            Operation operation = operations.get(state.processCount);
            operation.operate(state);
        }
        return state.registerB;
    }

    public int solvePart2() {
        return 0;
    }

    private List<String> readData(String fileName) {
        return DataReader.readData(fileName, MainClass.class);
    }

    private Operation parseOperation(Matcher matcher) {
        Operation operation = new Operation();
        operation.op = matcher.group(1);
        String first = matcher.group(2);
        if (first.length() == 1 && Character.isLetter(first.charAt(0))) {
            operation.register = first;
        } else {
            operation.value = Integer.parseInt(first);
        }
        String second = matcher.group(4);
        if (second != null && !second.isEmpty()) {
            operation.value = Integer.parseInt(second);
        }
        return operation;
    }

    public <T> Stream<T> tokenStream(List<String> input, Pattern pattern, Function<Matcher, T> tokenGenerator) {
        return input
                .stream()
                .map(token -> matchRegex(pattern, token))
                .map(tokenGenerator);
    }

    public Matcher matchRegex(final Pattern pattern, final CharSequence input) {
        final Matcher matcher = pattern.matcher(input);
        if (matcher.matches()) {
            return matcher;
        } else {
            throw new IllegalArgumentException("Input '" + input + "' does not match pattern " + pattern.pattern());
        }
    }
}
