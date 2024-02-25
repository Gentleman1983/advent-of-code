package de.havox_design.aoc2018.day25;

import de.havox_design.aoc.utils.java.AoCFunctionality;

import java.util.List;

public class FourDimensionalAdventure implements AoCFunctionality {
    private final List<Position4d> positions;

    public FourDimensionalAdventure(String fileName) {
        PositionParser parser = new PositionParser();
        List<String> input = readData(fileName);

        positions = input
                .stream()
                .map(parser::parseLine)
                .toList();
    }

    public static long processTask1(String fileName) {
        FourDimensionalAdventure instance = new FourDimensionalAdventure(fileName);
        return instance.processTask1();
    }

    public static String processTask2(String fileName) {
        FourDimensionalAdventure instance = new FourDimensionalAdventure(fileName);
        return instance.processTask2();
    }

    public long processTask1() {
        int sizeMinus1 = positions.size() - 1;
        Integer[] parents = new Integer[positions.size()];
        int constellations = positions.size();

        for (int i = 0; i < sizeMinus1; i++) {
            for (int j = i + 1; j < positions.size(); j++) {
                if (positions.get(i).manhattanDistance(positions.get(j)) <= 3) {
                    int iParent = getParent(parents, i);
                    int jParent = getParent(parents, j);

                    if (iParent != jParent) {
                        parents[jParent] = iParent;
                        constellations--;
                    }
                }
            }
        }

        return constellations;
    }

    public String processTask2() {
        return "Merry XMas!!!";
    }

    private int getParent(final Integer[] parents, int i) {
        Integer parent = parents[i];

        if (parent == null) {
            return i;
        }

        parent = getParent(parents, parent);
        parents[i] = parent;

        return parent;
    }
}
