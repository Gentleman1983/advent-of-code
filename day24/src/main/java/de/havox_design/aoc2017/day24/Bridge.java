package de.havox_design.aoc2017.day24;

public class Bridge {
    private int length;
    private int strength;

    public Bridge(final int length, final int strength) {
        super();
        this.length = length;
        this.strength = strength;
    }

    public int getStrength() {
        return strength;
    }

    public int getLength() {
        return length;
    }

    public void incLength() {
        length++;
    }

    public void incStrength(final int additionalStrength) {
        this.strength += additionalStrength;
    }
}
