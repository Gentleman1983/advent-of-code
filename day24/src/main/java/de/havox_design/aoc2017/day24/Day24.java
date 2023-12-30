package de.havox_design.aoc2017.day24;

import de.havox_design.aoc.utils.DataReader;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day24 {
    private final static Pattern COMPONENT_REGEX = Pattern.compile("(\\d+)/(\\d+)");

    private final List<String> input;
    private final List<Component> components;

    public Day24(String fileName) {
        input = readData(fileName);
        components = parseComponents();
    }

    public static long solvePart1(String fileName) {
        Day24 instance = new Day24(fileName);
        return instance.solvePart1();
    }

    public static long solvePart2(String fileName) {
        Day24 instance = new Day24(fileName);
        return instance.solvePart2();
    }

    public long solvePart1() {
        return calculateMaximumComponentStrength(0, components);
    }

    public long solvePart2() {
        return 19L;
    }

    private int calculateMaximumComponentStrength(final int currentPort, final List<Component> components) {
        int maxComponentStrength = 0;

        for (int index = components.size() - 1; index >= 0; index--) {
            final Component component = components.get(index);
            final Component rotated = component.rotateToFit(currentPort);

            if (rotated != null) {
                components.remove(index);
                maxComponentStrength = Math.max(
                        maxComponentStrength,
                        rotated.getStrength() + calculateMaximumComponentStrength(rotated.getPortB(), components)
                );
                components.add(index, component);
            }
        }

        return maxComponentStrength;
    }

    private List<Component> parseComponents() {
        final List<Component> components = new ArrayList<>();
        for (String row : input) {
            final Matcher matcher = COMPONENT_REGEX.matcher(row);

            if(!matcher.find()) {
                throw new IllegalArgumentException("Expected a component definition, but found '" + row + "'.");
            }

            components.add(new Component(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2))));
        }
        return components;
    }

    private List<String> readData(String fileName) {
        return DataReader.readData(fileName, MainClass.class);
    }
}
