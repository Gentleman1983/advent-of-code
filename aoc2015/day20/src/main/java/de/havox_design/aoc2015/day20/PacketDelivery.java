package de.havox_design.aoc2015.day20;

import de.havox_design.aoc2015.utils.DataReader;

import java.util.Arrays;

public class PacketDelivery {
    private final Integer input;

    public PacketDelivery(String fileName) {
        input = readData(fileName);
    }

    public static int solvePart1(String fileName) {
        PacketDelivery instance = new PacketDelivery(fileName);
        return instance.solvePart1();
    }

    public static int solvePart2(String fileName) {
        PacketDelivery instance = new PacketDelivery(fileName);
        return instance.solvePart2();
    }

    public int solvePart1() {
        int minPresents = input;
        final int[] houses = new int[minPresents / 10];
        Arrays.fill(houses, 1);
        for (int elve = 2; elve < houses.length; elve++) {
            for (int houseNumber = elve; houseNumber < houses.length; houseNumber += elve) {
                houses[houseNumber] += elve * 10;
            }
        }

        int houseNumber = 0;
        while (houses[houseNumber] < minPresents) {
            houseNumber++;
        }
        return houseNumber;
    }

    public int solvePart2() {
        int minPresents = input;
        int[] houses = new int[minPresents / 11];
        Arrays.fill(houses, 1);
        for (int elve = 2; elve < minPresents; elve++) {
            for (int houseNumber = elve, deliveries = 0; deliveries < 50 && houseNumber < houses.length; deliveries++, houseNumber += elve) {
                houses[houseNumber] += elve * 11;
            }
        }

        int houseNumber = 0;
        while (houses[houseNumber] < minPresents) {
            houseNumber++;
        }
        return houseNumber;
    }

    private Integer readData(String fileName) {
        return Integer.parseInt(DataReader.readData(fileName, MainClass.class).get(0));
    }
}
