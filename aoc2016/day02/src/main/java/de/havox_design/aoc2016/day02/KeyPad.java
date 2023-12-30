package de.havox_design.aoc2016.day02;
import java.util.Set;

public abstract class KeyPad {
    public abstract Set<Key> getKeypadElements();
    public abstract String getValueForKey(Key key);
}
