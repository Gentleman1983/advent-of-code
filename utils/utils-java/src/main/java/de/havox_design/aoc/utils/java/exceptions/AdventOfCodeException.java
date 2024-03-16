package de.havox_design.aoc.utils.java.exceptions;

public class AdventOfCodeException extends RuntimeException {
    public AdventOfCodeException(String message, Exception e) {
        super(message, e);
    }

    public AdventOfCodeException(Exception e) {
        this(e.getMessage(), e);
    }
}
