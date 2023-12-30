package de.havox_design.aoc2017.day22;

import de.havox_design.aoc2017.day19.Position;

@FunctionalInterface
public interface VirusFunction {
    Position apply(
            Position currentPosition,
            State currentState,
            BidirectionalGrowingArray<State> currentRow,
            Counter counter
    );
}
