package de.havox_design.aoc2018.day12;

import java.util.List;
import java.util.Map;

public record GardenInput(List<Boolean> initialState, Map<List<Boolean>, Boolean> patterns) {}
