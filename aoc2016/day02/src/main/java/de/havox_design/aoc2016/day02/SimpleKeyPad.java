package de.havox_design.aoc2016.day02;

import java.util.NoSuchElementException;
import java.util.Set;

public class SimpleKeyPad extends KeyPad {
    private static KeyPad instance;

    public static final Key ONE = new Key(0, 0);
    public static final Key TWO = new Key(1, 0);
    public static final Key THREE = new Key(2, 0);
    public static final Key FOUR = new Key(0, 1);
    public static final Key FIVE = new Key(1, 1);
    public static final Key SIX = new Key(2, 1);
    public static final Key SEVEN = new Key(0, 2);
    public static final Key EIGHT = new Key(1, 2);
    public static final Key NINE = new Key(2, 2);
    private static final Set<Key> KEYPAD = Set.of(ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE);

    private SimpleKeyPad() {
        super();
    }

    public static KeyPad getInstance() {
        if (null == instance) {
            instance = new SimpleKeyPad();
        }

        return instance;
    }

    @Override
    public Set<Key> getKeypadElements() {
        return KEYPAD;
    }

    @Override
    public String getValueForKey(Key key) {
        if (ONE.equals(key)) {
            return "1";
        } else if (TWO.equals(key)) {
            return "2";
        } else if (THREE.equals(key)) {
            return "3";
        } else if (FOUR.equals(key)) {
            return "4";
        } else if (FIVE.equals(key)) {
            return "5";
        } else if (SIX.equals(key)) {
            return "6";
        } else if (SEVEN.equals(key)) {
            return "7";
        } else if (EIGHT.equals(key)) {
            return "8";
        } else if (NINE.equals(key)) {
            return "9";
        } else {
            throw new NoSuchElementException("This is no Keypad element.");
        }
    }
}