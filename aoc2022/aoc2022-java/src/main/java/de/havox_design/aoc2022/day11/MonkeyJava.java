package de.havox_design.aoc2022.day11;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import java.util.function.UnaryOperator;

public class MonkeyJava {
    private final List<Long> items = new LinkedList<>();
    private final Function<Long, Long> operation;
    private final long test;
    private final Function<Boolean, Integer> target;
    private long count = 0L;
    private long multiples = -1;

    public MonkeyJava(List<Long> startItems, UnaryOperator<Long> operation, long test, Function<Boolean, Integer> target) {
        items.addAll(startItems);
        this.operation = operation;
        this.test = test;
        this.target = target;
    }

    public void round(List<MonkeyJava> monkeys) {
        if (multiples == -1) {
            multiples = monkeys.stream().map(m -> m.test).reduce(1L, (a, b) -> a * b);
        }

        while (!(items.isEmpty())) {
            long item = items.removeFirst();
            long newValue = operation.apply(item);

            newValue = newValue % multiples;

            int to = target.apply(newValue % test == 0L);
            monkeys.get(to).items.add(newValue);
            ++count;
        }
    }

    public long getCount() {
        return count;
    }
}
