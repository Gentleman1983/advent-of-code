package de.havox_design.aoc2022.day11;


import de.havox_design.aoc.utils.java.AoCFunctionality;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.UnaryOperator;

public class MonkeyInTheMiddleJava implements AoCFunctionality {
    private final String input;

    public MonkeyInTheMiddleJava(String fileName) {
        input = readString(fileName);
    }

    public static long solvePart2(String fileName, Integer rounds) {
        MonkeyInTheMiddleJava instance = new MonkeyInTheMiddleJava(fileName);

        return instance.solvePart2(rounds);
    }

    public static long solvePart2(String fileName) {
        MonkeyInTheMiddleJava instance = new MonkeyInTheMiddleJava(fileName);

        return instance.solvePart2();
    }

    public long solvePart2(int rounds) {
        List<MonkeyJava> monkeys = readMonkeys();

        executeRounds(rounds, monkeys);

        return monkeys
                .stream()
                .sorted((a, b) -> (int) (b.getCount() - a.getCount()))
                .mapToLong(MonkeyJava::getCount)
                .limit(2)
                .reduce(1L, (a, b) -> a * b);
    }

    public long solvePart2() {
        return solvePart2(10_000);
    }

    private void executeRounds(int rounds, List<MonkeyJava> monkeys) {
        for (var i = 0; i < rounds; i++) {
            for (var monkey : monkeys) {
                monkey.round(monkeys);
            }
        }
    }

    private List<MonkeyJava> readMonkeys() {
        return Arrays
                .stream(input.split("\r?\n\r?\n"))
                .map(monkey -> linesToMonkey(monkey.lines().toList()))
                .toList();
    }

    private MonkeyJava linesToMonkey(List<String> lines) {
        return new MonkeyJava(
                Arrays
                        .stream(lines.get(1).trim().substring("Starting items: ".length()).split(", "))
                        .map(Long::parseLong)
                        .toList(),
                toOperation(lines.get(2).trim().substring("Operation: new = old ".length()).split(" ")),
                toTest(lines.get(3).split(" ")),
                toSelection(lines.get(4).split(" "), lines.get(5).split(" "))
        );
    }

    private UnaryOperator<Long> toOperation(String[] parts) {
        boolean isOld = "old".equals(parts[parts.length - 1]);
        long v = isOld ? -1 : Integer.parseInt(parts[parts.length - 1]);

        if (parts[0].equals("+")) {
            return isOld ? a -> a + a : a -> a + v;
        } else if (parts[0].equals("*")) {
            return isOld ? a -> a * a : a -> a * v;
        } else {
            throw new IllegalStateException("what is this? '" + parts[0] + "'");
        }
    }

    private long toTest(String[] parts) {
        return Long.parseLong(parts[parts.length - 1]);
    }

    @SuppressWarnings("java:S5411")
    private Function<Boolean, Integer> toSelection(String[] trueLineParts, String[] falseLineParts) {
        int v1 = Integer.parseInt(trueLineParts[trueLineParts.length - 1]);
        int v2 = Integer.parseInt(falseLineParts[falseLineParts.length - 1]);
        return b -> b ? v1 : v2;
    }
}
