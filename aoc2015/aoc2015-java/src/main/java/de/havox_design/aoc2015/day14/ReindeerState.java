package de.havox_design.aoc2015.day14;

public enum ReindeerState {
    FLYING,
    RESTING;

    public static ReindeerState toggle(ReindeerState currentState) {
        return FLYING == currentState ? RESTING : FLYING;
    }
}
