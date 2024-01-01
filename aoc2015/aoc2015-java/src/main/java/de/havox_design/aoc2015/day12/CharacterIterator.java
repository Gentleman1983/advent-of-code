package de.havox_design.aoc2015.day12;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class CharacterIterator implements Iterator<Character> {
    private final String data;
    private int current = 0;

    public CharacterIterator(String data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        return current < data.length();
    }

    @Override
    public Character next() {
        if(!hasNext()) {
            throw new NoSuchElementException("There are no more elements...");
        }

        return data.charAt(current++);
    }

    public char current() {
        return data.charAt(current);
    }

    public char back() {
        return data.charAt(--current);
    }
}
