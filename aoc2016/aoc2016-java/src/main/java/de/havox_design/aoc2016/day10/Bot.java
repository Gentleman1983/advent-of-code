package de.havox_design.aoc2016.day10;

import java.util.List;
import java.util.Map;

@SuppressWarnings("javaarchitecture:S7027")
public record Bot(int id, Target lowTarget, Target highTarget, Integer low, Integer high) {

    Bot(int id, int low) {
        this(id, null, null, low, null);
    }

    public Bot merge(Integer value) {
        if (low == null) {
            return new Bot(this.id, this.lowTarget, this.highTarget, value, null);
        } else {
            int newLow = Math.min(low, value);
            int newHigh = high == null ? Math.max(low, value) : Math.max(high, value);

            return new Bot(this.id, this.lowTarget, this.highTarget, newLow, newHigh);
        }
    }

    public Bot merge(Bot update) {
        if (this.id != update.id) {
            throw new IllegalArgumentException("Mismatched id");
        }

        return new Bot(this.id, update.lowTarget, update.highTarget, this.low, this.high);
    }

    boolean isInstructionProcessed(Map<Integer, Bot> bots, Map<Integer, List<Integer>> outputs) {
        if (high == null || !lowTarget.isReady(bots) || !highTarget.isReady(bots)) {
            return false;
        }

        lowTarget.update(bots, outputs, low);
        highTarget.update(bots, outputs, high);

        return true;
    }
}
