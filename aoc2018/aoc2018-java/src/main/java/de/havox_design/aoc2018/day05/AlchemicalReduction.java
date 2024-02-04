package de.havox_design.aoc2018.day05;

import de.havox_design.aoc.utils.java.AoCFunctionality;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AlchemicalReduction implements AoCFunctionality {
    private final String input;

    public AlchemicalReduction(String fileName) {
        input = readString(fileName);
    }

    public static int processTask1(String fileName) {
        AlchemicalReduction instance = new AlchemicalReduction(fileName);
        return instance.processTask1();
    }

    public static long processTask2(String fileName) {
        AlchemicalReduction instance = new AlchemicalReduction(fileName);
        return instance.processTask2();
    }

    public int processTask1() {
        return calculatePolymerLength(input);
    }

    public long processTask2() {
        List<Integer> results = new ArrayList<>();

        for(char type : findAllTypes(input)) {
            String reducedPolymer = reducePolymer(input, type);
            results.add(calculatePolymerLength(reducedPolymer));
        }

        return results
                .stream()
                .min(Integer::compareTo)
                .orElseThrow(() -> new IllegalStateException("This should never happen..."));
    }

    private int calculatePolymerLength(String polymer) {
        String currentPolymer = polymer;
        int currentLength = Integer.MIN_VALUE;

        while(currentLength != currentPolymer.length()) {
            currentLength = currentPolymer.length();

            for(int i = 0; i < currentPolymer.length()-1; i++) {
                char currentChar = currentPolymer.charAt(i);
                char nextChar = currentPolymer.charAt(i+1);

                if(isSameTypeButOppositePolarity(currentChar, nextChar)) {
                    currentPolymer = currentPolymer.substring(0, i) + currentPolymer.substring(i+2);
                    break;
                }
            }
        }

        return currentPolymer.length();
    }

    private boolean isSameTypeButOppositePolarity(char thisChar, char otherChar) {
        return Character.toLowerCase(thisChar) == Character.toLowerCase(otherChar) && thisChar!= otherChar;
    }

    private Set<Character> findAllTypes(String polymer) {
        Set<Character> types = new HashSet<>();

        for(int i = 0; i < polymer.length(); i++) {
            types.add(Character.toLowerCase(polymer.charAt(i)));
        }

        return types;
    }

    private String reducePolymer(String polymer, char type) {
        return polymer
                .replaceAll(String.valueOf(Character.toLowerCase(type)), "")
                .replaceAll(String.valueOf(Character.toUpperCase(type)), "");
    }
}
