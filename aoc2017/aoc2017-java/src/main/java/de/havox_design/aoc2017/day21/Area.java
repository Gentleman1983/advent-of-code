package de.havox_design.aoc2017.day21;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@SuppressWarnings("javaarchitecture:S7027")
public class Area {
    private static final char ICON_PIXEL_ON = '#';

    private final List<String> grid;
    private final int size;
    private int added = 0;

    Area(int size) {
        this.grid = new ArrayList<>(size);
        this.size = size;
    }

    Area(List<String> grid) {
        this.grid = grid;
        this.size = grid.size();
    }

    Area next(List<Rule> rules) {
        var newSize = size % 2 == 0 ? (size / 2) * 3 : (size / 3) * 4;

        return split()
                .map(v -> v.convert(rules))
                .reduce(new Area(newSize), Area::join);
    }

    private Stream<Area> split() {
        if (this.size == 3) {
            return Stream.of(this);
        }

        var columns = size % 2 == 0 ? size / 2 : size / 3;
        var width = size / columns;

        return IntStream
                .iterate(0, i -> i < size, i -> i + width)
                .boxed()
                .flatMap(i ->
                        IntStream
                                .iterate(0, j -> j < size, j -> j + width)
                                .boxed()
                                .map(j ->
                                        IntStream
                                                .range(i, i + width)
                                                .mapToObj(y -> grid.get(y).substring(j, j + width))
                                                .collect(
                                                        Collectors.collectingAndThen(
                                                                Collectors.toUnmodifiableList(),
                                                                Area::new
                                                        )
                                                )
                                )
                );
    }

    private Area convert(List<Rule> rules) {
        return rules
                .stream()
                .map(rule -> rule.getConvertedOutput(this))
                .flatMap(Optional::stream)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Expected pattern."));
    }

    private Area join(Area other) {
        var columns = size / other.size;

        if (added % columns == 0) {
            grid.addAll(other.grid);
        } else {
            var start = other.size * (added / columns);
            var iterator = other.grid.iterator();

            IntStream
                    .iterate(start, i -> i < start + other.size, i -> i + 1)
                    .forEach(i ->
                            grid.set(i, grid.get(i) + iterator.next())
                    );
        }

        added++;

        return this;
    }

    long countPixelsOn() {
        return grid
                .stream()
                .flatMapToInt(String::chars)
                .filter(v -> v == ICON_PIXEL_ON)
                .count();
    }

    Area flipHorizontal() {
        return with(
                IntStream
                        .range(0, size)
                        .mapToObj(i ->
                                grid.get(size - i - 1)
                        )
        );
    }

    Area flipVertical() {
        return with(
                grid
                        .stream()
                        .map(v ->
                                new StringBuilder(v)
                                        .reverse()
                                        .toString()
                        )
        );
    }

    Area rotate() {
        return with(
                IntStream
                        .range(0, size)
                        .mapToObj(x ->
                                IntStream.range(0, size)
                                        .mapToObj(grid::get)
                                        .map(v -> String.valueOf(v.charAt(x)))
                                        .collect(Collectors.joining())
                        )
        );
    }

    private static Area with(Stream<String> stream) {
        return stream
                .collect(
                        Collectors
                                .collectingAndThen(
                                        Collectors.toUnmodifiableList(),
                                        Area::new
                                )
                );
    }

    @Override
    @SuppressWarnings("squid:S6201")
    public boolean equals(Object o) {
        return o instanceof Area
                && Objects.equals(grid, ((Area) o).grid);
    }

    @Override
    public int hashCode() {
        return Objects
                .hash(grid);
    }
}
