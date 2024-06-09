package de.havox_design.aoc2015.day09;


import de.havox_design.aoc.utils.java.AoCFunctionality;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AllInASingleNight implements AoCFunctionality {
    private static final Pattern DISTANCE_PATTERN = Pattern.compile("(\\S+) to (\\S+) = (\\d+)");
    private final Map<String, Map<String, Integer>> input;

    public AllInASingleNight(String fileName) {
        input = parseData(fileName);
    }

    public static int solvePart1(String fileName) {
        AllInASingleNight instance = new AllInASingleNight(fileName);

        return instance.solvePart1();
    }

    public static int solvePart2(String fileName) {
        AllInASingleNight instance = new AllInASingleNight(fileName);

        return instance.solvePart2();
    }

    public int solvePart1() {
        Queue<DeliveryNetwork> queue = new PriorityQueue<>();

        input.keySet().forEach(city -> queue.add(new DeliveryNetwork(city)));

        while (!queue.isEmpty() && queue.peek().getSize() < input.size()) {
            DeliveryNetwork top = queue.poll();

            assert top != null;
            queue.addAll(top.getNexts(input.get(top.lastCity())));
        }

        assert queue.peek() != null;

        return queue.peek().getDistance();
    }

    public int solvePart2() {
        Queue<DeliveryNetwork> queue = new PriorityQueue<>(
                Comparator
                        .comparingInt(DeliveryNetwork::getSize)
                        .thenComparing(DeliveryNetwork::getDistance, Comparator.reverseOrder()));

        input.keySet().forEach(city -> queue.add(new DeliveryNetwork(city)));

        while (!queue.isEmpty() && queue.peek().getSize() != input.size()) {
            DeliveryNetwork top = queue.poll();

            assert top != null;
            queue.addAll(top.getNexts(input.get(top.lastCity())));
        }

        assert queue.peek() != null;

        return queue.peek().getDistance();
    }

    private Map<String, Map<String, Integer>> parseData(String fileName) {
        List<String> fileDataRows = readData(fileName);
        Map<String, Map<String, Integer>> landscape = new HashMap<>();

        for (String line : fileDataRows) {
            Matcher matcher = DISTANCE_PATTERN.matcher(line);

            if (!matcher.matches()) {
                throw new IllegalArgumentException("I cannot interprete line: " + line);
            }
            addToNetwork(landscape, matcher.group(1), matcher.group(2), Integer.parseInt(matcher.group(3)));
        }

        return landscape;
    }

    private void addToNetwork(Map<String, Map<String, Integer>> deliveryNetwork, String city1, String city2, int distance) {
        deliveryNetwork.computeIfAbsent(city1, k -> new HashMap<>()).put(city2, distance);
        deliveryNetwork.computeIfAbsent(city2, k -> new HashMap<>()).put(city1, distance);
    }
}
