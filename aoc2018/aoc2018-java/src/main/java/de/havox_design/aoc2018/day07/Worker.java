package de.havox_design.aoc2018.day07;

import java.util.Map;

public record Worker(Task task, long start, Map<String, Integer> processingTime) {
    public long getEnd() {
        return start + processingTime.get(task.id());
    }
}
