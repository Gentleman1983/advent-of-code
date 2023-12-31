package de.havox_design.aoc2016.day02;


import de.havox_design.aoc.utils.java.AoCFunctionality;

import static de.havox_design.aoc2016.day02.Direction.*;

import java.util.List;

public class BathroomSecurity implements AoCFunctionality {
    private final List<String> input;

    public BathroomSecurity(String fileName) {
        input = readData(fileName);
    }

    public static String solvePart1(String fileName) {
        BathroomSecurity instance = new BathroomSecurity(fileName);
        return instance.solvePart1();
    }

    public static String solvePart2(String fileName) {
        BathroomSecurity instance = new BathroomSecurity(fileName);
        return instance.solvePart2();
    }

    public String solvePart1() {
        KeyPad keypad = SimpleKeyPad.getInstance();
        Key currentElement = SimpleKeyPad.FIVE;
        StringBuilder bathroomCode = new StringBuilder();
        searchBathroomCode(currentElement, keypad, bathroomCode);
        return bathroomCode.toString();
    }

    public String solvePart2() {
        KeyPad keypad = ComplexKeyPad.getInstance();
        Key currentElement = ComplexKeyPad.FIVE;
        StringBuilder bathroomCode = new StringBuilder();
        searchBathroomCode(currentElement, keypad, bathroomCode);
        return bathroomCode.toString();
    }

    private void searchBathroomCode(Key currentElement, KeyPad keypad, StringBuilder bathroomCode) {
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

                if(isOnKeypad(nextElement, keypad)) {
                    currentElement = nextElement;
                }
            }

            bathroomCode.append(keypad.getValueForKey(currentElement));
        }
    }

    private boolean isOnKeypad(Key key, KeyPad keypad) {
        return keypad.getKeypadElements().contains(key);
    }
}
