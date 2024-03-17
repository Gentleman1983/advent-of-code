package de.havox_design.aoc2019.day10;

import com.google.common.math.LongMath;
import de.havox_design.aoc.utils.java.AoCFunctionality;
import org.apache.commons.lang3.tuple.Pair;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.lang.Math.abs;

public class MonitoringStation implements AoCFunctionality {
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
        return compute();
    }

    public long processTask2() {
        return 0;
    }

    private long compute() {
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

        return max;
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

    private Pair<Long, Long> vaporizeAsteroids( Pair<Long, Long> station, Pair<Long, Long> direction, Set<Pair<Long, Long>> asteroids, int columns, int rows, boolean all ) {
        long xDiff = direction.getLeft() - station.getLeft();
        long yDiff = direction.getRight() - station.getRight();
        long gcd = LongMath.gcd( abs( xDiff ), abs( yDiff ) );

        if ( gcd != 0 ) {
            xDiff /= gcd;
            yDiff /= gcd;
        }

        long x = station.getLeft() + xDiff;
        long y = station.getRight() + yDiff;
        boolean vaporized;
        Pair<Long, Long> vaporize;

        do {
            vaporize = Pair.of( x, y );
            vaporized = asteroids.remove( vaporize );
            x += xDiff;
            y += yDiff;
        } while ( ( !vaporized || all ) && 0 <= x && x < columns && 0 <= y && y < rows );

        return vaporized ? vaporize : null;
    }
}
