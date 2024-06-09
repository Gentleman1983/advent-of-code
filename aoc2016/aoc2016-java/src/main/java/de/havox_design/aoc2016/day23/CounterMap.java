package de.havox_design.aoc2016.day23;

import java.util.HashMap;
import java.util.stream.LongStream;

public class CounterMap<K> extends HashMap<K, Long> {
    public CounterMap() {
        super();
    }

    public CounterMap(int initialCapacity) {
        super(initialCapacity);
    }

    public long getValue(K key) {
        return getOrDefault(key, 0L);
    }

    public long put(K key, int value) {
        put(key, Long.valueOf(value));

        return value;
    }

    public long increment(K key) {
        return add(key, 1);
    }

    public long decrement(K key) {
        return add(key, -1);
    }

    public long add(K key, long delta) {
        long newValue = getValue(key) + delta;

        put(key, newValue);

        return newValue;
    }

    public long multiply(K key, long factor) {
        long newValue = getValue(key) * factor;

        put(key, newValue);

        return newValue;
    }

    public LongStream valueStream() {
        return values().stream().mapToLong(Long::longValue);
    }

    public long sum() {
        return valueStream().sum();
    }

    public long minimum() {
        return valueStream().min().orElseThrow();
    }

    public long maximum() {
        return valueStream().max().orElseThrow();
    }

    public long count(long value) {
        return valueStream().filter(v -> v == value).count();
    }
}
