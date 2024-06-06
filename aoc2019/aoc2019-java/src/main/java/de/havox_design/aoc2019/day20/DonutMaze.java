package de.havox_design.aoc2019.day20;

import static de.havox_design.aoc2019.day20.Pair.ZERO;
import static java.lang.Character.isLetter;

import de.havox_design.aoc.utils.java.AoCFunctionality;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.HashSetValuedHashMap;

import java.util.*;
import java.util.stream.Stream;

public class DonutMaze implements AoCFunctionality {
    private static final char ICON_PASSAGE = '.';
    private static final char ICON_WALL = '#';

    private final List<String> input;

    private long direction = 0L;

    public DonutMaze(String fileName) {
        input = readData(fileName);
    }

    public static long processTask1(String fileName) {
        DonutMaze instance = new DonutMaze(fileName);
        return instance.processTask1();
    }

    public static long processTask2(String fileName) {
        DonutMaze instance = new DonutMaze(fileName);
        return instance.processTask2();
    }

    public long processTask1() {
        Pair<Pair<Long, Long>, Long> source = new Pair<>(ZERO, 0L);
        Pair<Pair<Long, Long>, Long> destination = new Pair<>(ZERO, 0L);
        MultiValuedMap<Pair<Long, Long>, Pair<Pair<Long, Long>, Long>> edges = initialize(source, destination);

        return computeDistance(edges, source, destination);
    }

    public long processTask2() {
        direction = 1L;

        return processTask1();
    }

    @SuppressWarnings("squid:S3824")
    private long computeDistance(
            final MultiValuedMap<Pair<Long, Long>, Pair<Pair<Long, Long>, Long>> edges,
            final Pair<Pair<Long, Long>, Long> source,
            final Pair<Pair<Long, Long>, Long> destination
    ) {
        Queue<Pair<Pair<Long, Long>, Long>> queue = new LinkedList<>();
        Map<Pair<Pair<Long, Long>, Long>, Long> distances = new HashMap<>();

        queue.add(source);
        distances.put(source, 0L);

        while (!queue.isEmpty()) {
            Pair<Pair<Long, Long>, Long> current = queue.remove();

            if (current.equals(destination)) {
                return distances.get(current);
            }

            for (Pair<Pair<Long, Long>, Long> n : edges.get(current.getFirst())) {
                long floor = current.getSecond() + n.getSecond();

                if (floor == -1) {
                    continue;
                }

                Pair<Pair<Long, Long>, Long> cell = new Pair<>(n.getFirst(), floor);

                if (!distances.containsKey(cell)) {
                    queue.add(cell);
                    distances.put(cell, distances.get(current) + 1);
                }
            }
        }

        throw new IllegalStateException();
    }

    private MultiValuedMap<Pair<Long, Long>, Pair<Pair<Long, Long>, Long>> initialize(
            Pair<Pair<Long, Long>, Long> source,
            Pair<Pair<Long, Long>, Long> destination
    ) {
        Map<Pair<Long, Long>, Character> map = initializeMap();
        long maxX = map
                .keySet()
                .stream()
                .mapToLong(Pair::getFirst)
                .max()
                .orElseThrow();
        long maxY = map
                .keySet()
                .stream()
                .mapToLong(Pair::getSecond)
                .max()
                .orElseThrow();
        MultiValuedMap<Pair<Long, Long>, Pair<Pair<Long, Long>, Long>> edges = new HashSetValuedHashMap<>();
        Map<String, Pair<Long, Long>> portals = new HashMap<>();

        for (Map. Entry<Pair<Long, Long>, Character> p : map.entrySet()) {
            if (p.getValue() == ICON_PASSAGE) {
                for (Pair<Long, Long> n : computeNeighbours(p.getKey(), map)) {
                    edges.put(p.getKey(), new Pair<>(n, 0L));
                    edges.put(n, new Pair<>(p.getKey(), 0L));
                }
            } else if (isLetter(p.getValue())) {
                addPortal(p, map, portals, edges, source, destination, maxX, maxY);
            }
        }

        return edges;
    }

    private Map<Pair<Long, Long>, Character> initializeMap() {
        Map<Pair<Long, Long>, Character> map = new HashMap<>();
        Pair<Long, Long> current = new Pair<>(0L, 0L);

        input
                .forEach(
                        line -> {
                            current.setFirst(0L);

                            for (Character c : line.toCharArray()) {
                                map.put(new Pair<>(current.getFirst(), current.getSecond()), c);
                                current.setFirst(current.getFirst() + 1);
                            }

                            current.setSecond(current.getSecond() + 1);
                        }
                );

        return map;
    }

    @SuppressWarnings("squid:S107")
    private void addPortal(
            Map.Entry<Pair<Long, Long>, Character> p,
            Map<Pair<Long, Long>, Character> map,
            Map<String, Pair<Long, Long>> portals,
            MultiValuedMap<Pair<Long, Long>, Pair<Pair<Long, Long>, Long>> edges,
            Pair<Pair<Long, Long>, Long> source,
            Pair<Pair<Long, Long>, Long> destination,
            long maxX,
            long maxY
    ) {
        Pair<Long, Long> pos = p.getKey();
        Character c = p.getValue();
        String portal = getPortalName(map, pos, c);
        List<Pair<Long, Long>> cells = computeNeighbours(pos, map);

        if (!cells.isEmpty()) {
            Pair<Long, Long> cell = cells.getFirst();

            if (portals.containsKey(portal)) {
                Pair<Long, Long> in;
                Pair<Long, Long> out;

                if (isOutPortal(cell, maxX, maxY)) {
                    out = cell;
                    in = portals.get(portal);
                } else {
                    out = portals.get(portal);
                    in = cell;
                }

                edges.put(in, new Pair<>(out, direction));
                edges.put(out, new Pair<>(in, -direction));
            } else if (portal.equals("AA")) {
                source.setFirst(cell);
                source.setSecond(0L);
            } else if (portal.equals("ZZ")) {
                destination.setFirst(cell);
                destination.setSecond(0L);
            } else {
                portals.put(portal, cell);
            }
        }
    }

    private boolean isOutPortal(Pair<Long, Long> cell, long maxX, long maxY) {
        long x = cell.getFirst();
        long y = cell.getSecond();

        return x == 2 || y == 2 || x == maxX - 2 || y == maxY - 2;
    }

    private String getPortalName(
            Map<Pair<Long, Long>, Character> map,
            Pair<Long, Long> position,
            Character c
    ) {
        Stream<String> a = Stream
                .of(
                        new Pair<>(position.getFirst() + 1, position.getSecond()),
                        new Pair<>(position.getFirst(), position.getSecond() + 1)
                )
                .filter(n -> isPortal(n, map))
                .map(map::get)
                .map(n -> concat(c, n));
        Stream<String> b = Stream
                .of(
                        new Pair<>(position.getFirst() - 1, position.getSecond()),
                        new Pair<>(position.getFirst(), position.getSecond() - 1)
                )
                .filter(n -> isPortal(n, map))
                .map(map::get)
                .map(n -> concat(n, c));

        return Stream
                .concat(a, b)
                .findFirst()
                .orElseThrow();
    }

    private String concat(Character a, Character b) {
        return String.valueOf(a) + b;
    }

    private List<Pair<Long, Long>> computeNeighbours(Pair<Long, Long> pos, Map<Pair<Long, Long>, Character> map) {
        return Stream
                .of(
                        new Pair<>(pos.getFirst() + 1, pos.getSecond()),
                        new Pair<>(pos.getFirst() - 1, pos.getSecond()),
                        new Pair<>(pos.getFirst(), pos.getSecond() - 1),
                        new Pair<>(pos.getFirst(), pos.getSecond() + 1)
                )
                .filter(n -> isCell(n, map))
                .toList();
    }

    private boolean isCell(Pair<Long, Long> n, final Map<Pair<Long, Long>, Character> map) {
        return map.getOrDefault(n, ICON_WALL) == ICON_PASSAGE;
    }

    private boolean isPortal(Pair<Long, Long> n, Map<Pair<Long, Long>, Character> map) {
        return isLetter(map.getOrDefault(n, ICON_WALL));
    }
}
