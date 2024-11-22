package de.havox_design.aoc2022.day16;

public record StateJava(
        ValveJava myLocation,
        int myMinutes,
        ValveJava elephantLocation,
        int elephantMinutes,
        int released,
        long opened,
        StateJava pred
) {
}
