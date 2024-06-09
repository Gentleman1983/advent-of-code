package de.havox_design.aoc2016.day04;

import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;

public record Room(String name, int sectorId, String checksum) {
    private static final Comparator<Map.Entry<String, Long>> COMPARATOR =
            (a, b) -> {
        var roomNumberComparison = b.getValue().compareTo(a.getValue());

        return roomNumberComparison == 0 ? a.getKey().compareTo(b.getKey()) : roomNumberComparison;
    };

    boolean isValid() {
        return name.chars()
                .filter(c -> c != ' ')
                .mapToObj(c -> String.valueOf((char) c))
                .collect(Collectors.groupingBy(v -> v, Collectors.counting()))
                .entrySet().stream()
                .sorted(COMPARATOR)
                .limit(5)
                .map(Map.Entry::getKey)
                .collect(Collectors.joining())
                .equals(checksum);
    }

    boolean checkForRoomsStoringObjectsFromNorthPole() {
        return isValid() && name.chars()
                .map(c -> c == ' ' ? c : 'a' + (((c - 'a') + sectorId) % 26))
                .mapToObj(c -> String.valueOf((char) c))
                .collect(Collectors.joining())
                .startsWith("north");
    }
}
