package de.havox_design.aoc.utils.java.model.keypad;
import java.util.Set;

public abstract class KeyPad {
    public abstract Set<Key> getKeypadElements();
    public abstract String getValueForKey(Key key);
}
