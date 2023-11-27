package de.havox_design.aoc2016.day02;

import static de.havox_design.aoc2016.day02.Direction.*;
import static de.havox_design.aoc2016.day02.Key.*;

import de.havox_design.aoc2016.utils.DataReader;

import java.util.List;

public class Day02 {
    private final List<String> input;

    public Day02(String fileName) {
        input = readData(fileName);
    }

    public static String solvePart1(String fileName) {
        Day02 instance = new Day02(fileName);
        return instance.solvePart1();
    }

    public static long solvePart2(String fileName) {
        Day02 instance = new Day02(fileName);
        return instance.solvePart2();
    }

    public String solvePart1() {
        StringBuilder bathroomCode = new StringBuilder();
        Key currentElement = FIVE;

        for(String instruction : input) {
            for(char c : instruction.toCharArray()) {
                Key nextElement = switch (c) {
                    case 'U' -> UP.next(currentElement);
                    case 'D' -> DOWN.next(currentElement);
                    case 'L' -> LEFT.next(currentElement);
                    case 'R' -> RIGHT.next(currentElement);
                    default -> throw new IllegalArgumentException("Symbol '" + c + "' on instruction '" + instruction +
                            "' is no supported operation.");
                };

                if(isOnKeypad(nextElement)) {
                    currentElement = nextElement;
                }
            }

            bathroomCode.append(Key.getValueForKey(currentElement));
        }

        return bathroomCode.toString();
    }

    public long solvePart2() {return 0L;
    }

    private boolean isOnKeypad(Key key) {
        return Key.getKeypadElements().contains(key);
    }

    private List<String> readData(String fileName) {
        return DataReader.readData(fileName, MainClass.class);
    }
}
