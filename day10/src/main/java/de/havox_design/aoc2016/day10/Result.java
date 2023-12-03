package de.havox_design.aoc2016.day10;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public record Result(Set<Bot> bots, Map<Integer, List<Integer>> outputs) {
    Map<Integer, Integer> filterOutputs(int... ids) {
        Set<Integer> targets = IntStream
                .of(ids)
                .boxed()
                .collect(Collectors.toSet());
        return outputs
                .entrySet()
                .stream()
                .filter(entry -> targets.contains(entry.getKey()))
                .collect(
                        Collectors.toMap(
                                Map.Entry::getKey,
                                entry -> Optional
                                        .of(entry.getValue()).filter(v -> v.size() == 1)
                                        .orElseThrow()
                                        .get(0)
                        )
                );
    }
}
