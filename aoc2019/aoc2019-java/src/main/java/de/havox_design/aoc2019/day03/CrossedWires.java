package de.havox_design.aoc2019.day03;

import de.havox_design.aoc.utils.java.AoCFunctionality;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CrossedWires implements AoCFunctionality {
    private final GridPoint centralPort = new GridPoint(0, 0);
    private final List<Wire> wires;
    private final Set<GridPoint> intersectionPoints;

    public CrossedWires(String fileName) {
        List<String> wirePaths = readData(fileName);

        wires = wirePaths
                .stream()
                .map(wirePathStr -> Wire.parseWirePath(centralPort, wirePathStr))
                .toList();
        intersectionPoints = findWireIntersectionPoints();
    }

    public static long processTask1(String fileName) {
        CrossedWires instance = new CrossedWires(fileName);
        return instance.processTask1();
    }

    public static long processTask2(String fileName) {
        CrossedWires instance = new CrossedWires(fileName);
        return instance.processTask2();
    }

    public long processTask1() {
        GridPoint closestIntersectionPoint = getClosesIntersectionPoint();

        return closestIntersectionPoint.distanceTo(centralPort);
    }

    public long processTask2() {
        return intersectionPoints
                .stream()
                .mapToLong(this::signalDistanceTo)
                .min()
                .orElse(0L);
    }

    private Set<GridPoint> findWireIntersectionPoints() {
        Set<GridPoint> resultingIntersectionPoints = new HashSet<>();

        for (int i = 0; i < wires.size(); i++) {
            for (int j = i + 1; j < wires.size(); j++) {
                Wire wire = wires.get(i);
                Wire otherWire = wires.get(j);
                resultingIntersectionPoints.addAll(wire.intersectionPointsWith(otherWire));
            }
        }

        resultingIntersectionPoints.remove(centralPort);

        return resultingIntersectionPoints;
    }

    private GridPoint getClosesIntersectionPoint() {
        return intersectionPoints
                .stream()
                .min(Comparator.comparingInt(centralPort::distanceTo))
                .orElse(centralPort);
    }

    private int signalDistanceTo(GridPoint intersectionPoint) {
        return wires
                .stream()
                .mapToInt(wire -> wire.signalDistanceTo(intersectionPoint))
                .sum();
    }
}
