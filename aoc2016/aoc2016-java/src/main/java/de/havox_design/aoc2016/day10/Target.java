package de.havox_design.aoc2016.day10;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

public record Target(String type, int id) {
    private static final String TYPE_BOT = "bot";
    private static final String TYPE_OUTPUT = "output";

    boolean isReady(Map<Integer, Bot> bots) {
        return type.equals(TYPE_OUTPUT) || bots.containsKey(id);
    }

    @SuppressWarnings("javaarchitecture:S7027")
    void update(Map<Integer, Bot> bots, Map<Integer, List<Integer>> outputs, int value) {
        switch (type) {
            case TYPE_BOT -> Optional
                    .of(bots)
                    .filter(this::isReady)
                    .ifPresent(b -> b.computeIfPresent(id, (k, bot) -> bot.merge(value)));
            case TYPE_OUTPUT -> outputs
                    .merge(
                            id,
                            List.of(value),
                            (a, b) -> Stream.of(a, b).flatMap(List::stream).toList()
                    );
            default -> throw new IllegalArgumentException("Unknown type");
        }
    }
}
