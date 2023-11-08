package de.havox_design.aoc2015.day03;

import de.havox_design.aoc2015.utils.DataReader;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PresentDelivery {
    private final String input;

    public PresentDelivery(String fileName) {
        input = readData(fileName).get(0);
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
        Set<Location> visitedHouses = new HashSet<>();

        Location currentLocation = new Location(0, 0);
        visitedHouses.add(currentLocation);

        for (char c : input.toCharArray()) {
            currentLocation = calculateSantasNextHouse(c, currentLocation);
            visitedHouses.add(currentLocation);
        }

        return visitedHouses.size();
    }

    public int solvePart2() {
        Set<Location> visitedHouses = new HashSet<>();

        Location currentLocationSanta = new Location(0, 0);
        Location currentLocationRoboSanta = new Location(0, 0);
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

    private Location calculateSantasNextHouse(char c, Location currentLocation) {
        if ('<' == c) {
            return new Location(currentLocation.x() - 1, currentLocation.y());
        } else if ('>' == c) {
            return new Location(currentLocation.x() + 1, currentLocation.y());
        } else if ('v' == c) {
            return new Location(currentLocation.x(), currentLocation.y() - 1);
        } else if ('^' == c) {
            return new Location(currentLocation.x(), currentLocation.y() + 1);
        } else {
            throw new IllegalArgumentException("Illegal character found.");
        }
    }

    private List<String> readData(String fileName) {
        return DataReader.readData(fileName, MainClass.class);
    }
}
