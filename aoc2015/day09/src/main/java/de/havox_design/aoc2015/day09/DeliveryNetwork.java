package de.havox_design.aoc2015.day09;

import java.util.*;

public class DeliveryNetwork implements Comparable<DeliveryNetwork> {
    private static final Comparator<DeliveryNetwork> CITY_PATH_COMPARATOR = Comparator.comparingInt(DeliveryNetwork::getDistance);

    private final List<String> steps = new ArrayList<>();
    private final Set<String> seen = new HashSet<>();
    private final int distance;

    DeliveryNetwork(String start) {
        steps.add(start);
        seen.add(start);
        distance = 0;
    }

    private DeliveryNetwork(String next, int cost, DeliveryNetwork history) {
        if (history.contains(next)) {
            throw new IllegalArgumentException("city: " + next + " is already visited");
        }
        steps.addAll(history.steps);
        seen.addAll(history.seen);
        steps.add(next);
        seen.add(next);
        this.distance = cost + history.distance;
    }

    boolean contains(String city) {
        return seen.contains(city);
    }

    int getDistance() {
        return distance;
    }

    int getSize() {
        return steps.size();
    }

    String lastCity() {
        return steps.get(steps.size() - 1);
    }

    List<DeliveryNetwork> getNexts(Map<String, Integer> availables) {
        return availables.entrySet().stream().filter(e -> !contains(e.getKey()))
                .map(e -> new DeliveryNetwork(e.getKey(), e.getValue(), this)).toList();
    }

    @Override
    @SuppressWarnings("squid:S1210")
    public int compareTo( DeliveryNetwork other) {
        return CITY_PATH_COMPARATOR.compare(this, other);
    }
}
