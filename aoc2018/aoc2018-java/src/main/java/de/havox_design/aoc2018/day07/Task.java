package de.havox_design.aoc2018.day07;

import java.util.Set;

public record Task(String id, Set<Task> children) {}
