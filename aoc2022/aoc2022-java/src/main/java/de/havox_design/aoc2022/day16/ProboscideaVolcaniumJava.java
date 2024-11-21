package de.havox_design.aoc2022.day16;


import de.havox_design.aoc.utils.java.AoCFunctionality;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProboscideaVolcaniumJava implements AoCFunctionality {
    private final List<String> input;
    private final Pattern pattern = Pattern
            .compile("Valve (\\w+) has flow rate=(\\d+); tunnels? leads? to valves? (\\w+(, \\w+)*)");

    public ProboscideaVolcaniumJava(String fileName) {
        input = readData(fileName);
    }

    public static long solvePart2(String fileName) {
        ProboscideaVolcaniumJava instance = new ProboscideaVolcaniumJava(fileName);

        return instance.solvePart2();
    }

    public long solvePart2() {
        return calc(m -> m.get("AA"), m -> m.get("AA"));
    }

    @SuppressWarnings("java:S3776")
    private int calc(Function<Map<String, ValveJava>, ValveJava> pos1, Function<Map<String, ValveJava>, ValveJava> elephantPosFinder) {
        final Map<String, ValveJava> valves = readValves();
        final ValveJava[] relevantValves = valves
                .values()
                .stream()
                .filter(v -> v.getRate() > 0)
                .toArray(ValveJava[]::new);
        final int[][] dist = calculateDistances(valves);
        final int maxRate = valves
                .values()
                .stream()
                .mapToInt(ValveJava::getRate)
                .max()
                .orElseThrow();

        final PriorityQueue<StateJava> states = new PriorityQueue<>(Comparator.comparing(StateJava::released)
                .reversed());
        states.add(new StateJava(pos1.apply(valves), 0, elephantPosFinder.apply(valves), 0, 0, 0, null));
        int maxReleased = -1;

        while (!states.isEmpty()) {
            final StateJava currentState = states.poll();
            maxReleased = Math.max(maxReleased, currentState.released());

            final int remainingMovesMax = (26 - currentState.myMinutes()) / 2 + (currentState.elephantLocation() == null ?
                    0 :
                    (26 - currentState.elephantMinutes())) / 2;

            if (maxReleased < currentState.released() + (remainingMovesMax * remainingMovesMax / 2) * maxRate) {
                for (ValveJava dest : relevantValves) {
                    if ((dest != currentState.myLocation() && ((currentState.opened() & dest.getMask()) == 0))) {
                        if (currentState.elephantLocation() == null || currentState.myMinutes() <= currentState.elephantMinutes()) {
                            final int newTime = currentState.myMinutes() + dist[currentState.myLocation().getIndex()][dest.getIndex()] + 1;

                            if (newTime < 26) {
                                states.add(new StateJava(dest, newTime, currentState.elephantLocation(), currentState.elephantMinutes(), currentState.released() + dest.getRate() * (26 - newTime), currentState.opened() | dest.getMask(), currentState));
                            }
                        } else {
                            final int newTime = currentState.elephantMinutes() + dist[currentState.elephantLocation().getIndex()][dest.getIndex()] + 1;

                            if (newTime < 26) {
                                states.add(new StateJava(currentState.myLocation(), currentState.myMinutes(), dest, newTime, currentState.released() + dest.getRate() * (26 - newTime), currentState.opened() | dest.getMask(), currentState));
                            }
                        }
                    }
                }
            }
        }

        return maxReleased;
    }

    @SuppressWarnings("java:S3776")
    private int[][] calculateDistances(Map<String, ValveJava> valves) {
        int[][] dist = new int[valves.size()][valves.size()];

        for (int[] row : dist) {
            Arrays.fill(row, -1);
        }

        boolean ready;

        do {
            ready = true;

            for (ValveJava valve : valves.values()) {
                dist[valve.getIndex()][valve.getIndex()] = 0;

                for (ValveJava destination : valve.getTunnels()) {
                    dist[valve.getIndex()][destination.getIndex()] = dist[destination.getIndex()][valve.getIndex()] = 1;

                    for (ValveJava other : valves.values()) {
                        if (dist[destination.getIndex()][other.getIndex()] != -1 && (dist[valve.getIndex()][other.getIndex()] == -1 || dist[destination.getIndex()][other.getIndex()] + 1 < dist[valve.getIndex()][other.getIndex()])) {
                            dist[valve.getIndex()][other.getIndex()] = dist[other.getIndex()][valve.getIndex()] = dist[destination.getIndex()][other.getIndex()] + 1;
                            ready = false;
                        }
                    }
                }
            }
        } while (!ready);

        return dist;
    }

    private Map<String, ValveJava> readValves() {
        final Map<String, ValveJava> valves = new TreeMap<>();
        input.stream()
                .map(token -> matchRegex(pattern, token))
                .forEach(m -> parseValve(valves, m));
        final AtomicInteger idx = new AtomicInteger(0);
        valves
                .keySet()
                .stream()
                .sorted()
                .forEach(name -> valves.get(name).setIndex(idx.getAndIncrement()));

        return valves;
    }

    private void parseValve(Map<String, ValveJava> valves, Matcher matcher) {
        ValveJava valve = valves
                .computeIfAbsent(matcher.group(1), n -> new ValveJava());

        valve.setRate(Integer.parseInt(matcher.group(2)));
        valve.setTunnels(Arrays.stream(matcher.group(3).split(", ")).map(n -> valves.computeIfAbsent(n, name -> new ValveJava())).toArray(ValveJava[]::new));
    }

    private Matcher matchRegex(final Pattern pattern, final CharSequence input) {
        final Matcher matcher = pattern.matcher(input);

        if (matcher.matches()) {
            return matcher;
        } else {
            throw new IllegalArgumentException("Input '" + input + "' does not match pattern " + pattern.pattern());
        }
    }
}
