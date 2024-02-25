package de.havox_design.aoc2018.day24;

import java.util.Set;

public record Group(
        String name,
        int id,
        int units,
        int hitPoints,
        int initiative,
        Set<String> weaknesses,
        Set<String> immunities,
        String damageType,
        int damage
) implements GroupAbilities {
    @Override
    public int getDamage() {
        return damage;
    }

    @Override
    public int getUnits() {
        return units;
    }

    public MutableGroup asMutableGroup(int boost) {
        return new MutableGroup(name, id, units, hitPoints, initiative, weaknesses, immunities, damageType, damage, boost);
    }
}
