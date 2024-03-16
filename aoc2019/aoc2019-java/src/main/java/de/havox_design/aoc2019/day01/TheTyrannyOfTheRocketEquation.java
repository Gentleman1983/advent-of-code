package de.havox_design.aoc2019.day01;

import de.havox_design.aoc.utils.java.AoCFunctionality;

import java.util.List;

public class TheTyrannyOfTheRocketEquation implements AoCFunctionality {
    private final List<Long> input;

    public TheTyrannyOfTheRocketEquation(String fileName) {
        input = readData(fileName).stream().map(line -> Long.parseLong(line.trim())).toList();
    }

    public static long processTask1(String fileName) {
        TheTyrannyOfTheRocketEquation instance = new TheTyrannyOfTheRocketEquation(fileName);
        return instance.processTask1();
    }

    public static long processTask2(String fileName) {
        TheTyrannyOfTheRocketEquation instance = new TheTyrannyOfTheRocketEquation(fileName);
        return instance.processTask2();
    }

    public long processTask1() {
        long totalFuelRequirement = 0L;

        for (long moduleMass : input) {
            totalFuelRequirement += calculateFuelRequirement(moduleMass);
        }

        return totalFuelRequirement;
    }

    public long processTask2() {
        long totalFuelRequirement = 0L;

        for (long moduleMass : input) {
            long fuelToPropelMass = calculateFuelRequirement(moduleMass);
            long fuelToPropelFuel = calculateFuelToPropelFuel(fuelToPropelMass);

            totalFuelRequirement += fuelToPropelMass + fuelToPropelFuel;
        }

        return totalFuelRequirement;
    }

    private long calculateFuelRequirement(long mass) {
        return (mass / 3) - 2;
    }

    private long calculateFuelToPropelFuel(long fuelMass) {
        long totalFuelToPropelFuel = 0L;

        for(long currentFuelToPropelFuel = calculateFuelRequirement(fuelMass); currentFuelToPropelFuel > 0; currentFuelToPropelFuel = calculateFuelRequirement(currentFuelToPropelFuel)) {
            totalFuelToPropelFuel += currentFuelToPropelFuel;
        }

        return totalFuelToPropelFuel;
    }
}
