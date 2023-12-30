package de.havox_design.utils.exceptions;

public class ReadDataException extends RuntimeException {
    public ReadDataException(Exception e) {
        super(e.getMessage(), e);
    }
}
