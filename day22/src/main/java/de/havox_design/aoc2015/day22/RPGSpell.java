package de.havox_design.aoc2015.day22;

import java.util.Arrays;

enum RPGSpell {
    MAGIC_MISSILE(53),
    DRAIN(73),
    SHIELD(113),
    POISON(173),
    RECHARGE(229);

    private final int costs;

    RPGSpell(int costs) {
        this.costs = costs;
    }

    static RPGSpell[] getPossibleSpells(int budget, int[] durations) {
        return Arrays
                .stream(values())
                .filter(sp -> sp.costs <= budget && durations[sp.ordinal()] <= 1)
                .toArray(RPGSpell[]::new);
    }

    public int getCosts() {
        return costs;
    }

    public int duration() {
        return switch (this) {
            case MAGIC_MISSILE, DRAIN -> 0;
            case SHIELD, POISON -> 6;
            case RECHARGE -> 5;
        };
    }
}
