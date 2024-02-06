package de.havox_design.aoc2018.day07;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class TaskParser {
    private static final String ILLEGAL_STATE_MESSAGE = "This should not happen...";
    private static final String TASK_FORMAT = "Step (.+) must be finished before step (.+) can begin.";
    @SuppressWarnings("squid:S5852")
    private static final Pattern TASK_PATTERN = Pattern.compile(TASK_FORMAT);

    public List<Task> parse(List<String> input) {
        Map<String, Set<String>> dependencies = new HashMap<>();

        for (String line : input) {
            Matcher matcher = TASK_PATTERN.matcher(line);
            if (matcher.matches()) {
                String dependency = matcher.group(1);
                String dependent = matcher.group(2);

                dependencies.putIfAbsent(dependency, new HashSet<>());
                dependencies.get(dependency).add(dependent);
            }
        }

        List<String> missingKeys = new ArrayList<>();
        for(Set<String> children : dependencies.values()) {
            for(String child : children) {
                if(!dependencies.containsKey(child)) {
                    missingKeys.add(child);
                }
            }
        }
        for(String missingKey : missingKeys) {
            dependencies.put(missingKey, Collections.emptySet());
        }

        List<Task> tasks = new ArrayList<>();

        while (tasks.size() < dependencies.size()) {
            String independantKey = dependencies
                    .entrySet()
                    .stream()
                    .filter(entry ->
                            !tasks
                                    .stream()
                                    .map(Task::id)
                                    .toList()
                                    .contains(entry.getKey())
                    )
                    .filter(entry ->
                            new HashSet<>(tasks
                                    .stream()
                                    .map(Task::id)
                                    .toList())
                                    .containsAll(entry.getValue())
                    )
                    .map(Map.Entry::getKey)
                    .findFirst()
                    .orElseThrow(() -> new IllegalStateException(ILLEGAL_STATE_MESSAGE));

            Task task = new Task(
                    independantKey,
                    dependencies
                            .get(independantKey)
                            .stream()
                            .map(key ->
                                    tasks
                                            .stream()
                                            .filter(t -> t.id().equals(key))
                                            .findFirst()
                                            .orElseThrow(() -> new IllegalStateException(ILLEGAL_STATE_MESSAGE))
                            )
                            .collect(Collectors.toSet())
            );
            tasks.add(task);
        }

        return tasks;
    }
}
