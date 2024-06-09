package de.havox_design.aoc2015.day03;


import de.havox_design.aoc.utils.java.AoCFunctionality;
import de.havox_design.aoc.utils.kotlin.model.positions.Position2d;

import java.util.HashSet;
import java.util.Set;

public class PresentDelivery implements AoCFunctionality {
    private final String input;

    public PresentDelivery(String fileName) {
        input = readData(fileName).getFirst();
    }

    public static int solvePart1(String fileName) {
        PresentDelivery instance = new PresentDelivery(fileName);
        return instance.solvePart1();
    }

    public static int solvePart2(String fileName) {
        PresentDelivery instance = new PresentDelivery(fileName);
        return instance.solvePart2();
    }

    public int solvePart1() {
        Set<Position2d<Integer>> visitedHouses = new HashSet<>();

        Position2d<Integer> currentLocation = new Position2d<>(0, 0);
        visitedHouses.add(currentLocation);

        for (char c : input.toCharArray()) {
            currentLocation = calculateSantasNextHouse(c, currentLocation);
            visitedHouses.add(currentLocation);
        }

        return visitedHouses.size();
    }

    public int solvePart2() {
        Set<Position2d<Integer>> visitedHouses = new HashSet<>();

        Position2d<Integer> currentLocationSanta = new Position2d<>(0, 0);
        Position2d<Integer> currentLocationRoboSanta = new Position2d<>(0, 0);
        boolean santaMoves = true;

        visitedHouses.add(currentLocationSanta);
        visitedHouses.add(currentLocationRoboSanta);

        for (char c : input.toCharArray()) {
            if (santaMoves) {
                currentLocationSanta = calculateSantasNextHouse(c, currentLocationSanta);
                visitedHouses.add(currentLocationSanta);
            } else {
                currentLocationRoboSanta = calculateSantasNextHouse(c, currentLocationRoboSanta);
                visitedHouses.add(currentLocationRoboSanta);
            }

            santaMoves = !santaMoves;
        }

        return visitedHouses.size();
    }

    private Position2d<Integer> calculateSantasNextHouse(char c, Position2d<Integer> currentLocation) {
        if ('<' == c) {
            return new Position2d<>(currentLocation.getX() - 1, currentLocation.getY());
        } else if ('>' == c) {
            return new Position2d<>(currentLocation.getX() + 1, currentLocation.getY());
        } else if ('v' == c) {
            return new Position2d<>(currentLocation.getX(), currentLocation.getY() - 1);
        } else if ('^' == c) {
            return new Position2d<>(currentLocation.getX(), currentLocation.getY() + 1);
        } else {
            throw new IllegalArgumentException("Illegal character found.");
        }
    }
}
