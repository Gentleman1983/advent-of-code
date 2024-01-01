package de.havox_design.aoc2016.day07;

import java.util.Set;
import java.util.stream.Stream;

public record Address(Set<String> supernet, Set<String> hypernet) {
    Stream<String> supernetStream() {
        return supernet
                .stream();
    }

    Stream<String> hypernetStream() {
        return hypernet
                .stream();
    }
}
