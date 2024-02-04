package de.havox_design.aoc2018.day05;

import de.havox_design.aoc.utils.java.AoCFunctionality;

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
        String currentPolymer = input;
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

    public long processTask2() {
        return 0;
    }

    private boolean isSameTypeButOppositePolarity(char thisChar, char otherChar) {
        return Character.toLowerCase(thisChar) == Character.toLowerCase(otherChar) && thisChar!= otherChar;
    }
}
