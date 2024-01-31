package de.havox_design.aoc.utils.java.model.programs.jsonparser;

import java.util.List;

public final class JSONString implements CharSequence, JSONEntity {

    private String string;

    public JSONString(String string) {
        this.string = string;
    }

    @Override
    public int length() {
        return string.length();
    }

    @Override
    public char charAt(int index) {
        return string.charAt(index);
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return string.subSequence(start, end);
    }

    @Override
    public String toString() {
        return "'" + string + "'";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof JSONString jss) {
            return string.equals(jss.string);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return string.hashCode();
    }

    @Override
    public List<Integer> getIntegersWithoutRed() {
        return List.of();
    }

}
