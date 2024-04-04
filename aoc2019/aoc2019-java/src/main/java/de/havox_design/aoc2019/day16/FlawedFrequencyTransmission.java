package de.havox_design.aoc2019.day16;

import de.havox_design.aoc.utils.java.AoCFunctionality;

public class FlawedFrequencyTransmission implements AoCFunctionality {
    private final String input;

    public FlawedFrequencyTransmission(String fileName) {
        input = readString(fileName);
    }

    public static String processTask1(String fileName) {
        FlawedFrequencyTransmission instance = new FlawedFrequencyTransmission(fileName);
        return instance.processTask1(100);
    }

    public static long processTask2(String fileName) {
        FlawedFrequencyTransmission instance = new FlawedFrequencyTransmission(fileName);
        return instance.processTask2();
    }

    public String processTask1(int phases) {
        return process(phases);
    }

    public long processTask2() {
        return 0;
    }

    private String process(int phases) {
        return null;
    }
}
