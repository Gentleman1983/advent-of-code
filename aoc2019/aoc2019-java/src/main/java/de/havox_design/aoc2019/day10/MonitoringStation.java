package de.havox_design.aoc2019.day10;

import de.havox_design.aoc.utils.java.AoCFunctionality;
import org.apache.commons.lang3.tuple.Pair;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

import static de.havox_design.aoc.utils.java.helper.JavaMathUtils.greatestCommonDivisor;
import static java.lang.Math.abs;

public class MonitoringStation implements AoCFunctionality {
    private static final Logger LOGGER = Logger.getLogger(MonitoringStation.class.getName());
    private static final char ICON_ASTEROID = '#';

    private final List<String> input;

    public MonitoringStation(String fileName) {
        input = readData(fileName);
    }

    public static long processTask1(String fileName) {
        MonitoringStation instance = new MonitoringStation(fileName);

        return instance.processTask1();
    }

    public static long processTask2(String fileName) {
        MonitoringStation instance = new MonitoringStation(fileName);

        return instance.processTask2();
    }

    public long processTask1() {
        return computePart1();
    }

    public long processTask2() {
        return computePart2();
    }

    private long computePart1() {
        return computeStation().getLeft();
    }

    @SuppressWarnings("squid:S2259")
    private long computePart2() {
        Pair<Long, Long> station = computeStation().getRight();
        int x = 0;
        int y = 0;
        Set<Pair<Long, Long>> asteroids = new HashSet<>();

        for (String line : input) {
            x = 0;

            for (final char c : line.toCharArray()) {
                if (c == ICON_ASTEROID) {
                    asteroids.add(Pair.of((long) x, (long) y));
                }

                x++;
            }

            y++;
        }

        asteroids.remove(station);

        Map<Double, Set<Pair<Long, Long>>> targets = new ConcurrentHashMap<>();

        for (Pair<Long, Long> asteroid : asteroids) {
            double direction = getAngle(station, asteroid);

            targets.putIfAbsent(direction, new HashSet<>());
            targets.get(direction).add(asteroid);
        }

        Pair<Long, Long> target = null;
        int i = 0;
        Iterator<Double> laserDirectionIterator = targets.keySet().stream().sorted().iterator();

        while (i < 200 && !asteroids.isEmpty()) {
            if (!laserDirectionIterator.hasNext()) {
                laserDirectionIterator = targets.keySet().stream().sorted().iterator();
            }

            double laserDirection = laserDirectionIterator.next();

            target = getNearestTarget(targets.get(laserDirection), station);
            asteroids.remove(target);
            targets.get(laserDirection).remove(target);
            i++;

            if (targets.get(laserDirection).isEmpty()) {
                targets.remove(laserDirection);
            }

            if (Set.of(1, 2, 3, 10, 20, 50, 100, 199, 200).contains(i)) {
                Pair<Long, Long> finalTarget = target;

                int finalI = i;
                LOGGER.info(() -> String.format("%s. asteroid vaporised: %s", finalI, finalTarget));
            }
        }

        return target.getLeft() * 100 + target.getRight();
    }

    private Pair<Long, Long> getNearestTarget(Set<Pair<Long, Long>> targets, Pair<Long, Long> station) {
        long distance = Long.MAX_VALUE;
        Pair<Long, Long> nextTarget = null;

        for (Pair<Long, Long> target : targets) {
            long targetDistance = abs(target.getLeft() - station.getLeft()) + abs(target.getRight() - station.getRight());

            if (targetDistance < distance) {
                distance = targetDistance;
                nextTarget = target;
            }
        }

        return nextTarget;
    }

    private Pair<Long, Pair<Long, Long>> computeStation() {
        final int columns;
        final int rows;
        int x = 0;
        int y = 0;
        Set<Pair<Long, Long>> asteroids = new HashSet<>();

        for (String line : input) {
            x = 0;

            for (final char c : line.toCharArray()) {
                if (c == ICON_ASTEROID) {
                    asteroids.add(Pair.of((long) x, (long) y));
                }
                x++;
            }

            y++;
        }

        columns = x;
        rows = y;

        long max = 0;
        Pair<Long, Long> station = null;

        for (Pair<Long, Long> asteroid : asteroids) {
            long res = computeAsteroidsDetected(asteroid, asteroids, columns, rows);

            if (res > max) {
                max = res;
                station = asteroid;
            }
        }

        return Pair.of(max, station);
    }

    private long computeAsteroidsDetected(Pair<Long, Long> station, Set<Pair<Long, Long>> asteroids, int columns, int rows) {
        Set<Pair<Long, Long>> visible = new HashSet<>(asteroids);
        long count = 0;

        visible.remove(station);

        while (!visible.isEmpty()) {
            vaporizeAsteroids(station, visible.stream().findAny().orElseThrow(), visible, columns, rows, true);
            count++;
        }

        return count;
    }

    private Pair<Long, Long> vaporizeAsteroids(Pair<Long, Long> station, Pair<Long, Long> direction, Set<Pair<Long, Long>> asteroids, int columns, int rows, boolean all) {
        long xDiff = direction.getLeft() - station.getLeft();
        long yDiff = direction.getRight() - station.getRight();
        long gcd = greatestCommonDivisor(abs(xDiff), abs(yDiff));

        if (gcd != 0) {
            xDiff /= gcd;
            yDiff /= gcd;
        }

        long x = station.getLeft() + xDiff;
        long y = station.getRight() + yDiff;
        boolean vaporized;
        Pair<Long, Long> vaporize;

        do {
            vaporize = Pair.of(x, y);
            vaporized = asteroids.remove(vaporize);
            x += xDiff;
            y += yDiff;
        } while ((!vaporized || all) && 0 <= x && x < columns && 0 <= y && y < rows);

        return vaporized ? vaporize : null;
    }

    public double getAngle(Pair<Long, Long> station, Pair<Long, Long> target) {
        double angle = 90 + Math.toDegrees(
                Math.atan2(
                        (double) target.getRight() - station.getRight(),
                        (double) target.getLeft() - station.getLeft()
                )
        );

        while (angle < 0) {
            angle += 360;
        }
        while (angle >= 360) {
            angle -= 360;
        }

        return angle;
    }
}
