package de.havox_design.aoc2015.utils.exceptions;

public class ReadDataException extends RuntimeException {
    public ReadDataException(Exception e) {
        super(e.getMessage(), e);
    }
}
