package de.havox_design.aoc.utils.java.exceptions;

public class ReadDataException extends RuntimeException {
    public ReadDataException(String message, Exception e) {
        super(message, e);
    }

    public ReadDataException(Exception e) {
        this(e.getMessage(), e);
    }
}
