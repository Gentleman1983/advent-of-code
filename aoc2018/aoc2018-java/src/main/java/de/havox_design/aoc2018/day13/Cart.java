package de.havox_design.aoc2018.day13;

import de.havox_design.aoc.utils.kotlin.model.coordinates.Coordinate;
import de.havox_design.aoc.utils.kotlin.model.coordinates.FourDirectionsFlipped;

public record Cart(int id, Coordinate location, FourDirectionsFlipped direction) {}
