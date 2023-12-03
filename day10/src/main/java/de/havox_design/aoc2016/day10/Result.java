package de.havox_design.aoc2016.day10;

import java.util.List;
import java.util.Map;
import java.util.Set;

public record Result(Set<Bot> bots, Map<Integer, List<Integer>> outputs) {
}
