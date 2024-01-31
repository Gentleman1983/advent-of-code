package de.havox_design.aoc2017.day22;

import de.havox_design.aoc.utils.java.model.arrays.BidirectionalGrowingArray;
import de.havox_design.aoc.utils.kotlin.model.directed_position.DirectedPosition;

@FunctionalInterface
public interface VirusFunction {
    DirectedPosition apply(
            DirectedPosition currentPosition,
            State currentState,
            BidirectionalGrowingArray<State> currentRow,
            Counter counter
    );
}
