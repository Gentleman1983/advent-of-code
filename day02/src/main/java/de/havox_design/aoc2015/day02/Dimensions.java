package de.havox_design.aoc2015.day02;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public record Dimensions(int length, int width, int height) {
    public int getSmallestSide() {
        Optional<Integer> minimum = getSides().stream().min(Integer::compareTo);

        if (minimum.isPresent()) {
            return minimum.get();
        } else {
            throw new IllegalStateException("This should never happen...");
        }
    }

    public List<Integer> getSides() {
        List<Integer> data = new ArrayList<>();

        data.add(length * width);
        data.add(height * length);
        data.add(width * height);

        return data;
    }

    public static Dimensions getForDataRow(String dataRow) {
        if (dataRow == null || dataRow.isEmpty()) {
            throw new IllegalArgumentException("Expected data row to have format of LxWxH with L=length, W=width, H=height.");
        }

        String[] elements = dataRow.split("x");

        if (elements.length != 3) {
            throw new IllegalArgumentException("Expected data row to have format of LxWxH with L=length, W=width, H=height.");
        }

        int length, width, height;
        try {
            length = Integer.parseInt(elements[0]);
            width = Integer.parseInt(elements[1]);
            height = Integer.parseInt(elements[2]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Expected data row to have format of LxWxH with L=length, W=width, H=height with integer values.", e);
        }

        return new Dimensions(length, width, height);
    }
}
