package de.havox_design.aoc2019.day24;

import de.havox_design.aoc.utils.java.AoCFunctionality;
import de.havox_design.aoc.utils.kotlin.model.positions.Position2d;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PlanetOfDiscord implements AoCFunctionality {
    private static final int STEPS = 200;

    private final List<String> input;

    public PlanetOfDiscord(String fileName) {
        input = readData(fileName);
    }

    public static long processTask1(String fileName) {
        PlanetOfDiscord instance = new PlanetOfDiscord(fileName);
        return instance.processTask1();
    }

    public static long processTask2(String fileName) {
        return processTask2(fileName, STEPS);
    }

    public static long processTask2(String fileName, int steps) {
        PlanetOfDiscord instance = new PlanetOfDiscord(fileName);
        return instance.processTask2(steps);
    }

    public long processTask1() {
        SimpleEris eris = new SimpleEris(input);
        Set<Set<Position2d<Integer>>> states = new HashSet<>();

        while (states.add(eris.getBugs())) {
            eris.evolve();
        }

        return eris.biodiversityRating();
    }

    public long processTask2() {
        return processTask2(STEPS);
    }

    public long processTask2(int steps) {
        ComplexEris eris = new ComplexEris(input);

        for (int i = 0; i < steps; i++) {
            eris.evolve();
        }
        return eris.getBugs().size();
    }
}
