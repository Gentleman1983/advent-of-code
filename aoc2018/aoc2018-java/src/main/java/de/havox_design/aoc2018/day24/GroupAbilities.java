package de.havox_design.aoc2018.day24;

public interface GroupAbilities {
    int getDamage();
    int getUnits();
    default int getEffectivePower() {
        return getDamage() * getUnits();
    }
}
