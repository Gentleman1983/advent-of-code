package de.havox_design.aoc2016.day04;

import de.havox_design.aoc.utils.java.input.DataReader;

import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Day04 {
    private static final Pattern PATTERN = Pattern.compile("(?<name>[a-z-]+)-(?<sectorId>\\d+)\\[(?<checksum>[a-z]+)]");

    private final List<String> input;

    public Day04(String fileName) {
        input = readData(fileName);
    }

    public static long solvePart1(String fileName) {
        Day04 instance = new Day04(fileName);
        return instance.solvePart1();
    }

    public static long solvePart2(String fileName) {
        Day04 instance = new Day04(fileName);
        return instance.solvePart2();
    }

    public long solvePart1() {
        return parseAll(Room::isValid)
                .mapToInt(Room::sectorId)
                .sum();
    }

    public long solvePart2() {
        return parseAll(Room::checkForRoomsStoringObjectsFromNorthPole)
                .findFirst()
                .map(Room::sectorId)
                .orElseThrow();
    }

    private Stream<Room> parseAll(Predicate<Room> predicate) {
        return input
                .stream()
                .map(this::parse)
                .filter(predicate);
    }

    private Room parse(String roomName) {
        var matcher = PATTERN.matcher(roomName);
        if (matcher.matches()) {
            return new Room(
                    matcher.group("name").replace("-", " "),
                    Integer.parseInt(matcher.group("sectorId")),
                    matcher.group("checksum")
            );
        }
        throw new IllegalArgumentException("Invalid value " + roomName);
    }

    private List<String> readData(String fileName) {
        return DataReader.readData(fileName, MainClass.class);
    }
}
