package de.havox_design.aoc2017.day24;


import de.havox_design.aoc.utils.java.AoCFunctionality;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ElectromagneticMoat implements AoCFunctionality {
    @SuppressWarnings("squid:S5852")
    private static final Pattern COMPONENT_REGEX = Pattern.compile("(\\d+)/(\\d+)");

    private final List<String> input;
    private final List<Component> components;

    public ElectromagneticMoat(String fileName) {
        input = readData(fileName);
        components = parseComponents();
    }

    public static long solvePart1(String fileName) {
        ElectromagneticMoat instance = new ElectromagneticMoat(fileName);
        return instance.solvePart1();
    }

    public static long solvePart2(String fileName) {
        ElectromagneticMoat instance = new ElectromagneticMoat(fileName);
        return instance.solvePart2();
    }

    public long solvePart1() {
        return calculateMaximumBridgeStrength(0, components);
    }

    public long solvePart2() {
        return calculateLongestBridge(0).getStrength();
    }

    private int calculateMaximumBridgeStrength(final int currentPort, final List<Component> components) {
        int maxComponentStrength = 0;

        for (int index = components.size() - 1; index >= 0; index--) {
            final Component component = components.get(index);
            final Component rotated = component.rotateToFit(currentPort);

            if (rotated != null) {
                components.remove(index);
                maxComponentStrength = Math.max(
                        maxComponentStrength,
                        rotated.getStrength() + calculateMaximumBridgeStrength(rotated.getPortB(), components)
                );
                components.add(index, component);
            }
        }

        return maxComponentStrength;
    }

    private Bridge calculateLongestBridge(final int currentPort) {
        Bridge longestAndStrongestBridge = new Bridge(0, 0);

        for (int i = components.size() - 1; i >= 0; i--) {
            final Component component = components.get(i);
            final Component rotated = component.rotateToFit(currentPort);

            if (rotated != null) {
                components.remove(i);

                final Bridge subBridge = calculateLongestBridge(rotated.getPortB());

                subBridge.increaseStrength(rotated.getStrength());
                subBridge.increaseLength();
                longestAndStrongestBridge = detectStrongerBridge(longestAndStrongestBridge, subBridge);
                components.add(i, component);
            }
        }
        return longestAndStrongestBridge;
    }

    private Bridge detectStrongerBridge(final Bridge bridgeA, final Bridge bridgeB) {
        if (bridgeA.getLength() > bridgeB.getLength()) {
            return bridgeA;
        }
        else if (bridgeA.getLength() < bridgeB.getLength()) {
            return bridgeB;
        }
        else if (bridgeA.getStrength() > bridgeB.getStrength()) {
            return bridgeA;
        }
        else {
            return bridgeB;
        }
    }

    private List<Component> parseComponents() {
        final List<Component> componentList = new ArrayList<>();
        for (String row : input) {
            final Matcher matcher = COMPONENT_REGEX.matcher(row);

            if(!matcher.find()) {
                throw new IllegalArgumentException("Expected a component definition, but found '" + row + "'.");
            }

            componentList.add(new Component(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2))));
        }
        return componentList;
    }
}
