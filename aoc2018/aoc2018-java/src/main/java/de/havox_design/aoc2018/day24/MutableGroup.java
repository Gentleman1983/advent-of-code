package de.havox_design.aoc2018.day24;

import java.util.Set;

public class MutableGroup implements GroupAbilities {
    private final String name;
    private final int id;
    private int units;
    private final int hitPoints;
    private final int initiative;
    private final Set<String> weaknesses;
    private final Set<String> immunities;
    private final String damageType;
    private final int damage;
    private final int boost;

    @SuppressWarnings("squid:S107")
    public MutableGroup(
            String name,
            int id,
            int units,
            int hitPoints,
            int initiative,
            Set<String> weaknesses,
            Set<String> immunities,
            String damageType,
            int damage,
            int boost
    ) {
        this.name = name;
        this.id = id;
        this.units = units;
        this.hitPoints = hitPoints;
        this.initiative = initiative;
        this.weaknesses = weaknesses;
        this.immunities = immunities;
        this.damageType = damageType;
        this.damage = damage;
        this.boost = boost;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    @Override
    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        this.units = units;
    }

    public void reduceUnits(int units) {
        setUnits(Math.max(0, this.units - units));
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public int getInitiative() {
        return initiative;
    }

    public Set<String> getWeaknesses() {
        return weaknesses;
    }

    public Set<String> getImmunities() {
        return immunities;
    }

    public String getDamageType() {
        return damageType;
    }

    @Override
    public int getDamage() {
        return damage;
    }

    public int getBoost() {
        return boost;
    }

    @Override
    public int getEffectivePower() {
        return (getDamage() + getBoost()) * getUnits();
    }
}
