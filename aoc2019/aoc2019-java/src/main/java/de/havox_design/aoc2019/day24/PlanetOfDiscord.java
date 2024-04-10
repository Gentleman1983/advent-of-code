package de.havox_design.aoc2019.day24;

import de.havox_design.aoc.utils.java.AoCFunctionality;
import de.havox_design.aoc.utils.kotlin.model.positions.Position2d;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PlanetOfDiscord implements AoCFunctionality {
    private final List<String> input;

    public PlanetOfDiscord(String fileName) {
        input = readData(fileName);
    }

    public static long processTask1(String fileName) {
        PlanetOfDiscord instance = new PlanetOfDiscord(fileName);
        return instance.processTask1();
    }

    public static long processTask2(String fileName) {
        PlanetOfDiscord instance = new PlanetOfDiscord(fileName);
        return instance.processTask2();
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
        return 0;
    }
}
